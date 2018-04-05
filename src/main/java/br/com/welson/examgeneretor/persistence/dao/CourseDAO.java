package br.com.welson.examgeneretor.persistence.dao;

import br.com.welson.examgeneretor.custom.CustomRestTemplate;
import br.com.welson.examgeneretor.persistence.model.Course;
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
 * @author Welson Teles on 3/20/2018
 */
public class CourseDAO implements Serializable {

    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/list";
    private final String DELETE_OR_FIND_ONE_URL = ApiUtil.BASE_URL + "/professor/course/{id}";
    private final String CREATE_AND_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/";
    private final CustomRestTemplate restTemplate;
    private final JsonUtil jsonUtil;
    private final ParameterizedTypeReference<List<Course>> listCourseTypeReference = new ParameterizedTypeReference<List<Course>>() {
    };

    @Inject
    public CourseDAO(CustomRestTemplate restTemplate, JsonUtil jsonUtil) {
        this.restTemplate = restTemplate;
        this.jsonUtil = jsonUtil;
    }

    public List<Course> list(String name) {
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_URL).queryParam("name", name).build();
        return restTemplate.exchange(url.toUriString(), HttpMethod.GET, jsonUtil.tokenizedHttpEntityHeader(), listCourseTypeReference).getBody();
    }

    public Course findById(long id) {
        return restTemplate.exchange(DELETE_OR_FIND_ONE_URL, HttpMethod.GET, jsonUtil.tokenizedHttpEntityHeader(), Course.class, id).getBody();
    }


    public Course update(Course course) {
        return createOrUpdate(HttpMethod.PUT, course);
    }

    public Course create(Course course) {
        return createOrUpdate(HttpMethod.POST, course);
    }

    private Course createOrUpdate(HttpMethod httpMethod, Course course) {
        return restTemplate.exchange(CREATE_AND_UPDATE_URL, httpMethod, jsonUtil.tokenizedHttpEntityHeader(course), Course.class).getBody();
    }

    public void delete(Long id) {
        restTemplate.exchange(DELETE_OR_FIND_ONE_URL, HttpMethod.DELETE, jsonUtil.tokenizedHttpEntityHeader(), Course.class, id);
    }
}
