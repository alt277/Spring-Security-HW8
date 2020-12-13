package com.example.SpringSecurityHW8.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.SpringSecurityHW8.entities.views.ProductView;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product extends AbstractItem {

    @NotEmpty
    @Size(min=3, max=20, message="Title must have 3-20 characters")
    @Column(name = "title")
    @JsonView(ProductView.IdName.class)
    private String title;

    @NotEmpty
    @Size(min=3, max=20, message="Title must have 3-20 characters")
    @Column(name = "brand_name")
    @JsonView(ProductView.IdName.class)
    private String brandName;

    @Column(name = "image")
    @JsonView(ProductView.FullProduct.class)
    private String image;

    @Column(name = "price")
    @JsonView(ProductView.FullProduct.class)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
    }

    public Product(String title, String brandName, Double price, String image, Category category) {
        this.title = title;
        this.brandName = brandName;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", brandName=" + brandName +
                ", image=" + image +
                '}';
    }
}
