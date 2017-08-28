package com.example.gp62.cookbook

import android.app.Application
import com.example.gp62.cookbook.base.baseUrl
import com.example.gp62.cookbook.sp.initSharPrefence
import com.example.gp62.cookbook.sp.sharePrefensUtils
import com.google.gson.Gson
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory

/**
 * Created by GP62 on 2017/8/12.
 */
class CookAppliction : Application() {

    override fun onCreate() {
        super.onCreate()
        //初始化RxEasy
//        EasyHttp.init(this)
//        //参数配置
//        EasyHttp.getInstance()
//                //全局统一设置全局URL
//                .setBaseUrl(baseUrl)//设置全局URL
//                // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
//                .debug("EasyHttp", true)
//                //如果使用默认的60秒,以下三行也不需要设置
//                .setReadTimeOut(100 * 1000)
//                .setWriteTimeOut(100 * 100)
//                .setConnectTimeout(100 * 100)
//                //可以全局统一设置超时重连次数,默认为3次,那么最差的情况会请求4次(一次原始请求,三次重连请求),
//                //不需要可以设置为0
//                .setRetryCount(3)//网络不好自动重试3次
//                //可以全局统一设置超时重试间隔时间,默认为500ms,不需要可以设置为0
//                .setRetryDelay(500)//每次延时500ms重试
//                //可以全局统一设置超时重试间隔叠加时间,默认为0ms不叠加
//                .setRetryIncreaseDelay(500)//每次延时叠加500ms
//                .setCacheMode(CacheMode.NO_CACHE)
//                //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体请看CacheMode
////                .setCacheMode(CacheMode.FIRSTCACHE)
//                //可以全局统一设置缓存时间,默认永不过期
////                .setCacheTime(-1)//-1表示永久缓存,单位:秒 ，Okhttp和自定义RxCache缓存都起作用
//                //全局设置自定义缓存保存转换器，主要针对自定义RxCache缓存
////                .setCacheDiskConverter(SerializableDiskConverter())//默认缓存使用序列化转化
//                //全局设置自定义缓存大小，默认50M
////                .setCacheMaxSize(100 * 1024 * 1024)//设置缓存大小为100M
//                //设置缓存版本，如果缓存有变化，修改版本后，缓存就不会被加载。特别是用于版本重大升级时缓存不能使用的情况
////                .setCacheVersion(1)//缓存版本为1
////                .setHttpCache(Cache)//设置Okhttp缓存，在缓存模式为DEFAULT才起作用
////                .addConverterFactory(GsonConverterFactory.create(Gson()))//本框架没有采用Retrofit的Gson转化，所以不用配置
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        //初始化share
        sharePrefensUtils= initSharPrefence(applicationContext)

    }
}