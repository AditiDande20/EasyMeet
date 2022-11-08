package com.easy.meet.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.easy.meet.utils.Constant

@Entity(tableName = Constant.EVENT_TABLE)
data class EventEntity(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @NonNull
    val title : String,
    val description : String?,
    @NonNull
    val status : String,
    val place : String?,
    @NonNull
    val link : String,
    val final_date : String?,
    @NonNull
    val created_at : String
)
