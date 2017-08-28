package com.example.gp62.cookbook.base;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.example.gp62.cookbook.annotion.BindViewID;


import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by GP62 on 2017/8/16.
 */

public class Text {
    /**
     * 反射获取activity上的指定注解
     * @param activity
     */

    public void getId(Activity activity){

        try {
            Class<?> cla=activity.getClass();
            Field[] declaredFields = cla.getDeclaredFields();
            for (Field field:
                    declaredFields) {
                if (field.isAnnotationPresent(BindViewID.class)){
                    BindViewID annotation = field.getAnnotation(BindViewID.class);
                    int id = annotation.getId();
                    activity.findViewById(id);
                    field.set(activity,activity.findViewById(id));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void getId(Fragment fragment){
        try {
            Class<?> cla=fragment.getClass();
            Field[] declaredFields = cla.getDeclaredFields();
            for (Field field:
                    declaredFields) {
                if (field.isAnnotationPresent(BindViewID.class)){
                    BindViewID annotation = field.getAnnotation(BindViewID.class);
                    int id = annotation.getId();
                        field.set(fragment,fragment.getActivity().findViewById(id));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TabLayout.OnTabSelectedListener on=new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };


    public void testThread(){

        //缓存线程池，当需要执行任务超过了线程池的长度，首先会去看有没有空闲的线程，如果没有空闲的线程，就会新建线程执行
        ExecutorService executorService = Executors.newCachedThreadPool();


        //android事务管理，系统自带的，可以不被杀死。设置他每隔多久执行一次。应用：android日历等提醒
//        JobInfo
//        JobScheduler
//        JobService
    }











}

