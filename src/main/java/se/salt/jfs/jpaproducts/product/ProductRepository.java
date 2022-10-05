package se.salt.jfs.jpaproducts.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.salt.jfs.jpaproducts.productgroup.ProductGroup;

import java.util.List;

@Repository
public class ProductRepository{
    @Autowired
    JpaProductRepository jpaRepo;
    public List<Product> listProducts() {

        return (List<Product>) jpaRepo.findAll();
    }

    public List<Product> listAllProducts() {

        return (List<Product>) jpaRepo.findAll();
    }

    public Product getById(Long id) {

        return jpaRepo.findProductById(id);
    }

    public Product saveProduct(Product newProduct) {
        return jpaRepo.save(newProduct);
    }

    public void deleteProduct(Product product) {
        jpaRepo.delete(product);
    }

    public void deleteProduct(Long productId) {
        jpaRepo.deleteById(productId);
    }

    public List<Product> findProductByName(String productName) {
        return jpaRepo.findProductByName(productName);
    }

    public List<Product> productsForGroups(List<ProductGroup> groups) {
        return jpaRepo.findProducts(groups);
    }
}
