package com.easy.meet.screens.authentication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.meet.models.User
import com.easy.meet.screens.authentication.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth

    init {
        auth = Firebase.auth
    }

    fun insertUser(user: User)  {
        viewModelScope.launch {
            userRepository.insertUser(user)
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