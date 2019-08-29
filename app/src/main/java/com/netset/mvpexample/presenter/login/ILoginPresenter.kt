package com.netset.mvpexample.presenter.login

import com.netset.mvpexample.base.BaseActivity

interface ILoginPresenter {
    fun callLogin(email: String, password: String, baseActivity: BaseActivity)
    fun onDestroy()
}
