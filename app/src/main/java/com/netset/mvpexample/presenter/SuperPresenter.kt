package com.netset.mvpexample.presenter

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Patterns


import com.netset.mvpexample.mvpexample.MainApplication
import okhttp3.MediaType

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import okhttp3.RequestBody




abstract class SuperPresenter {


    protected val isNetworkAvailable: Boolean
        get() {
            val cm = MainApplication.instance!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo

            return netInfo != null && netInfo.isConnected
        }


    protected fun getEmailValidation(email: String): Boolean {
        return email == "" || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    protected fun getPasswordValidation(password: String): Boolean {
        return password == ""
    }

    protected fun getComparePasswordValidation(password: String, compare_pwd: String): Boolean {
        return password != compare_pwd
    }

    protected fun getPasswordLength(password: String): Boolean {
        return password.length <= 8
    }

    protected fun getRequestBodyParam(value: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/form-data"), value)
    }

}