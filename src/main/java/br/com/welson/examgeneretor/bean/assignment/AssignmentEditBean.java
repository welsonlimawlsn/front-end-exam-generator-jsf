package br.com.welson.examgeneretor.bean.assignment;

import br.com.welson.examgeneretor.annotation.ExceptionHandler;
import br.com.welson.examgeneretor.persistence.dao.AssignmentDAO;
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
public class AssignmentEditBean implements Serializable {
    private final AssignmentDAO assignmentDAO;
    private Assignment assignment;
    private long assignmentId;

    @Inject
    public AssignmentEditBean(AssignmentDAO assignmentDAO) {
        this.assignmentDAO = assignmentDAO;
    }

    @ExceptionHandler
    public void init() {
        assignment = assignmentDAO.findById(assignmentId);
    }

    @ExceptionHandler
    public String update() {
        assignmentDAO.update(assignment);
        Messages.create("The assignment \"{0}\" was successfully updated.", assignment.getTitle()).flash().add();
        return "list?faces-redirect=true&courseId=" + assignment.getCourse().getId();
    }

    @ExceptionHandler
    public String delete() {
        assignmentDAO.delete(assignment.getId());
        Messages.create("The assignment \"{0}\" was successfully deleted", assignment.getTitle()).flash().add();
        return "list?faces-redirect=true&courseId=" + assignment.getCourse().getId();
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(long assignmentId) {
        this.assignmentId = assignmentId;
    }
}
