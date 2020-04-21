package com.guoguo.fengyulou.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskVo {
    private Long id;
    /**
     * 任务简述
     */
    private String sketch;
    /**
     * 所属类型
     */
    private String type;
    /**
     * 任务状态 1未完成 2已完成
     */
    private Integer status;
    /**
     * 所属项目
     */
    private String project;
    /**
     * 负责人id
     */
    private Long userId;
    /**
     * 创建人id
     */
    private Long createUserId;
    /**
     * 完成时间
     */
    private Date finishTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * id串
     */
    private List<Long> ids;
}
