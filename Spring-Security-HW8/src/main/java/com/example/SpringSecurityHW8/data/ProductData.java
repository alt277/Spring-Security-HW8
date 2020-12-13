package com.example.SpringSecurityHW8.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProductData {

    private Long id;
    private String title;
    private String brandName;
    private Double price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

    public ProductData() {
    }

    public ProductData(Long id, String title, String brandName, Double price, Date createDate, Date modifyDate) {
        this.id = id;
        this.title = title;
        this.brandName = brandName;
        this.price = price;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "ProductData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", brandName='" + brandName + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
