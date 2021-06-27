package com.app.booking.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RouteInfo(
    @PrimaryKey val id : String,
    val name : String,
    val source : String,
    val tripDuration : String,
    val destination : String
)