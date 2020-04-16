package com.guoguo.murongqing.entity.prescription;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 药方
 */
@Data
public class Prescription {
    private Long id;
    /**
     * 药方名称
     */
    private String name;
    /**
     * 药方编号
     */
    private String number;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 状态 0未处理 1已处理
     */
    private Integer status;

    /**
     * id串
     */
    private List<Long> ids;
    /**
     * 药方中药
     */
    private List<PrescriptionZhongyao> pzList;
}
