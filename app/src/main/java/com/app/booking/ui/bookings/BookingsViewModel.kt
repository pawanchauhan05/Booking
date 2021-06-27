package com.app.booking.ui.bookings

import androidx.lifecycle.*
import com.app.booking.data.source.IDataRepository
import com.app.booking.pojo.EmptyView
import com.app.booking.pojo.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

class BookingsViewModel(
    private val dataRepository: IDataRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _empty = MutableLiveData<EmptyView>()
    val empty: LiveData<EmptyView> = _empty

    private val _bookingList = MutableLiveData<Result>()
    val bookingList: LiveData<Result> = _bookingList


    fun getBookings(currentTime: Date) {
        _dataLoading.postValue(true)

        viewModelScope.launch(ioDispatcher) {
            dataRepository.getBookingData(currentTime).collect {
                when(it) {
                    is Result.Success -> {
                        _bookingList.postValue(Result.Success(it.dataList))
                        if(it.dataList.isEmpty()) {
                            _empty.postValue(EmptyView("NO BOOKINGS FOUND"))
                        }
                    }

                    is Result.Failure -> {
                        when (it.exception) {
                            is SocketTimeoutException -> {
                                _empty.postValue(EmptyView("TIMEOUT ERROR!"))
                            }
                            is UnknownHostException -> {
                                _empty.postValue(EmptyView("NO INTERNET!"))
                            }
                            else -> {
                                _empty.postValue(EmptyView(it.exception.message ?: "Something went wrong"))
                            }
                        }
                    }
                }
            }
        }.invokeOnCompletion {
            _dataLoading.postValue(false)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class BookingsViewModelFactory(
    private val dataRepository: IDataRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (BookingsViewModel(dataRepository) as T)
}