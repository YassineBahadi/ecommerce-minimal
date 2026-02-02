package ma.yassine.ecommerce_backend.config;

import lombok.AllArgsConstructor;
import ma.yassine.ecommerce_backend.entity.Category;
import ma.yassine.ecommerce_backend.entity.Product;
import ma.yassine.ecommerce_backend.repository.CategoryRepository;
import ma.yassine.ecommerce_backend.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author pc
 **/
@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final ProductRepository  productRepository;
    private final CategoryRepository   categoryRepository;


    @Override
    public void run(String... args) throws Exception {
        Category electronics = createCategoryIfNotExists("Electronics", "Electronic devices and accessories");
        Category clothing = createCategoryIfNotExists("Clothing", "Fashion and apparel");
        Category books = createCategoryIfNotExists("Books", "Books and literature");

        if (productRepository.count() == 0) {
            Product laptop = new Product("Laptop", "High-performance laptop", new BigDecimal("999.99"), 10);
            laptop.setCategory(electronics);
            laptop.setImageUrl("/assets/images/laptop.jpg");

            Product smartphone = new Product("Smartphone", "Latest smartphone", new BigDecimal("699.99"), 15);
            smartphone.setCategory(electronics);
            smartphone.setImageUrl("/assets/images/smartphone.jpg");

            Product tshirt = new Product("T-Shirt", "Cotton t-shirt", new BigDecimal("19.99"), 50);
            tshirt.setCategory(clothing);
            tshirt.setImageUrl("/assets/images/tshirt.jpg");

            Product novel = new Product("Novel Book", "Bestseller novel", new BigDecimal("14.99"), 30);
            novel.setCategory(books);
            novel.setImageUrl("/assets/images/novel.jpg");

            productRepository.saveAll(Arrays.asList(laptop, smartphone, tshirt, novel));
        }
    }

    private Category createCategoryIfNotExists(String name, String description) {
        return categoryRepository.findByName(name)
                .orElseGet(()->categoryRepository.save(new Category(name,description)));
    }


}
