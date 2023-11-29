package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class wikiArticle {
    public String summaryExtract;
    public String normalizedTitle;

    private static String targetURL = "https://en.wikipedia.org/api/rest_v1/page/random/summary";

    private String rawApiJson;
    //DO NOT COMMIT should be pulled from file
    private String authToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzYmQxOGU2N2EzZTM2NDVkYmQ1YWFjNjdhZDQ3MGM5ZSIsImp0aSI6IjIzNjU1OTU4YmIxZmFiMTllMDZjYWQxNTBjZGZlY2FlOGFkZjMzYzkyM2UyY2FiNmYwZDUyYWYyM2VmZGI2ZjhhZTNiZWI5MjM4ZDU2OTA2IiwiaWF0IjoxNzAxMTQwNjkyLjEzODkyMSwibmJmIjoxNzAxMTQwNjkyLjEzODkyNSwiZXhwIjozMzI1ODA0OTQ5Mi4xMzY2Niwic3ViIjoiNzQzODI3MjQiLCJpc3MiOiJodHRwczovL21ldGEud2lraW1lZGlhLm9yZyIsInJhdGVsaW1pdCI6eyJyZXF1ZXN0c19wZXJfdW5pdCI6NTAwMCwidW5pdCI6IkhPVVIifSwic2NvcGVzIjpbImJhc2ljIl19.uZVTw24KuAo38Hco5Kq621oUcr3DY3PpMMxguUysGKBxRt8yhYpwrEoRmyu51hy1C9Qikg2GT3-5Wy0zKQQx-h0lX0ZkX5hxRPFBZ7iWEFCLPhzKR0hD64o-ZcduskQbnkpFG_vcKSSc68vLmFo7ChNV--rmhI8G6ibuhXovjhlubzRTW0Nu9f2l4jE6VwdGKy2xmWWvymt1YcfV66T2NGNqpCwmvqP0UvenMkFciO14CIWD9suJzTD_4973iKRBPce63EFcud5mo980bNC90v8mWZT-1NKIey5IL9rXEN7KG-2QIZd28gQtRuOkx46dqrUnVwUmHYTW93dcJuNcFdh9rYNm8Or-lXfgrww8F8mjSeSymN9pgbAEjsuNwFWG56iJl78st8ifUUtAWffrF9gvxewVR89yTDAIr8FkhS6_-DOzdD9l_zFb5wd9ZHEhbvPxn2fwsHgjpmtlx07i8lzMRFNjTqd85LRL9yKBxNKmVp-GtWoKyFWXzTVV057_GpnfkYdUw7kkGCUs5aXdxHESSSRrpoNd87xjKutLM8ZBivy5gmGPo61M-C-6TVVYmj5dijGNt_RosjbtgZwQ_oijC1P_rjzuRNV6nB1H1_ymvX7ttw2Mx_1DqGy9Yr92O1aphuihE8lnhtjLb2h8syRkPVavye-rjFaHPvOAHZU";

    wikiArticle() {
        getRandomArticle();
    }

    private boolean getRandomArticle() {
        try {
            // Build request object
            HttpRequest articleRequest = HttpRequest.newBuilder()
            .uri(new URI(targetURL))
            .header("Authorization", "Bearer " + authToken)
            .header("accept", "application/problem+json")
            .GET()
            .build();

            // Synchronously send http GET request (and build to accept redirects)
            HttpResponse<String> response = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build()
            .send(articleRequest, BodyHandlers.ofString());

            if(response.statusCode() == 200 || response.statusCode() == 303) {
                // convert json to string & save to class instance
                parseArticleJson(response.body());
                // System.out.println(response.body());
            }
            else {
                System.out.println("Bad response from server: " + response.statusCode());
                return false;
            }

            return true;
        } catch (Exception e) {
            System.out.println("Failed to retrieve article with error: " + e);
            return false;
        }
    }

    private boolean parseArticleJson(String articleBodyString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(articleBodyString);

            this.normalizedTitle = json.at("/title").toString().replace("\"", "");
            this.summaryExtract = json.at("/extract").toString().replace("\"", "");;

            // System.out.println(json.at("/title"));
            // System.out.println(json.at("/extract"));
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }

        return true;
    }
}

