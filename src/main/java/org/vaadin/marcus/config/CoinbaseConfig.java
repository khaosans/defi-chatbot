package org.vaadin.marcus.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vaadin.marcus.service.CoinbaseService;

@Configuration
public class CoinbaseConfig {

    @Value("${coinbase.api.baseUrl}")
    private String baseUrl;

    @Value("${coinbase.api.key}")
    private String apiKey;

    @Value("${coinbase.api.secret}")
    private String apiSecret;

    @Value("${coinbase.api.passphrase}")
    private String apiPassphrase;

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(OptionalTypeAdapter.FACTORY)
                .create();
    }

    @Bean
    public CoinbaseService coinbaseService(OkHttpClient okHttpClient, Gson gson) {
        return new CoinbaseService(okHttpClient, gson, baseUrl, apiKey, apiSecret, apiPassphrase);
    }
}