package com.example.gp62.cookbook.adpater

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.bean.TypeData
import com.example.gp62.cookbook.utlis.SpaceItemDecoration
import org.jetbrains.anko.find

/**
 * Created by GP62 on 2017/8/25.
 */
class LeixingAdapater(context: Context,list :ArrayList<TypeData.ResultBeanX.ResultBean>)
    : RecyclerView.Adapter<LeixingAdapater.LeiViewHold>() {

    private var listData=list

    private var mContext=context

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LeiViewHold {
        val inflate = LayoutInflater.from(mContext).inflate(R.layout.leixing_item, parent, false)
        return LeiViewHold(inflate)
    }

    override fun onBindViewHolder(holder: LeiViewHold?, position: Int) {
        var hold=holder
        hold?.textName?.text="分类名:  "+listData[position].name
        if (listData[position].list!=null){
            hold?.recycler?.layoutManager=GridLayoutManager(mContext,5,GridLayoutManager.HORIZONTAL,false)
//            hold?.recycler?.addItemDecoration(SpaceItemDecoration(10))
            hold?.recycler?.adapter=LeiJuTIAdapter(mContext,listData[position].list
                    as ArrayList<TypeData.ResultBeanX.ResultBean.ListBean>)
        }else{
            hold?.recycler?.adapter=LeiJuTIAdapter(mContext,ArrayList())
        }

    }

    /**
     * 添加数据
     */
    fun addData(list: ArrayList<TypeData.ResultBeanX.ResultBean>){
        if (listData==null){
            listData=ArrayList()
        }else{
            listData.clear()
        }
        listData.addAll(list)
        notifyDataSetChanged()
    }


    class LeiViewHold(view :View) : RecyclerView.ViewHolder(view){
        var textName=view.find<TextView>(R.id.lei_text_name)
        var recycler=view.find<RecyclerView>(R.id.lei_recycler)
    }
}