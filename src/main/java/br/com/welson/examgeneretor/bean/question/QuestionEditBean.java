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
public class QuestionEditBean implements Serializable {
    private final QuestionDAO questionDAO;
    private Question question;
    private long questionId;

    @Inject
    public QuestionEditBean(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @ExceptionHandler
    public void init() {
        question = questionDAO.findById(questionId);
    }

    @ExceptionHandler
    public String update() {
        questionDAO.update(question);
        Messages.create("The question \"{0}\" was successfully updated.", question.getTitle()).flash().add();
        return "list?faces-redirect=true&courseId=" + question.getCourse().getId();
    }

    @ExceptionHandler
    public String delete() {
        questionDAO.delete(question.getId());
        Messages.create("The question \"{0}\" was successfully deleted", question.getTitle()).flash().add();
        return "list?faces-redirect=true&courseId=" + question.getCourse().getId();
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
