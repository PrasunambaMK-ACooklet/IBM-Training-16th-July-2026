package com.bank.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * TOPIC: HTTP Client API
 *
 * java.net.http.HttpClient (introduced as standard in Java 11) replaced the
 * old, clunky HttpURLConnection. It supports HTTP/1.1 and HTTP/2, blocking
 * and asynchronous calls, and a fluent builder API. Here it's used to
 * simulate a bank service calling an external exchange-rate API before
 * processing a foreign currency transaction.
 */
public class HttpClientDemo {

    public static void main(String[] args) {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.frankfurter.app/latest?from=USD&to=INR"))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .header("Accept", "application/json")
                .build();

        // ---- Synchronous (blocking) call ----
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Sync call status code: " + response.statusCode());
            System.out.println("Sync call body: " + response.body());
        } catch (IOException | InterruptedException e) {
            System.out.println("Sync call failed (e.g. no network access in this environment): " + e.getMessage());
        }

        // ---- Asynchronous (non-blocking) call using CompletableFuture ----
        CompletableFuture<String> asyncCall = client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> "Async call failed: " + ex.getMessage());

        System.out.println("Main thread keeps doing other banking work while the async call is in flight...");

        String asyncResult = asyncCall.join(); // wait for completion just to print the result here
        System.out.println("Async call result: " + asyncResult);
    }
}
