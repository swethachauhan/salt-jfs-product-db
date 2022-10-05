package se.salt.jfs.jpaproducts.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.salt.jfs.jpaproducts.productgroup.ProductGroup;

import javax.transaction.Transactional;
import java.util.List;

public interface JpaProductRepository extends CrudRepository<Product,Long> {

    Product findProductById(Long id);

    List<Product> findProductByName(String name);

    @Query(value ="SELECT p FROM Product p where p.productGroup in :groups")
    List<Product> findProducts(@Param("groups") List<ProductGroup> groups);
//    List<Product> findProductByProductGroupIsIn(List<ProductGroup> groups);
}
