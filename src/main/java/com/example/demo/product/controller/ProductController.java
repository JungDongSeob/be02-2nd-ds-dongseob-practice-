package com.example.demo.product.controller;

import com.example.demo.product.model.PostCreateReq;
import com.example.demo.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("create")
    public ResponseEntity create(PostCreateReq postCreateReq){
        productService.create(postCreateReq);
        return ResponseEntity.ok().body("상품 등록 완료");
    }
}
