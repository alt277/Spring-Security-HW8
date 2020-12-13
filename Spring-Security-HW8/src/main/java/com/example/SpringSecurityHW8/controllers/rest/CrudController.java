package com.example.SpringSecurityHW8.controllers.rest;

import com.example.SpringSecurityHW8.data.ProductData;
import com.example.SpringSecurityHW8.exceptions.ResourceNotFoundException;
import com.example.SpringSecurityHW8.facade.ProductFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class CrudController {

    private  ProductFacade productFacade;

    public CrudController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    //get          /demo      получить все данные
    //get          /demo/1    получить по идентификатору 1
    //post         /demo      создать данные
    //put          /demo/1    обновить данные объекта по идентификатору 1
    //patch        /demo/1    обновить конкретные поля для объекта по идентификатору 1
    //delete       /demo/1    удалить ресурс по идентификатору 1

    @GetMapping
    public List<ProductData> getDemo() {
        return productFacade.getAllProductData();
    }

    @GetMapping("/{id}")
    public ProductData getDemo(
            @PathVariable("id") Long id
    ) {
        return productFacade
                .getAllProductData()
                .stream()
                .filter(productData -> productData.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists"));
    }

    @PostMapping
    public String postDemo(
            @ModelAttribute String demo
    ) {
        return "post demo " + demo;
    }

    @PutMapping("/{id}")
    public String putDemo(
            @PathVariable("id") String id,
            @ModelAttribute String demo
    ) {
        return "put demo " + id;
    }

    @PatchMapping("/{id}")
    public String patchDemo(
            @PathVariable("id") String id,
            @ModelAttribute String demo
    ) {
        return "patch demo " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteDemo(
            @PathVariable("id") String id
    ) {
        return "remove demo " + id;
    }
}
