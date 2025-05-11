package com.spring.marketplace.controller;

import com.spring.marketplace.docs.documents.DownloadFileDoc;
import com.spring.marketplace.docs.documents.GetReportFilesNameDoc;
import com.spring.marketplace.docs.documents.UploadFileDoc;
import com.spring.marketplace.service.FileStorageService;
import com.spring.marketplace.service.ReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Tag(name = "Document Controller", description = "This controller is used for " +
        "uploading&downloading files by the user")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/documents")
public class DocumentsController {

    private final ReportService reportService;
    private final FileStorageService fileStorageService;

    @GetMapping
    @GetReportFilesNameDoc
    public ResponseEntity<List<String>> getReportFilesName() {
        return ResponseEntity.ok().body(reportService.getReportFilesName());
    }

    @PostMapping("/upload")
    @UploadFileDoc
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file) {
        fileStorageService.uploadFile(file);
        return ResponseEntity.ok("File was upload successfully");
    }

    @GetMapping("/download")
    @DownloadFileDoc
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {
        Resource fileToDownload = fileStorageService.downloadFile(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileToDownload);
    }
}