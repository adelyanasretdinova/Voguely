package com.wit.voguely.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginSignUpViewModel : ViewModel() {
    private val _selectedTab = MutableStateFlow(SelectedTab.LOGIN)
    val selectedTab = _selectedTab.asStateFlow()
    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()

    fun onSelectedTabChanged(selectedTab: SelectedTab) {
        viewModelScope.launch {
            _selectedTab.update { selectedTab }
        }
    }

    fun buttonClicked(email: String, password: String) {
        viewModelScope.launch {
            when (selectedTab.value) {
                SelectedTab.SIGN_UP -> signUp(email, password)
                SelectedTab.LOGIN -> logIn(email, password)
            }
        }
    }

    private fun signUp(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            Firebase.auth
                .createUserWithEmailAndPassword(email, password)
                .await()
            _event.emit(LoginSuccess)
        } catch (e: Exception) {
            _event.emit(LoginFail(e.localizedMessage))
        }
    }

    private fun logIn(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            Firebase.auth
                .signInWithEmailAndPassword(email, password)
                .await()
            _event.emit(LoginSuccess)
        } catch (e: Exception) {
            _event.emit(LoginFail(e.localizedMessage))
        }
    }
}