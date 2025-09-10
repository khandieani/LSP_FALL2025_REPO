package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ETLPipeline {
    public static void main(String[] args) {
        String inputPath  = System.getProperty("user.dir")+ "/data/products.csv";;
        String outputPath = System.getProperty("user.dir")+ "/data/transformed_products.csv";;

        List<String[]> extractedData = extract(inputPath);
        if (extractedData == null) {
            System.out.println("ETL process aborted.");
            return;
        }

        List<String[]> transformedData = transform(extractedData);

        load(transformedData, outputPath);

        int rowsRead = extractedData.size() - 1;
        int rowsTransformed = transformedData.size() - 1;
        int rowsSkipped = rowsRead - rowsTransformed;

        System.out.println("ETL Process Summary:");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output written to: " + outputPath);
    }

    public static List<String[]> extract(String path) {
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

    public static List<String[]> transform(List<String[]> data) {
        List<String[]> transformed = new ArrayList<>();
        if (data.isEmpty()) return transformed;

        transformed.add(new String[]{"ProductID", "Name", "Price", "Category", "PriceRange"});

        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);

            if (row.length != 4) {
                System.err.println("Skipping invalid row: " + Arrays.toString(row));
                continue;
            }

            try {
                int productId = Integer.parseInt(row[0]);
                String name = row[1].toUpperCase();
                BigDecimal price = new BigDecimal(row[2]);
                String category = row[3];

                BigDecimal originalPrice = price;

                if (category.equalsIgnoreCase("Electronics")) {
                    price = price.multiply(new BigDecimal("0.90"));
                    price = price.setScale(2, RoundingMode.HALF_UP);
                } else {
                    price = price.setScale(2, RoundingMode.HALF_UP);
                }

                if (category.equalsIgnoreCase("Electronics") && price.compareTo(new BigDecimal("500.00")) > 0) {
                    category = "Premium Electronics";
                }

                String priceRange = getPriceRange(price);

                transformed.add(new String[]{
                        String.valueOf(productId),
                        name,
                        price.toString(),
                        category,
                        priceRange
                });

            } catch (Exception e) {
                System.err.println("Skipping row due to parse error: " + Arrays.toString(row));
            }
        }

        return transformed;
    }

    private static String getPriceRange(BigDecimal price) {
        if (price.compareTo(new BigDecimal("10.00")) <= 0) return "Low";
        else if (price.compareTo(new BigDecimal("100.00")) <= 0) return "Medium";
        else if (price.compareTo(new BigDecimal("500.00")) <= 0) return "High";
        else return "Premium";
    }

    public static void load(List<String[]> data, String path) {
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
