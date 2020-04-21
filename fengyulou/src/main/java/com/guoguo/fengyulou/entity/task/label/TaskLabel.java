package com.guoguo.fengyulou.entity.task.label;

import lombok.Data;

/**
 * 任务标签
 */
@Data
public class TaskLabel {
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 0正常 1删除
     */
    private Integer delete;

    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页条数
     */
    private Integer pageSize;
}
