package com.example.gp62.cookbook.adpater

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.ToggleButton
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.bean.DataResult
import com.example.gp62.cookbook.utlis.SpaceItemDecoration

/**
 * Created by GP62 on 2017/8/22.
 */
class ShowProcessAdapter(context: Context, data: ArrayList<DataResult.ResultBeanX.ResultBean.ListBean>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mContext = context
    /**
     * 所需数据
     */
    private var listData = data

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val from = LayoutInflater.from(mContext)
        val inflate = from.inflate(R.layout.showprocess_serach, parent, false)
        return OneProcessViewHold(inflate)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        var hold = holder as OneProcessViewHold
        hold?.recycerProcess.layoutManager = LinearLayoutManager(mContext)
        hold?.recycerProcess?.addItemDecoration(SpaceItemDecoration(30))
        hold?.toggle?.setOnCheckedChangeListener(object :CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked){
                    hold?.recycerProcess.visibility=View.GONE
                }else{
                    hold?.recycerProcess.visibility=View.VISIBLE
                }
            }
        })
        //根据名字不同，加载对应的数据
        when (position) {

            0 -> {
                hold?.textname?.text = "制作过程"
                if (listData[0].process == null) {
                    hold?.recycerProcess.adapter = ProcessAdapter(mContext, ArrayList())
                } else {
                    hold?.recycerProcess.adapter = ProcessAdapter(mContext, listData[0].process
                            as ArrayList<DataResult.ResultBeanX.ResultBean.ListBean.ProcessBean>)
                }
            }
            1 -> {
                hold?.textname?.text = "所需原料"
                if (listData[0].material == null){
                    hold?.recycerProcess.adapter = MetrlaAdapater(mContext, ArrayList())
                }else{
                    hold?.recycerProcess.adapter=MetrlaAdapater(mContext,listData[0].material
                            as ArrayList<DataResult.ResultBeanX.ResultBean.ListBean.MaterialBean>)
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return listData.size
    }

    /**
     * 返回需要加载的类型
     */
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    /**
     * 外层Recycler
     */
    class OneProcessViewHold(view: View) : RecyclerView.ViewHolder(view) {
        var recycerProcess = view.findViewById(R.id.xiangxie_recycler) as RecyclerView
        var toggle = view.findViewById(R.id.toggle_button) as ToggleButton
        var textname = view.findViewById(R.id.text_name) as TextView
    }
}