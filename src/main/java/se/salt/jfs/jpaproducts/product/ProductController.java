package se.salt.jfs.jpaproducts.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import se.salt.jfs.jpaproducts.productgroup.ProductGroup;
import se.salt.jfs.jpaproducts.productgroup.ProductGroupService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;
  private final ProductGroupService productGroupService;

  public ProductController(ProductService service, ProductGroupService productGroupService) {
    this.productService  = service;
    this.productGroupService = productGroupService;
  }

  @GetMapping
  ResponseEntity<List<ProductResponseDTO>> getAllProducts(@RequestParam @Nullable  String[] group){
    if(group == null || group.length == 0) {
      List<ProductResponseDTO> allProducts = productService.getAllProducts().stream()
              .map(entity -> ProductConverter.toResponseDTO(entity))
              .toList();
      return ResponseEntity.ok().body(allProducts);
    }
    List<ProductGroup> productGroups = productGroupService.getProductGroups(group);

    List<ProductResponseDTO> productsByGroups = productService.findByGroup(productGroups).stream()
            .map(entity -> ProductConverter.toResponseDTO(entity))
            .toList();
    return ResponseEntity.ok().body(productsByGroups);
  }

  @GetMapping(path="{id}")
  ResponseEntity<ProductResponseDTO> getSpecificProduct(@PathVariable long id){
    Product product = productService.findById(id);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(ProductConverter.toResponseDTO(product), HttpStatus.OK);
  }

  @PutMapping(path="{id}")
  ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody CreateProductDTO incoming, @PathVariable long id) {
    Product stored = productService.findById(id);

    if(stored == null) {
      return ResponseEntity.notFound().build();
    }
    Product productToAdd = new Product();
    productToAdd.setId(stored.getId());
    productToAdd.setPrice(incoming.price());
    productToAdd.setName(incoming.name());
    productToAdd.setDescription(incoming.description());
    productToAdd.setProductGroup(getProductGroup(incoming.group()));

    Product result = productService.saveProduct(productToAdd);
    return new ResponseEntity<>(ProductConverter.toResponseDTO(result), HttpStatus.ACCEPTED);
  }

  private ProductGroup getProductGroup(@JsonProperty Integer groupId) {
    if(groupId == null ){
      return null;
    }
    ProductGroup productGroup = productGroupService.listProductGroups()
      .stream()
      .filter(group -> group.getId().equals(Long.valueOf(groupId)))
      .findFirst().orElse(null);
    return productGroup;
  }

  @PatchMapping(path="{id}")
  ResponseEntity<ProductResponseDTO> patchProduct(@RequestBody CreateProductDTO product, @PathVariable long id) {
    Product prod = ProductConverter.fromDto(product);
    prod.setId(id);
    prod.setProductGroup(getProductGroup(product.group()));

    Product updatedProduct = productService.updateProductData(prod);
    if(updatedProduct == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(ProductConverter.toResponseDTO(updatedProduct), HttpStatus.ACCEPTED);
  }

  @DeleteMapping(path="{id}")
  ResponseEntity<Void> deleteProduct(@PathVariable long id) {
    try{

      productService.deleteProduct(id);
      return ResponseEntity.noContent().build();
    }catch(DataAccessException ex){
      return ResponseEntity.noContent().build();
    }
  }

  @PostMapping
  ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateProductDTO newProduct, HttpServletRequest req) {
      Product prod = ProductConverter.fromDto(newProduct);
      prod.setProductGroup(getProductGroup(newProduct.group()));
      Product product = productService.saveProduct(prod);
      URI location = URI.create((req.getRequestURL() + "/" + product.getId()).replace("products//", "products/"));
      return ResponseEntity.created(location).body(ProductConverter.toResponseDTO(product));
  }
}
