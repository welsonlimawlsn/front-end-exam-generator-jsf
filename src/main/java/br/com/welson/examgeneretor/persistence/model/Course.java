package br.com.welson.examgeneretor.persistence.model;

/**
 * @author Welson Teles on 3/20/2018
 */
public class Course extends AbstractEntity {
    private String name;
    private Professor professor;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public static final class CourseBuilder {
        private Course course;

        private CourseBuilder() {
            course = new Course();
        }

        public static CourseBuilder newCourse() {
            return new CourseBuilder();
        }

        public CourseBuilder id(Long id) {
            course.setId(id);
            return this;
        }

        public CourseBuilder name(String name) {
            course.setName(name);
            return this;
        }

        public CourseBuilder professor(Professor professor) {
            course.setProfessor(professor);
            return this;
        }

        public Course build() {
            return course;
        }
    }
}
