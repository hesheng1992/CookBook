package com.example.gp62.cookbook.http;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;

import com.example.gp62.cookbook.inteface.RequustResult;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Handler;

/**
 * Created by GP62 on 2017/8/17.
 */

public class HttpUtils{

    private static String BaseUrl="";

    private static Thread thread;

    private static RequustResult  requustResult;
    /**
     * 是否关闭请求连接
     */
    public static boolean closeRequest=false;
    /**
     * 请求方式
     */
    private static String requstMethod;

    private static Context contextco;



    private static android.os.Handler handler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    if (contextco!=null){
                        //回调结果
                        requustResult.onSucess( msg.obj);
                    }
                    break;
                case 1:
                    if (contextco!=null){
                        //错误回调
                        requustResult.onFiled(msg.obj.toString());
                    }
                    break;
            }

        }
    };

    /**
     * 执行网络请求
     */
    private static<T> void getDataRequestGet(final Context context, final String url, final Class<T> clas){
        if (!closeRequest&&context!=null){
            thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL burl=new URL(BaseUrl+url);
                        HttpURLConnection content = (HttpURLConnection) burl.openConnection();
                        content.setRequestMethod("GET");
                        InputStream inputStream = content.getInputStream();
                        BufferedInputStream bu=new BufferedInputStream(inputStream);
                        int len=0;
                        byte b[]=new byte[1024];
                        StringBuilder sb=new StringBuilder();
                        while ((len=bu.read(b))!=-1) {
                            String s=new String(b,0,len);
                            sb.append(s);
                        }
                        content.connect();
                        System.err.println("huoquwangc");
                        Gson gson=new Gson();
                        T o = gson.fromJson(sb.toString(), clas);
                        Message message=handler.obtainMessage();
                        message.obj=o;
                        message.what=0;
                        handler.sendMessage(message);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Message message=handler.obtainMessage();
                        message.obj=e;
                        message.what=1;
                        handler.sendMessage(message);
                    }
                }
            });
        }
        thread.start();
    }

    /**
     * 执行请求
     * @param url
     * @param reqestMethod
     */
    public static<T> void execte(Context context,final String url, final String reqestMethod, final Class<T> clas,
                                 RequustResult  requustResuf){
        closeRequest=false;
        contextco=context;
        getDataRequestGet(context,url,clas);
        requustResult=requustResuf;
        thread.start();
    }


    /**
     * 设置基础的url
     * @param baseurl
     */
    public static void setBaseUrl(String baseurl){
        BaseUrl=baseurl;
    }

    /**
     * 获取基础url
     * @return
     */
    public static String getBaseUrl(){
        if (TextUtils.isEmpty(BaseUrl)){
            return null;
        }
        return BaseUrl;
    }

    public static void closeConnction(){
        closeRequest=true;
    }

    /**
     * 构建请求参数
     */
    static class Buider{

    }

}
