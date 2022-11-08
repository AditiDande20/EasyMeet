package com.easy.meet.models

data class User(
    val id : String,
    val name : String,
    val email : String,
    val password : String,
    val first_seen : String,
    val last_seen : String
)
