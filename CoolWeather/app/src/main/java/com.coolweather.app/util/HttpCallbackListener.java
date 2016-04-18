package com.coolweather.app.util;

/**
 * Created by jixiaoyong on 2016/4/18.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}