package com.example.gp62.cookbook.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.adpater.LeixingAdapater
import com.example.gp62.cookbook.bean.TypeData
import com.example.gp62.cookbook.present.MainPresent
import com.example.gp62.cookbook.utlis.SpaceItemDecoration
import com.example.gp62.cookbook.view.dialog.LoadingDialog
import org.jetbrains.anko.find
import rx.Subscription

/**
 * Created by GP62 on 2017/8/16.
 * 分类查询
 */
class LeiXingFragment : BaseFragment() {

    private var leixingRecycler: RecyclerView?=null

    private var leiAdapater: LeixingAdapater?=null

    private var listData: ArrayList<TypeData.ResultBeanX.ResultBean>?=null

    private var leixinPre: MainPresent?=null

    private var refresh: SwipeRefreshLayout?=null;

    //判断是否需要刷新
    private var flagLoad=true

    private var arr= arrayOfNulls<Int>(4)

    private var subscrip: Subscription?=null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = setContentView(R.layout.fenlei_fragment, container)
        initView(view)
        return view
    }


    @SuppressLint("ResourceAsColor")
    private fun initView(view :View) {
        leixingRecycler=view.find(R.id.leixing_recycler)
        refresh=view.find(R.id.swwprefresh)
        setSwipLayput(refresh!!)
        leiAdapater= LeixingAdapater(activity, ArrayList())
        leixingRecycler?.layoutManager=LinearLayoutManager(activity)
        leixingRecycler?.addItemDecoration(SpaceItemDecoration(20))
        leixingRecycler?.adapter=leiAdapater
        leixinPre= MainPresent(activity,this@LeiXingFragment)
        leixinPre?.setLeixingFragment()
        var loading = LoadingDialog(activity, R.mipmap.greenloading)
        setLoading(loading)
    }


    /**
     * 开始刷新数据
     */
    override fun startRefresh(){
        subscrip = leixinPre?.getLeiXingData()
    }


    fun addData(list: ArrayList<TypeData.ResultBeanX.ResultBean>){
        if (listData==null){
            listData= ArrayList<TypeData.ResultBeanX.ResultBean>()
        }
        listData?.clear()
        listData?.addAll(list)
        leiAdapater?.addData(listData as ArrayList<TypeData.ResultBeanX.ResultBean>)
    }

    /**判断页面是否可见
     *
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.e("Tga","aa")
        if (isVisibleToUser&&flagLoad){
            flagLoad=false
            refresh?.isRefreshing=true
            onRefrsh.onRefresh()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscrip?.unsubscribe()
    }

}