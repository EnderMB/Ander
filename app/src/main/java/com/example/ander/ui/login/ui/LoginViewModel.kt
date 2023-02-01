package com.example.ander.ui.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

//Para crear estados
class LoginViewModel: ViewModel(){

    //_ para indicar que es la privada
    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _pass = MutableLiveData<String>()
    val pass : LiveData<String> = _pass

    //para habilitar o deshabilitar boton de login
    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    //validar
    fun onLoginChanged(email: String, pass: String){
        _email.value = email
        _pass.value = pass
        _loginEnable.value = isValidEmail(email) && isValidPass(pass)
    }

    private fun isValidPass(pass: String): Boolean = pass.length > 6

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    //corrutina
    suspend fun onLoginSelected() {
        _isLoading.value = true
        delay(3000)
        _isLoading.value = false
    }
}