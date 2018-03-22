package br.com.welson.examgeneretor.custom;

import org.springframework.core.ParameterizedTypeReference;

/**
 * @author Welson Teles on 3/20/2018
 */
public class CustomTypeReference<T> extends ParameterizedTypeReference<T> {

    public ParameterizedTypeReference<T> typeReference() {
        return new ParameterizedTypeReference<T>() {
        };
    }
}
