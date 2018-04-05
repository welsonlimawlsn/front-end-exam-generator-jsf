package br.com.welson.examgeneretor.persistence.model;

/**
 * @author Welson Teles on 4/1/2018
 */
public class Choice extends AbstractEntity {

    // @NotEmpty(message = "The field title cannot be empty")
    private String title;

    //@NotNull(message = "The field correctAnswer must be true or false")
    private boolean correctAnswer;
    private Question question;
    private Professor professor;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public static final class ChoiceBuilder {
        private Choice choice;

        private ChoiceBuilder() {
            choice = new Choice();
        }

        public static ChoiceBuilder newChoice() {
            return new ChoiceBuilder();
        }

        public ChoiceBuilder id(Long id) {
            choice.setId(id);
            return this;
        }

        public ChoiceBuilder enabled(boolean enabled) {
            choice.setEnabled(enabled);
            return this;
        }

        public ChoiceBuilder title(String title) {
            choice.setTitle(title);
            return this;
        }

        public ChoiceBuilder correctAnswer(boolean correctAnswer) {
            choice.setCorrectAnswer(correctAnswer);
            return this;
        }

        public ChoiceBuilder question(Question question) {
            choice.setQuestion(question);
            return this;
        }

        public ChoiceBuilder professor(Professor professor) {
            choice.setProfessor(professor);
            return this;
        }

        public Choice build() {
            return choice;
        }
    }
}
