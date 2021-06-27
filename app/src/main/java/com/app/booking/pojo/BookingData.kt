package com.app.booking.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
data class BookingData(
    @PrimaryKey(autoGenerate = true) var id : Int = 0,
    @ColumnInfo(name = "trip_start_time") @TypeConverters(DateConverter::class) val tripStartTime : Long,
    @ColumnInfo(name = "source") val source : String,
    @ColumnInfo(name = "destination") val destination : String,
    @ColumnInfo(name = "tripDuration") val tripDuration : String
)