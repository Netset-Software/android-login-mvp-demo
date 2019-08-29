package com.netset.mvpexample.presenter.login

interface IloginView {

    fun onSuccess(o: Any)

    fun onFailure(message: String)
}
