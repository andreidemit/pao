
import java.util.ArrayList;
import java.util.List;

public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private String courseId;

    public Assignment(String assignmentId, String assignmentName, String courseId) {
        this.assignmentId = assignmentId;
        this.assignmentName = assignmentName;
        this.courseId = courseId;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public static List<Assignment> readAssignmentsFromCSV(String fileName) {
        List<Assignment> assignments = new ArrayList<>();
        List<String[]> data = CSVUtils.readFromCSV(fileName);
        for (String[] values : data) {
            assignments.add(new Assignment(values[0], values[1], values[2]));
        }
        return assignments;
    }

    public static void writeAssignmentsToCSV(String fileName, List<Assignment> assignments) {
        List<String[]> data = new ArrayList<>();
        for (Assignment assignment : assignments) {
            data.add(new String[]{assignment.getAssignmentId(), assignment.getAssignmentName(), assignment.getCourseId()});
        }
        CSVUtils.writeToCSV(fileName, data);
    }
}
