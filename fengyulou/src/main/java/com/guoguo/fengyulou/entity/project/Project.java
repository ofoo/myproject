package com.guoguo.fengyulou.entity.project;

import lombok.Data;

/**
 * 项目
 */
@Data
public class Project {
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
