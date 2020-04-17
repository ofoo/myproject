package com.guoguo.murongqing.dao.prescription;

import com.guoguo.murongqing.entity.prescription.PrescriptionZhongyao;

import java.util.List;

public interface PrescriptionZhongyaoDao {

    int addPzBatch(List<PrescriptionZhongyao> pzList);

    List<PrescriptionZhongyao> getPzListByPId(Long pId);
}
