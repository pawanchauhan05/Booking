package com.app.booking.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.booking.pojo.BookingData

@Dao
interface BookingDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookingData(bookingDataList : List<BookingData>)

    @Query("SELECT * FROM bookingdata")
    fun getAllBookingData(): List<BookingData>

    @Query("DELETE FROM bookingdata")
    fun deleteAll()
}