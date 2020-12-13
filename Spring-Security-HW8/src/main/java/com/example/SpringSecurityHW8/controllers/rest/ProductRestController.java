package com.example.SpringSecurityHW8.controllers.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.SpringSecurityHW8.data.ProductData;
import com.example.SpringSecurityHW8.entities.Product;
import com.example.SpringSecurityHW8.entities.views.ProductView;
import com.example.SpringSecurityHW8.exceptions.ResourceNotFoundException;
import com.example.SpringSecurityHW8.facade.ProductFacade;
import com.example.SpringSecurityHW8.services.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products/api/v1")
public class ProductRestController {

    private ProductService productService;
    private ProductFacade productFacade;

    public ProductRestController(ProductService productService, ProductFacade productFacade) {
        this.productService = productService;
        this.productFacade = productFacade;
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Product> productsToXml() {
        return productService.findAll();
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> productsToJson() {
        return productService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(
            @PathVariable("id") Long id
    ) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id=" + id + " not found"));
    }

    @PostMapping
    public Product createProduct(
            @RequestBody Product product
    ) {
        product.setId(null);
        return productService.saveOrUpdate(product);
    }

    @PutMapping("/{id}")
    public Product putProduct(
            @PathVariable("id") Long id,
            @RequestBody Product product
    ) {
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(
            @PathVariable("id") Long id
    ) {
        System.out.println("id = " + id);
        productService.remove(id);
    }

    @GetMapping(value = "/jsonData/{id}",produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ProductData productDataToJson(
            @PathVariable Long id
    ){
        return productFacade.getProductById(id);
    }

    @GetMapping(value = "/jsonData")
    @ResponseBody
    public List<ProductData> productDataToJson(){
        return productFacade.getAllProductDataFromRepository();
    }

    @GetMapping(value = "/id", produces= MediaType.APPLICATION_JSON_VALUE)
    @JsonView(ProductView.Id.class)
    @ResponseBody
    public List<Product> productIdToJson(){
        return productService.findAll();
    }

    @GetMapping(value = "/idName", produces= MediaType.APPLICATION_JSON_VALUE)
    @JsonView(ProductView.IdName.class)
    @ResponseBody
    public List<Product> productIdNameToJson(){
        return productService.findAll();
    }

    @GetMapping(value = "/fullPlain", produces= MediaType.APPLICATION_JSON_VALUE)
    @JsonView(ProductView.FullProduct.class)
    @ResponseBody
    public List<Product> productFullPlainToJson(){
        return productService.findAll();
    }

    @GetMapping(value = "/responseEntityToJson", produces= MediaType.APPLICATION_JSON_VALUE)
    @JsonView(ProductView.FullProduct.class)
    @ResponseBody
    public ResponseEntity responseEntityToJson(){
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Stackoverflow");
        map.put("name", "Alex");
        map.put("products", productService.findAll());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }



    @GetMapping(value = "/entityWithPaging", params = { "page", "size" })
    @JsonView(ProductView.FullProduct.class)
    public ResponseEntity entityWithPaging(
            Pageable pageable,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ){
        PageRequest pageRequest1 = PageRequest.of(page, size);

        System.out.println(pageable);
        System.out.println(pageRequest1);

        Map<String, Object> map = new HashMap<>();
        map.put("products", productService.findAll());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
