package com.huseyinbulbul.simplerepos.common

import android.content.Context
import android.content.SharedPreferences
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FavouriteManagerTest {
    private val sharedPreferences = mockk<SharedPreferences>(relaxed = true)
    private lateinit var manager: FavouriteManager

    @BeforeAll
    fun setup(){
        manager = FavouriteManager(sharedPreferences)
    }

    @Test
    fun addFavouriteTest(){
        manager.addFavourite("12")

        assert(manager.favourites[manager.favourites.size - 1] == "12")
    }

    @Test
    fun removeFavouriteTest(){
        manager.favourites[0] = "22"
        manager.removeFavourite("22")

        assert(manager.favourites[0] != "22")
    }

    @Test
    fun isFavouriteTest(){
        manager.favourites[0] = "33"

        assert(manager.isFavourite("33"))
        assert(!manager.isFavourite("44"))
    }
}