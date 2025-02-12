package com.spring.mail.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    Resource downloadFile(String fileName);
    void uploadFile(MultipartFile file);
}
