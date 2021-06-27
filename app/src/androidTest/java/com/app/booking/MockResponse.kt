package com.app.booking

import com.app.booking.pojo.BookingData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MockResponse {
    private const val bookingDataJson : String = "[\n" +
            "  {\n" +
            "    \"destination\": \"Silk Board\",\n" +
            "    \"id\": 1,\n" +
            "    \"source\": \"E City\",\n" +
            "    \"tripDuration\": \"1hrs\",\n" +
            "    \"tripStartTime\": 1624699517447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Silk Board\",\n" +
            "    \"id\": 2,\n" +
            "    \"source\": \"E City\",\n" +
            "    \"tripDuration\": \"1hrs\",\n" +
            "    \"tripStartTime\": 1624699817447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Silk Board\",\n" +
            "    \"id\": 3,\n" +
            "    \"source\": \"E City\",\n" +
            "    \"tripDuration\": \"1hrs\",\n" +
            "    \"tripStartTime\": 1624700117447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Bomanhalli\",\n" +
            "    \"id\": 4,\n" +
            "    \"source\": \"Koramangala\",\n" +
            "    \"tripDuration\": \"45 min\",\n" +
            "    \"tripStartTime\": 1624703117447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Marathahalli\",\n" +
            "    \"id\": 5,\n" +
            "    \"source\": \"Yashwantpur\",\n" +
            "    \"tripDuration\": \"2hrs\",\n" +
            "    \"tripStartTime\": 1624713017447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Marathahalli\",\n" +
            "    \"id\": 6,\n" +
            "    \"source\": \"Yashwantpur\",\n" +
            "    \"tripDuration\": \"2hrs\",\n" +
            "    \"tripStartTime\": 1624713497447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"E City\",\n" +
            "    \"id\": 7,\n" +
            "    \"source\": \"Koramangala\",\n" +
            "    \"tripDuration\": \"2hrs\",\n" +
            "    \"tripStartTime\": 1624714217447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Marathahalli\",\n" +
            "    \"id\": 8,\n" +
            "    \"source\": \"Yashwantpur\",\n" +
            "    \"tripDuration\": \"2hrs\",\n" +
            "    \"tripStartTime\": 1624714517447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"E City\",\n" +
            "    \"id\": 9,\n" +
            "    \"source\": \"Koramangala\",\n" +
            "    \"tripDuration\": \"2hrs\",\n" +
            "    \"tripStartTime\": 1624714517447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Bomanhalli\",\n" +
            "    \"id\": 10,\n" +
            "    \"source\": \"Koramangala\",\n" +
            "    \"tripDuration\": \"45 min\",\n" +
            "    \"tripStartTime\": 1624714517447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"E City\",\n" +
            "    \"id\": 11,\n" +
            "    \"source\": \"Koramangala\",\n" +
            "    \"tripDuration\": \"2hrs\",\n" +
            "    \"tripStartTime\": 1624714817447\n" +
            "  },\n" +
            "  {\n" +
            "    \"destination\": \"Bomanhalli\",\n" +
            "    \"id\": 12,\n" +
            "    \"source\": \"Koramangala\",\n" +
            "    \"tripDuration\": \"45 min\",\n" +
            "    \"tripStartTime\": 1624717817447\n" +
            "  }\n" +
            "]"

    private var bookingDataList : MutableList<BookingData>

    init {
        val token: TypeToken<List<BookingData?>?> = object : TypeToken<List<BookingData?>?>() {}
        bookingDataList = Gson().fromJson(bookingDataJson, token.type)
    }

    fun getBookingDataList() : MutableList<BookingData> {
        return bookingDataList
    }

}