package com.example.SpringSecurityHW8.utils;

import com.example.SpringSecurityHW8.entities.Order;
import com.example.SpringSecurityHW8.repositories.specifications.OrderSpecifications;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class OrderFilter {
    private Specification<Order> spec;
    private String filterDefinition;

    public OrderFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        String customerName = params.get("customerName");
        if (customerName != null && !customerName.isBlank()) {
            spec = spec.and(OrderSpecifications.customerName(customerName));
            filterDefinitionBuilder.append("&title=").append(customerName);
        }

        if (params.containsKey("min_price") && !params.get("min_price").isBlank()) {
            Integer minPrice = Integer.parseInt(params.get("min_price"));
            spec = spec.and(OrderSpecifications.priceGreaterOrEqualsThan(minPrice));
            filterDefinitionBuilder.append("&min_price=").append(minPrice);
        }

        if (params.containsKey("max_price") && !params.get("max_price").isBlank()) {
            Integer maxPrice = Integer.parseInt(params.get("max_price"));
            spec = spec.and(OrderSpecifications.priceLesserOrEqualsThan(maxPrice));
            filterDefinitionBuilder.append("&max_price=").append(maxPrice);
        }

        filterDefinition = filterDefinitionBuilder.toString();
    }

    public Specification<Order> getSpec() {
        return spec;
    }

    public void setSpec(Specification<Order> spec) {
        this.spec = spec;
    }

    public String getFilterDefinition() {
        return filterDefinition;
    }

    public void setFilterDefinition(String filterDefinition) {
        this.filterDefinition = filterDefinition;
    }
}
