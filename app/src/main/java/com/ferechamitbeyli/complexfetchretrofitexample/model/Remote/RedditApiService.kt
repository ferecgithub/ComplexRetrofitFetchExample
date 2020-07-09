package com.ferechamitbeyli.complexfetchretrofitexample.model.Remote

import com.ferechamitbeyli.complexfetchretrofitexample.model.Response
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RedditApiService {
    private val BASE_URL = "https://www.reddit.com"

    private val api = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(RedditApi::class.java)

    fun getData(): Single<Response> {
        return api.getData()
    }
}