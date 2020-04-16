package com.guoguo.murongqing.service.impl.prescription;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoguo.murongqing.dao.prescription.PrescriptionDao;
import com.guoguo.murongqing.dao.prescription.PrescriptionZhongyaoDao;
import com.guoguo.murongqing.entity.common.Fenye;
import com.guoguo.murongqing.entity.prescription.Prescription;
import com.guoguo.murongqing.entity.prescription.PrescriptionZhongyao;
import com.guoguo.murongqing.service.prescription.PrescriptionZhongyaoService;
import com.guoguo.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("prescriptionZhongyaoService")
public class PrescriptionZhongyaoServiceImpl implements PrescriptionZhongyaoService {
    @Autowired
    private PrescriptionZhongyaoDao prescriptionZhongyaoDao;

    @Override
    public List<PrescriptionZhongyao> getPzListByPId(Long pId) {
        return prescriptionZhongyaoDao.getPzListByPId(pId);
    }
}
