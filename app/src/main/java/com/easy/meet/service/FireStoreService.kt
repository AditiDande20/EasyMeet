package com.easy.meet.service

import android.content.Context
import com.easy.meet.utils.Constant
import com.easy.meet.utils.Utils
import com.google.firebase.firestore.FirebaseFirestore

object FireStoreService {

    fun insertDataToFireStore(
        context: Context,
        collectionName: String,
        data: Any,
        id: String,
        onDone: (String) -> Unit
    ) {

        val db = FirebaseFirestore.getInstance()
        try {
            db.collection(collectionName).document(id).set(data).addOnSuccessListener {
                Utils.showToast(context, "Saved")
                onDone(Constant.SUCCESS)
            }.addOnFailureListener {
                onDone(Constant.FAILURE)
                Utils.showToast(context, "Failure")
            }
        } catch (e: Exception) {
            onDone(Constant.EXCEPTION)
        }

    }

}