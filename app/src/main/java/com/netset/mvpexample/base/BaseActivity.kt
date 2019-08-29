package com.netset.mvpexample.base

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.netset.mvpexample.R
import com.netset.mvpexample.network.ApiClient
import com.netset.mvpexample.network.ApiInterface
import com.netset.mvpexample.presenter.login.LoginActivity
import com.netset.mvpexample.ui.utils.FadeOutTransformation
import org.json.JSONObject


open class BaseActivity : AppCompatActivity() {

    private var mProgressDialog: ProgressDialog? = null
    private var apiClient: ApiClient? = null
    var apiInterface: ApiInterface ?= null
    private var fadeOutTransformation: FadeOutTransformation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Creating Api Client for Network calls
         * [ApiClient]
         * [ApiInterface]
         */
        createApiClient()

        /**
         * Creating Object of View pager animation class
         * */
        fadeOutTransformation = FadeOutTransformation()
    }
    /**
     * Set ViewPager Animation
     */
    fun setPagerAnim(viewPager: ViewPager) {
        viewPager.setPageTransformer(true, fadeOutTransformation)
    }

    /**
     * Api Client create for Network calls using retrofit and Rx-java
     * Now client is created we can access it any Activity or Fragment.
     */
    private fun createApiClient() {
        apiClient = ApiClient(this)
        apiInterface = apiClient!!.getClient().create(ApiInterface::class.java)
    }


    /**
     * To display progress dialog.
     * to be displayed while making any network call.
     */
    fun showProgressDialog() {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog(this)
                mProgressDialog!!.setCancelable(false)
                mProgressDialog!!.setTitle("")
                mProgressDialog!!.setMessage("Please wait...")
                mProgressDialog!!.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * To hide progress dialog.
     * But first check if Progress Dialog is not null and progress
     * dialog is currently active/displaying
     */
    fun hideProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog!!.isShowing) {
                mProgressDialog!!.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * Intent Method for starting activity and clearing all the activities in the stack.
     */
    fun startActivityWithFinish(required_class: Class<*>) {
        val intent = Intent(this, required_class)
        startActivity(intent)
        finishAffinity()
    }


    /**
     * This method will check all the error thrown on onError Method of Rx-java
     * first we will check that if exception is of HttpException
     * then check it will Html Codes like 401,402 and all
     * if its is 401: Redirect user on Login Screen as this error id for
     * Unauthorized or Session expire.
     */
    fun onResponseError(e: Throwable) {
        if (e is HttpException) {
            val errorCode = e.response().code()
            if (errorCode == 401) {
                showCommonToast(getString(R.string.session_expire))
                startActivityWithFinish(LoginActivity::class.java)
            } else {
                try {
                    val errorBody = e.response().errorBody()!!.string()
                    val msgobj = JSONObject(errorBody)
                    val msg = msgobj.optString("message")
                    showCommonToast(msg)
                } catch (e1: Exception) {
                    e1.printStackTrace()
                }

            }
        } else {
            showCommonToast(e.toString())
        }
    }


    /**
     * Showing Toast for whole app.
     */
    fun showCommonToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
