package ma.yassine.ecommerce_backend.repository;

import ma.yassine.ecommerce_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author pc
 **/
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByActiveTrue();

    List<Product> findByCategoryIdAndActiveTrue(Long categoryId);

    List<Product> findByActiveTrueAndStockQuantityGreaterThan(Integer stockQuantity);

    List<Product> findByCategoryNameAndActiveTrue(String categoryName);

    @Query("SELECT p FROM Product p WHERE p.active=true AND " + "(LOWER(p.name) LIKE lower(concat('%',:query,'%')) OR " + "lower(p.description) LIKE lower(concat('%',:query,'%') )  ) " )
    List<Product> searchProducts(@Param("query") String query);
}
