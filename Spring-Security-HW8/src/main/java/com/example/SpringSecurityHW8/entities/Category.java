package com.example.SpringSecurityHW8.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends AbstractItem {

    @NotEmpty
    @Size(min=3, max=20, message="Title must have 3-20 characters")
    @Column(name = "code")
    private String code;

    @NotEmpty
    @Size(min=3, max=20, message="Title must have 3-20 characters")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> productList = new ArrayList<>();

    public Category() {

    }

    public Category(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
