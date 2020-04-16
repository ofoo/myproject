package com.guoguo.murongqing.service.prescription;

import com.guoguo.murongqing.entity.prescription.PrescriptionZhongyao;

import java.util.List;

public interface PrescriptionZhongyaoService {
    /**
     * 根据药方id查询药方药材
     * @param pId
     * @return
     */
    List<PrescriptionZhongyao> getPzListByPId(Long pId);
}
