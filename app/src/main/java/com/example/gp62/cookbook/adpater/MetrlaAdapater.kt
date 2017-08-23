package com.example.gp62.cookbook.adpater

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.bean.DataResult

/**
 * Created by GP62 on 2017/8/23.
 */
/**
 * 原料
 */
class MetrlaAdapater(context: Context,list: ArrayList<DataResult.ResultBeanX.ResultBean.ListBean.MaterialBean>) :RecyclerView.Adapter<MetrlaAdapater.MetrlaViewHold>(){

    private var listData=list

    private var mContext=context

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MetrlaViewHold {
       var view= LayoutInflater.from(mContext).inflate(R.layout.need_stack_item,parent,false)
        return MetrlaViewHold(view)
    }


    override fun onBindViewHolder(holder: MetrlaViewHold?, position: Int) {
        var metrlHold=holder
        metrlHold?.name?.text="原料名字:  "+listData[position].mname
        metrlHold?.amount?.text="用量:  "+listData[position].amount
        if (listData[position].type=="1"){
            metrlHold?.type?.text="原料类型:  主料"
        }else{
            metrlHold?.type?.text="原料类型:  辅料"
        }

    }


    class MetrlaViewHold(view :View) : RecyclerView.ViewHolder(view){
        var name=view.findViewById(R.id.materia_name)  as TextView
        var amount=view.findViewById(R.id.materia_amount) as TextView
        var type=view.findViewById(R.id.materia_type) as TextView
    }
}