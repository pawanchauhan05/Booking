package com.app.booking.data.source

import com.app.booking.data.source.local.ILocalDataSource
import com.app.booking.data.source.remote.RemoteDataSource
import com.app.booking.pojo.BookingData
import com.app.booking.pojo.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class DataRepository(
    val localDataSource: ILocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IDataRepository {

    override suspend fun getBookingData(currentTime: Date): Flow<Result> = flow {
        try {
            val bookingList = mutableListOf<BookingData>()
            val localList = localDataSource.getAllBookingData()
            if(localList.isNotEmpty()) {
                emit(Result.Success(localList))
            }

            val response = remoteDataSource.getBookings()
            response.routeTimings.forEach {
                it.value.forEach { routeTiming ->
                    val cal = Calendar.getInstance().also { calInstance ->
                        calInstance.time = currentTime
                        val arr = routeTiming.tripStartTime.split(":")
                        arr?.let {
                            calInstance.set(Calendar.HOUR_OF_DAY, arr[0].toInt())
                            calInstance.set(Calendar.MINUTE, arr[1].toInt())
                        }
                    }
                    val tripTime = cal.time
                    if (tripTime > currentTime) {
                        response.routeInfo.first { routeInfo ->
                            routeInfo.id == it.key
                        }.also {routeInfo ->
                            val bookingData =
                                BookingData(tripStartTime = tripTime.time, source = routeInfo.source, destination= routeInfo.destination, tripDuration = routeInfo.tripDuration)
                            bookingList.add(bookingData)
                        }
                    }
                }
            }
            bookingList.sortBy { it.tripStartTime }
            emit(Result.Success(bookingList)).also {
                localDataSource.deleteAll()
                localDataSource.insertBookingData(bookingList)
            }
        } catch (ex: Exception) {
            emit(Result.Failure(ex))
        }
    }


}