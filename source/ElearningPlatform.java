import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ElearningPlatform {
    private List<User> users;
    private List<Course> courses;
    private List<Assignment> assignments;
    private Map<String, String> actionLog;
    private static final String USERS_CSV = "data/users.csv";
    private static final String COURSES_CSV = "data/courses.csv";
    private static final String ASSIGNMENTS_CSV = "data/assignments.csv";
    private static final String ACTION_LOG_CSV = "data/action_log.csv";

    public ElearningPlatform() {
        this.users = User.readUsersFromCSV(USERS_CSV);
        this.courses = Course.readCoursesFromCSV(COURSES_CSV);
        this.assignments = Assignment.readAssignmentsFromCSV(ASSIGNMENTS_CSV);
        this.actionLog = new HashMap<>();
    }

    public void authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                logAction("authenticateUser");
                System.out.println("******************************");
                System.out.println("**Authentication successful.**");
                System.out.println("******************************");
                return;
            }
        }
        System.out.println("Authentication failed.");
    }

    public void createCourse(String courseId, String courseName) {
        Course course = new Course(courseId, courseName);
        courses.add(course);
        Course.writeCoursesToCSV(COURSES_CSV, courses);
        logAction("createCourse");
        System.out.println("Course created: " + courseName);
    }

    public void enrollStudent(String username, String courseId) {
        User user = findUserByUsername(username);
        if (user != null && user instanceof Student) {
            Course course = findCourseById(courseId);
            if (course != null) {
                ((Student) user).enrollInCourse(course);
                User.writeUsersToCSV(USERS_CSV, users);
                logAction("enrollStudent");
                System.out.println(username + " enrolled in " + courseId);
            }
        }
    }

    public void createAssignment(String courseId, String assignmentName) {
        Course course = findCourseById(courseId);
        if (course != null) {
            String assignmentId = UUID.randomUUID().toString();
            Assignment assignment = new Assignment(assignmentId, assignmentName, courseId);
            assignments.add(assignment);
            Assignment.writeAssignmentsToCSV(ASSIGNMENTS_CSV, assignments);            
            logAction("createAssignment");
            System.out.println("Assignment created: " + assignmentName);
        }
    }

    public void evaluateTest(String testId, String studentUsername) {
        // Implementare evaluare test
        logAction("evaluateTest");
    }

    public void displayAvailableCourses() {
        for (Course course : courses) {
            System.out.println(course.getCourseName());
        }
        logAction("displayAvailableCourses");
    }

    private User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private Course findCourseById(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    public void deleteCourse(String courseId) {
        Course course = findCourseById(courseId);
        if (course != null) {
            courses.remove(course);
            Course.writeCoursesToCSV(COURSES_CSV, courses);
            logAction("deleteCourse");
            System.out.println("Course deleted: " + courseId);
        } else {
            System.out.println("Course not found: " + courseId);
        }
    }
    
    public void deleteAssignment(String courseId, String assignmentId) {
        Course course = findCourseById(courseId);
        if (course != null) {
            Assignment assignmentToRemove = null;
            for (Assignment assignment : course.getAssignments()) {
                if (assignment.getAssignmentId().equals(assignmentId)) {
                    assignmentToRemove = assignment;
                    break;
                }
            }
            if (assignmentToRemove != null) {
                course.getAssignments().remove(assignmentToRemove);
                Course.writeCoursesToCSV(COURSES_CSV, courses);
                logAction("deleteAssignment");
                System.out.println("Assignment deleted: " + assignmentId);
            } else {
                System.out.println("Assignment not found: " + assignmentId);
            }
        } else {
            System.out.println("Course not found: " + courseId);
        }
    }
    
    public void registerUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists: " + username);
                return;
            }
        }

        User user = new User(username, password);
        users.add(user);
        User.writeUsersToCSV(USERS_CSV, users);
        logAction("registerUser");
        System.out.println("User registered: " + username);
    }
    
    public void createTest(String testId, String testName, String courseId) {
        Course course = findCourseById(courseId);
        if (course != null) {
            Test test = new Test(testId, testName, courseId);
            course.addTest(test);
            Course.writeCoursesToCSV(COURSES_CSV, courses);
            logAction("createTest");
            System.out.println("Test created: " + testName);
        } else {
            System.out.println("Course not found: " + courseId);
        }
    }

    private void logAction(String actionName) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        CSVUtils.appendToCSV(ACTION_LOG_CSV, new String[]{actionName, timeStamp});
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
    
        while (!exit) {            
            menuOptions();
    
            int choice = scanner.nextInt();
            scanner.nextLine();  // consumă newline
    
            switch (choice) {
                case 1:
                    menuOptionRegisterUser(scanner);
                    break;
                case 2:
                    menuOptionAuthenticateUser(scanner);
                    break;                    
                case 3:
                    menuOptionDisplayAvailableCourses();
                    break;                    
                case 4:
                    menuOptionCreateCourse(scanner);                   
                    break;
                case 5:                    
                    menuOptionEnrollStudent(scanner);
                    break;
                case 6:
                    menuOptionDeleteCourse(scanner);
                    break;
                case 7:
                    menuOptionCreateAssignment(scanner);
                    break;
                case 8:
                    menuOptionDeleteAssignment(scanner);
                    break;
                case 9:
                    menuOptionCreateTest(scanner);
                    break;
                case 10:
                    menuOptionEvaluateTest(scanner);
                    break;
                case 11:
                    exit = true;
                    System.out.println("Ieșire din aplicație.");
                    break;
                default:
                    System.out.println("Opțiune invalidă. Vă rugăm să încercați din nou.");
                    break;
            }
        }
        scanner.close();
    }

    /**
     * Displays the menu options for the e-learning platform.
     */
    private void menuOptions() {
        System.out.println("1. Înregistrare utilizator");
        System.out.println("2. Autentificare utilizator");
        System.out.println("3. Afișare cursuri disponibile");
        System.out.println("4. Creare curs");
        System.out.println("5. Înscriere cursant la curs");
        System.out.println("6. Eliminare curs");
        System.out.println("7. Creare temă");
        System.out.println("8. Eliminare temă");
        System.out.println("9. Creare test");
        System.out.println("10. Evaluare test");
        System.out.println("11. Ieșire");
    }

    private void menuOptionRegisterUser(Scanner scanner) {
        System.out.print("Introduceți username: ");
        String newUser = scanner.nextLine();
        System.out.print("Introduceți parola: ");
        String newPassword = scanner.nextLine();
        registerUser(newUser, newPassword);
    }

    private void menuOptionAuthenticateUser(Scanner scanner) {
        System.out.print("Introduceți username: ");
        String username = scanner.nextLine();
        System.out.print("Introduceți parola: ");
        String password = scanner.nextLine();
        authenticateUser(username, password);
    }

    private void menuOptionDisplayAvailableCourses() {
        displayAvailableCourses();
    }

    private void menuOptionCreateCourse(Scanner scanner) {
        System.out.print("Introduceți ID curs: ");
        String courseId = scanner.nextLine();
        System.out.print("Introduceți nume curs: ");
        String courseName = scanner.nextLine();
        createCourse(courseId, courseName);
    }

    private void menuOptionEnrollStudent(Scanner scanner) {
        System.out.print("Introduceți username cursant: ");
        String studentUsername = scanner.nextLine();
        System.out.print("Introduceți ID curs: ");
        String courseIdToEnroll = scanner.nextLine();
        enrollStudent(studentUsername, courseIdToEnroll);
    }

    private void menuOptionDeleteCourse(Scanner scanner) {
        System.out.print("Introduceți ID curs de eliminat: ");
        String courseIdToDelete = scanner.nextLine();
        deleteCourse(courseIdToDelete);
    }

    private void menuOptionCreateAssignment(Scanner scanner) {
        System.out.print("Introduceți ID curs: ");
        String courseId = scanner.nextLine();
        System.out.print("Introduceți nume temă: ");
        String assignmentName = scanner.nextLine();
        createAssignment(courseId, assignmentName);
    }

    private void menuOptionDeleteAssignment(Scanner scanner) {
        System.out.print("Introduceți ID curs: ");
        String courseId = scanner.nextLine();
        System.out.print("Introduceți ID temă de eliminat: ");
        String assignmentId = scanner.nextLine();
        deleteAssignment(courseId, assignmentId);
    }

    private void menuOptionCreateTest(Scanner scanner) {
        System.out.print("Introduceți ID test: ");
        String testId = scanner.nextLine();
        System.out.print("Introduceți nume test: ");
        String testName = scanner.nextLine();
        System.out.print("Introduceți ID curs: ");
        String courseId = scanner.nextLine();
        createTest(testId, testName, courseId);
    }

    private void menuOptionEvaluateTest(Scanner scanner) {
        System.out.print("Introduceți ID test: ");
        String testId = scanner.nextLine();
        System.out.print("Introduceți username cursant: ");
        String studentUsername = scanner.nextLine();
        evaluateTest(testId, studentUsername);
    }

    public static void main(String[] args) {
        ElearningPlatform platform = new ElearningPlatform();
        platform.run();
    }
}
