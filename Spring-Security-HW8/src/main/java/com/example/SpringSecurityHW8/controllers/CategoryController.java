package com.example.SpringSecurityHW8.controllers;

import com.example.SpringSecurityHW8.services.CategoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{code}")
    public String getOneProductById(
            @PathVariable String code,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "6") int size,
            Model model
    ) {
        page = page > 0 ? page - 1 : page;
        model.addAttribute("products", categoryService.getProductsByCategory(code, PageRequest.of(page, size)));
        model.addAttribute("categories", categoryService.getCategoryByCode(code));
        return "index";
    }
}
