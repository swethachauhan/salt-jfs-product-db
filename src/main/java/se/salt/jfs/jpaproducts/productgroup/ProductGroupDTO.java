package se.salt.jfs.jpaproducts.productgroup;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductGroupDTO(@JsonProperty Long id, @JsonProperty  String name) {

    ProductGroupDTO(ProductGroup entity){
        this(entity.getId(), entity.getName());
    }
}
