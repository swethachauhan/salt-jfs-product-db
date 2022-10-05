package se.salt.jfs.jpaproducts.productgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupService {

  @Autowired
  public ProductGroupRepository repo;
  @Autowired
  public JpaProductGroupRepository jpaRepo;

  public ProductGroupService(ProductGroupRepository repo) {
    this.repo = repo;
  }

  public List<ProductGroup> listProductGroups(){
//    Iterable<ProductGroup>  all = jpaRepo.findAll();
//    return Streamable.of(all).toList();
    return (List<ProductGroup>) jpaRepo.findAll();
  }

  public List<ProductGroup> getProductGroups(String[] groupNames) {

    return repo.findGroups(groupNames);
  }

}
