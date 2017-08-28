package com.example.gp62.cookbook.base

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.util.Log
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.annotion.BindViewID
import com.google.gson.Gson
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by GP62 on 2017/8/12.
 */
/**
 * key:2672dcad901292807247685185071f36
 */
//基础url
val baseUrl="https://way.jd.com/"


/**
 * 设置全局的retrofit
 */
fun getApiData(context: Context) :Retrofit{
    //打印log
    var logInterceptor= HttpLoggingInterceptor()
    logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    var okhttp=OkHttpClient.Builder()
            .cache( Cache(context.cacheDir,100 * 1024 * 1024))
            .addInterceptor(logInterceptor)
            .build()

    var retrifit=Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okhttp)
            .build()
    return retrifit
}

/**
 * 反射获取activity上的指定注解
 * @param activity
 */
fun getBindId(activity :Activity) {
    try {
        val cla =activity.javaClass
        val obj = cla.newInstance()
        val declaredFields = cla.declaredFields
        for (field in declaredFields) {
            if (field.isAnnotationPresent(BindViewID::class.java)) {
                val annotation = field.getAnnotation(BindViewID::class.java)
                field.isAccessible=true
                val id = annotation.getId
                activity.findViewById(id)
                field.set(activity, activity.findViewById(id))
            }
        }
    } catch (e: InstantiationException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}

/**
 * 获取fragment上的熟悉id
 */
fun getId(fragment: Fragment) {
    try {
        val cla = fragment.javaClass
        val declaredFields = cla.declaredFields
        for (field in declaredFields) {
            if (field.isAnnotationPresent(BindViewID::class.java)) {
                val annotation = field.getAnnotation(BindViewID::class.java)
                val id = annotation.getId
                field.set(fragment, fragment.activity.findViewById(id))
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
/**
 * 配置刷新时的动画颜色
 */
val colors= intArrayOf(R.color.color_myproperty_aialable, R.color.color_myproperty_huodong
        , R.color.color_myproperty_daishou, R.color.color_myproperty_dongjie)
