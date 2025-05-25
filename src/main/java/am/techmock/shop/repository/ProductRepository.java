package am.techmock.shop.repository;

import am.techmock.shop.model.Product;
import am.techmock.shop.model.ProductCategory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Stream;

@Repository
public class ProductRepository {

    private final NamedParameterJdbcTemplate queryTemplate;

    public ProductRepository(DataSource dataSource) {
        queryTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<ProductCategory> list() {
        String sql = "SELECT id, title, description, cover_image, cover_video FROM product_category";
        
        List<ProductCategory> categories = queryTemplate.query(sql, (rs, rowNum) -> {
            int categoryId = rs.getInt("id");
            return ProductCategory.of(
                    categoryId,
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("cover_image"),
                    rs.getString("cover_video"),
                    getProductsForCategory(categoryId)
            );
        });
        
        return categories;
    }

    private Map<Integer, Product> getProductsForCategory(int categoryId) {
        String sql = "SELECT p.id, p.title, p.image, p.description, p.price " +
                "FROM product p " +
                "JOIN product_category_mapping pcm ON p.id = pcm.product_id " +
                "WHERE pcm.category_id = :categoryId";
        
        Map<Integer, Product> products = new LinkedHashMap<>();
        
        queryTemplate.query(
                sql,
                new MapSqlParameterSource("categoryId", categoryId),
                (rs, rowNum) -> {
                    int productId = rs.getInt("id");
                    Product product = Product.of(
                            productId,
                            rs.getString("title"),
                            rs.getString("image"),
                            rs.getString("description"),
                            rs.getInt("price")
                    );
                    products.put(productId, product);
                    return null;
                }
        );
        
        return products;
    }

    public Optional<ProductCategory> getCategoryById(int id) {
        String sql = "SELECT id, title, description, cover_image, cover_video FROM product_category WHERE id = :id";
        
        List<ProductCategory> categories = queryTemplate.query(
                sql,
                new MapSqlParameterSource("id", id),
                (rs, rowNum) -> ProductCategory.of(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("cover_image"),
                        rs.getString("cover_video"),
                        getProductsForCategory(id)
                )
        );
        
        return categories.isEmpty() ? Optional.empty() : Optional.of(categories.get(0));
    }

    public ProductCategory addCategory(ProductCategory category) {
        String sql = "INSERT INTO product_category (title, description, cover_image, cover_video) " +
                "VALUES (:title, :description, :coverImage, :coverVideo)";
        
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", category.title())
                .addValue("description", category.description())
                .addValue("coverImage", category.coverImage())
                .addValue("coverVideo", category.coverVideo());
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        queryTemplate.update(sql, params, keyHolder);
        
        int id = keyHolder.getKey().intValue();
        
        return ProductCategory.of(
                id,
                category.title(),
                category.description(),
                category.coverImage(),
                category.coverVideo(),
                new LinkedHashMap<>()
        );
    }

    public void removeCategory(int id) {
        // Due to foreign key constraints with ON DELETE CASCADE,
        // deleting the category will automatically delete related mappings
        String sql = "DELETE FROM product_category WHERE id = :id";
        
        queryTemplate.update(
                sql,
                new MapSqlParameterSource("id", id)
        );
    }

    public boolean updateCategory(int id, ProductCategory category) {
        String sql = "UPDATE product_category " +
                "SET title = :title, description = :description, cover_image = :coverImage, cover_video = :coverVideo " +
                "WHERE id = :id";
        
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("title", category.title())
                .addValue("description", category.description())
                .addValue("coverImage", category.coverImage())
                .addValue("coverVideo", category.coverVideo());
        
        int rowsAffected = queryTemplate.update(sql, params);
        
        return rowsAffected > 0;
    }

    public Stream<Product> getProductById(int id) {
        String sql = "SELECT id, title, image, description, price FROM product WHERE id = :id";
        
        List<Product> products = queryTemplate.query(
                sql,
                new MapSqlParameterSource("id", id),
                (rs, rowNum) -> Product.of(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("image"),
                        rs.getString("description"),
                        rs.getInt("price")
                )
        );
        
        return products.stream();
    }

    public boolean addProductToCategory(int categoryId, Product product) {
        // First, check if the category exists
        String checkCategorySql = "SELECT COUNT(*) FROM product_category WHERE id = :id";
        
        Integer categoryCount = queryTemplate.queryForObject(
                checkCategorySql,
                new MapSqlParameterSource("id", categoryId),
                Integer.class
        );
        
        if (categoryCount == null || categoryCount == 0) {
            return false;
        }
        
        // Insert the product
        String insertProductSql = "INSERT INTO product (title, image, description, price) " +
                "VALUES (:title, :image, :description, :price)";
        
        MapSqlParameterSource productParams = new MapSqlParameterSource()
                .addValue("title", product.title())
                .addValue("image", product.image())
                .addValue("description", product.description())
                .addValue("price", product.price());
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        queryTemplate.update(insertProductSql, productParams, keyHolder);
        
        int productId = keyHolder.getKey().intValue();
        
        // Create the mapping between product and category
        String insertMappingSql = "INSERT INTO product_category_mapping (product_id, category_id) " +
                "VALUES (:productId, :categoryId)";
        
        MapSqlParameterSource mappingParams = new MapSqlParameterSource()
                .addValue("productId", productId)
                .addValue("categoryId", categoryId);
        
        queryTemplate.update(insertMappingSql, mappingParams);
        
        return true;
    }

    public boolean updateProduct(int categoryId, int productId, Product product) {
        // First, check if the product exists and belongs to the category
        String checkProductSql = "SELECT COUNT(*) FROM product_category_mapping " +
                "WHERE product_id = :productId AND category_id = :categoryId";
        
        Integer productCount = queryTemplate.queryForObject(
                checkProductSql,
                new MapSqlParameterSource()
                        .addValue("productId", productId)
                        .addValue("categoryId", categoryId),
                Integer.class
        );
        
        if (productCount == null || productCount == 0) {
            return false;
        }
        
        // Update the product
        String updateProductSql = "UPDATE product " +
                "SET title = :title, image = :image, description = :description, price = :price " +
                "WHERE id = :id";
        
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", productId)
                .addValue("title", product.title())
                .addValue("image", product.image())
                .addValue("description", product.description())
                .addValue("price", product.price());
        
        int rowsAffected = queryTemplate.update(updateProductSql, params);
        
        return rowsAffected > 0;
    }

    public boolean removeProductFromCategory(int categoryId, int productId) {
        // First, check if the product exists and belongs to the category
        String checkProductSql = "SELECT COUNT(*) FROM product_category_mapping " +
                "WHERE product_id = :productId AND category_id = :categoryId";
        
        Integer productCount = queryTemplate.queryForObject(
                checkProductSql,
                new MapSqlParameterSource()
                        .addValue("productId", productId)
                        .addValue("categoryId", categoryId),
                Integer.class
        );
        
        if (productCount == null || productCount == 0) {
            return false;
        }
        
        // Remove the mapping between product and category
        String deleteMappingSql = "DELETE FROM product_category_mapping " +
                "WHERE product_id = :productId AND category_id = :categoryId";
        
        queryTemplate.update(
                deleteMappingSql,
                new MapSqlParameterSource()
                        .addValue("productId", productId)
                        .addValue("categoryId", categoryId)
        );
        
        // Check if the product is still associated with any category
        String checkOtherMappingsSql = "SELECT COUNT(*) FROM product_category_mapping " +
                "WHERE product_id = :productId";
        
        Integer otherMappingsCount = queryTemplate.queryForObject(
                checkOtherMappingsSql,
                new MapSqlParameterSource("productId", productId),
                Integer.class
        );
        
        // If the product is not associated with any category, delete it
        if (otherMappingsCount != null && otherMappingsCount == 0) {
            String deleteProductSql = "DELETE FROM product WHERE id = :id";
            
            queryTemplate.update(
                    deleteProductSql,
                    new MapSqlParameterSource("id", productId)
            );
        }
        
        return true;
    }
}