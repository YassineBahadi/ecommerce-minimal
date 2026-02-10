package ma.yassine.ecommerce_backend.service;

import lombok.AllArgsConstructor;
import ma.yassine.ecommerce_backend.dto.ProductDTO;
import ma.yassine.ecommerce_backend.entity.Category;
import ma.yassine.ecommerce_backend.entity.Product;
import ma.yassine.ecommerce_backend.repository.CategoryRepository;
import ma.yassine.ecommerce_backend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pc
 **/
@Service
@Transactional
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProducts(){
        return productRepository.findByActiveTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id){
        Product product=productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found"));
        return convertToDTO(product);
    }

    public List<ProductDTO> getProductsByCategory(Long categoryId){
        return productRepository.findByCategoryIdAndActiveTrue(categoryId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> searchProducts(String query){
        return productRepository.searchProducts(query).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO productDTO){
        Product product=new Product();
        updateProductFromDTO(product,productDTO);
        Product savedProduct=productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id,ProductDTO productDTO){
        Product product=productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));

        updateProductFromDTO(product,productDTO);
        Product updatedProduct=productRepository.save(product);
        return convertToDTO(updatedProduct);
    }

    public void deleteProduct(Long id){
        Product product =productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found"));

        product.setActive(false);
        productRepository.save(product);
    }

    public List<ProductDTO> getProductsInStock() {
        return productRepository.findByActiveTrueAndStockQuantityGreaterThan(0).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private ProductDTO convertToDTO(Product product){
        ProductDTO dto=new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setImageUrl(product.getImageUrl());
        dto.setActive(product.getActive());

        if(product.getCategory()!=null){
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }
        return dto;
    }

    private void updateProductFromDTO(Product product,ProductDTO dto){
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setImageUrl(dto.getImageUrl());
        product.setActive(dto.getActive());

        if(dto.getCategoryId()!=null){
            Category category=categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(()->new RuntimeException("Category not found"));
            product.setCategory(category);
        }
    }





}
