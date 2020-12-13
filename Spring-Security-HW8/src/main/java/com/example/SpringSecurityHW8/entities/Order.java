package com.example.SpringSecurityHW8.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.SpringSecurityHW8.entities.views.OrderView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends AbstractItem {

    @Column(name = "code")
    @JsonView(OrderView.IdCode.class)
    private String code;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(OrderView.IdCodePriceUser.class)
    private User user;

    @Column(name = "totalPrice")
    @JsonView(OrderView.IdCodePriceUser.class)
    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JsonView(OrderView.IdCodeUserOrderEntry.class)
    private List<OrderEntry> orderEntries = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(List<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }
}
