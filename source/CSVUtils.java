import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for CSV file operations including reading from, writing to, and appending to CSV files.
 */
public class CSVUtils {

    /**
     * Reads data from a CSV file and returns it as a list of string arrays.
     * Each string array represents a row in the CSV file.
     *
     * @param fileName The name of the CSV file to read from.
     * @return A List of String arrays, where each String array represents a row in the CSV file.
     */
    public static List<String[]> readFromCSV(String fileName) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Writes data to a CSV file. Each string array in the list represents a row in the CSV file.
     *
     * @param fileName The name of the CSV file to write to.
     * @param data The data to write to the CSV file, where each String array represents a row.
     */
    public static void writeToCSV(String fileName, List<String[]> data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String[] record : data) {
                writer.append(String.join(",", record)).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends a single record to a CSV file. The record is represented as a string array.
     *
     * @param fileName The name of the CSV file to append to.
     * @param record The record to append to the CSV file, represented as a String array.
     */
    public static void appendToCSV(String fileName, String[] record) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.append(String.join(",", record)).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}