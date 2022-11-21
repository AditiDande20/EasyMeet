package com.easy.meet.screens.authentication.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.meet.models.User
import com.easy.meet.service.FireStoreService
import com.easy.meet.utils.Constant
import com.easy.meet.utils.Utils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(val context: Context) : ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth

    fun insertUser(user: User,onDone : (String) -> Unit) {
        viewModelScope.launch {
            FireStoreService.insertDataToFireStore(context,Constant.USER_TABLE,user,Utils.getCurrentUserID()) {
                onDone(it)
            }
        }
    }

    fun signInWithEmailPassword(email: String, password: String, onSuccessful: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    onSuccessful()
                }
                    .addOnFailureListener {
                    }
            } catch (e: Exception) {
            }
        }
    }

    fun createUserWithEmailPassword(email: String, password: String, onSuccessful: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    onSuccessful()
                }.addOnFailureListener {
                }
            } catch (e: Exception) {
            }
        }
    }

}