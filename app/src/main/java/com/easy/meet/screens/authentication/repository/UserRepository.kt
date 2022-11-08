package com.easy.meet.screens.authentication.repository

import android.util.Log
import com.easy.meet.dao.UserDao
import com.easy.meet.models.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {
    suspend fun insertUser(user: User)  {
        userDao.insertUser(user = user)
    }
    suspend fun updateUser(user: User) = userDao.updateUser(user = user)
    suspend fun deleteUser(user: User) = userDao.deleteUser(user = user)
    suspend fun getUser(id: String) = userDao.getUser(id = id)
}