package com.soft_cup.service.controller;

import com.soft_cup.service.Service.FileManageService;
import com.soft_cup.service.utils.ResponseResult;
import com.soft_cup.service.utils.TaskId;
import com.soft_cup.service.utils.UploadAndRun;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author szc
 * @create 2022/7/5 22:45
 */
@RestController
@RequestMapping("/Inf/ChangeDetection")
public class ChangeDetectionController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FileManageService fileManageService;

    UploadAndRun uploadAndRun = new UploadAndRun();

    @PostMapping("/uploadAndRun/files")
    @ResponseBody
    public ResponseResult filesuploadAndRun(@RequestParam("files") MultipartFile[] files){
        return uploadAndRun.goFiles(rabbitTemplate, fileManageService, files, TaskId.CHANGE_DETECTION);
    }

    @PostMapping("/uploadAndRun/zip")
    @ResponseBody
    public ResponseResult zipUploadAndRun(@RequestParam("zip") MultipartFile zip){
        return uploadAndRun.goZip(rabbitTemplate, fileManageService, zip, TaskId.CHANGE_DETECTION);
    }
}
