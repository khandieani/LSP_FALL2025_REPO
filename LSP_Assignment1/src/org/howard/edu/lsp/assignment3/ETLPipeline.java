package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main ETL pipeline class that orchestrates the extraction, transformation, and loading of product data.
 */
public class ETLPipeline {
    private final FileHandler fileHandler;
    private final Transformer transformer;
    private List<Product> products;
    private int rowsRead;
    private int rowsTransformed;

    /**
     * Constructs a new ETL pipeline with the specified file handler and transformer.
     * 
     * @param fileHandler The file handler to use for I/O operations
     * @param transformer The transformer to apply to the products
     */
    public ETLPipeline(FileHandler fileHandler, Transformer transformer) {
        this.fileHandler = fileHandler;
        this.transformer = transformer;
        this.products = new ArrayList<>();
    }

    /**
     * Extracts data from the input file and converts it to Product objects.
     * 
     * @param inputPath Path to the input file
     * @return true if extraction was successful, false otherwise
     */
    public boolean extract(String inputPath) {
        List<String[]> rawData = fileHandler.readFile(inputPath);
        if (rawData == null || rawData.isEmpty()) {
            return false;
        }

        // Skip header row in count
        rowsRead = rawData.size() - 1;
        
        // Process data starting from row 1 (after header)
        for (int i = 1; i < rawData.size(); i++) {
            String[] row = rawData.get(i);
            
            if (row.length != 4) {
                System.err.println("Skipping invalid row: " + Arrays.toString(row));
                continue;
            }

            try {
                Product product = new Product(
                    Integer.parseInt(row[0]),
                    row[1].toUpperCase(),
                    new BigDecimal(row[2]),
                    row[3]
                );
                products.add(product);
            } catch (Exception e) {
                System.err.println("Skipping row due to parse error: " + Arrays.toString(row));
            }
        }

        return true;
    }

    /**
     * Transforms the extracted products using the configured transformer.
     */
    public void transform() {
        transformer.transformProducts(products);
        rowsTransformed = products.size();
    }

    /**
     * Loads the transformed products to the output file.
     * 
     * @param outputPath Path to the output file
     */
    public void load(String outputPath) {
        List<String[]> outputData = new ArrayList<>();
        
        // Add header
        outputData.add(new String[]{"ProductID", "Name", "Price", "Category", "PriceRange"});
        
        // Add transformed products
        for (Product product : products) {
            outputData.add(product.toCsvRow());
        }

        fileHandler.writeFile(outputData, outputPath);
    }

    /**
     * Gets the number of rows read from the input file.
     * 
     * @return Number of rows read
     */
    public int getRowsRead() {
        return rowsRead;
    }

    /**
     * Gets the number of rows successfully transformed.
     * 
     * @return Number of rows transformed
     */
    public int getRowsTransformed() {
        return rowsTransformed;
    }

    /**
     * Gets the number of rows skipped during processing.
     * 
     * @return Number of rows skipped
     */
    public int getRowsSkipped() {
        return rowsRead - rowsTransformed;
    }

    /**
     * Main method to run the ETL pipeline.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Use relative paths from the project root
        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";

        FileHandler fileHandler = new CsvFileHandler();
        Transformer transformer = new ProductTransformer();
        ETLPipeline pipeline = new ETLPipeline(fileHandler, transformer);

        if (!pipeline.extract(inputPath)) {
            System.out.println("ETL process aborted.");
            return;
        }

        pipeline.transform();
        pipeline.load(outputPath);

        System.out.println("ETL Process Summary:");
        System.out.println("Rows read: " + pipeline.getRowsRead());
        System.out.println("Rows transformed: " + pipeline.getRowsTransformed());
        System.out.println("Rows skipped: " + pipeline.getRowsSkipped());
        System.out.println("Output written to: " + outputPath);
    }
}