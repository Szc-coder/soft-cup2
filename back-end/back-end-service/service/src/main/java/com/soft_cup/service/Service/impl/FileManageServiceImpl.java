package com.soft_cup.service.Service.impl;

import com.soft_cup.service.Service.FileManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author szc
 * @create 2022/7/5 21:59
 */
@Service
public class FileManageServiceImpl implements FileManageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileManageService.class);

    @Value("${file_hostPath}")
    private String fileHostPath;

    @Override
    public List<String> uploadFiles(MultipartFile[] files) throws IOException {
        List<String> fileLocations = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            if (!multipartFile.isEmpty()) {
                String originalFilename = multipartFile.getOriginalFilename();
                multipartFile.transferTo(new File(fileHostPath + originalFilename));
                fileLocations.add(fileHostPath+originalFilename);
            }
        }
        return fileLocations;
    }

    @Override
    public List<String> uploadZip(MultipartFile zip) throws IOException {
        List<String> fileLocations = new ArrayList<>();
        String originalFilename = zip.getOriginalFilename();
        fileLocations.add(fileHostPath+originalFilename);
        zip.transferTo(new File(fileHostPath + originalFilename));
        return fileLocations;
    }
}
