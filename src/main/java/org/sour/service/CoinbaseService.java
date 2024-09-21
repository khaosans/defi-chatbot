package org.sour.service;

import java.io.IOException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class CoinbaseService {
    private static final Logger logger = LoggerFactory.getLogger(CoinbaseService.class);

    private final OkHttpClient httpClient;
    private final Gson gson;
    private final String baseUrl;
    private final String apiKey;
    private final String apiSecret;
    private final String apiPassphrase;

    public CoinbaseService(OkHttpClient httpClient, Gson gson, @Value("${coinbase.api.baseUrl}") String baseUrl, 
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

    public AccountDetails getAccounts() throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String method = "GET";
        String requestPath = "/accounts";
        String prehash = timestamp + method + requestPath;
        String signature = generateSignature(prehash, apiSecret);

        Request request = new Request.Builder()
                .url(baseUrl + requestPath)
                .addHeader("CB-ACCESS-KEY", apiKey)
                .addHeader("CB-ACCESS-SIGN", signature)
                .addHeader("CB-ACCESS-TIMESTAMP", timestamp)
                .addHeader("CB-ACCESS-PASSPHRASE", apiPassphrase)
                .addHeader("Content-Type", "application/json")
                .build();

        logger.debug("Sending request to Coinbase API: {}", request.url());
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                logger.error("Coinbase API request failed. Status: {}, Body: {}", response.code(), response.body().string());
                throw new IOException("Unexpected code " + response);
            }
            String responseBody = response.body().string();
            logger.debug("Coinbase API response: {}", responseBody);
            AccountDetails account = gson.fromJson(responseBody, AccountDetails.class);
            logger.info("Retrieved account from Coinbase");
            return account;
        }
    }

    private String generateSignature(String prehash, String secret) throws IOException {
        try {
            byte[] secretDecoded = Base64.getDecoder().decode(secret);
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secretDecoded, "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(prehash.getBytes()));
        } catch (Exception e) {
            logger.error("Error generating signature", e);
            throw new IOException("Error generating signature", e);
        }
    }
}