package br.com.welson.examgeneretor.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.faces.annotation.RequestCookieMap;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import java.util.Map;

import static br.com.welson.examgeneretor.custom.CustomURLEncoderDecoder.decodeUTF8;

/**
 * @author Welson Teles on 3/19/2018
 */
public class JsonUtil {

    @Inject
    @RequestCookieMap
    private Map<String, Object> cookieMap;

    public HttpHeaders createJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    public HttpHeaders createTokenizedHeader() {
        HttpHeaders headers = createJsonHeader();
        Cookie token = (Cookie) cookieMap.get("token");
        headers.add("Authorization", decodeUTF8(token.getValue()));
        return headers;
    }
}
