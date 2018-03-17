package br.com.welson.examgeneretor.persistence.dao;

import br.com.welson.examgeneretor.persistence.model.Token;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.Serializable;

import static org.springframework.http.HttpMethod.POST;

public class LoginDAO implements Serializable {

    private final String BASE_URL = "http://localhost:8085/login";
    private final RestTemplate restTemplate;

    @Inject
    public LoginDAO(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Token loginReturningToken(String username, String password) {
        String loginJson = new StringBuilder(300)
                .append("{\"username\":")
                .append(addQuotes(username))
                .append(",\"password\":")
                .append(addQuotes(password)).append("}").toString();
        ResponseEntity<Token> tokenExchange = restTemplate.exchange(BASE_URL, POST, new HttpEntity<>(loginJson, createJsonHeader()), Token.class);
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
}
