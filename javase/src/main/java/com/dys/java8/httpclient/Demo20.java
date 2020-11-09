package com.dys.java8.httpclient;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Demo20 {
    public static void main(String[] args) throws Exception {

               URI uri = URI.create("https://www.google.com");

        var httpRequest = HttpRequest.newBuilder().timeout(Duration.ofMillis(3000))
                .header("key1", "v1")
                .header("key2", "v2")
                .uri(uri).build();

        HttpClient httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .proxy(ProxySelector.of(new InetSocketAddress("127.0.0.1", 52309)))
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
    }
}
