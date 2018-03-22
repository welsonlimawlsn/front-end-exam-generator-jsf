package br.com.welson.examgeneretor.custom;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Without CustomRestTemplate class the @Inject for RestTemplate will not work
 */

public class CustomRestTemplate extends RestTemplate {

    public CustomRestTemplate() {
        this.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }
}
