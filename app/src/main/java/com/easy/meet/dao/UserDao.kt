package com.easy.meet.dao

import androidx.room.*
import com.easy.meet.entities.UserEntity
import com.easy.meet.models.User
import com.easy.meet.utils.Constant

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = UserEntity::class)
    suspend fun insertUser(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE, entity = UserEntity::class)
    suspend fun updateUser(user: User)

    @Delete(entity = UserEntity::class)
    suspend fun deleteUser(user: User)

    @Query(value = "select * from " + Constant.USER_TABLE + " where id=:id")
    suspend fun getUser(id: String): User

}