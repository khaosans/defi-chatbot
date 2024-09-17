package org.vaadin.marcus.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CoinbaseService {

    private final OkHttpClient httpClient;
    private final Gson gson;
    private final String baseUrl;
    private final String apiKey;
    private final String apiSecret;
    private final String apiPassphrase;

    public CoinbaseService(OkHttpClient httpClient, Gson gson,
                           @Value("${coinbase.api.baseUrl}") String baseUrl,
                           @Value("${coinbase.api.key}") String apiKey,
                           @Value("${coinbase.api.secret}") String apiSecret,
                           @Value("${coinbase.api.passphrase}") String apiPassphrase) {
        this.httpClient = httpClient;
        this.gson = gson;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.apiPassphrase = apiPassphrase;
    }

    public Double getPortfolioValue() {
        try {
            String response = makeRequest("GET", "/accounts");
            JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
            JsonArray accounts = jsonResponse.getAsJsonArray("data");
            return StreamSupport.stream(accounts.spliterator(), false)
                    .mapToDouble(account -> account.getAsJsonObject().get("balance").getAsDouble())
                    .sum();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching portfolio value", e);
        }
    }

    public List<AccountDetails> getAccountDetails() {
        try {
            String response = makeRequest("GET", "/accounts");
            JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
            JsonArray accounts = jsonResponse.getAsJsonArray("data");
            return StreamSupport.stream(accounts.spliterator(), false)
                    .map(account -> gson.fromJson(account, AccountDetails.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return List.of();
        }
    }

    public Double getCurrentPrice(String currency) {
        try {
            String response = makeRequest("GET", "/products/" + currency + "-USD/ticker");
            JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
            return jsonResponse.get("price").getAsDouble();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching current price for " + currency, e);
        }
    }

    private String makeRequest(String method, String endpoint) throws IOException {
        long timestamp = System.currentTimeMillis() / 1000;
        String requestPath = endpoint;
        String body = ""; // For GET requests, body is empty

        String signature = generateSignature(timestamp, method, requestPath, body);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, body);
        Request request = new Request.Builder()
                .url(baseUrl + endpoint)
                .method(method, method.equals("GET") ? null : requestBody)
                .addHeader("CB-ACCESS-KEY", apiKey)
                .addHeader("CB-ACCESS-SIGN", signature)
                .addHeader("CB-ACCESS-TIMESTAMP", String.valueOf(timestamp))
                .addHeader("CB-ACCESS-PASSPHRASE", apiPassphrase)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    private String generateSignature(long timestamp, String method, String requestPath, String body) {
        try {
            String prehash = timestamp + method.toUpperCase() + requestPath + body;
            byte[] secretDecoded = Base64.getDecoder().decode(apiSecret);
            SecretKeySpec keyspec = new SecretKeySpec(secretDecoded, "HmacSHA256");
            Mac sha256 = Mac.getInstance("HmacSHA256");
            sha256.init(keyspec);
            return Base64.getEncoder().encodeToString(sha256.doFinal(prehash.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Error generating signature", e);
        }
    }
}