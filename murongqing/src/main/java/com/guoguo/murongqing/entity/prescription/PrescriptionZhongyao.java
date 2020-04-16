package com.guoguo.murongqing.entity.prescription;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 药方中药
 */
@Data
public class PrescriptionZhongyao {
    private Long id;
    /**
     * 药方id
     */
    private Long prescriptionId;
    /**
     * 中药名称
     */
    private String name;
    /**
     * 中药剂量
     */
    private String dosage;
}
