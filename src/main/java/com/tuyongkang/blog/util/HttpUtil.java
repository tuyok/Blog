package com.tuyongkang.blog.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用于发送http请求的工具
 */
public class HttpUtil {

    public static void main(String[] args) throws Exception {
        /*JSONObject result = getJSONObjectByGet("http://api.gpsspg.com/convert/coord/?oid=7719&key=65C4CCAE69A25389111FC675712757F1&from=2&to=0&latlng=22.543344,113.904589");
        System.out.println("返回的结果："+result);*/

/*        UserLocationInVo userLocationInVo = new UserLocationInVo();
        userLocationInVo.setVehiclePhone("18566285042");
        String url = "http://10.9.160.25:8988/ecall-tsp-consumer/ecallSmartAndTsp/userInfoBySim.json";
//        String url = "http://10.9.32.143:8080/ecall-tsp-consumer/ecallSmartAndTsp/userInfoBySim.json";
        JSONObject result = getJsonObjectByPostJson(url,JSON.toJSONString(userLocationInVo));
        System.out.println("返回的结果："+result);*/

    }

    public static JSONObject getJsonObjectByPostJson(String url, String jsonStr) {
        //创建httpClient连接
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject result = null;
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity stringEntity = new StringEntity(jsonStr);
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");//发送json数据需要设置contentType
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"), 10 * 1024);
                        StringBuilder strBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            strBuilder.append(line);
                        }
                        // 用fastjson的JSON将返回json字符串转为json对象
                        System.out.println(strBuilder.toString()+"=======");
                        result = JSON.parseObject(strBuilder.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (reader != null) {
                            try {
                                //关闭流
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * Post方式 得到JSONObject
     * @param paramsHashMap post参数
     * @param url
     * @param encoding 编码utf-8
     * @return
     */
    public static JSONObject getJSONObjectByPost(Map<String, String> paramsHashMap, String url, String encoding) {
        //创建httpClient连接
        CloseableHttpClient httpClient = HttpClients.createDefault();

        JSONObject result = null;
        List<NameValuePair> nameValuePairArrayList = new ArrayList<>();
        // 将传过来的参数添加到List<NameValuePair>中
        if (paramsHashMap != null && !paramsHashMap.isEmpty()) {
            //遍历map
            for (Map.Entry<String, String> entry : paramsHashMap.entrySet()) {
                nameValuePairArrayList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        UrlEncodedFormEntity entity;
        try {
            // 利用List<NameValuePair>生成Post请求的实体数据
            // UrlEncodedFormEntity 把输入数据编码成合适的内容
            entity = new UrlEncodedFormEntity(nameValuePairArrayList, encoding);
            HttpPost httpPost = new HttpPost(url);
            // 为HttpPost设置实体数据
            httpPost.setEntity(entity);
            // HttpClient 发送Post请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // CloseableHttpResponse
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"), 10 * 1024);
                        StringBuilder strBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            strBuilder.append(line);
                        }
                        // 用fastjson的JSON将返回json字符串转为json对象
                        result = JSON.parseObject(strBuilder.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (reader != null) {
                            try {
                                //关闭流
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送get请求
     * @param url
     * @return
     */
    public static JSONObject getJSONObjectByGet(String url){
        JSONObject resultJsonObject=null;

        //创建httpClient连接
        CloseableHttpClient httpClient = HttpClients.createDefault();

        StringBuilder entityStringBuilder=new StringBuilder();
        //利用URL生成一个HttpGet请求
        HttpGet httpGet=new HttpGet(url);
        // HttpClient 发送Post请求
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse=httpClient.execute(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //得到httpResponse的状态响应码
        if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
            //得到httpResponse的实体数据
            HttpEntity httpEntity=httpResponse.getEntity();
            if (httpEntity!=null) {
                BufferedReader reader=null;
                try {
                    reader=new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"), 8*1024);
                    String line;
                    while ((line=reader.readLine())!=null) {
                        entityStringBuilder.append(line);
                    }
                    // 从HttpEntity中得到的json String数据转为json
                    String json=entityStringBuilder.toString();
                    resultJsonObject= JSON.parseObject(json);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            //关闭流
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return resultJsonObject;
    }

}

