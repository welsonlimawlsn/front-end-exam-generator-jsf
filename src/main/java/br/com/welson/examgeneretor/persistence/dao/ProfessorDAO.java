package br.com.welson.examgeneretor.persistence.dao;

import br.com.welson.examgeneretor.annotation.ExceptionHandler;
import br.com.welson.examgeneretor.persistence.model.Professor;
import br.com.welson.examgeneretor.util.ApiUtil;
import br.com.welson.examgeneretor.util.JsonUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * @author Welson Teles on 3/19/2018
 */
public class ProfessorDAO implements Serializable {
    private final String BASE_URL = ApiUtil.BASE_URL + "/professor";
    private final JsonUtil jsonUtil;

    @Inject
    public ProfessorDAO(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
    }

    @ExceptionHandler
    public Professor getProfessorById(long id) {
        ResponseEntity<Professor> professorEntity = new RestTemplate().exchange(BASE_URL + "/1", HttpMethod.GET, new HttpEntity<>(jsonUtil.createTokenizedHeader()), Professor.class);
        Professor professor = professorEntity.getBody();
        return professor;
    }

}
