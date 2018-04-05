package br.com.welson.examgeneretor.bean.choice;

import br.com.welson.examgeneretor.annotation.ExceptionHandler;
import br.com.welson.examgeneretor.persistence.dao.ChoiceDAO;
import br.com.welson.examgeneretor.persistence.dao.QuestionDAO;
import br.com.welson.examgeneretor.persistence.model.Choice;
import br.com.welson.examgeneretor.persistence.model.Question;
import org.omnifaces.util.Messages;
import org.primefaces.event.RowEditEvent;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Welson Teles on 4/2/2018
 */
@Named
@ViewScoped
public class ChoiceBean implements Serializable {

    private final ChoiceDAO choiceDAO;
    private final QuestionDAO questionDAO;
    private Choice choice = new Choice();
    private Question question;
    private List<Choice> choiceList;
    private long questionId;

    @Inject
    public ChoiceBean(ChoiceDAO choiceDAO, QuestionDAO questionDAO) {
        this.choiceDAO = choiceDAO;
        this.questionDAO = questionDAO;
    }

    @ExceptionHandler
    public void init() {
        question = questionDAO.findById(questionId);
        buildChoiceWithQuestion();
        search();
    }

    private void search() {
        choiceList = choiceDAO.list(questionId);
    }

    @ExceptionHandler
    public void save() {
        Choice choice = choiceDAO.create(this.choice);
        buildChoiceWithQuestion();
        search();
        Messages.addGlobalInfo("The choice {0} was successfully added", choice.getTitle());
    }

    private void buildChoiceWithQuestion() {
        choice = Choice.ChoiceBuilder.newChoice().question(question).build();
    }

    @ExceptionHandler
    public void onRowEditUpdateChoice(RowEditEvent event) {
        Choice choice = (Choice) event.getObject();
        choiceDAO.update(choice);
        search();
        Messages.addGlobalInfo("The choice {0} was successfully updated", choice.getTitle());
    }

    @ExceptionHandler
    public void delete(Choice choice) {
        choiceDAO.delete(choice.getId());
        choiceList.remove(choice);
        Messages.addGlobalInfo("The choice {0} was successfully deleted", choice.getTitle());
    }

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Choice> getChoiceList() {
        return choiceList;
    }

    public void setChoiceList(List<Choice> choiceList) {
        this.choiceList = choiceList;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
