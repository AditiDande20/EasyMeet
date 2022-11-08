package com.easy.meet.di

import android.content.Context
import androidx.room.Room
import com.easy.meet.dao.*
import com.easy.meet.database.EventDatabase
import com.easy.meet.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): EventDatabase =
        Room.databaseBuilder(
            context,
            EventDatabase::class.java,
            Constant.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideEventDAO(eventDatabase: EventDatabase): EventDao =
        eventDatabase.getEventDao()

    @Provides
    @Singleton
    fun provideEventAttendanceDAO(eventDatabase: EventDatabase): EventAttendanceDao =
        eventDatabase.getEventAttendanceDao()

    @Provides
    @Singleton
    fun provideEventDateDAO(eventDatabase: EventDatabase): EventDateDao =
        eventDatabase.getEventDateDao()

    @Provides
    @Singleton
    fun provideEventAvailabilityDAO(eventDatabase: EventDatabase): EventAvailabilityDao =
        eventDatabase.getEventDateAvailabilityDao()

    @Provides
    @Singleton
    fun provideUserDAO(eventDatabase: EventDatabase): UserDao =
        eventDatabase.getUserDao()


}