package com.netset.mvpexample.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginToken {

    @SerializedName("user")
    @Expose
    var user: User? = null

}