package se.salt.jfs.jpaproducts.productgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/productgroups")
public class ProductGroupController {

  Logger logger = Logger.getLogger(ProductGroupController.class.getName());
  @Autowired
  private ProductGroupService service;

  @GetMapping
  public ResponseEntity<List<ProductGroupDTO>> listGroups(){
    List<ProductGroup> body = service.listProductGroups();
    logger.info(body.toString());
    return ResponseEntity.ok(body.stream().map(entity -> new ProductGroupDTO(entity)).toList());
  }
}
