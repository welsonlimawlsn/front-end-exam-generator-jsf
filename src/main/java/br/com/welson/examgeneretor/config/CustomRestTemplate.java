package br.com.welson.examgeneretor.config;

import org.springframework.web.client.RestTemplate;

/**
 * Wihtout this class the @Inject for RestTemplate will not work
 */
public class CustomRestTemplate extends RestTemplate {
}
