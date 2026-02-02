package ma.yassine.ecommerce_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Data @NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Product name is required")
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "price is required")
    @DecimalMin(value="0.0",inclusive = false,message = "Price must be greater than 0 ")
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stockQuantity;

    private String imageUrl;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @Column(nullable = false)
    private Boolean active=true;

    @Column(name="created_at",updatable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt=LocalDateTime.now();
        updatedAt=LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt=LocalDateTime.now();
    }

    public Product(String name,String description,BigDecimal price,Integer stockQuantity){
        this.name=name;
        this.description=description;
        this.price=price;
        this.stockQuantity=stockQuantity;
    }

}
