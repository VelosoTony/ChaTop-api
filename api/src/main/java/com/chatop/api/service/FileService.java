package com.chatop.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.nio.file.*;




@Service // Spécialisation de @Component
public class FileService {

    @Value("${server.hostname}")
    private String HOSTNAME;

    @Value("${server.port}")
    private String SERVER_PORT;

    @Value("${upload.folder}")
    private String UPLOAD_FOLDER;



    public String uploadPicture(MultipartFile multipartFile) throws IOException {


    Path uploadPath = Paths.get("/src/main/resources/images").normalize();
        
    if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(multipartFile.getOriginalFilename());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            return getFileUrl(multipartFile.getOriginalFilename());

        } catch (IOException ioe) {     
            return null; 
        }
    }

    private String getFileUrl(String filename){
        
        String url = String.format("http://%s:%s/%s/%s",this.HOSTNAME,this.SERVER_PORT,this.UPLOAD_FOLDER, filename);

        return UriComponentsBuilder.fromHttpUrl(url).toUriString();
    }

}