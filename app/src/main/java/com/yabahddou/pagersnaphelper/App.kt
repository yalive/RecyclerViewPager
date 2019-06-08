package com.yabahddou.pagersnaphelper

import android.app.Application

/**
 ***************************************
 * Created by Abdelhadi on 2019-06-01.
 ***************************************
 */
class App : Application() {

    companion object {
        lateinit var INSTANCE: App
            private set
    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}