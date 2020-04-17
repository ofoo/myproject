package com.guoguo.murongqing.service.impl.prescription;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoguo.murongqing.dao.prescription.PrescriptionDao;
import com.guoguo.murongqing.dao.prescription.PrescriptionZhongyaoDao;
import com.guoguo.murongqing.entity.common.Fenye;
import com.guoguo.murongqing.entity.prescription.Prescription;
import com.guoguo.murongqing.entity.prescription.PrescriptionZhongyao;
import com.guoguo.murongqing.service.prescription.PrescriptionService;
import com.guoguo.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("prescriptionService")
public class PrescriptionServiceImpl implements PrescriptionService {
    @Autowired
    private PrescriptionDao prescriptionDao;
    @Autowired
    private PrescriptionZhongyaoDao prescriptionZhongyaoDao;

    @Override
    @Transactional
    public int addPrescription(Prescription prescription) {
        int row = prescriptionDao.addPrescription(prescription);
        if (row>0) {
            List<PrescriptionZhongyao> pzList = prescription.getPzList();
            if (ObjectUtils.isNotNull(pzList)) {
                //封装药方id
                for (PrescriptionZhongyao pz : pzList) {
                    pz.setPrescriptionId(prescription.getId());
                }
                int row1 = prescriptionZhongyaoDao.addPzBatch(pzList);
            }
        }
        return row;
    }

    @Override
    public PageInfo<Prescription> getPrescriptionList(Prescription prescription, Fenye fenye) {
        PageHelper.startPage(fenye.getPageNum(), fenye.getPageSize(),"id desc");
        List<Prescription> list = prescriptionDao.getPrescriptionList(prescription);
        /*if (ObjectUtils.isNotNull(list)) {
            for (Prescription p : list) {
                //查询药方药材
                List<PrescriptionZhongyao> pzList = prescriptionZhongyaoDao.getPzListByPId(p.getId());
                p.setPzList(pzList);
            }
        }*/
        PageInfo<Prescription> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int delPrescription(Long[] ids) {
        return prescriptionDao.delPrescription(ids);
    }

    @Override
    public Prescription getPrescriptionById(Long id) {
        Prescription prescription = prescriptionDao.getPrescriptionById(id);
        if (ObjectUtils.isNotNull(prescription)) {
            //查询药方药材
            List<PrescriptionZhongyao> pzList = prescriptionZhongyaoDao.getPzListByPId(prescription.getId());
            prescription.setPzList(pzList);
        }
        return prescription;
    }

    @Override
    public int updatePrescriptionName(Prescription prescription) {
        return prescriptionDao.updatePrescriptionName(prescription);
    }

    @Override
    public int updateStatus(Long id) {
        return prescriptionDao.updateStatus(id);
    }
}
