package br.com.welson.examgeneretor.persistence.dao;

import br.com.welson.examgeneretor.custom.CustomRestTemplate;
import br.com.welson.examgeneretor.persistence.model.Choice;
import br.com.welson.examgeneretor.util.ApiUtil;
import br.com.welson.examgeneretor.util.JsonUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * @author Welson Teles on 4/2/2018
 */
public class ChoiceDAO implements Serializable {

    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/question/choice/list/{questionId}";
    private final String DELETE_OR_FIND_URL = ApiUtil.BASE_URL + "/professor/course/question/choice/{id}";
    private final String CREATE_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/question/choice";
    private final CustomRestTemplate restTemplate;
    private final JsonUtil jsonUtil;
    private final ParameterizedTypeReference<List<Choice>> choiceListTypeReference = new ParameterizedTypeReference<List<Choice>>() {
    };

    @Inject
    public ChoiceDAO(CustomRestTemplate restTemplate, JsonUtil jsonUtil) {
        this.restTemplate = restTemplate;
        this.jsonUtil = jsonUtil;
    }

    public List<Choice> list(long questionId) {
        ResponseEntity<List<Choice>> exchange = restTemplate.exchange(LIST_URL, HttpMethod.GET, jsonUtil.tokenizedHttpEntityHeader(), choiceListTypeReference, questionId);
        return exchange.getBody();
    }

    public Choice create(Choice choice) {
        return createOrUpdate(HttpMethod.POST, choice);
    }

    public Choice update(Choice choice) {
        return createOrUpdate(HttpMethod.PUT, choice);
    }

    public Choice delete(long id) {
        return restTemplate.exchange(DELETE_OR_FIND_URL, HttpMethod.DELETE, jsonUtil.tokenizedHttpEntityHeader(), Choice.class, id).getBody();
    }

    private Choice createOrUpdate(HttpMethod httpMethod, Choice choice) {
        return restTemplate.exchange(CREATE_UPDATE_URL, httpMethod, jsonUtil.tokenizedHttpEntityHeader(choice), Choice.class).getBody();
    }
}
