package com.main.CrediLink.itau.config;

import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;


public class CertificateConfig {

    @Value("${itau.ssl.keystore-path}")
    private String keystorePath;

    @Value("${itau.ssl.keystore-password}")
    private String keystorePassword;

    private SSLContext createSSLContext() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (InputStream is = new FileInputStream(keystorePath)) {
            keyStore.load(is, keystorePassword.toCharArray());
        }
        return SSLContexts.custom()
                .loadKeyMaterial(keyStore, keystorePassword.toCharArray())
                .loadTrustMaterial(null, (chain, authType) -> true)
                .build();
    }

    @Bean
    public CloseableHttpClient itauFeignHttpClient() throws Exception {
        SSLContext sslContext = createSSLContext();
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext);

        return HttpClients.custom()
                .setSSLSocketFactory(sslSocketFactory)
                .build();
    }

    @Bean
    public Client feignClient(CloseableHttpClient httpClient) {
        return new ApacheHttpClient(httpClient);
    }
}
