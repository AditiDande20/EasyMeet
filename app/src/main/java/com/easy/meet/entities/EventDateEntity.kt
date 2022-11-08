package com.easy.meet.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.easy.meet.utils.Constant

@Entity(tableName = Constant.EVENT_DATE_TABLE)
data class EventDateEntity(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @NonNull
    val event_id : Int,
    @NonNull
    val user_id : String,
    @NonNull
    val date : String
)
