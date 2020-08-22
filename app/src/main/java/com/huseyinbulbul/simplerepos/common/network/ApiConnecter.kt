package com.huseyinbulbul.simplerepos.common.network

import android.text.TextUtils
import com.huseyinbulbul.simplerepos.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.internal.http.RealInterceptorChain
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class ApiConnecter {
    companion object{
        private val BASE_URL = "https://api.github.com/"
        private var api: SimpleReposApi? = null

        fun getApi(): SimpleReposApi{
            if(api == null){
                api = getRetrofit(BASE_URL).create(SimpleReposApi::class.java)
            }

            return api as SimpleReposApi
        }

        fun getRetrofit(baseUrl: String): Retrofit{
            val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            builder.addInterceptor(AddHeaderInterceptor())
            builder.connectTimeout(120, TimeUnit.SECONDS)
            builder.readTimeout(120, TimeUnit.SECONDS)
            builder.writeTimeout(120, TimeUnit.SECONDS)

            if(BuildConfig.DEBUG){
                val logger = HttpLoggingInterceptor()
                logger.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(logger)
            }

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private class AddHeaderInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {

                val builder = chain.request().newBuilder()
                builder.addHeader("Accept-Language", "tr")
                builder.addHeader("App-Version", BuildConfig.VERSION_NAME)
                builder.addHeader("User-Agent", System.getProperty("http.agent"))

                return chain.proceed(builder.build())
            }
        }
    }
}