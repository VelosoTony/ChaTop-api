package com.chatop.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;

@Service // Spécialisation de @Component
public class FileService {
      
    public static boolean uploadPicture(MultipartFile multipartFile) throws IOException {
        
        String uploadDir = "images/";

        String fileName = multipartFile.getOriginalFilename();

        Path uploadPath = Paths.get(uploadDir);
         
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException ioe) {     
            return false; 
        }
    }
}