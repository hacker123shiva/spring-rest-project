package com.api.book.bootrestbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.book.bootrestbook.helpers.FileUploadHelper;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());
        System.out.println(file.getName());
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(500).body("Request must contain file");
            }

            // if (!file.getContentType().equals("image/jpeg")) {
            // return ResponseEntity.status(500).body("JPEG content type is not allowed");
            // }

            // file upload code...
            boolean f = fileUploadHelper.uploadFile(file);

            // Where to upload file on server:
            // upload_dir=D:\telusko\JavaAdvanceMock\SpringBootSTS\bootrestbook\src\main\resources\static

            if (f) {
                System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath());
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/images/").path(file.getOriginalFilename()).toUriString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong! Try again");

    }
}
