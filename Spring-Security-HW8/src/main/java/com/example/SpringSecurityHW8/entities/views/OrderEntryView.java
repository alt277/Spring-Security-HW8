package com.example.SpringSecurityHW8.entities.views;

public final class OrderEntryView extends CommonView {

    public interface Entry extends CommonFull, ProductView.FullProduct, OrderView.IdCode {}
    public interface Order extends CommonFull, ProductView.FullProduct, OrderView.IdCode {}

}