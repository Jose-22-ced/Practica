package com.es.practica.service;



import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.es.practica.models.vm.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class S3Service {
    private final static String BUCKET = "springboot1bucket";

    @Autowired
    private AmazonS3Client amazonS3Client;


    public String putObject(MultipartFile multipartFile){
        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        String key = String.format("%s.%s", UUID.randomUUID(),extension);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET,key,multipartFile.getInputStream(),objectMetadata);
            putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3Client.putObject(putObjectRequest);
            return key;
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public Asset getObject(String key){
        S3Object  s3Object = amazonS3Client.getObject(BUCKET,key);
        ObjectMetadata objectMetadata = s3Object.getObjectMetadata();

        try {
            S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
            byte[] bytes = IOUtils.toByteArray(s3ObjectInputStream);

            return new Asset(bytes,objectMetadata.getContentType());
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public void delectObject(String key){
        amazonS3Client.deleteObject(BUCKET,key);
    }

    public String getObjectUrl(String key){
        return String.format("https://%s.s3.amazonaws.com/%s",BUCKET,key);
    }

}
