package com.netset.mvpexample.presenter.login

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.EditText

import com.netset.mvpexample.R
import com.netset.mvpexample.base.BaseActivity
import com.netset.mvpexample.pojo.LoginResponse
import com.netset.mvpexample.presenter.home.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : BaseActivity(), View.OnClickListener, IloginView {

    private var iloginPresenter: ILoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iloginPresenter = ImplLoginPresenter(this)
        btn_login!!.setOnClickListener(this)

    }


    /**
     * Handling Clicks of the views.
     **/
    override fun onClick(view: View?) {
        /**
         * Calling Login api and validating
         **/
        when (view!!.id) {
            R.id.btn_login -> {
                iloginPresenter!!.callLogin(et_username.text.toString(), et_password.text.toString(), this)
            }
        }
    }


    /**
     * Handling Success case of the api
     * */
    override fun onSuccess(o: Any) {
        if (o is LoginResponse) {
            val response = o;
            if (response.userinfo != null) {
                showCommonToast(getString(R.string.on_login_sucess))
                startActivityWithFinish(HomeActivity::class.java)
            }else{
                showCommonToast(getString(R.string.on_login_failure))
            }
        }
    }

    /**
     * Handling Failure case of the api
     * */
    override fun onFailure(message: String) {
        showCommonToast(message)
    }

}
