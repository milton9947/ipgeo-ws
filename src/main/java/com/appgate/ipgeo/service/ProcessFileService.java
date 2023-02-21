package com.appgate.ipgeo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProcessFileService {
    public String storeFile(MultipartFile multipartFile) throws IOException;
    public void processFile(String pathFile) throws IOException;
}
