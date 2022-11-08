package com.easy.meet.dao

import androidx.room.*
import com.easy.meet.entities.EventAvailabilityEntity
import com.easy.meet.models.EventAvailability
import com.easy.meet.utils.Constant
import kotlinx.coroutines.flow.Flow

@Dao
interface EventAvailabilityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = EventAvailabilityEntity::class)
    suspend fun insertAttendanceAvailabilityDate(eventAvailability: EventAvailability): Long

    @Update(onConflict = OnConflictStrategy.REPLACE, entity = EventAvailabilityEntity::class)
    suspend fun updateAttendanceAvailabilityDate(eventAvailability: EventAvailability)

    @Delete(entity = EventAvailabilityEntity::class)
    suspend fun deleteAttendanceAvailabilityDate(eventAvailability: EventAvailability)

    @Query(value = "select * from " + Constant.EVENT_AVAILABILITY_TABLE + " where date_id=:dateID and event_id =:eventID")
    fun getAttendanceAvailabilityDates(
        eventID: Int,
        dateID: Int
    ): Flow<List<EventAvailability>>

}