package com.easy.meet.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
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

    fun capitalizeWords(string: String): String {
        return string.substring(0, 1).uppercase(Locale.getDefault()) + string.substring(1)
            .lowercase(Locale.getDefault())
    }

    fun showSnackBar(scaffoldState: ScaffoldState, coroutineScope: CoroutineScope,title : String) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = title
            )
            /*when (snackbarResult) {
                SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
                SnackbarResult.ActionPerformed -> Log.d("SnackbarDemo", "Snackbar's button clicked")
            }*/
        }
    }

}