package com.easy.meet.models

data class EventAttendance(
    val id : Int,
    val event_id : Int,
    val host_id : String,
    val attendee_id : String,
    val comments : String?
)
