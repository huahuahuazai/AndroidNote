package com.coolweather.app.util;

//这是一个HttpCallbackListener接口
//供HttpUtil类使用本接口回调服务返回结果
public interface HttpCallbackListener {
	void onFinish(String response);
	void onError(Exception e);

}
