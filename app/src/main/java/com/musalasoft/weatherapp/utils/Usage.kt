package com.recyclego.userapp.utils

import android.content.Context
import android.widget.Toast

import java.util.regex.Pattern

fun Context.showToast(message: String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

fun Context.showToastLong(message: String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}


fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isPhoneValid(phone: String): Boolean {
    return android.util.Patterns.PHONE.matcher(phone).matches()
}
