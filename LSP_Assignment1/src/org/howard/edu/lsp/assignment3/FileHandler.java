package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Interface for file operations in the ETL pipeline.
 */
public interface FileHandler {
    /**
     * Reads data from a file.
     * 
     * @param path The path to the input file
     * @return List of string arrays containing the file data
     */
    List<String[]> readFile(String path);

    /**
     * Writes data to a file.
     * 
     * @param data The data to write
     * @param path The path to the output file
     */
    void writeFile(List<String[]> data, String path);
}