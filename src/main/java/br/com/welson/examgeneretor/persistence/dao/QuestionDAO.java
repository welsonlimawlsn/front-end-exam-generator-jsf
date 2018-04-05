package br.com.welson.examgeneretor.persistence.dao;

import br.com.welson.examgeneretor.custom.CustomRestTemplate;
import br.com.welson.examgeneretor.persistence.model.Question;
import br.com.welson.examgeneretor.util.ApiUtil;
import br.com.welson.examgeneretor.util.JsonUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * @author Welson Teles on 3/27/2018
 */
public class QuestionDAO implements Serializable {

    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/question/list/{courseId}";
    private final String DELETE_OR_FIND_ONE_URL = ApiUtil.BASE_URL + "/professor/course/question/{id}";
    private final String CREATE_AND_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/question/";
    private final CustomRestTemplate restTemplate;
    private final JsonUtil jsonUtil;
    private final ParameterizedTypeReference<List<Question>> questionListTypeReference = new ParameterizedTypeReference<List<Question>>(){};

    @Inject
    public QuestionDAO(CustomRestTemplate restTemplate, JsonUtil jsonUtil) {
        this.restTemplate = restTemplate;
        this.jsonUtil = jsonUtil;
    }


    public List<Question> list(long courseId, String title) {
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_URL).queryParam("title", title).build();
        return restTemplate.exchange(url.toUriString(), HttpMethod.GET, jsonUtil.tokenizedHttpEntityHeader(), questionListTypeReference, courseId).getBody();
    }

    public Question findById(long id) {
        return restTemplate.exchange(DELETE_OR_FIND_ONE_URL, HttpMethod.GET, jsonUtil.tokenizedHttpEntityHeader(), Question.class, id).getBody();
    }


    public Question update(Question question) {
        return createOrUpdate(HttpMethod.PUT, question);
    }

    public Question create(Question question) {
        return createOrUpdate(HttpMethod.POST, question);
    }

    private Question createOrUpdate(HttpMethod httpMethod, Question question) {
        return restTemplate.exchange(CREATE_AND_UPDATE_URL, httpMethod, jsonUtil.tokenizedHttpEntityHeader(question), Question.class).getBody();
    }

    public void delete(Long id){
        restTemplate.exchange(DELETE_OR_FIND_ONE_URL, HttpMethod.DELETE, jsonUtil.tokenizedHttpEntityHeader(), Question.class, id);
    }
}
