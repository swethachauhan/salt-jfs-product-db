package se.salt.jfs.jpaproducts.productgroup;


import se.salt.jfs.jpaproducts.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
//@Table(name="product_group")
public class ProductGroup {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="pg_id")
  private Long id;

  @Column(nullable = false)
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
