package com.huseyinbulbul.simplerepos.common.network

import com.huseyinbulbul.simplerepos.common.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleReposApi {

    @GET("users/{user}/repos")
    fun getRepos(@Path("user") user: String): Observable<List<Repository>>
}