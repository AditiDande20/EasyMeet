package com.easy.meet.models

import android.os.Parcelable
import com.easy.meet.utils.Constant
import com.easy.meet.utils.Utils
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize


@Parcelize
data class Event(
    val id: String = "",
    val title: String,
    val description: String?,
    val status: String,
    val place: String?,
    val link: String,
    val final_date: String?,
    val created_at: String,
    val user_id: String = Utils.getCurrentUserID(),
    val event_dates: List<String>
) : Parcelable {
    companion object {
        fun DocumentSnapshot.toEvent(): Event? {
            return try {
                val id = getString(Constant.ID)!!
                val title = getString(Constant.TITLE)!!
                val description = getString(Constant.DESCRIPTION)!!
                val status = getString(Constant.STATUS)!!
                val place = getString(Constant.PLACE)!!
                val link = getString(Constant.LINK)!!
                val finalDate = getString(Constant.FINAL_DATE)!!
                val createdAt = getString(Constant.CREATED_AT)!!
                val eventDate = get(Constant.EVENT_DATES)!!
                Event(
                    id,
                    title,
                    description,
                    status,
                    place,
                    link,
                    finalDate,
                    createdAt,
                    Utils.getCurrentUserID(),
                    eventDate as List<String>
                )
            } catch (e: Exception) {
                null
            }
        }
    }
}
