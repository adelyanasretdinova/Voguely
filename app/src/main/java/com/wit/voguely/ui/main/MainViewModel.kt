package com.wit.voguely.ui.main

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class MainViewModel : ViewModel() {

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }
}