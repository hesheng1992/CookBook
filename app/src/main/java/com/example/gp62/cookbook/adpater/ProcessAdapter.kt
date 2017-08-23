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
 * Created by GP62 on 2017/8/22.
 */
class ProcessAdapter(context: Context, data: ArrayList<DataResult.ResultBeanX.ResultBean.ListBean.ProcessBean>)
    : RecyclerView.Adapter<ProcessAdapter.ProViewHold>() {

    var mcontext = context

    private var listData = data

    override fun onBindViewHolder(holder: ProViewHold?, position: Int) {
        val hold=holder
        Glide.with(mcontext)
                .load(listData[position].pic)
                .into(hold?.proImage)
        hold?.processBuzhou?.text="第"+(position+1)+"步"
        hold?.processName?.text=listData[position].pcontent

    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProViewHold {
        val inflate = LayoutInflater.from(mcontext).inflate(R.layout.process_show, parent,false)
        return ProViewHold(inflate)
    }


    override fun getItemCount(): Int {
        return listData.size
    }



    class ProViewHold(view: View) : RecyclerView.ViewHolder(view) {
        var processBuzhou = view.findViewById(R.id.process_bushu) as TextView
        var processName = view.findViewById(R.id.process_name) as TextView
        var proImage = view.findViewById(R.id.process_image) as ImageView
    }

}