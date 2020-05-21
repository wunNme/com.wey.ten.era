package com.wey.ten.era.application.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author ful
 * @Description:
 * @Date in 16:11 2018/10/17
 * @Modify By:
 */
public class HttpUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String,Object> httpGet(String url, Map<String, String> parameters) {
        String result="";
        BufferedReader in = null;// 读取响应输入流
        StringBuffer sb = new StringBuffer();// 存储参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            if(parameters.size()==1){
                for(String name:parameters.keySet()){
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8"));
                }
                params=sb.toString();
            }else{
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            String full_url = url + "?" + params;
            System.out.println(full_url);
            // 创建URL对象
            URL connURL = new URL(full_url);
            // 打开URL连接
            HttpURLConnection httpConn = (HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 建立实际的连接
            httpConn.connect();
            // 响应头部获取
            Map<String, List<String>> headers = httpConn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : headers.keySet()) {
                System.out.println(key + "\t：\t" + headers.get(key));
            }
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn
                    .getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result +=line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Map<String,Object> result_map = (Map<String,Object>)JSONObject.fromObject(result);
        return result_map ;
    }



    /**
     * http请求工具类，post请求
     *
     * @param url    url
     * @param params 参数值 仅支持String和list两种类型
     * @return
     * @throws Exception
     */
    public static Map<String,Object> httpPost(String url, Map<String, String> params) {
        DefaultHttpClient defaultHttpClient = null;
        BufferedReader bufferedReader = null;
        try {
            defaultHttpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
            if (params != null) {
                //转换为json格式并打印，不需要的你们可以不要
                String jsonParams = objectMapper.writeValueAsString(params);

                HttpEntity httpEntity = new StringEntity(jsonParams, "utf-8");
                httpPost.setEntity(httpEntity);
            }
            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                String errorLog="请求失败，errorCode:"+httpResponse.getStatusLine().getStatusCode();


            }
            //读取返回信息
            String output;
            bufferedReader=new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(),"utf-8"));
            StringBuilder stringBuilder=new StringBuilder();
            while ((output=bufferedReader.readLine())!=null){
                stringBuilder.append(output);
            }
            Map<String,Object> result_map = (Map<String,Object>)JSONObject.fromObject(stringBuilder.toString());
            return result_map;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
          return null;
        }catch (IOException e){
            e.printStackTrace();
         return null;
        }finally {
            if(defaultHttpClient!=null)
                defaultHttpClient.getConnectionManager().shutdown();
            if(bufferedReader!=null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
