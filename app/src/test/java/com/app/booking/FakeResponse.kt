package com.app.booking

import com.app.booking.pojo.BookingData
import com.app.booking.pojo.ResponseData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.SocketTimeoutException

object FakeResponse {
    private const val bookingDataJson : String = "[\n" +
            "  {\n" +
            "    \"destination\": \"Silk Board\",\n" +
            "    \"id\": 0,\n" +
            "    \"source\": \"E City\",\n" +
            "    \"tripDuration\": \"1hrs\",\n" +
            "    \"tripStartTime\": 1624699517447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Silk Board\",\n" +
            "    \"id\": 0,\n" +
            "    \"source\": \"E City\",\n" +
            "    \"tripDuration\": \"1hrs\",\n" +
            "    \"tripStartTime\": 1624699817447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Silk Board\",\n" +
            "    \"id\": 0,\n" +
            "    \"source\": \"E City\",\n" +
            "    \"tripDuration\": \"1hrs\",\n" +
            "    \"tripStartTime\": 1624700117447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Bomanhalli\",\n" +
            "    \"id\": 0,\n" +
            "    \"source\": \"Koramangala\",\n" +
            "    \"tripDuration\": \"45 min\",\n" +
            "    \"tripStartTime\": 1624703117447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Marathahalli\",\n" +
            "    \"id\": 0,\n" +
            "    \"source\": \"Yashwantpur\",\n" +
            "    \"tripDuration\": \"2hrs\",\n" +
            "    \"tripStartTime\": 1624713017447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Marathahalli\",\n" +
            "    \"id\": 0,\n" +
            "    \"source\": \"Yashwantpur\",\n" +
            "    \"tripDuration\": \"2hrs\",\n" +
            "    \"tripStartTime\": 1624713497447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"E City\",\n" +
            "    \"id\": 0,\n" +
            "    \"source\": \"Koramangala\",\n" +
            "    \"tripDuration\": \"2hrs\",\n" +
            "    \"tripStartTime\": 1624714217447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Marathahalli\",\n" +
            "    \"id\": 0,\n" +
            "    \"source\": \"Yashwantpur\",\n" +
            "    \"tripDuration\": \"2hrs\",\n" +
            "    \"tripStartTime\": 1624714517447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"E City\",\n" +
            "    \"id\": 0,\n" +
            "    \"source\": \"Koramangala\",\n" +
            "    \"tripDuration\": \"2hrs\",\n" +
            "    \"tripStartTime\": 1624714517447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Bomanhalli\",\n" +
            "    \"id\": 0,\n" +
            "    \"source\": \"Koramangala\",\n" +
            "    \"tripDuration\": \"45 min\",\n" +
            "    \"tripStartTime\": 1624714517447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"E City\",\n" +
            "    \"id\": 0,\n" +
            "    \"source\": \"Koramangala\",\n" +
            "    \"tripDuration\": \"2hrs\",\n" +
            "    \"tripStartTime\": 1624714817447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Bomanhalli\",\n" +
            "    \"id\": 0,\n" +
            "    \"source\": \"Koramangala\",\n" +
            "    \"tripDuration\": \"45 min\",\n" +
            "    \"tripStartTime\": 1624717817447\n" +
            "  }\n" +
            "]"

    private const val responseJsonString : String = "{\n" +
            "  \"routeInfo\": [\n" +
            "    {\n" +
            "      \"id\": \"r002\",\n" +
            "      \"name\": \"k-12\",\n" +
            "      \"source\": \"Yashwantpur\",\n" +
            "      \"tripDuration\": \"2hrs\",\n" +
            "      \"destination\": \"Marathahalli\",\n" +
            "      \"icon\": \"http://\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"r003\",\n" +
            "      \"name\": \"k-11\",\n" +
            "      \"tripDuration\": \"45 min\",\n" +
            "      \"source\": \"Koramangala\",\n" +
            "      \"destination\": \"Bomanhalli\",\n" +
            "      \"icon\": \"http://\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"r004\",\n" +
            "      \"name\": \"k-14\",\n" +
            "      \"source\": \"E City\",\n" +
            "      \"tripDuration\": \"1hrs\",\n" +
            "      \"destination\": \"Silk Board\",\n" +
            "      \"icon\": \"http://\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"r001\",\n" +
            "      \"name\": \"R-1\",\n" +
            "      \"source\": \"Marathahalli\",\n" +
            "      \"tripDuration\": \"2hrs\",\n" +
            "      \"destination\": \"E City\",\n" +
            "      \"icon\": \"http://\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"r005\",\n" +
            "      \"name\": \"G-12\",\n" +
            "      \"tripDuration\": \"2hrs\",\n" +
            "      \"source\": \"Koramangala\",\n" +
            "      \"destination\": \"E City\",\n" +
            "      \"icon\": \"http://\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"routeTimings\": {\n" +
            "    \"r002\": [\n" +
            "      {\n" +
            "        \"totalSeats\": 13,\n" +
            "        \"avaiable\": 5,\n" +
            "        \"tripStartTime\": \"18:40\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"totalSeats\": 13,\n" +
            "        \"avaiable\": 0,\n" +
            "        \"tripStartTime\": \"18:48\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"totalSeats\": 13,\n" +
            "        \"avaiable\": 1,\n" +
            "        \"tripStartTime\": \"19:05\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"r005\": [\n" +
            "      {\n" +
            "        \"totalSeats\": 13,\n" +
            "        \"avaiable\": 5,\n" +
            "        \"tripStartTime\": \"19:10\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"totalSeats\": 13,\n" +
            "        \"avaiable\": 0,\n" +
            "        \"tripStartTime\": \"19:00\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"totalSeats\": 13,\n" +
            "        \"avaiable\": 1,\n" +
            "        \"tripStartTime\": \"19:05\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"r001\": [],\n" +
            "    \"r004\": [\n" +
            "      {\n" +
            "        \"totalSeats\": 13,\n" +
            "        \"avaiable\": 5,\n" +
            "        \"tripStartTime\": \"14:55\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"totalSeats\": 13,\n" +
            "        \"avaiable\": 0,\n" +
            "        \"tripStartTime\": \"15:00\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"totalSeats\": 13,\n" +
            "        \"avaiable\": 1,\n" +
            "        \"tripStartTime\": \"15:05\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"r003\": [\n" +
            "      {\n" +
            "        \"totalSeats\": 12,\n" +
            "        \"avaiable\": 10,\n" +
            "        \"tripStartTime\": \"15:55\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"totalSeats\": 12,\n" +
            "        \"avaiable\": 9,\n" +
            "        \"tripStartTime\": \"20:00\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"totalSeats\": 12,\n" +
            "        \"avaiable\": 1,\n" +
            "        \"tripStartTime\": \"19:05\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}"


    private val ex : Exception = SocketTimeoutException("TIMEOUT ERROR!")

    private var responseData : ResponseData
    private var bookingDataList : MutableList<BookingData>

    init {
        val token: TypeToken<List<BookingData?>?> = object : TypeToken<List<BookingData?>?>() {}
        bookingDataList = Gson().fromJson(bookingDataJson, token.type)

        responseData = Gson().fromJson(responseJsonString, ResponseData::class.java)
    }

    fun getBookingDataList() : MutableList<BookingData> {
        return bookingDataList
    }

    fun getSuccessResponseData() : ResponseData {
        return responseData
    }

    fun getResponseWithError() : Exception {
        return ex
    }
}