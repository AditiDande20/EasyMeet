package com.easy.meet.models

data class EventAvailability(
    val id : Int,
    val event_id : Int,
    val date_id : Int,
    val attendance_id : Int,
    val status : String
)
