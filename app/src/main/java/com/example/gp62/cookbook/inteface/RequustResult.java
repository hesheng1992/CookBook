package com.example.gp62.cookbook.inteface;

/**
 * Created by GP62 on 2017/8/17.
 */

public interface RequustResult <T>{
    //成功
    void onSucess(T t);
    //失败
    void onFiled(String e);
}
