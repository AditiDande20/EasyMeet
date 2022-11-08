package com.easy.meet.models

data class Event(
  val id : Int = 1,
  val title : String,
  val description : String?,
  val status : String,
  val place : String?,
  val link : String,
  val final_date : String?,
  val created_at : String
)
