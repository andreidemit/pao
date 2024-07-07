package dev.unibuc.pao.source;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseId;
    private String courseName;
    private List<Assignment> assignments;
    private List<Test> tests;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.assignments = new ArrayList<>();
        this.tests = new ArrayList<>();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public List<Test> getTests() {
        return tests;
    }

    public void addTest(Test test) {
        tests.add(test);
    }

    public static List<Course> readCoursesFromCSV(String fileName) {
        List<Course> courses = new ArrayList<>();
        List<String[]> data = CSVUtils.readFromCSV(fileName);
        for (String[] values : data) {
            courses.add(new Course(values[0], values[1]));
        }
        return courses;
    }

    public static void writeCoursesToCSV(String fileName, List<Course> courses) {
        List<String[]> data = new ArrayList<>();
        for (Course course : courses) {
            data.add(new String[]{course.getCourseId(), course.getCourseName()});
        }
        CSVUtils.writeToCSV(fileName, data);
    }
}
