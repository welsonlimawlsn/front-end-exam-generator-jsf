package br.com.welson.examgeneretor.persistence.dao;

import br.com.welson.examgeneretor.custom.CustomRestTemplate;
import br.com.welson.examgeneretor.persistence.model.Assignment;
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
public class AssignmentDAO implements Serializable {

    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/assignment/list/{courseId}";
    private final String DELETE_OR_FIND_ONE_URL = ApiUtil.BASE_URL + "/professor/course/assignment/{id}";
    private final String CREATE_AND_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/assignment/";
    private final CustomRestTemplate restTemplate;
    private final JsonUtil jsonUtil;
    private final ParameterizedTypeReference<List<Assignment>> assignmentListTypeReference = new ParameterizedTypeReference<List<Assignment>>(){};

    @Inject
    public AssignmentDAO(CustomRestTemplate restTemplate, JsonUtil jsonUtil) {
        this.restTemplate = restTemplate;
        this.jsonUtil = jsonUtil;
    }


    public List<Assignment> list(long courseId, String title) {
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_URL).queryParam("title", title).build();
        return restTemplate.exchange(url.toUriString(), HttpMethod.GET, jsonUtil.tokenizedHttpEntityHeader(), assignmentListTypeReference, courseId).getBody();
    }

    public Assignment findById(long id) {
        return restTemplate.exchange(DELETE_OR_FIND_ONE_URL, HttpMethod.GET, jsonUtil.tokenizedHttpEntityHeader(), Assignment.class, id).getBody();
    }


    public Assignment update(Assignment assignment) {
        return createOrUpdate(HttpMethod.PUT, assignment);
    }

    public Assignment create(Assignment assignment) {
        return createOrUpdate(HttpMethod.POST, assignment);
    }

    private Assignment createOrUpdate(HttpMethod httpMethod, Assignment assignment) {
        return restTemplate.exchange(CREATE_AND_UPDATE_URL, httpMethod, jsonUtil.tokenizedHttpEntityHeader(assignment), Assignment.class).getBody();
    }

    public void delete(Long id){
        restTemplate.exchange(DELETE_OR_FIND_ONE_URL, HttpMethod.DELETE, jsonUtil.tokenizedHttpEntityHeader(), Assignment.class, id);
    }
}
