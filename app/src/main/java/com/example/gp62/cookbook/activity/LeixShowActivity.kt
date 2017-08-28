package com.example.gp62.cookbook.activity

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.widget.ImageView
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.adpater.MainSerchAdapter
import com.example.gp62.cookbook.annotion.BindViewID
import com.example.gp62.cookbook.base.BaseAvtivity
import com.example.gp62.cookbook.base.getBindId
import com.example.gp62.cookbook.bean.BaseData
import com.example.gp62.cookbook.bean.DataResult
import com.example.gp62.cookbook.present.MainPresent
import com.example.gp62.cookbook.utlis.SpaceItemDecoration
import org.jetbrains.anko.find

/**
 * 按类搜索展示菜品
 */
class LeixShowActivity : BaseAvtivity() {
    @BindViewID(getId = R.id.recylerview_leixing)
    private var recycler: RecyclerView?=null

    @BindViewID(getId = R.id.swip_leixing)
    private var swip: SwipeRefreshLayout?=null

    private var adpater: MainSerchAdapter? = null

    private var listData: ArrayList<BaseData>? = null

    private var mainPre: MainPresent?=null

    private var classid: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leix_show)
        getBindId(this)//注解获取id
        initview()
    }

    fun initview(){
        frame=find(R.id.frame)
        photoView=find(R.id.photoview)
        inmae=find(R.id.frame_image)
        photoView?.setScaleType(ImageView.ScaleType.CENTER_INSIDE)
        photoView?.enable()
        photoView?.setOnClickListener(this)
        mainPre= MainPresent(this)
        mainPre?.setActivity(this@LeixShowActivity)
        setSwipLayput(swip as SwipeRefreshLayout)
        recycler?.layoutManager = LinearLayoutManager(this)
        recycler?.addItemDecoration(SpaceItemDecoration(30))
        adpater = MainSerchAdapter(this, ArrayList(), photoviewShowImpl)
        recycler?.adapter=adpater
        classid=intent.getStringExtra("classId")
        if(!TextUtils.isEmpty(classid)){
            swip?.isRefreshing=true
            mainPre?.getDataMainSerch(classid as String,"0","20")
        }
    }


    override fun startRefresh(){
        if(!TextUtils.isEmpty(classid)){
            mainPre?.getDataMainSerch(classid as String,"0","20")
        }else{
            swip?.isRefreshing=false
        }
    }

    /**
     * 加载数据到适配器
     */
    fun getData(sera: ArrayList<BaseData>) {
        if (listData == null) {
            listData = ArrayList<BaseData>()
        }
        if (sera != null) {
            listData?.clear()
            listData?.addAll(sera)
            if (listData != null) {
                adpater?.addData(listData as ArrayList<DataResult.ResultBeanX.ResultBean.ListBean>)
            }
        }
    }
}
