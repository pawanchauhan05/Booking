package com.app.booking.data.source.local

import com.app.booking.pojo.BookingData

class FakeLocalDataSource(private var bookingDataList: MutableList<BookingData> = mutableListOf()) : ILocalDataSource {

    override fun insertBookingData(bookingDataList: List<BookingData>) {
        this.bookingDataList.addAll(bookingDataList)
    }

    override fun getAllBookingData(): List<BookingData> {
        return this.bookingDataList
    }

    override fun deleteAll() {
        this.bookingDataList.clear()
    }
}