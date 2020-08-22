package com.huseyinbulbul.simplerepos.common

import android.app.Application
import android.preference.PreferenceManager

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        FavouriteManager.init(PreferenceManager.getDefaultSharedPreferences(this))
    }
}