package com.app.booking.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.booking.pojo.BookingData

@Database(entities = [BookingData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getBookingDataDao(): BookingDataDao
}