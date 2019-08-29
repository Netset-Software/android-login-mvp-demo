package com.netset.mvpexample.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Userinfo {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("created_at")
    @Expose
    var created_at: String? = null
    @SerializedName("updated_at")
    @Expose
    var updated_at: String? = null


}