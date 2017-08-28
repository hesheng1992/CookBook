package com.example.gp62.cookbook.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.view.dialog.LoadingDialog

/**
 * Created by GP62 on 2017/8/25.
 */
abstract class BaseFragment : Fragment() {
    /**
     * 加载对话框
     */
    private var loading: LoadingDialog? = null

    private var refresh: SwipeRefreshLayout? = null;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loading = LoadingDialog(activity, R.mipmap.greenloading)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * 设置对话框值
     */
    fun setLoading(loading: LoadingDialog){
        this.loading=loading
    }

    fun showDiolg() {
        loading?.show()
    }

    fun dissLoading() {
        if (loading?.isShowing as Boolean){
            loading?.dismiss()
        }
    }

    /**
     * 设置布局
     */
    fun setContentView(id: Int, viewGroup: ViewGroup?): View {
        val inflate = LayoutInflater.from(activity).inflate(id, viewGroup, false)
        return inflate
    }

    /**
     * 初始化下刷新
     */
    fun setSwipLayput(swip: SwipeRefreshLayout) {
        this.refresh = swip
        refresh?.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light
                , android.R.color.holo_red_light, android.R.color.holo_orange_light)
        refresh?.setOnRefreshListener(onRefrsh)
    }

    /**
     * 设置刷新
     */
    val onRefrsh = object : SwipeRefreshLayout.OnRefreshListener {
        override fun onRefresh() {
            startRefresh()
        }
    }

    /**
     * 开始刷新数据
     */
    abstract fun startRefresh()

    /**
     * 停止刷新
     */
    fun stopRefresh() {
        val refreshing = refresh?.isRefreshing
        if (refreshing as Boolean) {
            refresh?.isRefreshing = false
        }
    }

    /**
     * 提示
     */
    fun showToast(str: String) {
        Toast.makeText(activity, str, Toast.LENGTH_SHORT).show()
    }

}