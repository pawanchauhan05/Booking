package com.app.booking.data.source.remote

import com.app.booking.FakeResponse
import com.app.booking.pojo.ResponseData

class FakeRemoteDataSource :  RemoteDataSource {

    enum class ResponseType {
        SHOULD_RETURN_ERROR,
        SHOULD_RETURN_SUCCESS
    }

    private var responseType = ResponseType.SHOULD_RETURN_SUCCESS

    fun setResponseType(responseType: ResponseType) {
        this.responseType = responseType
    }

    override suspend fun getBookings(): ResponseData {
        return when(responseType) {
            ResponseType.SHOULD_RETURN_SUCCESS -> FakeResponse.getSuccessResponseData()
            ResponseType.SHOULD_RETURN_ERROR -> throw FakeResponse.getResponseWithError()
        }
    }
}