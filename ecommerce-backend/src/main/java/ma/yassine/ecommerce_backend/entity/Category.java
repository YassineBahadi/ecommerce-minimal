package ma.yassine.ecommerce_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data @NoArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name is required")
    @Column(nullable = false,unique = true)
    private String name;


    private String description;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Product> products=new ArrayList<>();

    public Category(String name,String description){
        this.name=name;
        this.description=description;
    }

}
