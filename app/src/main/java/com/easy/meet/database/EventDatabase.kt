package com.easy.meet.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.easy.meet.dao.*
import com.easy.meet.entities.*

@Database(
    entities = [EventEntity::class, EventDateEntity::class, EventAttendanceEntity::class, EventAvailabilityEntity::class, UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EventDatabase : RoomDatabase() {
    abstract fun getEventDao(): EventDao
    abstract fun getUserDao(): UserDao
    abstract fun getEventDateDao(): EventDateDao
    abstract fun getEventDateAvailabilityDao(): EventAvailabilityDao
    abstract fun getEventAttendanceDao(): EventAttendanceDao

}