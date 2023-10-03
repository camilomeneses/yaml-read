package dev.camilo.yamlread.store;

import java.util.List;

import dev.camilo.yamlread.product.Product;

public record Store(String title, String description, List<Product> products) {
  
}
