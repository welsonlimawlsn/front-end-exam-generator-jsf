package br.com.welson.examgeneretor.bean.course;

import br.com.welson.examgeneretor.persistence.dao.CourseDAO;
import br.com.welson.examgeneretor.persistence.model.Course;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Welson Teles on 3/20/2018
 */
@Named
@ViewScoped
public class CourseListBean implements Serializable {

    private final CourseDAO courseDAO;
    private List<Course> courseList;
    private String name = "";

    @Inject
    public CourseListBean(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @PostConstruct
    public void init() {
        search();
    }

    public void search() {
        courseList = courseDAO.list(name);
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
