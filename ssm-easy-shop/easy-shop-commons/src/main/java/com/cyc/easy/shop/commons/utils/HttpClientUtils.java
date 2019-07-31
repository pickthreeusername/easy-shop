package com.cyc.easy.shop.commons.utils;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * httpclient工具类
 */
public class HttpClientUtils {
    private final static String METHOD_GET = "get";
    private final static String METHOD_POST = "post";

    private final static String KEY_USER_AGENT = "User-Agent";
    private final static String KEY_CONNECTION = "Connection";
    private final static String KEY_COOKIE = "Cookie";
    private final static String VALUE_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";
    private final static String VALUE_CONNECTION = "keep-alive";

    /**
     *  创建 get请求
     * @param url 请求地址
     * @return
     */
    public static String doGet(String url) {
        return createRequest(url, METHOD_GET, null);
    }

    /**
     * 创建 post 请求
     * @param url
     * @param params 请求参数
     * @return
     */
    public static String doPost(String url, BasicNameValuePair... params) {
        return createRequest(url, METHOD_POST, null, params);
    }

    /**
     * 创建http请求
     * @param url 地址
     * @param method 请求方式
     * @param cookie cookie信息
     * @param params post请求需要的参数
     * @return
     */
    private static  String createRequest(String url, String method,String cookie, BasicNameValuePair... params) {
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = null;
        HttpPost httpPost = null;

        //响应
        CloseableHttpResponse httpResponse = null;

        String data = null;
        try{
            //GET请求
            if (METHOD_GET.equals(method)) {
                // 创建 HttpGet 请求
                httpGet = new HttpGet(url);
                // 设置长连接
                httpGet.setHeader(KEY_CONNECTION, VALUE_CONNECTION);
                // 设置代理（模拟浏览器版本）
                httpGet.setHeader(KEY_USER_AGENT, VALUE_USER_AGENT);
                // 设置 Cookie
                httpGet.setHeader(KEY_COOKIE, cookie);

                httpResponse = httpClient.execute(httpGet);
            }
            //POST请求
            else if (METHOD_POST.equals(method)) {
                //创建httpPost请求
                httpPost = new HttpPost(url);
                // 设置长连接
                httpPost.setHeader(KEY_CONNECTION, VALUE_CONNECTION);
                // 设置代理（模拟浏览器版本）
                httpPost.setHeader(KEY_USER_AGENT, VALUE_USER_AGENT);
                // 设置 Cookie
                httpPost.setHeader(KEY_COOKIE, "");


                if (params != null && params.length > 0) {
                    List<BasicNameValuePair> paramsList = Arrays.asList(params);
                    //设置参数
                    httpPost.setEntity(new UrlEncodedFormEntity(paramsList,"UTF-8"));

                    httpResponse = httpClient.execute(httpPost);
                }

            }
            data = EntityUtils.toString(httpResponse.getEntity());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }


}
