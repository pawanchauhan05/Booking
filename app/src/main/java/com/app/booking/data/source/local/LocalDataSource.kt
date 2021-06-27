package com.app.booking.data.source.local

import com.app.booking.pojo.BookingData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class LocalDataSource internal constructor(
    private val bookingDataDao: BookingDataDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO)  : ILocalDataSource {

    override fun insertBookingData(bookingDataList: List<BookingData>) {
        bookingDataDao.insertBookingData(bookingDataList)
    }

    override fun getAllBookingData(): List<BookingData> {
        return bookingDataDao.getAllBookingData()
    }

    override fun deleteAll() {
        bookingDataDao.deleteAll()
    }

}