package com.coin.analys.backend.util;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;

public class HttpRequest {

    public static void get(String requestURL, HashMap<String, String> headers, String param){
        CloseableHttpClient request = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpGet httpGet = new HttpGet(requestURL + "?" + param);

        try{
            StringEntity params = new StringEntity(param);

            headers.forEach(httpGet::addHeader);

            response = request.execute(httpGet);

            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("응답 내용: " + responseBody);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (response != null) {
                    response.close();
                }
                request.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void post(String requestURL, HashMap<String, String> headers, String requestBody){
        CloseableHttpClient request = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(requestURL);

        try{

            StringEntity entity = new StringEntity(requestBody);

            httpPost.setEntity(entity);
            response = request.execute(httpPost);

            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("응답 내용: " + responseBody);

        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            try {
                if (response != null) {
                    response.close();
                }
                request.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
