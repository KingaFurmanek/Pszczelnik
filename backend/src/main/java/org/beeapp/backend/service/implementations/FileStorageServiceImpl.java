package org.beeapp.backend.service.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beeapp.backend.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${application.file.upload.photos}")
    private String fileUploadPath;

    @Override
    public String saveFile(MultipartFile file) {
        return uploadFile(file);
    }

    private String uploadFile(MultipartFile file) {
        final String finalUploadPath = fileUploadPath + File.separator;
        File targetFolder = new File(finalUploadPath);

        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if (!folderCreated) {
                log.warn("Failed to create the target folder: {}", targetFolder);
                return null;
            }
        }
        final String fileExtension = getFileExtension(file.getOriginalFilename());
        String targetFilePath = finalUploadPath + System.currentTimeMillis() + "." + fileExtension;
        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.write(targetPath, file.getBytes());
            log.info("File saved to: {}", targetFilePath);
            return targetFilePath;
        } catch (IOException e) {
            log.error("File was not saved", e);
        }
        return null;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1).toLowerCase();
    }
}
