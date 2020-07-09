package com.ferechamitbeyli.complexfetchretrofitexample.model.Remote

import com.ferechamitbeyli.complexfetchretrofitexample.model.Response
import io.reactivex.Single
import retrofit2.http.GET

interface RedditApi {
    @GET(".json")
    fun getData(): Single<Response>
}