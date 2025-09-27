package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a product in the ETL pipeline.
 */
public class Product {
    private int productId;
    private String name;
    private BigDecimal price;
    private String category;
    private String priceRange;

    /**
     * Constructs a new Product with the specified attributes.
     * 
     * @param productId The unique identifier of the product
     * @param name The name of the product
     * @param price The price of the product
     * @param category The category of the product
     */
    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = calculatePriceRange();
    }

    /**
     * Calculates the price range based on the product's price.
     * 
     * @return The price range category (Low, Medium, High, or Premium)
     */
    private String calculatePriceRange() {
        if (price.compareTo(new BigDecimal("10.00")) <= 0) return "Low";
        else if (price.compareTo(new BigDecimal("100.00")) <= 0) return "Medium";
        else if (price.compareTo(new BigDecimal("500.00")) <= 0) return "High";
        else return "Premium";
    }

    /**
     * Gets the product ID.
     * 
     * @return The product ID
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Gets the product name.
     * 
     * @return The product name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the product price.
     * 
     * @return The product price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Gets the product category.
     * 
     * @return The product category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the price range category.
     * 
     * @return The price range category
     */
    public String getPriceRange() {
        return priceRange;
    }

    /**
     * Sets the price of the product.
     * 
     * @param price The new price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
        this.priceRange = calculatePriceRange();
    }

    /**
     * Sets the category of the product.
     * 
     * @param category The new category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Converts the product to a CSV row format.
     * 
     * @return Array of strings representing the product's data
     */
    public String[] toCsvRow() {
        return new String[]{
            String.valueOf(productId),
            name,
            price.toString(),
            category,
            priceRange
        };
    }
}