package com.netset.mvpexample.presenter.login

import com.netset.mvpexample.R
import com.netset.mvpexample.base.BaseActivity
import com.netset.mvpexample.pojo.LoginResponse
import com.netset.mvpexample.pojo.LoginToken
import com.netset.mvpexample.pojo.User
import com.netset.mvpexample.presenter.SuperPresenter

import org.json.JSONObject

import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Methods of RxAndroid
 * RxAndroid is specific to Android Platform with few added classes on top of RxJava.
 * More specifically, Schedulers are introduced in RxAndroid (AndroidSchedulers.mainThread())
 * which plays major role in supporting multithreading concept in android applications.
 * Schedulers basically decides the thread on which a particular code runs whether on background thread or main thread. Apart from it everything we use is from RxJava library only.
 * Even through there are lot of Schedulers available, Schedulers.io() and AndroidSchedulers.mainThread() are extensively used in android programming. Below are the list of schedulers available and their brief introduction.
 * Schedulers.io() – This is used to perform non CPU-intensive operations like making network calls, reading disc / files, database operations etc., This maintains pool of threads.
 * AndroidSchedulers.mainThread() – This provides access to android Main Thread / UI Thread. Usually operations like updating UI, user interactions happens on this thread. We shouldn’t perform any intensive operations on this thread as it makes the app glitchy or ANR dialog can be thrown.
 * Schedulers.newThread() – Using this, a new thread will be created each time a task is scheduled. It’s usually suggested not to use schedular unless there is a very long running operation. The threads created via newThread() won’t be reused.
 * Schedulers.computation() – This schedular can be used to perform CPU-intensive operations like processing huge data, bitmap processing etc., The number of threads created using this scheduler completely depends on number CPU cores available.
 * Schedulers.single() – This scheduler will execute all the tasks in sequential order they are added. This can be used when there is necessity of sequential execution is required.
 * Schedulers.immediate() – This scheduler executes the the task immediately in synchronous way by blocking the main thread.
 * Schedulers.trampoline() – It executes the tasks in First In – First Out manner. All the scheduled tasks will be executed one by one by limiting the number of background threads to one.
 * Schedulers.from() – This allows us to create a scheduler from an executor by limiting number of threads to be created. When thread pool is occupied, tasks will be queued.
 * Now we have the basic concepts needed. Let’s start with some key concepts of RxJava that everyone should aware of.
 * 4. RxJava Basics: Observable, Observer
 * RxJava is all about two key components: Observable and Observer. In addition to these, there are other things like Schedulers, Operators and Subscription.
 * Observable: Observable is a data stream that do some work and emits data.
 * Observer: Observer is the counter part of Observable. It receives the data emitted by Observable.
 * Subscription: The bonding between Observable and Observer is called as Subscription. There can be multiple Observers subscribed to a single Observable.
 * Operator / Transformation: Operators modifies the data emitted by Observable before an observer receives them.
 * Schedulers: Schedulers decides the thread on which Observable should emit the data and on which
 * Observer should receives the data i.e background thread, main thread etc.,
 */

class ImplLoginPresenter(internal var iloginView: IloginView?) : SuperPresenter(), ILoginPresenter {

    override fun callLogin(email: String, password: String, baseActivity: BaseActivity) {
        if (iloginView != null) {
            if (isNetworkAvailable) {
                if (getEmailValidation(email)) {
                    iloginView!!.onFailure(baseActivity.getString(R.string.email_error))
                } else if (getPasswordValidation(password)) {
                    iloginView!!.onFailure(baseActivity.getString(R.string.password_error))
                } else {
                    callLoginApi(email, password, baseActivity)
                }
            } else {
                iloginView!!.onFailure(baseActivity.getString(R.string.no_internet))
            }
        }
    }

    override fun onDestroy() {
        iloginView = null
    }

    private fun callLoginApi(email: String, password: String, baseActivity: BaseActivity) {
        try {
            baseActivity.showProgressDialog()
            val loginToken = LoginToken()
            val userToken = User()
            userToken.email = email
            userToken.password = password
            loginToken.user = userToken

            baseActivity.apiInterface!!.Login(loginToken).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : SingleObserver<LoginResponse> {
                        override fun onSubscribe(d: Disposable) {

                        }
                        override fun onSuccess(loginResponse: LoginResponse) {
                            baseActivity.hideProgressDialog()
                            iloginView!!.onSuccess(loginResponse)
                        }

                        override fun onError(e: Throwable) {
                            baseActivity.hideProgressDialog()
                            baseActivity.onResponseError(e)
                        }
                    })


        } catch (e: Exception) {
            e.printStackTrace()
            baseActivity.hideProgressDialog()
            iloginView!!.onFailure("Something went wrong please try again")
        }

    }
}
