package br.com.welson.examgeneretor.persistence.dao;

import br.com.welson.examgeneretor.annotation.ExceptionHandler;
import br.com.welson.examgeneretor.custom.CustomRestTemplate;
import br.com.welson.examgeneretor.persistence.model.support.Token;
import br.com.welson.examgeneretor.util.JsonUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.io.Serializable;

import static br.com.welson.examgeneretor.util.ApiUtil.LOGIN_URL;
import static org.springframework.http.HttpMethod.POST;

public class LoginDAO implements Serializable {

    private final CustomRestTemplate restTemplate;
    private final JsonUtil jsonUtil;

    @Inject
    public LoginDAO(CustomRestTemplate restTemplate, JsonUtil jsonUtil) {
        this.restTemplate = restTemplate;
        this.jsonUtil = jsonUtil;
    }

    @ExceptionHandler
    public Token loginReturningToken(String username, String password) {
        ResponseEntity<Token> tokenExchange = restTemplate.exchange(LOGIN_URL, POST, new HttpEntity<>(createJsonForLogin(username, password), jsonUtil.createJsonHeader()), Token.class);
        return tokenExchange.getBody();
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    private String addQuotes(String value) {
        return new StringBuilder(300).append("\"").append(value).append("\"").toString();
    }

    private String createJsonForLogin(String username, String password) {
        return new StringBuilder(300)
                .append("{\"username\":")
                .append(addQuotes(username))
                .append(",\"password\":")
                .append(addQuotes(password)).append("}").toString();
    }
}
