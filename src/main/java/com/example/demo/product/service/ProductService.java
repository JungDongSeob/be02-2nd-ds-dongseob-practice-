package com.example.demo.product.service;

import com.example.demo.product.model.PostCreateReq;
import com.example.demo.product.model.Product;
import com.example.demo.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class ProductService {
    @Value("${project.upload.path}")
    private String uploadPath;
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public String makeFolder(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        folderPath = uploadPath + File.separator + folderPath;

        File uploadPathFolder = new File(folderPath);
        if(uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }

        return folderPath;
    }
    public String saveFile(MultipartFile file) {
        String originalName = file.getOriginalFilename();

        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();
        String saveFileName = folderPath + File.separator + uuid + "_" + originalName;
        File saveFile = new File(saveFileName);

        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return saveFileName;
    }
    public void create(PostCreateReq postCreateReq){
        String saveFileName = saveFile(postCreateReq.getImage());

        productRepository.save(Product.builder()
                .image(saveFileName.replace(File.separator, "/"))
                .name(postCreateReq.getName())
                .information(postCreateReq.getInformation())
                .build());
    }
    public void read(){

    }
    public void delete(){

    }
}
