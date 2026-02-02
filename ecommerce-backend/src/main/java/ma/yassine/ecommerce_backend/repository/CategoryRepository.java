package ma.yassine.ecommerce_backend.repository;

import ma.yassine.ecommerce_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author pc
 **/
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);

    boolean existsByName(String name);
}

