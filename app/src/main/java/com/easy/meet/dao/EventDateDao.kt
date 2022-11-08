package com.easy.meet.dao

import androidx.room.*
import com.easy.meet.entities.EventDateEntity
import com.easy.meet.models.EventDate
import com.easy.meet.utils.Constant
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = EventDateEntity::class)
    suspend fun insertExpectedEventDate(eventDate: EventDate): Long

    @Update(onConflict = OnConflictStrategy.REPLACE, entity = EventDateEntity::class)
    suspend fun updateExpectedEventDate(eventDate: EventDate)

    @Delete(entity = EventDateEntity::class)
    suspend fun deleteExpectedEventDate(eventDate: EventDate)

    @Query(value = "select * from " + Constant.EVENT_DATE_TABLE + " where event_id =:eventID")
    fun getExpectedEventDates(eventID: Int): Flow<List<EventDate>>

}