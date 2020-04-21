package com.guoguo.fengyulou.entity.member.label;

import lombok.Data;

/**
 * 人员列表
 */
@Data
public class MemberLabel {
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
