package dev.unibuc.pao.source;
import java.util.List;
import java.util.ArrayList;

public class Student extends User {
    private List<Course> enrolledCourses;

    public Student(String username, String password) {
        super(username, password);
        this.enrolledCourses = new ArrayList<>();
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    public static List<Student> readStudentsFromCSV(String fileName) {
        List<Student> students = new ArrayList<>();
        List<String[]> data = CSVUtils.readFromCSV(fileName);
        for (String[] values : data) {
            students.add(new Student(values[0], values[1]));
        }
        return students;
    }

    public static void writeStudentsToCSV(String fileName, List<Student> students) {
        List<String[]> data = new ArrayList<>();
        for (Student student : students) {
            data.add(new String[]{student.getUsername(), student.getPassword()});
        }
        CSVUtils.writeToCSV(fileName, data);
    }
}
