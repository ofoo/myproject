package com.guoguo.fengyulou.entity.member;

import lombok.Data;

/**
 * 人员
 */
@Data
public class Member {
    private Long id;
    /**
     * 人员姓名
     */
    private String name;
    /**
     * 人员标签id
     */
    private Long memberLabelId;
    /**
     * 人员手机号
     */
    private String mobile;
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
    /**
     * 人员标签
     */
    private String memberLabelName;
}
