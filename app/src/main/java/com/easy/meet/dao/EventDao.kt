package com.easy.meet.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.easy.meet.entities.EventEntity
import com.easy.meet.models.Event
import com.easy.meet.utils.Constant
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Insert(onConflict = REPLACE, entity = EventEntity::class)
    suspend fun insertEvent(event : Event) : Long

    @Update(onConflict = REPLACE, entity = EventEntity::class)
    suspend fun updateEvent(event : Event)

    @Delete( entity = EventEntity::class)
    suspend fun deleteEvent(event : Event)

    @Query(value = "select * from " + Constant.EVENT_TABLE + " where id=:id")
    suspend fun getEvent(id : Int) : Event

    @Query("select * from " + Constant.EVENT_TABLE)
    fun getAllEvents(): Flow<List<Event>>
}