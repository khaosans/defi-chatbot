package org.sour.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3Config {

    @Bean
    public Web3j web3j() {
        // Replace with your Ethereum node URL
        return Web3j.build(new HttpService("https://your.ethereum.node.url"));
    }
}