package com.example.gp62.cookbook.adpater

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.activity.LeixShowActivity
import com.example.gp62.cookbook.bean.TypeData
import org.jetbrains.anko.find

/**
 * Created by GP62 on 2017/8/25.
 */
class LeiJuTIAdapter(context: Context,list: ArrayList<TypeData.ResultBeanX.ResultBean.ListBean>)
    : RecyclerView.Adapter<LeiJuTIAdapter.JutiViewHold> (){

    val mContex=context

    var listData=list

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): JutiViewHold {
        val inflate = LayoutInflater.from(mContex).inflate(R.layout.leixing_juti_item, parent, false)
        return JutiViewHold(inflate)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: JutiViewHold?, position: Int) {
        var hold=holder
        hold?.textname?.text=listData[position].name
        hold?.view?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                var intent=Intent(mContex,LeixShowActivity::class.java)
                intent.putExtra("classId",listData[position].classid)
                mContex.startActivity(intent)
            }
        })
    }


    class JutiViewHold(view :View) :RecyclerView.ViewHolder(view){
        var textname=view.find<TextView>(R.id.juti_text_name)
        var view=view
    }
}