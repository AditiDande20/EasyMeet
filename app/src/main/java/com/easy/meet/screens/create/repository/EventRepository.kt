package com.easy.meet.screens.create.repository

import com.easy.meet.dao.EventDao
import com.easy.meet.models.Event
import javax.inject.Inject

class EventRepository @Inject constructor(private val eventDao: EventDao) {
    suspend fun insertEvent(event: Event) : Long = eventDao.insertEvent(event = event)
    suspend fun deleteEvent(event: Event) = eventDao.deleteEvent(event = event)
    suspend fun updateEvent(event: Event) = eventDao.updateEvent(event = event)
    suspend fun getEvent(id : Int) = eventDao.getEvent(id = id)
}