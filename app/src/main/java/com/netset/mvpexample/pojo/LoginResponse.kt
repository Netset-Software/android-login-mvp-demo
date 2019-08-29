package com.netset.mvpexample.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse {

    @SerializedName("user")
    @Expose
    var userinfo: Userinfo? = null

}