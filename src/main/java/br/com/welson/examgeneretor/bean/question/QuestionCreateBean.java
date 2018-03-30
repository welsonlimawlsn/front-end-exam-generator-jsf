package br.com.welson.examgeneretor.bean.question;

import br.com.welson.examgeneretor.annotation.ExceptionHandler;
import br.com.welson.examgeneretor.persistence.dao.CourseDAO;
import br.com.welson.examgeneretor.persistence.dao.QuestionDAO;
import br.com.welson.examgeneretor.persistence.model.Course;
import br.com.welson.examgeneretor.persistence.model.Question;
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
public class QuestionCreateBean implements Serializable {
    private final CourseDAO courseDAO;
    private final QuestionDAO questionDAO;
    private Course course;
    private Question question = new Question();
    private long courseId;

    @Inject
    public QuestionCreateBean(CourseDAO courseDAO, QuestionDAO questionDAO) {
        this.courseDAO = courseDAO;
        this.questionDAO = questionDAO;
    }

    @ExceptionHandler
    public void init() {
        course = courseDAO.findById(courseId);
    }

    @ExceptionHandler
    public void save() {
        question.setCourse(course);
        questionDAO.create(question);
        Messages.addGlobalInfo("The question {0} was successfully added.", question.getTitle());
        question = new Question();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
