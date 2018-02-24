package com.haige.luban.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.haige.luban.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${wechat_login_url}") 
	private String WECHAT_LOGIN_URL;
	
	@Value("${appid}") 
	private String APP_ID;//小程序唯一标识
	
	@Value("${secret}") 
	private String SECRET;//小程序的 app secret
	
	@Value("authorization_code")
	private String GRANT_TYPE;//填写为 authorization_code,腾讯的规定，此处可认为是常量
	
	/**
	 * 微信小程序登录
	 * @param code
	 * @return
	 */
	public String login(String code) {
		//获取可关闭的 httpCilent
		CloseableHttpClient httpClient  = HttpClients.createDefault();
		logger.info(WECHAT_LOGIN_URL);
		//配置超时时间
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(60000).setConnectionRequestTimeout(60000)
                .setSocketTimeout(60000).setRedirectsEnabled(true).build();
        
        HttpPost httpPost = new HttpPost(WECHAT_LOGIN_URL);
        //设置超时时间
        httpPost.setConfig(requestConfig);
        //装配post请求参数
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>(); 
        list.add(new BasicNameValuePair("appid", APP_ID));  //小程序唯一标识
        list.add(new BasicNameValuePair("secret", SECRET)); //小程序的 app secret
        list.add(new BasicNameValuePair("js_code", code)); //登录时获取的 code
        list.add(new BasicNameValuePair("grant_type", GRANT_TYPE)); //填写为 authorization_code
        String strResult = "";
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8"); 
            //设置post求情参数
            httpPost.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if(httpResponse != null){ 
                StatusLine statusLine = httpResponse.getStatusLine();
                if (statusLine.getStatusCode() == 200) {
                    strResult = EntityUtils.toString(httpResponse.getEntity());
                } else if (statusLine.getStatusCode() == 400) {
                    strResult = "Error Response: " + statusLine.toString();
                } else if (statusLine.getStatusCode() == 500) {
                    strResult = "Error Response: " + statusLine.toString();
                } else {
                    strResult = "Error Response: " + statusLine.toString();
                } 
            }else{
                 
            }
            logger.info(strResult);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(httpClient != null){
                    httpClient.close(); //释放资源
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return strResult;
	}
}
