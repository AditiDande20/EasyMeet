package com.easy.meet.service

import android.content.Context
import android.util.Log
import com.easy.meet.utils.Constant
import com.easy.meet.utils.Utils
import com.google.firebase.firestore.FirebaseFirestore

object FirestoreService {

    fun insertDataToFirestore(context: Context, collectionName: String, data: Any) {
        val db = FirebaseFirestore.getInstance()
        val id: String
        try {
            id = if(collectionName!=Constant.USER_TABLE){
                db.collection(collectionName).document().id
            }else{
                Utils.getCurrentUserID()
            }

            db.collection(collectionName).document(id).set(data).addOnSuccessListener {
                Utils.showToast(context, "Saved")
            }.addOnFailureListener {
                Utils.showToast(context, "Failure")
                Log.e("Aditi === >","error ::"+it.message)
            }
        } catch (e: Exception) {

        }

    }

}