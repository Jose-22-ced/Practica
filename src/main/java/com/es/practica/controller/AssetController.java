package com.es.practica.controller;


import com.es.practica.models.vm.Asset;
import com.es.practica.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    Map<String,String> uStringStringMap(@RequestParam MultipartFile multipartFile){
        String key = s3Service.putObject(multipartFile);

        Map<String,String> result = new HashMap<>();
        result.put("key",key);
        result.put("url",s3Service.getObjectUrl(key));

        return result;
    }

    @GetMapping(value = "/get-object",params = "key")
    ResponseEntity<ByteArrayResource> getObject(@RequestParam String key){
        Asset asset = s3Service.getObject(key);
        ByteArrayResource byteArrayResource = new ByteArrayResource(asset.getContent());

        return ResponseEntity
                .ok()
                .header("Content-Type",asset.getContentType())
                .contentLength(asset.getContent().length)
                .body(byteArrayResource);
    }

    @DeleteMapping(value = "/delect-object",params = "key")
    void delectObject(@RequestParam String key){
        s3Service.delectObject(key);
    }
}
