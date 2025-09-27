package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.util.List;

/**
 * Abstract base class for product transformers.
 */
public abstract class Transformer {
    /**
     * Transforms a product according to business rules.
     * 
     * @param product The product to transform
     */
    public abstract void transformProduct(Product product);

    /**
     * Transforms a list of products.
     * 
     * @param products The list of products to transform
     */
    public void transformProducts(List<Product> products) {
        for (Product product : products) {
            transformProduct(product);
        }
    }
}