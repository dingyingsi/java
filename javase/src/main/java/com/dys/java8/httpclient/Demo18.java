package com.dys.java8.httpclient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Demo18 {
    public static void main(String[] args) throws Exception {
        // get();
        post();
    }

    private static void get() throws Exception {
        String uriS = "https://www.baidu.com";
        URI uri = URI.create(uriS);

        var httpRequest = HttpRequest.newBuilder(uri).build();

        var httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
    }

    private static void post() throws Exception {
        String uriS = "https://newtestsso.xlgxapp.com/sso-oauth2/login";
        URI uri = URI.create(uriS);

        var httpRequest = HttpRequest.newBuilder(uri)
                .version(HttpClient.Version.HTTP_1_1)
                .header("Authorization", "Basic dWlwLWF1dGgtY2xpZW50LWlkOnVpcC1hdXRoLWNsaWVudC1zZWNyZXQ=")
                .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString("username=admin&password=Xl123456"))
                .timeout(Duration.ofMillis(1000))
                .build();

        var httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
    }
}
