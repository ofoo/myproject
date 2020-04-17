package com.guoguo.murongqing.service.prescription;

import com.github.pagehelper.PageInfo;
import com.guoguo.murongqing.entity.common.Fenye;
import com.guoguo.murongqing.entity.prescription.Prescription;

import java.util.List;

public interface PrescriptionService {
    /**
     * 添加药方
     * @param prescription
     * @return
     */
    int addPrescription(Prescription prescription);

    /**
     * 药方列表
     * @param prescription
     * @return
     */
    PageInfo<Prescription> getPrescriptionList(Prescription prescription, Fenye fenye);

    /**
     * 根据id删除药方
     * @param ids
     * @return
     */
    int delPrescription(Long[] ids);

    /**
     * 根据id查询药方
     * @param id
     * @return
     */
    Prescription getPrescriptionById(Long id);

    /**
     * 修改药方名称
     * @param prescription
     * @return
     */
    int updatePrescriptionName(Prescription prescription);

    /**
     * 修改药方状态
     * @param id
     * @return
     */
    int updateStatus(Long id);
}
