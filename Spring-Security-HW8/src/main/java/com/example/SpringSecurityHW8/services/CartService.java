package com.example.SpringSecurityHW8.services;


import com.example.SpringSecurityHW8.entities.OrderEntry;
import com.example.SpringSecurityHW8.entities.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {

    private List<OrderEntry> orderEntries;
    private Double totalPrice = 0.0;
    private Integer totalQuantity = 0;

    @PostConstruct
    public void init() {
        orderEntries = new ArrayList<>();
    }

    public void addOneAndUpdate(Product p) {
        for (OrderEntry orderEntry : orderEntries) {
            if (orderEntry.getProduct().getId().equals(p.getId())) {
                orderEntry.setQuantity(orderEntry.getQuantity() + 1);
                recalculate();
                return;
            }
        }
        orderEntries.add(new OrderEntry(p));
        recalculate();
        //System.out.println("total = " + totalPrice);
        //System.out.println("size entry = " + orderEntries.size());
    }

    public void removeOneAndUpdate(Long productId) {
        Iterator<OrderEntry> iterator = getOrderEntryIterator();
        while (iterator.hasNext()) {
            OrderEntry orderEntry = iterator.next();
            if (orderEntry.getProduct().getId().equals(productId)) {
                if(orderEntry.getQuantity() - 1 == 0){
                    iterator.remove();
                } else {
                    orderEntry.setQuantity(orderEntry.getQuantity() - 1);
                }
                recalculate();
                return;
            }
        }
    }

    public void removeAll(Long productId) {
        Iterator<OrderEntry> iterator = getOrderEntryIterator();
        while (iterator.hasNext()) {
            OrderEntry o = iterator.next();
            if (o.getProduct().getId().equals(productId)) {
                iterator.remove();
                recalculate();
                return;
            }
        }
    }

    public void recalculate() {
        totalPrice = 0.0;
        totalQuantity = 0;
        for (OrderEntry orderEntry : orderEntries) {
            Double price = orderEntry.getBasePrice() * orderEntry.getQuantity();
            orderEntry.setTotalPrice(price);
            totalPrice += price;
            totalQuantity += orderEntry.getQuantity();
        }
    }

    public void clearCart() {
        orderEntries = new ArrayList<>();
        recalculate();
    }

    Iterator<OrderEntry> getOrderEntryIterator(){
        return orderEntries.iterator();
    }

    public List<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(List<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
