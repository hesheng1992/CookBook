package com.example.gp62.cookbook.adpater

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.bean.DataResult

/**
 * Created by GP62 on 2017/8/16.
 */
class MainSerchAdapter(context :Context,data :ArrayList<DataResult.ResultBeanX.ResultBean.ListBean>) : RecyclerView.Adapter<MainSerchAdapter.ViewHold>() {

    var listData=data
    private val context=context



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHold {
        val inflate = LayoutInflater.from(context).inflate(R.layout.shucai_list_item, parent,false)
        return ViewHold(inflate)
    }

    override fun onBindViewHolder(holder: ViewHold?, position: Int) {
        val viewHold =holder
        var listName=listData[position]
        viewHold?.textName?.text= listName.name
        viewHold?.peoplenum?.text=listName.peoplenum
        viewHold?.preparetime?.text=listName.preparetime
        viewHold?.cookingtime?.text=listName.cookingtime
        viewHold?.content?.text=listName.content
        viewHold?.tag?.text=listName.tag
        Glide.with(context).load(listName.pic)
                .error(R.mipmap.bar2)
                .into(viewHold?.image)

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun addData(list: ArrayList<DataResult.ResultBeanX.ResultBean.ListBean>){
        if (listData==null){
            listData=ArrayList()
        }else{
            listData.clear()
        }
        listData.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHold(view :View) : RecyclerView.ViewHolder(view){
        //菜名
        var textName=view.findViewById(R.id.name) as TextView
        //图片
        var image=view.findViewById(R.id.cai_image) as ImageView
        //使用人数
        var peoplenum=view.findViewById(R.id.peoplenum) as TextView
        /**
         * 准备时间
         */
        var preparetime=view.findViewById(R.id.preparetime) as TextView
        //烹饪时间
        var cookingtime=view.findViewById(R.id.cookingtime) as TextView
        //描述
        var content=view.findViewById(R.id.content) as TextView
        /**
         * 功效
         */
        var tag=view.findViewById(R.id.tag) as TextView

    }
}