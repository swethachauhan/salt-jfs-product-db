package se.salt.jfs.jpaproducts.productgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductGroupRepository {
    @Autowired
    JpaProductGroupRepository jpaRepo;
    public List<ProductGroup> listProductGroups() {
        return (List<ProductGroup>) jpaRepo.findAll();
    }

    public List<ProductGroup> findGroups(String[] groupNames) {
        return jpaRepo.findAllByNameIsIn(groupNames);
    }

}

