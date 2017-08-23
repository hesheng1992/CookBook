package com.example.gp62.cookbook.adpater

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.bean.HistorySerchData
import com.example.gp62.cookbook.inteface.RecyclerViewOnitemListner

/**
 * Created by GP62 on 2017/8/18.
 */
class HistroySerachAdapter(context: Context, list: ArrayList<HistorySerchData>) : RecyclerView.Adapter<HistroySerachAdapter.HisViewHold>() {

    var listData = list

    var mContext = context

    var onIteamListner: RecyclerViewOnitemListner? = null

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HisViewHold {
        var view = LayoutInflater.from(mContext).inflate(R.layout.sercah_history_item, parent, false)
        return HisViewHold(view)
    }

    /**
     * 传递接口回调对象
     */
    fun getCallBack(onclicItem: RecyclerViewOnitemListner) {
        this.onIteamListner=onclicItem
    }

    override fun onBindViewHolder(holder: HisViewHold?, position: Int) {
        var hold = holder
        hold?.textName?.text = listData[position].name
        hold?.textNumber?.text = listData[position].numb.toString()
        hold?.view?.setOnClickListener{
            v ->if (this.onIteamListner!=null){
            this.onIteamListner?.onItemListnerRecycler(listData[position].name)
        }
        }
    }

    //尽量创建为静态的内部类，因为不是静态的话，它会隐式的持有外部类的引用，就可以调用
    //外部类的方法，为了避免持有外部类的对象，所以申明为静态好些，避免加载耗时操作时，内存泄漏
    class HisViewHold(view: View) : RecyclerView.ViewHolder(view) {
        var textName = view.findViewById(R.id.history_name) as TextView
        var textNumber = view.findViewById(R.id.history_number) as TextView
        var view = view
    }

    /**
     * 添加数据
     */
    fun addData(list: ArrayList<HistorySerchData>) {
        if (listData == null) {
            listData = ArrayList()
        }
        if (listData != null) {
            listData.clear()
        }
        listData.addAll(list)
        notifyDataSetChanged()
    }
}