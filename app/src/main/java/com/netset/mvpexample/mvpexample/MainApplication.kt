package com.netset.mvpexample.mvpexample

import android.app.Application
import android.content.Context
import android.content.res.Configuration

/**
 * Created by netset on 2/6/2019.
 */

class MainApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun attachBaseContext(context: Context) {

        super.attachBaseContext(context)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    companion object {

        @get:Synchronized
        var instance: MainApplication? = null
            private set
    }
}
