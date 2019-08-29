package com.netset.mvpexample.network

import com.netset.mvpexample.pojo.LoginResponse
import com.netset.mvpexample.pojo.LoginToken
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("sign_in")
    fun Login(@Body loginToken: LoginToken): Single<LoginResponse>
}