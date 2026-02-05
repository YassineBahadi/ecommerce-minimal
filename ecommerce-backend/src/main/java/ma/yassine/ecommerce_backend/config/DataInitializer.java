package ma.yassine.ecommerce_backend.config;

import lombok.AllArgsConstructor;
import ma.yassine.ecommerce_backend.entity.*;
import ma.yassine.ecommerce_backend.repository.CategoryRepository;
import ma.yassine.ecommerce_backend.repository.ProductRepository;
import ma.yassine.ecommerce_backend.repository.RoleRepository;
import ma.yassine.ecommerce_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author pc
 **/
@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final ProductRepository  productRepository;
    private final CategoryRepository   categoryRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



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

        // Initialize roles
        if (roleRepository.count() == 0) {
            Role adminRole = new Role(ERole.ROLE_ADMIN);
            Role userRole = new Role(ERole.ROLE_USER);
            roleRepository.saveAll(Arrays.asList(adminRole, userRole));
        }

        // Initialize admin user
        if (!userRepository.existsByEmail("admin@ecommerce.com")) {
            User admin = new User("admin@ecommerce.com",
                    passwordEncoder.encode("admin123"),
                    "Admin", "User");

            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Admin role not found"));
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            admin.setRoles(roles);

            userRepository.save(admin);
        }

        // Initialize regular user
        if (!userRepository.existsByEmail("user@ecommerce.com")) {
            User user = new User("user@ecommerce.com",
                    passwordEncoder.encode("user123"),
                    "John", "Doe");

            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("User role not found"));
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);

            userRepository.save(user);
        }
    }

    private Category createCategoryIfNotExists(String name, String description) {
        return categoryRepository.findByName(name)
                .orElseGet(()->categoryRepository.save(new Category(name,description)));
    }


}
