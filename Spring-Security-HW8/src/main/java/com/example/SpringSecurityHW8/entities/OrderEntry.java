package com.example.SpringSecurityHW8.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.SpringSecurityHW8.entities.views.OrderEntryView;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderEntry extends AbstractItem {

    @Column(name = "quantity")
    @JsonView(OrderEntryView.Entry.class)
    private int quantity;

    @Column(name = "price_per_product")
    @JsonView(OrderEntryView.Entry.class)
    private Double basePrice;

    @Column(name = "price")
    @JsonView(OrderEntryView.Entry.class)
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonView(OrderEntryView.Entry.class)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonView(OrderEntryView.Order.class)
    private Order order;

    public OrderEntry() {
    }

    public OrderEntry(Product product) {
        this.product = product;
        this.quantity = 1;
        this.basePrice = product.getPrice();
        this.totalPrice = product.getPrice();
    }

    public OrderEntry(Product product, Order order) {
        this.product = product;
        this.order = order;
        this.quantity = 1;
        this.basePrice = product.getPrice();
        this.totalPrice = product.getPrice();
        order.getOrderEntries().add(this);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
