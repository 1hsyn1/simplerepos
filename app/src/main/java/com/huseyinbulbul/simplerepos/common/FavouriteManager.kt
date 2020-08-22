package com.huseyinbulbul.simplerepos.common

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.lang.Exception

class FavouriteManager(private var sharedPreferences: SharedPreferences) {
    companion object{
        private var instance: FavouriteManager? = null
        private val SHARED_KEY = "favourites"

        fun getInstance(): FavouriteManager{
            if(instance == null){
                throw (Exception("must be initialized first at application on create"))
            }
            return instance as FavouriteManager
        }

        fun init(sharedPreferences: SharedPreferences){
            instance = FavouriteManager(sharedPreferences)
        }
    }

    var favourites = mutableListOf<String>()

    init {
        val str = sharedPreferences.getString(SHARED_KEY, "")
        favourites.addAll(str.split(","))
    }

    fun addFavourite(id: String){
        favourites.add(id)
        refreshShared()
    }

    fun removeFavourite(id: String){
        favourites.forEachIndexed { index, s ->
            if(s == id){
                favourites.removeAt(index)
                refreshShared()
                return
            }
        }
    }

    fun isFavourite(id: String): Boolean{
        favourites.forEach {
            if(id == it){
                return true
            }
        }
        return false
    }

    private fun refreshShared(){
        var str = ""
        favourites.forEachIndexed { index, s ->
            if(s.trim().isNotEmpty()) {
                str += s
                if (index != favourites.size) {
                    str += ","
                }
            }
        }
        sharedPreferences.edit().putString(SHARED_KEY, str).apply()
    }
}