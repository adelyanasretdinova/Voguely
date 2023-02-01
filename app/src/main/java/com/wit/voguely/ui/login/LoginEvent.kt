package com.wit.voguely.ui.login

sealed class LoginEvent

object LoginSuccess : LoginEvent()

class LoginFail(val message: String?) : LoginEvent()

object LoginFailPass : LoginEvent()

object LoginFailEmail : LoginEvent()