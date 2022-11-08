package com.easy.meet.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.easy.meet.utils.Constant

@Entity(tableName = Constant.EVENT_ATTENDANCE_TABLE)
data class EventAttendanceEntity(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @NonNull
    val event_id : Int,
    @NonNull
    val host_id : String,
    @NonNull
    val attendee_id : String,
    val comments : String?
)
