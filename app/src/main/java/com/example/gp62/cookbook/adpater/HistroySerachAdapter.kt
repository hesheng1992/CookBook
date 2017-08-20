package com.example.gp62.cookbook.adpater

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.bean.HistorySerchData

/**
 * Created by GP62 on 2017/8/18.
 */
class HistroySerachAdapter(context :Context,list :ArrayList<HistorySerchData>) : RecyclerView.Adapter<HistroySerachAdapter.HisViewHold>() {

    var listData=list

    var mContext=context
    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HisViewHold {
        var view = LayoutInflater.from(mContext).inflate(R.layout.sercah_history_item,parent,false)
        return HisViewHold(view)
    }


    override fun onBindViewHolder(holder: HisViewHold?, position: Int) {
        var hold=holder
        hold?.textName?.text=listData[position].name
        hold?.textNumber?.text=listData[position].numb.toString()
    }

    class HisViewHold(view :View) : RecyclerView.ViewHolder(view){
        var textName=view.findViewById(R.id.history_name) as TextView
        var textNumber=view.findViewById(R.id.history_number) as TextView
    }

    /**
     * 添加数据
     */
    fun addData(list: ArrayList<HistorySerchData>){
        if (listData==null){
            listData=ArrayList()
        }
        if (listData!=null){
            listData.clear()
        }
        listData.addAll(list)
        notifyDataSetChanged()
    }
}