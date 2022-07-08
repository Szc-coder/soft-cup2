package com.soft_cup.service.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author szc
 * @create 2022/7/5 21:58
 */

public interface FileManageService {
    List<String> uploadFiles(MultipartFile[] files) throws IOException;
    List<String> uploadZip(MultipartFile zip) throws IOException;
}
