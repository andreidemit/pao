import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/databaseName";
    private static final String USER = "username";
    private static final String PASSWORD = "password";

    /**
     * Reads data from a database table.
     *
     * @param query SQL query to execute.
     * @return List of data rows, each represented as a String array.
     */
    public static List<String[]> readFromDatabase(String query) {
        List<String[]> data = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Assuming there are 3 columns in the result set
                String[] row = {rs.getString(1), rs.getString(2), rs.getString(3)};
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Writes data to a database table.
     *
     * @param query SQL query to execute for inserting data.
     * @param data List of data rows, each represented as a String array.
     */
    public static void writeToDatabase(String query, List<String[]> data) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    stmt.setString(i + 1, row[i]);
                }
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}