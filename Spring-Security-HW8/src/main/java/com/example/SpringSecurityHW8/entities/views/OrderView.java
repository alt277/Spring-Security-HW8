package com.example.SpringSecurityHW8.entities.views;

public final class OrderView extends CommonView {

    public interface IdCode extends CommonFull{}
    public interface IdCodePriceUser extends CommonFull, UserView.IdName{}
    public interface IdCodeUserOrderEntry extends IdCodePriceUser, OrderEntryView.Entry{}

}