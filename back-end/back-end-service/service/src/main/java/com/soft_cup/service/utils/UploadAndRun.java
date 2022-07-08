package com.soft_cup.service.utils;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.ChannelN;
import com.soft_cup.service.Service.FileManageService;
import com.soft_cup.service.entity.Task;
import org.springframework.amqp.rabbit.connection.PublisherCallbackChannel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author szc
 * @create 2022/7/5 22:33
 */

public class UploadAndRun {
    public ResponseResult goFiles(RabbitTemplate rabbitTemplate, FileManageService fileManageService, MultipartFile[] files, Integer taskId)
    {
        if (Objects.isNull(files)){
            return new ResponseResult<>(300, "上传文件失败");
        }
        try {
            List<String> fileLoactions = fileManageService.uploadFiles(files);
            Task task = new Task(taskId, fileLoactions, 1, UUID.randomUUID().toString());
            rabbitTemplate.convertSendAndReceive("InferenceChannel",JSON.toJSONBytes(task));

            return new ResponseResult<>(200, "上传成功，请耐心等待推理成功");
        }
        catch (Exception e)
        {
            System.out.println(e);
            return new ResponseResult<>(300, "上传文件失败");
        }
    }

    public ResponseResult goZip(RabbitTemplate rabbitTemplate, FileManageService fileManageService,  MultipartFile zip, Integer taskId)
    {
        if (Objects.isNull(zip)){
            return new ResponseResult<>(300, "上传文件失败");
        }
        try {
            List<String> fileLoaction = fileManageService.uploadZip(zip);
            Task task = new Task(taskId, fileLoaction, 0, UUID.randomUUID().toString());
            rabbitTemplate.convertSendAndReceive("InferenceChannel",JSON.toJSONBytes(task));

            return new ResponseResult<>(200, "上传成功，请耐心等待推理成功");
        }
        catch (Exception e)
        {
            System.out.println(e);
            return new ResponseResult<>(300, "上传文件失败");
        }
    }

}
