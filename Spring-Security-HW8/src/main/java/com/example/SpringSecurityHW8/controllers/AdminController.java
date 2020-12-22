package com.example.SpringSecurityHW8.controllers;

import com.example.SpringSecurityHW8.entities.Order;
import com.example.SpringSecurityHW8.entities.Product;
import com.example.SpringSecurityHW8.entities.User;
import com.example.SpringSecurityHW8.exceptions.ResourceNotFoundException;
import com.example.SpringSecurityHW8.services.OrderService;
import com.example.SpringSecurityHW8.services.ProductService;
import com.example.SpringSecurityHW8.services.UserService;
import com.example.SpringSecurityHW8.utils.OrderFilter;
import com.example.SpringSecurityHW8.utils.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private ProductService productService;
    private OrderService orderService;
    private UserService userService;

    public AdminController(ProductService productService, OrderService orderService, UserService userService) {
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @GetMapping("/users")
    public String allUsers(Model model,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size) {

        PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, size.orElse(4),
                Sort.by("username").ascending());
        model.addAttribute("userPage", userService.findAllUsers(pageRequest));
        return "users";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @GetMapping("/products")
    public String showAllProducts(Model model,
                                  @RequestParam(defaultValue = "1", name = "p") Integer page,
                                  @RequestParam Map<String, String> params
    ) {
        page = (page < 1) ? 1 : page;
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> products = productService.findAll(productFilter.getSpec(), page - 1, 5);
        model.addAttribute("products", products);
        model.addAttribute("filterDefinition", productFilter.getFilterDefinition());
        productService.getProductByMaxPrice();

        return "products";
    }

    @GetMapping("/add")
    public String addProduct(
            Model model
    ) {
        model.addAttribute("product", new Product());
        return "product_add_form";
    }

    @PostMapping("/add")
    public String addProduct(
            @Valid @ModelAttribute Product product,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "product_add_form";
        }
        productService.addProduct(product);
        return "redirect:/products";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists (for edit)"));
        model.addAttribute("product", p);
        return "product_edit_form";
    }


    @PostMapping("/edit")
    public String showEditForm(@ModelAttribute Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/products";
    }


    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String deleteOneProductById(@PathVariable Long id) {
        productService.deleteById(id);
        return "ok";
    }


    @GetMapping("/orders")
    public String orders(
            @RequestParam Map<String, String> params,
            Model model
    ) {
        OrderFilter orderFilter = new OrderFilter(params);
        List<Order> orders = orderService.findAll(orderFilter.getSpec());
        model.addAttribute("orders", orders);
        return "orders";
    }


    @GetMapping("/orders/remove/{id}")
    public String remove(
            @PathVariable("id") Long id,
            Model model
    ) {
        orderService.remove(id);
        return "redirect:/orders";
    }

    @PostMapping("/user/update")
    public String updateUser(Model model, User user, BindingResult bindingResult,
                             @RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size) {
      User user1=new User();
      user1.setName(user.getName());
      user1.setRoles(user.getRoles());
        userService.save(user1);
        PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, size.orElse(4),
                Sort.by("username").ascending());
        model.addAttribute("userPage", userService.findAllUsers(pageRequest));

        return "users";
    }

    @GetMapping("/new_user")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }
}
