package com.easy.meet.dao

import androidx.room.*
import com.easy.meet.entities.EventAttendanceEntity
import com.easy.meet.models.EventAttendance
import com.easy.meet.utils.Constant
import kotlinx.coroutines.flow.Flow

@Dao
interface EventAttendanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = EventAttendanceEntity::class)
    suspend fun insertEventAttendance(eventAttendance: EventAttendance): Long

    @Update(onConflict = OnConflictStrategy.REPLACE, entity = EventAttendanceEntity::class)
    suspend fun updateEventAttendance(eventAttendance: EventAttendance)

    @Delete(entity = EventAttendanceEntity::class)
    suspend fun deleteEventAttendance(eventAttendance: EventAttendance)

    @Query(value = "select * from " + Constant.EVENT_ATTENDANCE_TABLE + " where event_id=:eventId")
    fun getEventAttendance(eventId: Int): Flow<List<EventAttendance>>

}