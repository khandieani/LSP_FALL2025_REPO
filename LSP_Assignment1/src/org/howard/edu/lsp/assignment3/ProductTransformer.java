package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Implements product transformation rules for the ETL pipeline.
 */
public class ProductTransformer extends Transformer {
    private static final BigDecimal ELECTRONICS_DISCOUNT = new BigDecimal("0.90");
    private static final BigDecimal PREMIUM_THRESHOLD = new BigDecimal("500.00");

    /**
     * Transforms a product according to the business rules:
     * - Applies 10% discount to Electronics
     * - Upgrades Electronics over $500 to Premium Electronics
     * 
     * @param product The product to transform
     */
    @Override
    public void transformProduct(Product product) {
        if (product.getCategory().equalsIgnoreCase("Electronics")) {
            // Apply 10% discount
            BigDecimal discountedPrice = product.getPrice()
                .multiply(ELECTRONICS_DISCOUNT)
                .setScale(2, RoundingMode.HALF_UP);
            product.setPrice(discountedPrice);

            // Check if it should be premium
            if (discountedPrice.compareTo(PREMIUM_THRESHOLD) > 0) {
                product.setCategory("Premium Electronics");
            }
        }
    }
}