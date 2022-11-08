package com.easy.meet.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.easy.meet.utils.Constant

@Entity(tableName = Constant.EVENT_AVAILABILITY_TABLE)
data class EventAvailabilityEntity(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @NonNull
    val event_id : Int,
    @NonNull
    val date_id : Int,
    @NonNull
    val attendance_id : Int,
    @NonNull
    val status : String
)
