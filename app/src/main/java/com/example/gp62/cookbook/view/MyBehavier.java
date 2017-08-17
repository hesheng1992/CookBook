package com.example.gp62.cookbook.view;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;

/**
 * Created by GP62 on 2017/8/13.
 */

/***
 * 自定义Behavior  参考AppBarLayout与RecylerView的联动
 * 当recyler向上或向下滚动时，AppBar做出相应。
 *  所以可以简单理解就是，AppBar是child, 而recyler就是dependency（从属）。
 *
 *  简单的说，这个Behavior就是为谁制定的，要作用于谁身上，也就是随着被改变着。而应用这个Behavior则将放在发生
 *  动作者身上。
 *      比如这里APPBar和Recylerview的关系   为AppBar制定的，然后放在Recycler身上。
 *
 */
public class MyBehavier extends CoordinatorLayout.Behavior {

    /**
     * 返回true就表示child 愿意从属于指定的view。建立关系
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //执行判断逻辑
        return super.layoutDependsOn(parent, child, dependency);
    }

    /**
     *当dependency发生改变时（位置、宽高等），执行这个函数
     * 返回true表示child的位置或者是宽高要发生改变，否则就返回false
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //child要执行的具体改变view的动作

        return super.onDependentViewChanged(parent, child, dependency);
    }
}
