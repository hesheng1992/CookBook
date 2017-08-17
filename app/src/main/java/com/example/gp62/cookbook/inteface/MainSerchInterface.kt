package com.example.gp62.cookbook.inteface

import com.example.gp62.cookbook.bean.DataResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by GP62 on 2017/8/16.
 */
interface MainSerchInterface {

    @GET("jisuapi/search?")
    fun getApiDataSercah(@Query("keyword") name :String,@Query("num") num :String,@Query("appkey") key :String) :Observable<DataResult>
}