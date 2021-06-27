package com.app.booking.ui.bookings

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.booking.FakeResponse
import com.app.booking.data.source.DataRepository
import com.app.booking.data.source.local.FakeLocalDataSource
import com.app.booking.data.source.remote.FakeRemoteDataSource
import com.app.booking.getOrAwaitValue
import com.app.booking.pojo.EmptyView
import com.app.booking.pojo.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class BookingsViewModelTest  {

    private lateinit var bookingsViewModel: BookingsViewModel
    private lateinit var dataRepository: DataRepository
    private lateinit var fakeLocalDataSource: FakeLocalDataSource
    private lateinit var fakeRemoteDataSource: FakeRemoteDataSource

    private lateinit var testCoroutineDispatcher : TestCoroutineDispatcher

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    /**
     * Initialising local variables for each Test case
     */
    @Before
    fun setUpViewModel() {
        fakeLocalDataSource = FakeLocalDataSource(mutableListOf())
        fakeRemoteDataSource = FakeRemoteDataSource()
        dataRepository = DataRepository(fakeLocalDataSource, fakeRemoteDataSource)

        testCoroutineDispatcher = TestCoroutineDispatcher()
        bookingsViewModel = BookingsViewModel(dataRepository, testCoroutineDispatcher)
    }

    /**
     * Test Case
     * when server giving success response and local source should be empty
     * expected result is booking list
     */
    @Test
    fun getBookingData_successResponse_shouldDisplayList() = testCoroutineDispatcher.runBlockingTest {
        val cal = Calendar.getInstance().apply {
            timeInMillis = 1624696037447
        }
        testCoroutineDispatcher.pauseDispatcher()
        bookingsViewModel.getBookings(cal.time)

        val data1 = bookingsViewModel.dataLoading.getOrAwaitValue()
        Assert.assertEquals(data1 , true)

        testCoroutineDispatcher.resumeDispatcher()

        val data2 = bookingsViewModel.bookingList.getOrAwaitValue()
        Assert.assertEquals(data2 , Result.Success(FakeResponse.getBookingDataList()))

        val data3 = bookingsViewModel.dataLoading.getOrAwaitValue()
        Assert.assertEquals(data3 , false)
    }

    /**
     * Test Case
     * when server giving error response and local source should be empty
     * expected result is Empty View with specified message
     */
    @Test
    fun getBookingData_errorResponse_shouldDisplayError() = testCoroutineDispatcher.runBlockingTest {
        fakeRemoteDataSource.setResponseType(FakeRemoteDataSource.ResponseType.SHOULD_RETURN_ERROR)

        val cal = Calendar.getInstance().apply {
            timeInMillis = 1624696037447
        }
        testCoroutineDispatcher.pauseDispatcher()
        bookingsViewModel.getBookings(cal.time)

        val data1 = bookingsViewModel.dataLoading.getOrAwaitValue()
        Assert.assertEquals(data1 , true)

        testCoroutineDispatcher.resumeDispatcher()

        val data2 = bookingsViewModel.empty.getOrAwaitValue()
        Assert.assertEquals(data2 , EmptyView("TIMEOUT ERROR!"))

        val data3 = bookingsViewModel.dataLoading.getOrAwaitValue()
        Assert.assertEquals(data3 , false)
    }

    /**
     * Test Case
     * when server giving success response and local source having cache data
     * expected result should be first list comes from local source then it should update from network source
     */
    @Test
    fun getBookingData_successResponse_shouldDisplayListFromLocal() = testCoroutineDispatcher.runBlockingTest {
        fakeLocalDataSource.insertBookingData(FakeResponse.getBookingDataList())

        val cal = Calendar.getInstance().apply {
            timeInMillis = 1624696037447
        }
        testCoroutineDispatcher.pauseDispatcher()
        bookingsViewModel.getBookings(cal.time)

        val data1 = bookingsViewModel.dataLoading.getOrAwaitValue()
        Assert.assertEquals(data1 , true)

        testCoroutineDispatcher.resumeDispatcher()

        val data2 = bookingsViewModel.bookingList.getOrAwaitValue()
        Assert.assertEquals(data2 , Result.Success(fakeLocalDataSource.getAllBookingData()))

        val data3 = bookingsViewModel.dataLoading.getOrAwaitValue()
        Assert.assertEquals(data3 , false)
    }

    /**
     * Test Case
     * when server giving success response and local source should be empty
     * expected result should be empty list because for mock response no booking is available for specified time
     */
    @Test
    fun getBookingData_successResponse_shouldDisplayNoBookings() = testCoroutineDispatcher.runBlockingTest {

        val cal = Calendar.getInstance().apply {
            timeInMillis = 1624718747994
        }
        testCoroutineDispatcher.pauseDispatcher()
        bookingsViewModel.getBookings(cal.time)

        val data1 = bookingsViewModel.dataLoading.getOrAwaitValue()
        Assert.assertEquals(data1 , true)

        testCoroutineDispatcher.resumeDispatcher()

        val data2 = bookingsViewModel.bookingList.getOrAwaitValue()
        Assert.assertEquals(data2 , Result.Success(emptyList()))

        val data3 = bookingsViewModel.empty.getOrAwaitValue()
        Assert.assertEquals(data3 , EmptyView("NO BOOKINGS FOUND"))

        val data4 = bookingsViewModel.dataLoading.getOrAwaitValue()
        Assert.assertEquals(data4 , false)
    }

}