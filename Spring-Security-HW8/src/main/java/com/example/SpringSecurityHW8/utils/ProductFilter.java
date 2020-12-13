package com.example.SpringSecurityHW8.utils;

import com.example.SpringSecurityHW8.entities.Product;
import com.example.SpringSecurityHW8.repositories.specifications.ProductSpecifications;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class ProductFilter {
    private Specification<Product> spec;
    private String filterDefinition;

    public ProductFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        String filterTitle = params.get("title");
        if (filterTitle != null && !filterTitle.isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(filterTitle));
            filterDefinitionBuilder.append("&title=").append(filterTitle);
        }

        if (params.containsKey("min_price") && !params.get("min_price").isBlank()) {
            Integer minPrice = Integer.parseInt(params.get("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
            filterDefinitionBuilder.append("&min_price=").append(minPrice);
        }

        if (params.containsKey("max_price") && !params.get("max_price").isBlank()) {
            Integer maxPrice = Integer.parseInt(params.get("max_price"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
            filterDefinitionBuilder.append("&max_price=").append(maxPrice);
        }

        filterDefinition = filterDefinitionBuilder.toString();
    }

    public Specification<Product> getSpec() {
        return spec;
    }

    public void setSpec(Specification<Product> spec) {
        this.spec = spec;
    }

    public String getFilterDefinition() {
        return filterDefinition;
    }

    public void setFilterDefinition(String filterDefinition) {
        this.filterDefinition = filterDefinition;
    }
}
