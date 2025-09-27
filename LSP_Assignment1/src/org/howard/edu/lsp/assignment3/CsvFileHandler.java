package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.*;

/**
 * Handles CSV file operations for the ETL pipeline.
 */
public class CsvFileHandler implements FileHandler {
    /**
     * Reads data from a CSV file.
     * 
     * @param path The path to the input CSV file
     * @return List of string arrays containing the CSV data, or null if an error occurs
     */
    @Override
    public List<String[]> readFile(String path) {
        List<String[]> data = new ArrayList<>();
        File file = new File(path);
        
        if (!file.exists()) {
            System.err.println("Error: Input file not found at '" + path + "'");
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split(",", -1);
                data.add(tokens);
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return null;
        }

        return data;
    }

    /**
     * Writes data to a CSV file.
     * 
     * @param data The data to write to the CSV file
     * @param path The path to the output CSV file
     */
    @Override
    public void writeFile(List<String[]> data, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }
    }
}