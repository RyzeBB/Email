package com.cwca.customer.course.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Course {
    private int id;
    private String name;
    private String title;
    private String startTime;
    private String  endTime;
    private String content;
    private String teacher;
    private Integer valid=1;
    private Integer learnCount;
    private Date createTime;

}
