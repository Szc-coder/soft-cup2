package com.soft_cup.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author szc
 * @create 2022/6/26 21:00
 */
@Data
@AllArgsConstructor
public class Task {
    private Integer taskId;
    private List<String> fileLocation;
    private Integer mode;
    private String uuid;
}
