package com.app.booking.data.source.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.booking.MockResponse
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var bookingDataDao: BookingDataDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        bookingDataDao = appDatabase.getBookingDataDao()
    }

    @Test
    fun insertAndReadBookingData() = runBlocking {
        bookingDataDao.insertBookingData(MockResponse.getBookingDataList())
        val bookingDataList = bookingDataDao.getAllBookingData()
        Assert.assertEquals(bookingDataList, MockResponse.getBookingDataList())
    }

    @Test
    fun insertAndDeleteArticle() = runBlocking {
        bookingDataDao.insertBookingData(MockResponse.getBookingDataList())
        bookingDataDao.deleteAll()
        val bookingDataList = bookingDataDao.getAllBookingData()
        Assert.assertTrue(bookingDataList.isEmpty())
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }
}