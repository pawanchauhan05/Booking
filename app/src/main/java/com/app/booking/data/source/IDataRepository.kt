package com.app.booking.data.source

import com.app.booking.pojo.Result
import kotlinx.coroutines.flow.Flow

import java.util.*

interface IDataRepository {
    suspend fun getBookingData(currentTime : Date) : Flow<Result>
}