package com.example.gp62.cookbook.fragment

import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.gp62.cookbook.MainActivity
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.adpater.HistroySerachAdapter
import com.example.gp62.cookbook.adpater.MainSerchAdapter
import com.example.gp62.cookbook.base.MAINCALLBACKQURAY
import com.example.gp62.cookbook.bean.BaseData
import com.example.gp62.cookbook.bean.DataResult
import com.example.gp62.cookbook.bean.HistorySerchData
import com.example.gp62.cookbook.database.DBMangerSql
import com.example.gp62.cookbook.inteface.RecyclerViewOnitemListner
import com.example.gp62.cookbook.present.MainPresent
import com.example.gp62.cookbook.utlis.SpaceItemDecoration
import com.example.gp62.cookbook.view.dialog.LoadingDialog
import rx.Subscription

/**
 * Created by GP62 on 2017/8/15.
 */
/**
 * 搜索
 */
class MainFragment : Fragment() {

    private var recycler: RecyclerView? = null

    private var edtext: EditText? = null
    /**
     * 搜索
     */
    private var btn: Button? = null

    private var adpater: MainSerchAdapter? = null

    private var listData: ArrayList<BaseData>? = null
    /**
     * 专门做逻辑处理的present层
     */
    private var mainPre: MainPresent? = null
    /**
     * 观察者
     */
    private var subsicption: Subscription? = null
    /**
     * 搜索名字
     */
    private var serachName: String? = null
    /**
     * 搜索结果适配器
     */
    private var historyAdapter: HistroySerachAdapter? = null
    /**
     * 判断是否改变布局
     */
    private var isChange = false

    private var textChange: Button? = null
    /**
     * 加载对话框
     */
    private var loading: LoadingDialog? = null
    /**
     * 搜索名称次数
     */
    private var relate: RelativeLayout? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.main_fragment, container, false)
        recycler = view.findViewById(R.id.recylerview) as RecyclerView
        edtext = view.findViewById(R.id.edtix) as EditText
        btn = view.findViewById(R.id.btn_seach) as Button
        textChange = view.findViewById(R.id.sousuo_history) as Button
        relate = view.findViewById(R.id.relative_seach) as RelativeLayout
        relate?.visibility = View.GONE
        textChange?.setOnClickListener(onClick)
        btn?.setOnClickListener(onClick)
        recycler?.layoutManager = LinearLayoutManager(activity)
        recycler?.addItemDecoration(SpaceItemDecoration(30))
        adpater = MainSerchAdapter(activity, ArrayList(), (activity as MainActivity).photoviewShowImpl)
        historyAdapter = HistroySerachAdapter(activity, ArrayList())
        historyAdapter?.getCallBack(recyclerItemOnclick)//传入回调对象引用
        recycler?.adapter = adpater
        mainPre = MainPresent(activity, this@MainFragment,handler = (activity as MainActivity).handler)
        loading = LoadingDialog(activity, R.mipmap.greenloading)
        return view
    }

    override fun onResume() {
        super.onResume()

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

    /**
     * 加载数据到适配器
     */
    fun getHisData(sera: ArrayList<BaseData>) {
        if (listData == null) {
            listData = ArrayList<BaseData>()
        }
        if (sera != null) {
            listData?.clear()
            listData?.addAll(sera)
            if (listData != null) {
                historyAdapter?.addData(listData as ArrayList<HistorySerchData>)
            }
        }
    }

    /**
     * 点击事件
     */
    private val onClick = object : View.OnClickListener {
        override fun onClick(v: View?) {

            when (v?.id) {
                R.id.btn_seach -> {
                    serachName = edtext?.text.toString()
                    if (!TextUtils.isEmpty(serachName)) {
                        showDiolg()
                        isChange = true
                        changeAdapter()
                        subsicption = mainPre?.getDataMainSerch(edtext?.text.toString(), "20")
                        Thread(Runnable {
                            kotlin.run {
                                //查询当前搜索的自动是否数据库存在
                                var flag = DBMangerSql.getInstanse(activity)?.qurey(serachName as String)
                                if (flag as Boolean) {
                                    //执行更新
                                    DBMangerSql.getInstanse(activity)?.addAndUpateDataBase("serchnum",
                                            arrayOf("1", serachName ?: ""), "update")
                                } else {
                                    //执行插入
                                    DBMangerSql.getInstanse(activity)?.addAndUpateDataBase("serchnum",
                                            arrayOf(serachName, "1"), "insert")
                                }
                            }
                        }).start()
                    } else {
                        showToast("不能输入空的菜名哦！")
                    }
                }
            //点击切换按钮
                R.id.sousuo_history -> {
                    changeAdapter()
                }
            }
        }
    }

    /**
     * 执行item点击回调。
     */
    private val recyclerItemOnclick = object : RecyclerViewOnitemListner {
        override fun onItemListnerRecycler(string: String) {
            isChange=true
            changeAdapter()
            showDiolg()
            subsicption = mainPre?.getDataMainSerch(string, "20")
        }
    }

    /**
     * 更换适配器
     */
    fun changeAdapter() {
        synchronized(this) {
            if (isChange) {
                relate?.visibility = View.GONE
                textChange?.text = "点击查看搜索历史"
                if (adpater == null) {
                    adpater = MainSerchAdapter(activity, ArrayList(), (activity as MainActivity).photoviewShowImpl)
                }
                recycler?.adapter = adpater
                isChange = false
            } else {
                showDiolg()
                relate?.visibility = View.VISIBLE
                textChange?.text = "点击查看搜索结果"
                if (historyAdapter == null) {
                    historyAdapter = HistroySerachAdapter(activity, ArrayList())
                }
                recycler?.adapter = historyAdapter
                isChange = true
                //加载数据库的数据
                mainPre?.changeHistoryData()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //解除订阅
        subsicption?.unsubscribe()
    }

    /**
     * 提示
     */
    fun showToast(str: String) {
        Toast.makeText(activity, str, Toast.LENGTH_SHORT).show()
    }

    fun showDiolg() {
        loading?.show()
    }

    fun dissLoading() {
        loading?.dismiss()
    }

}
