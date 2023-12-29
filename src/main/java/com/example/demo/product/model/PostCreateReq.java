package com.example.demo.product.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
@Data
@Builder
public class PostCreateReq {
    private MultipartFile image;
    private String name;
    private String information;

}
