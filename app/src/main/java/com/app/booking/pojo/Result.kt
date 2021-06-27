package com.app.booking.pojo

sealed class Result {
    data class Success(val dataList : List<BookingData>) : Result()
    data class Failure(val exception : Exception) : Result()
}
