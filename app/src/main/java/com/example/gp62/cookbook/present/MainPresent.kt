package com.example.gp62.cookbook.present

import android.content.Context
import com.example.gp62.cookbook.base.getApiData
import com.example.gp62.cookbook.bean.DataResult
import com.example.gp62.cookbook.fragment.MainFragment
import com.example.gp62.cookbook.inteface.MainSerchInterface
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by GP62 on 2017/8/16.
 */
class MainPresent(context: Context, main: MainFragment) {

    private val context = context

    private var main = main

    open fun getDataMainSerch(name: String, num: String) : Subscription {
        //创建接口将用于请求的接口
        val create = getApiData(context).create(MainSerchInterface::class.java)
        return create.getApiDataSercah(name,num,"2672dcad901292807247685185071f36")
                .subscribeOn(Schedulers.newThread())//指定订阅在新线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调在主线程
                .subscribe({
                    t -> main.getData(t.result.result.list as ArrayList<DataResult.ResultBeanX.ResultBean.ListBean>)
                },{
                    t ->  main.showToast(t.toString())
                },{

                })
//        HttpUtils.setBaseUrl(baseUrl)
//        HttpUtils.execte(context,"jisuapi/search?&keyword="+name+"&num="+num+"&appkey=2672dcad901292807247685185071f36","GET"
//        , DataResult().javaClass,object :RequustResult<DataResult>{
//            override fun onSucess(t: DataResult?) {
//                if (t != null) {
//                    main.getData(t.result.result.list as ArrayList<DataResult.ResultBeanX.ResultBean.ListBean>)
//                }
//            }
//
//            override fun onFiled(e: String?) {
//
//            }
//
//
//        })
    }



}

