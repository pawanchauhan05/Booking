package com.app.booking.pojo

data class ResponseData(
    val routeInfo : List<RouteInfo> = mutableListOf(),
    val routeTimings : Map<String, List<RouteTiming>> = mutableMapOf()
)