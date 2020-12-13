package com.example.SpringSecurityHW8.controllers.rest;

import com.example.SpringSecurityHW8.data.ProductData;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/api/v2")
public class TestController {

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {"application/product.v1+json"}
            )
    public ResponseEntity<ProductData> createProductV1(
            @RequestBody ProductData productData
    ) {
        System.out.println("v1 " + productData);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {"application/product.v2+json"}
            )
    public ResponseEntity<ProductData> createProductV2(
            @RequestBody ProductData productData
    ) {
        System.out.println("v2 " + productData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
