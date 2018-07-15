package com.vta.codingmobile.vtamovil.Services;

import com.vta.codingmobile.vtamovil.Model.Clases.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Mock {
    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setPrice("33033");
        product.setTitle("Product 1");
        products.add(product);

        product = new Product();
        product.setId(UUID.randomUUID());
        product.setPrice("33033");
        product.setTitle("Product 2");
        products.add(product);
        products.add(product);
        products.add(product);
        products.add(product);
        products.add(product);
        products.add(product);
        products.add(product);
        products.add(product);
        products.add(product);

        return products;
    }
}
