package com.coolweather.app.util;

//����һ��HttpCallbackListener�ӿ�
//��HttpUtil��ʹ�ñ��ӿڻص����񷵻ؽ��
public interface HttpCallbackListener {
	void onFinish(String response);
	void onError(Exception e);

}
