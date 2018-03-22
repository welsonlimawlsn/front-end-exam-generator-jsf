package br.com.welson.examgeneretor.bean.course;

import br.com.welson.examgeneretor.annotation.ExceptionHandler;
import br.com.welson.examgeneretor.persistence.dao.CourseDAO;
import br.com.welson.examgeneretor.persistence.model.Course;
import org.omnifaces.util.Messages;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Welson Teles on 3/21/2018
 */
@Named
@ViewScoped
public class CourseCreateBean implements Serializable {
    private final CourseDAO courseDAO;
    private Course course = new Course();

    @Inject
    public CourseCreateBean(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @ExceptionHandler
    public String save() {
        courseDAO.create(course);
        Messages.create("The course {0} was successfully added.", course.getName()).flash().add();
        return "list?faces-redirect=true";
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
