package com.example.SpringSecurityHW8.controllers;

import com.example.SpringSecurityHW8.entities.Order;
import com.example.SpringSecurityHW8.entities.Product;
import com.example.SpringSecurityHW8.exceptions.ResourceNotFoundException;
import com.example.SpringSecurityHW8.services.CartService;
import com.example.SpringSecurityHW8.services.OrderService;
import com.example.SpringSecurityHW8.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ProductService productService;
    private CartService cartService;
    private OrderService orderService;

    public CartController(ProductService productService, CartService cartService, OrderService orderService) {
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping
    public String showCartPage() {
        return "cart";
    }

    @GetMapping("/add/{product_id}")
    public void addToCart(
            @PathVariable(name = "product_id") Long productId
            ,HttpServletRequest request
            ,HttpServletResponse response
    ) throws IOException {
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + productId + " doesn't exists (add to cart"));
        cartService.addOneAndUpdate(product);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/removeOne/{product_id}")
    public String removeOne(@PathVariable(name = "product_id") Long productId) {
        cartService.removeOneAndUpdate(productId);
        return "redirect:/cart";
    }

    @GetMapping("/removeAll/{product_id}")
    public String removeAll(@PathVariable(name = "product_id") Long productId) {
        cartService.removeAll(productId);
        return "redirect:/cart";
    }


    @GetMapping("/clearCart")
    public String clearCart(
            Model model
    ) {
        cartService.clearCart();
        return "cart";
    }

    @GetMapping("/createOrder")
    public String createOrder(
            Model model
    ) {
        Order order = orderService.createOrder();
        model.addAttribute("order", order);
        return "success";
    }
}
