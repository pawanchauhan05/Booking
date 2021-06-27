package com.app.booking.data.source

import com.app.booking.FakeResponse
import com.app.booking.MainCoroutineRule
import com.app.booking.data.source.local.FakeLocalDataSource
import com.app.booking.data.source.remote.FakeRemoteDataSource
import com.app.booking.pojo.BookingData
import com.app.booking.pojo.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.*
import java.util.*

class DataRepositoryTest {

    private lateinit var fakeLocalDataSource: FakeLocalDataSource
    private lateinit var fakeRemoteDataSource: FakeRemoteDataSource
    private lateinit var dataRepository: DataRepository

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    /**
     * Initialising local variables for each Test case
     */
    @Before
    fun createRepository() {
        fakeLocalDataSource = FakeLocalDataSource(mutableListOf())
        fakeRemoteDataSource = FakeRemoteDataSource()

        dataRepository = DataRepository(fakeLocalDataSource, fakeRemoteDataSource, Dispatchers.Main)
    }

    /**
     * Test Case
     * when server is giving success response
     * expected result is ResponseData Object
     *
     */
    @Test
    fun getResponseDate_successResponseDataFromRemoteSource() = mainCoroutineRule.runBlockingTest {
        val responseData = fakeRemoteDataSource.getBookings()
        Assert.assertThat(responseData, IsEqual(FakeResponse.getSuccessResponseData()))
    }

    /**
     * Test Case
     * when server is giving success response
     * expected results should be:
     * 1) will get booking list for specified time
     * 2) booking list should be store in local data source
     *
     */
    @Test
    fun getBookingData_successResponseDataFromRemoteSource_successSaveLocalDataSource() = mainCoroutineRule.runBlockingTest {
        val cal = Calendar.getInstance().apply {
            timeInMillis = 1624696037447
        }
        val list = dataRepository.getBookingData(cal.time).toList()

        Assert.assertEquals(
            list, listOf(Result.Success(FakeResponse.getBookingDataList()))
        )
        Assert.assertEquals(
            dataRepository.localDataSource.getAllBookingData(),
            FakeResponse.getBookingDataList()
        )
    }

    /**
     * Test Case
     * when server is giving error response and local data source is empty
     * expected results should be:
     * 1) will get Exception with specified message
     * 2) local data source should be empty
     *
     */
    @Test
    fun getBookingData_errorResponseDataFromRemoteSource_emptyLocalDataSource() = mainCoroutineRule.runBlockingTest {
        fakeRemoteDataSource.setResponseType(FakeRemoteDataSource.ResponseType.SHOULD_RETURN_ERROR)
        val cal = Calendar.getInstance().apply {
            timeInMillis = 1624696037447
        }
        val list = dataRepository.getBookingData(cal.time).toList()

        Assert.assertEquals(
            list, listOf(Result.Failure(FakeResponse.getResponseWithError()))
        )

        Assert.assertEquals(
            dataRepository.localDataSource.getAllBookingData(),
            emptyList<BookingData>()
        )
    }

    /**
     * Test Case
     * when server is giving error response and local data source is not empty
     * expected results should be:
     * 1) will get booking list from local data source
     * 2) will get Exception with specified message
     * 3) local data source should not be clear
     *
     */
    @Test
    fun getBookingData_errorResponseDataFromRemoteSource_successDataFromLocalSource() = mainCoroutineRule.runBlockingTest {
        fakeRemoteDataSource.setResponseType(FakeRemoteDataSource.ResponseType.SHOULD_RETURN_ERROR)
        fakeLocalDataSource.insertBookingData(FakeResponse.getBookingDataList())
        val cal = Calendar.getInstance().apply {
            timeInMillis = 1624696037447
        }
        val list = dataRepository.getBookingData(cal.time).toList()
        Assert.assertEquals(
            list, listOf(Result.Success(FakeResponse.getBookingDataList()), Result.Failure(FakeResponse.getResponseWithError()))
        )
        Assert.assertEquals(
            dataRepository.localDataSource.getAllBookingData(),
            FakeResponse.getBookingDataList()
        )
    }

    /**
     * Test Case
     * when server is giving error response and local data source is not empty
     * expected results should be:
     * 1) will get booking list from local data source
     * 2) will get booking list from remote data source
     * 3) local data source should be updated with latest remote source data
     *
     */
    @Test
    fun getBookingData_successResponseDataFromRemoteSource_successDataFromLocalSource() = mainCoroutineRule.runBlockingTest {
        fakeLocalDataSource.insertBookingData(FakeResponse.getBookingDataList())
        val cal = Calendar.getInstance().apply {
            timeInMillis = 1624696037447
        }
        val list = dataRepository.getBookingData(cal.time).toList()

        Assert.assertEquals(
            list, listOf(Result.Success(FakeResponse.getBookingDataList()),
                Result.Success(FakeResponse.getBookingDataList()))
        )

        Assert.assertEquals(
            dataRepository.localDataSource.getAllBookingData(),
            FakeResponse.getBookingDataList()
        )

    }


    /**
     * Test Case
     * when server is giving error response and local data source is empty
     * expected results should be:
     * 1) will get empty booking list because not booking available for specified time for mock response
     * 2) local data source should be empty
     *
     */
    @Test
    fun getBookingData_emptyResponseDataFromRemoteSource_emptyDataFromLocalSource() = mainCoroutineRule.runBlockingTest {
        val cal = Calendar.getInstance().apply {
            timeInMillis = 1624718747994
        }
        val list = dataRepository.getBookingData(cal.time).toList()

        Assert.assertEquals(list, listOf(Result.Success(emptyList())))

        Assert.assertEquals(
            dataRepository.localDataSource.getAllBookingData(),
            emptyList<BookingData>()
        )

    }
}