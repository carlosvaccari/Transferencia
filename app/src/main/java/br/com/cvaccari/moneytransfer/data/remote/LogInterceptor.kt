package br.com.bakery.simplewallpaperlib.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class LogInterceptor {

    fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}