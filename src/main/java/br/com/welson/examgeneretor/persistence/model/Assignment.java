package br.com.welson.examgeneretor.persistence.model;

import java.time.LocalDateTime;

/**
 * @author Welson Teles on 4/6/2018
 */

public class Assignment extends AbstractEntity {

    //@NotEmpty(message = "The field title cannot be empty")
    private String title;
    private LocalDateTime createAt = LocalDateTime.now();
    private Course course;
    private Professor professor;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }


    public static final class AssignmentBuilder {
        private Assignment assignment;

        private AssignmentBuilder() {
            assignment = new Assignment();
        }

        public static AssignmentBuilder newAssignment() {
            return new AssignmentBuilder();
        }

        public AssignmentBuilder id(Long id) {
            assignment.setId(id);
            return this;
        }

        public AssignmentBuilder enabled(boolean enabled) {
            assignment.setEnabled(enabled);
            return this;
        }

        public AssignmentBuilder title(String title) {
            assignment.setTitle(title);
            return this;
        }

        public AssignmentBuilder createAt(LocalDateTime createAt) {
            assignment.setCreateAt(createAt);
            return this;
        }

        public AssignmentBuilder course(Course course) {
            assignment.setCourse(course);
            return this;
        }

        public AssignmentBuilder professor(Professor professor) {
            assignment.setProfessor(professor);
            return this;
        }

        public Assignment build() {
            return assignment;
        }
    }
}
