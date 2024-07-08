
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    public static List<User> readUsersFromCSV(String fileName) {
        List<User> users = new ArrayList<>();
        List<String[]> data = CSVUtils.readFromCSV(fileName);
        for (String[] values : data) {
            users.add(new User(values[0], values[1]));
        }
        return users;
    }

    public static void writeUsersToCSV(String fileName, List<User> users) {
        List<String[]> data = new ArrayList<>();
        for (User user : users) {
            data.add(new String[]{user.getUsername(), user.getPassword()});
        }
        CSVUtils.writeToCSV(fileName, data);
    }
}
