package com.app.flowformvalidation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class MainViewModel : ViewModel() {

    private val _firstName = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _userId = MutableStateFlow("")


    fun setFirstName(name:String){
        _firstName.value = name
    }

    fun setPassword(password:String){
        _password.value = password
    }

    fun setUserId(userId:String){
        _userId.value = userId
    }

    val isSubmitEnabled :Flow<Boolean> = combine(_firstName,_password,_userId){firstName,password,userId->
        val regexString = "[a-zA-Z]+"
        val isNameCorrect = firstName.matches(regexString.toRegex())
        val isPasswordCorrect = password.length > 8
        val isUserIdCorrect = userId.contains("_")
        return@combine isNameCorrect and isPasswordCorrect and isUserIdCorrect

    }
}