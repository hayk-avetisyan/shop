package am.techmock.shop.controller;

import am.techmock.shop.model.Product;
import am.techmock.shop.model.ProductCategory;
import am.techmock.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/categories")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping()
    public List<ProductCategory> getAllProducts() {
        return productRepository.list();
    }

    @PostMapping()
    public ResponseEntity<ProductCategory> addCategory(@RequestBody ProductCategory category) {
        ProductCategory newCategory = productRepository.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getCategoryById(@PathVariable int id) {
        Optional<ProductCategory> category = productRepository.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCategory(@PathVariable int id) {
        productRepository.removeCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> updateCategory(@PathVariable int id, @RequestBody ProductCategory category) {
        boolean updated = productRepository.updateCategory(id, category);
        if (updated) {
            return productRepository.getCategoryById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{categoryId}/products")
    public ResponseEntity<Void> addProductToCategory(@PathVariable int categoryId, @RequestBody Product product) {
        boolean added = productRepository.addProductToCategory(categoryId, product);
        if (added) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable int categoryId, @PathVariable int productId, @RequestBody Product product) {
        boolean updated = productRepository.updateProduct(categoryId, productId, product);
        if (updated) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<Void> removeProductFromCategory(@PathVariable int categoryId, @PathVariable int productId) {
        boolean removed = productRepository.removeProductFromCategory(categoryId, productId);
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}