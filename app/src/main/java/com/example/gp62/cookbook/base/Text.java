package com.example.gp62.cookbook.base;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.example.gp62.cookbook.annotion.BindViewID;


import java.lang.reflect.Field;


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


}

