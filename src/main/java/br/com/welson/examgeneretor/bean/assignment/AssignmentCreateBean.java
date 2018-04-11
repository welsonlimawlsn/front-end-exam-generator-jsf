package br.com.welson.examgeneretor.bean.assignment;

import br.com.welson.examgeneretor.annotation.ExceptionHandler;
import br.com.welson.examgeneretor.persistence.dao.CourseDAO;
import br.com.welson.examgeneretor.persistence.dao.AssignmentDAO;
import br.com.welson.examgeneretor.persistence.model.Course;
import br.com.welson.examgeneretor.persistence.model.Assignment;
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
public class AssignmentCreateBean implements Serializable {
    private final CourseDAO courseDAO;
    private final AssignmentDAO assignmentDAO;
    private Course course;
    private Assignment assignment = new Assignment();
    private long courseId;

    @Inject
    public AssignmentCreateBean(CourseDAO courseDAO, AssignmentDAO assignmentDAO) {
        this.courseDAO = courseDAO;
        this.assignmentDAO = assignmentDAO;
    }

    @ExceptionHandler
    public void init() {
        course = courseDAO.findById(courseId);
    }

    @ExceptionHandler
    public void save() {
        assignment.setCourse(course);
        assignmentDAO.create(assignment);
        Messages.addGlobalInfo("The assignment {0} was successfully added.", assignment.getTitle());
        assignment = new Assignment();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
