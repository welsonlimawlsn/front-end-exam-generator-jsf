package br.com.welson.examgeneretor.custom;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class CustomRestTemplate extends RestTemplate {

    public CustomRestTemplate() {
        this.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }
}
