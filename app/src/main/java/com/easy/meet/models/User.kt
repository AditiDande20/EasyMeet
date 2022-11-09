package com.easy.meet.models

import android.os.Parcelable
import com.easy.meet.utils.Constant
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val name: String,
    val email: String,
    val password: String,
    val first_seen: String,
    val last_seen: String
) : Parcelable {
    companion object {
        fun DocumentSnapshot.toUser(): User? {
            return try {
                val id = getString(Constant.ID)!!
                val name = getString(Constant.NAME)!!
                val email = getString(Constant.EMAIL)!!
                val password = getString(Constant.PASSWORD)!!
                val firstSeen = getString(Constant.FIRST_SEEN)!!
                val lastSeen = getString(Constant.LAST_SEEN)!!
                User(id, name, email, password, firstSeen, lastSeen)
            } catch (e: Exception) {
                null
            }
        }
    }

}
