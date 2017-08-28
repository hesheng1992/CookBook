package com.example.gp62.cookbook.present

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import com.example.gp62.cookbook.MainActivity
import com.example.gp62.cookbook.base.MAINCALLBACKQURAY
import com.example.gp62.cookbook.base.getApiData
import com.example.gp62.cookbook.bean.BaseData
import com.example.gp62.cookbook.bean.DataResult
import com.example.gp62.cookbook.bean.HistorySerchData
import com.example.gp62.cookbook.database.DBMangerSql
import com.example.gp62.cookbook.fragment.MainFragment
import com.example.gp62.cookbook.inteface.MainSerchInterface
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import android.support.v4.app.Fragment
import com.example.gp62.cookbook.activity.LeixShowActivity
import com.example.gp62.cookbook.bean.TypeData
import com.example.gp62.cookbook.fragment.LeiXingFragment

/**
 * Created by GP62 on 2017/8/16.
 */
class MainPresent(context: Context) {


    constructor(context: Context, main: Fragment):this(context){
        this.mainFrag=main
    }


    private var leiActivity: LeixShowActivity?=null

    private val context = context

    private var mainFrag :Fragment?=null
    /**
     * 获取依赖显示界面
     */
    private var main: MainFragment?=null

    private var leiXing: LeiXingFragment?=null


    fun setMainFragment(){
        main =mainFrag as MainFragment
    }

    fun setLeixingFragment(){
        leiXing =mainFrag as LeiXingFragment
    }

    fun setActivity(activity: Activity){
        leiActivity= activity as LeixShowActivity?
    }

    //创建接口将用于请求的接口
    val create = getApiData(context).create(MainSerchInterface::class.java)

    /**
     * 搜索菜谱
     * https://way.jd.com/jisuapi/search?keyword=白菜&num=2&appkey=2672dcad901292807247685185071f36
     */
    open fun getDataMainSerch(name: String, num: String): Subscription {

        return create.getApiDataSercah(name, num, "2672dcad901292807247685185071f36")
                .subscribeOn(Schedulers.newThread())//指定订阅在新线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调在主线程
                .subscribe({
                    t ->
                    main?.getData(t.result.result.list as ArrayList<BaseData>)
                    main?.dissLoading()
                    main?.stopRefresh()
                }, {

                    t ->
                    main?.dissLoading()
                    main?.stopRefresh()
                    main?.showToast(t.toString())

                }, {
                    main?.dissLoading()
                    main?.stopRefresh()
                })
    }

    /**
     * 查看搜索历史
     */
    open fun changeHistoryData() {
        var listData = ArrayList<BaseData>()
        //返回集合
        val qureyData = DBMangerSql.getInstanse(context)?.qureyData()
        if (qureyData != null) {
            for (a in qureyData) {
                val entries = a.entries
                entries.forEach {
                    var data = HistorySerchData(it.key, it.value)
                    listData.add(data)
                }
            }
        }
        main?.getHisData(listData)
        main?.dissLoading()
    }

    fun getQurayData() {
        //初始化加载第一条数据库数据

        try {//直接开启线程
            doAsync {
                //加载数据库的数据
                var listQuretData = DBMangerSql.getInstanse(context)?.qureyData()
                if (listQuretData != null && listQuretData.size > 0) {
                    var stringName: String = ""
                    val entries = listQuretData[0].entries
                    entries.forEach {
                        stringName = it.key
                    }
                    //回到主线程
                    uiThread {
                        if (!TextUtils.isEmpty(stringName)){
                            main?.initData(stringName)
                        }
                    }
                }
            }
        } catch(e: Exception) {
        } finally {

        }
    }

    fun getLeiXingData() :Subscription{
        return create.getAPIDataLeixing("2672dcad901292807247685185071f36")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    t ->
                    leiXing?.stopRefresh()
                    if(t.result.result!=null){
                        leiXing?.addData(t.result.result as ArrayList<TypeData.ResultBeanX.ResultBean>)
                    }

                },{
                    t ->
                    leiXing?.stopRefresh()
                    leiXing?.showToast(t.toString())
                },{
                    leiXing?.stopRefresh()
                }
                )

    }

    /**
     * 按类的id查询
     */
    open fun getDataMainSerch(classId: String, startnum: String,num :String): Subscription {

        return create.getAPIDataFenClassId(classId, startnum, num,"2672dcad901292807247685185071f36")
                .subscribeOn(Schedulers.newThread())//指定订阅在新线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调在主线程
                .subscribe({
                    t ->
                    leiActivity?.getData(t.result.result.list as ArrayList<BaseData>)
                    leiActivity?.stopRefresh()
                }, {

                    t ->
                    leiActivity?.stopRefresh()
                    leiActivity?.showToast(t.toString())

                }, {
                    leiActivity?.stopRefresh()
                })
    }


}

