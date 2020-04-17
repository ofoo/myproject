package com.guoguo.murongqing.dao.prescription;

import com.guoguo.murongqing.entity.prescription.Prescription;

import java.util.List;

public interface PrescriptionDao {

    int addPrescription(Prescription prescription);

    List<Prescription> getPrescriptionList(Prescription prescription);

    int delPrescription(Long[] ids);

    Prescription getPrescriptionById(Long id);

    int updatePrescriptionName(Prescription prescription);

    int updateStatus(Long id);
}
