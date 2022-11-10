package com.easy.meet.utils

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun getCurrentUserID(): String {
        return FirebaseAuth.getInstance().currentUser?.uid ?: ""
    }

    fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat(Constant.DATE_TIME_FORMAT, Locale.getDefault())
        return sdf.format(Date())
    }




}