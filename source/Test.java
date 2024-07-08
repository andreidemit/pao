
import java.util.ArrayList;
import java.util.List;

public class Test {
    private String testId;
    private String testName;
    private String courseId;

    public Test(String testId, String testName, String courseId) {
        this.testId = testId;
        this.testName = testName;
        this.courseId = courseId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public static List<Test> readTestsFromCSV(String fileName) {
        List<Test> tests = new ArrayList<>();
        List<String[]> data = CSVUtils.readFromCSV(fileName);
        for (String[] values : data) {
            tests.add(new Test(values[0], values[1], values[2]));
        }
        return tests;
    }

    public static void writeTestsToCSV(String fileName, List<Test> tests) {
        List<String[]> data = new ArrayList<>();
        for (Test test : tests) {
            data.add(new String[]{test.getTestId(), test.getTestName(), test.getCourseId()});
        }
        CSVUtils.writeToCSV(fileName, data);
    }
}
