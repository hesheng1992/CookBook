package com.example.gp62.cookbook.activity

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.bm.library.Info
import com.bm.library.PhotoView
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.adpater.ShowProcessAdapter
import com.example.gp62.cookbook.annotion.BindViewID
import com.example.gp62.cookbook.base.BaseAvtivity
import com.example.gp62.cookbook.base.getBindId
import com.example.gp62.cookbook.bean.DataResult
import com.example.gp62.cookbook.inteface.CallBackPhotoImpl
import com.example.gp62.cookbook.utlis.SpaceItemDecoration

class ShowProcessActivity : BaseAvtivity() {

    @BindViewID(getId = R.id.show_recyclerview)
    private var recycler: RecyclerView?=null

    @BindViewID(getId = R.id.line_show)
    private var linershow: LinearLayout?=null

    var list=ArrayList<DataResult.ResultBeanX.ResultBean.ListBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_process)
        getBindId(this)
        //模糊图片
        val drawable = BitmapDrawable(bluBitemapGet(R.drawable.show_process))
        linershow?.background = drawable
        val bundleExtra = intent.getBundleExtra("bundle")
        val parcelable=bundleExtra.get("process") as DataResult.ResultBeanX.ResultBean.ListBean
        list.add(parcelable)
        list.add(DataResult.ResultBeanX.ResultBean.ListBean())
        recycler?.layoutManager=LinearLayoutManager(this)
        recycler?.addItemDecoration(SpaceItemDecoration(30))
        if (parcelable!=null){
            recycler?.adapter=ShowProcessAdapter(this,list)
        }else{
            recycler?.adapter=ShowProcessAdapter(this,ArrayList())
        }
    }
}
