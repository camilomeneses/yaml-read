package dev.camilo.yamlread;

import dev.camilo.yamlread.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

import dev.camilo.yamlread.product.ProductRepository;
import dev.camilo.yamlread.store.Store;

@Component
public class DataLoader implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
  private final ObjectMapper objectMapper;
  private final ProductRepository productRepository;

  public DataLoader(ObjectMapper objectMapper, ProductRepository productRepository) {
    this.objectMapper = objectMapper;
    this.productRepository = productRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/store.yaml")) {
      Store store = objectMapper.readValue(inputStream, Store.class);
      store.products().forEach(p -> productRepository.save(new Product(p.id(), p.name(), p.price(), null)));
    } catch (IOException e) {
      log.error("Unable to load products: " + e.getMessage());
    }

  }
}
