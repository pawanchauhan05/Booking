package com.app.booking.data.source.remote

import com.app.booking.pojo.ResponseData
import retrofit2.http.GET

interface RemoteDataSource {

    @GET("data")
    suspend fun getBookings(): ResponseData
}