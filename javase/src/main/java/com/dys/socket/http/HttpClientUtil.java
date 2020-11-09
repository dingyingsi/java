package com.dys.socket.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    private void get() throws Exception {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        String url = "http://localhost:8080/Demo/user/auth";
        HttpPost post = new HttpPost(url);

        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("memberid", "4"));
        formparams.add(new BasicNameValuePair("image", "resources/upload/images/916970d6-1a9a-496a-8217-9964940a10f7.jpg,resources/upload/images/aec5925f-afa0-4ef9-8fbc-c27d349a6bd4.jpg"));
        formparams.add(new BasicNameValuePair("type", "3"));

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        post.setEntity(entity);

        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(post);

        if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(
                    closeableHttpResponse.getEntity(), "UTF-8");
            System.out.println(result);
        }
        closeableHttpResponse.close();
        closeableHttpClient.close();
    }

    private void post() throws Exception {
        Map<String, String> member = new HashMap<>();


        String url = "http://localhost:8080/Demo/user/modifyUserInfo";

        ObjectMapper mapper = new ObjectMapper();
        String memberJson = mapper.writeValueAsString(member);

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        StringEntity entity = new StringEntity(memberJson, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);

        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
        if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            System.out.println(result);
        }
        closeableHttpResponse.close();
        closeableHttpClient.close();
    }

    private void file() throws Exception {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        File file1 = new File(
                "E:/carimage/big/4028b2b64c0d9715014c2c1cb88f2168_big_0.jpg");
        File file2 = new File(
                "E:/carimage/big/4028b2b64c0d9715014c26b482e419f7_big_0.jpg");
        HttpPost post = new HttpPost("http://127.0.0.1:8080/Demo/user/uploadFiles");

        FileBody fileBody1 = new FileBody(file1);
        FileBody fileBody2 = new FileBody(file2);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addPart("image1", fileBody1);
        builder.addPart("image2", fileBody2);
        HttpEntity entity = builder.build();

        post.setEntity(entity);

        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(post);

        if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            System.out.println(result);
        }
        closeableHttpResponse.close();
        closeableHttpClient.close();
    }
}
