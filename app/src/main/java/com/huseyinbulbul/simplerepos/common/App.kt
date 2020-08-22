package com.huseyinbulbul.simplerepos.common

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        FavouriteManager.init(this)
    }
}