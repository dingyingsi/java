package com.dys.java8.httpclient;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Demo19 {
    public static void main(String[] args) {
        URI uri = URI.create("https://www.baidu.com");
        var httpClient = HttpClient.newBuilder().build();
        var request =
                HttpRequest.newBuilder().timeout(Duration.ofMillis(3000))
                        .header("key1", "v1")
                        .header("key2", "v2")
                        .uri(uri).build();
        try {
            //CompletableFuture<String> result = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
            var result = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body);
            System.out.println(result.get());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
