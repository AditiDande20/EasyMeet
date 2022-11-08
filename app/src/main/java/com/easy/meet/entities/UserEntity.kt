package com.easy.meet.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.easy.meet.utils.Constant

@Entity(tableName = Constant.USER_TABLE)
data class UserEntity(
    @NonNull
    @PrimaryKey
    val id: String,
    @NonNull
    val name: String,
    @NonNull
    val email: String,
    @NonNull
    val password: String,
    @NonNull
    val first_seen: String,
    @NonNull
    val last_seen: String
)
