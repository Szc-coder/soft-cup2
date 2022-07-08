package com.soft_cup.service.controller;

import com.soft_cup.service.Service.FileManageService;
import com.soft_cup.service.entity.Task;
import com.soft_cup.service.utils.ResponseResult;
import com.soft_cup.service.utils.TaskId;
import com.soft_cup.service.utils.UploadAndRun;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Author szc
 * @create 2022/7/5 22:02
 */
@RestController
@RequestMapping("/Inf/ObjectDetection")
public class ObjectDetectionController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FileManageService fileManageService;

    UploadAndRun uploadAndRun = new UploadAndRun();

    @PostMapping("/uploadAndRun/files")
    @ResponseBody
    public ResponseResult filesuploadAndRun(@RequestParam("files") MultipartFile[] files){
        return uploadAndRun.goFiles(rabbitTemplate, fileManageService, files, TaskId.OBJECT_DETECTION);
    }

    @PostMapping("/uploadAndRun/zip")
    @ResponseBody
    public ResponseResult zipUploadAndRun(@RequestParam("zip") MultipartFile zip){
        return uploadAndRun.goZip(rabbitTemplate, fileManageService, zip, TaskId.OBJECT_DETECTION);
    }

}
