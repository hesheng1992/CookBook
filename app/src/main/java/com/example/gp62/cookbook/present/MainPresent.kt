package com.example.gp62.cookbook.present

import android.content.Context
import android.os.Handler
import android.os.Message
import com.example.gp62.cookbook.MainActivity
import com.example.gp62.cookbook.base.MAINCALLBACKQURAY
import com.example.gp62.cookbook.base.getApiData
import com.example.gp62.cookbook.bean.BaseData
import com.example.gp62.cookbook.bean.DataResult
import com.example.gp62.cookbook.bean.HistorySerchData
import com.example.gp62.cookbook.database.DBMangerSql
import com.example.gp62.cookbook.fragment.MainFragment
import com.example.gp62.cookbook.inteface.MainSerchInterface
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by GP62 on 2017/8/16.
 */
class MainPresent(context: Context, main: MainFragment,handler: Handler) {

    private val context = context
    /**
     * 获取依赖显示界面
     */
    private var main = main

    private var mHandler=handler
    /**
     * 搜索菜谱
     * https://way.jd.com/jisuapi/search?keyword=白菜&num=2&appkey=2672dcad901292807247685185071f36
     */
    open fun getDataMainSerch(name: String, num: String) : Subscription {
        //创建接口将用于请求的接口
        val create = getApiData(context).create(MainSerchInterface::class.java)
        return create.getApiDataSercah(name,num,"2672dcad901292807247685185071f36")
                .subscribeOn(Schedulers.newThread())//指定订阅在新线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调在主线程
                .subscribe({
                    t -> main.getData(t.result.result.list as ArrayList<BaseData>)
                    main.dissLoading()
                },{

                    t ->
                    main.dissLoading()
                    main.showToast(t.toString())

                },{
                    main.dissLoading()
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

    /**
     * 查看搜索历史
     */
   open fun changeHistoryData(){
        var listData=ArrayList<BaseData>()
        //返回集合
        val qureyData = DBMangerSql.getInstanse(context)?.qureyData()
        if (qureyData!=null){
            for (a in qureyData) {
                val entries = a.entries
                entries.forEach{
                    var data=HistorySerchData(it.key,it.value)
                    listData.add(data)
                }
            }
        }
        main.getHisData(listData)
        main.dissLoading()
    }

    fun getQurayData(){
        //初始化加载第一条数据库数据
        Thread(Runnable {
            kotlin.run {
                //加载数据库的数据
                var listQuretData=DBMangerSql.getInstanse(context)?.qureyData()
                if (listQuretData!=null&&listQuretData.size>0){
                    var stringName :String=""
                    val entries = listQuretData[0].entries
                    entries.forEach{
                        stringName=it.key
                    }
                    var message=Message()
                    message.what= MAINCALLBACKQURAY
                    message.obj=stringName
                    mHandler.sendMessage(message)

                }
            }
        }).start()
    }
}

