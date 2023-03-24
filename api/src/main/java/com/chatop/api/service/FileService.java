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

    // Upload picture file and return the fileURL if success
    public String uploadPicture(MultipartFile multipartFile) throws IOException {

        // define the folder for the upload
        // !note use of resource folder to allow external access to the uploaded files
        Path uploadPath = Paths.get(String.format("/src/main/resources/%s", UPLOAD_FOLDER)).normalize();

        // create directory if not found
        if (!Files.exists(uploadPath)) {

            Files.createDirectories(uploadPath);

        }

        try (InputStream inputStream = multipartFile.getInputStream()) {

            String filename = multipartFile.getOriginalFilename();

            Path filePath = uploadPath.resolve(filename);

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            return getFileUrl(filename);

        } catch (IOException ioe) {
            return null;
        }
    }

    // generate full URL of the uploaded file example :
    // http://localhost:3001/images/myimage.png
    private String getFileUrl(String filename) {

        String url = String.format("http://%s:%s/%s/%s", this.HOSTNAME, this.SERVER_PORT, this.UPLOAD_FOLDER, filename);

        return UriComponentsBuilder.fromHttpUrl(url).toUriString();
    }

}