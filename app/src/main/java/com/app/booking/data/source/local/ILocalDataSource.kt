package com.app.booking.data.source.local

import com.app.booking.pojo.BookingData

interface ILocalDataSource {
    fun insertBookingData(bookingDataList : List<BookingData>)

    fun getAllBookingData(): List<BookingData>

    fun deleteAll()
}