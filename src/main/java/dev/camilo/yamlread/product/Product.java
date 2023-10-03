package dev.camilo.yamlread.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record Product(@Id Integer id, String name, Integer price, @Version Integer version) {
  
}
