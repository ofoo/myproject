package com.guoguo.murongqing.entity.common;

import lombok.Data;

/**
 * 分页对象
 */
@Data
public class Fenye {
    /**
     * 页号
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;

    public Fenye() {
        this.pageNum = 1;
        this.pageSize = 8;
    }
}
