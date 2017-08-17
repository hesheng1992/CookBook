package com.example.gp62.cookbook.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gp62.cookbook.R

/**
 * Created by GP62 on 2017/8/16.
 */
class LeiXingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=layoutInflater.inflate(R.layout.fenlei_fragment,container,false)
        return view
    }

}