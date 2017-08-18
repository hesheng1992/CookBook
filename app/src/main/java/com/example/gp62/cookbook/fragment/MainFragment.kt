package com.example.gp62.cookbook.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.adpater.MainSerchAdapter
import com.example.gp62.cookbook.bean.DataResult
import com.example.gp62.cookbook.database.DBMangerSql
import com.example.gp62.cookbook.present.MainPresent
import com.example.gp62.cookbook.utlis.SpaceItemDecoration
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

    private var listData: ArrayList<DataResult.ResultBeanX.ResultBean.ListBean>? = null
    /**
     * 专门做逻辑处理的present层
     */
    private var mainPre :MainPresent?=null
    /**
     * 观察者
     */
    private var subsicption : Subscription?=null
    /**
     * 搜索名字
     */
    private var serachName :String?=null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.main_fragment, container, false)
        recycler = view.findViewById(R.id.recylerview) as RecyclerView
        edtext = view.findViewById(R.id.edtix) as EditText
        btn = view.findViewById(R.id.btn_seach) as Button
        btn?.setOnClickListener(onClick)
        recycler?.layoutManager = LinearLayoutManager(activity)
        recycler?.addItemDecoration(SpaceItemDecoration(30))
        adpater = MainSerchAdapter(activity, ArrayList())
        recycler?.adapter = adpater
        mainPre= MainPresent(activity,this@MainFragment)
        return view
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * 加载数据到适配器
     */
    fun getData(sera: ArrayList<DataResult.ResultBeanX.ResultBean.ListBean>) {
        if (listData == null) {
            listData = ArrayList<DataResult.ResultBeanX.ResultBean.ListBean>()
        }
        if (sera != null) {
            listData?.clear()
            listData?.addAll(sera)
        }
        if (listData!=null){
            adpater?.addData(listData as ArrayList<DataResult.ResultBeanX.ResultBean.ListBean>)
        }
    }

    /**
     * 点击事件
     */
    private val onClick = object : View.OnClickListener {
        override fun onClick(v: View?) {

            when (v?.id) {
                R.id.btn_seach ->{
                    serachName=edtext?.text.toString()
                    if (!TextUtils.isEmpty(serachName)){
                        subsicption= mainPre?.getDataMainSerch(edtext?.text.toString(),"20")
                        Thread(Runnable {
                            kotlin.run {
                                //查询当前搜索的自动是否数据库存在
                                var flag= DBMangerSql.getInstanse(activity)?.qurey(serachName as String)
                                if (flag as Boolean){
                                    //执行更新
                                    DBMangerSql.getInstanse(activity)?.addAndUpateDataBase("serchnum",
                                            arrayOf("1",serachName ?:""),"update")
                                }else{
                                    //执行插入
                                    DBMangerSql.getInstanse(activity)?.addAndUpateDataBase("serchnum",
                                            arrayOf(serachName,"1"),"insert")
                                }
                            }
                        }).start()
                    }else{
                        showToast("不能输入空的菜名哦！")
                    }
                }
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
    fun showToast(str :String){
        Toast.makeText(activity,str,Toast.LENGTH_SHORT).show()
    }

}
