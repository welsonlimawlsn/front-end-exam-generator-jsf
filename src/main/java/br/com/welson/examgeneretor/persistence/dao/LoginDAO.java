package br.com.welson.examgeneretor.persistence.dao;

import br.com.welson.examgeneretor.annotation.ExceptionHandler;
import br.com.welson.examgeneretor.custom.CustomObjectMapper;
import br.com.welson.examgeneretor.custom.CustomRestTemplate;
import br.com.welson.examgeneretor.persistence.model.support.ErrorDetail;
import br.com.welson.examgeneretor.persistence.model.support.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;

import static org.springframework.http.HttpMethod.POST;

public class LoginDAO implements Serializable {

    private final String BASE_URL = "http://localhost:8085/login";
    private final CustomRestTemplate restTemplate;

    @Inject
    public LoginDAO(CustomRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @ExceptionHandler
    public Token loginReturningToken(String username, String password) {
        ResponseEntity<Token> tokenExchange = restTemplate.exchange(BASE_URL, POST, new HttpEntity<>(createJsonForLogin(username, password), createJsonHeader()), Token.class);
        return tokenExchange.getBody();
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    private String addQuotes(String value) {
        return new StringBuilder(300).append("\"").append(value).append("\"").toString();
    }

    private HttpHeaders createJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    private String createJsonForLogin(String username, String password) {
        return new StringBuilder(300)
                .append("{\"username\":")
                .append(addQuotes(username))
                .append(",\"password\":")
                .append(addQuotes(password)).append("}").toString();
    }
}
