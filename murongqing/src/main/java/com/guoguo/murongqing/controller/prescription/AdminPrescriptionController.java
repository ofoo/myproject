package com.guoguo.murongqing.controller.prescription;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.ServerResponse;
import com.guoguo.murongqing.controller.common.BaseController;
import com.guoguo.murongqing.entity.common.Fenye;
import com.guoguo.murongqing.entity.prescription.Prescription;
import com.guoguo.murongqing.entity.prescription.PrescriptionZhongyao;
import com.guoguo.murongqing.entity.zhongyao.Category;
import com.guoguo.murongqing.service.prescription.PrescriptionService;
import com.guoguo.util.DateUtils;
import com.guoguo.util.ObjectUtils;
import com.guoguo.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 中药管理
 */
@Controller
@RequestMapping("/admin/prescription")
@Slf4j
public class AdminPrescriptionController extends BaseController {

    @Autowired
    private PrescriptionService prescriptionService;

    /**
     * 分页查询药方
     *
     * @param model
     * @param prescription
     * @param fenye
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model, Prescription prescription, Fenye fenye) {
        PageInfo<Prescription> pageInfo = prescriptionService.getPrescriptionList(prescription, fenye);
        model.addAttribute("pageInfo", pageInfo);
        return "admin-prescription-list";
    }

    /**
     * 保存药方
     *
     * @param session
     * @param zyNames
     * @param dosages
     * @return
     */
    @RequestMapping("/ajax/save")
    @ResponseBody
    public ServerResponse ajaxSave(HttpSession session, @RequestParam String[] zyNames, @RequestParam String[] dosages) {
        if (ObjectUtils.isNotNull(zyNames)) {
            //药方
            String currentTime = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
            Prescription prescription = new Prescription();
            prescription.setName(currentTime);
            prescription.setNumber(currentTime);
            //药方中药
            List<PrescriptionZhongyao> pzList = new ArrayList<>();
            for (int i = 0; i < zyNames.length; i++) {
                PrescriptionZhongyao pz = new PrescriptionZhongyao();
                pz.setName(zyNames[i]);
                pz.setDosage(dosages[i]);
                pzList.add(pz);
            }
            prescription.setPzList(pzList);
            //添加药方
            int row = prescriptionService.addPrescription(prescription);
            if (row > 0) {
                //保存成功
                return ServerResponse.createBySuccess(currentTime);
            } else {
                //保存失败
                return ServerResponse.createByError();
            }
        } else {
            //保存失败
            return ServerResponse.createByError();
        }
    }

    /**
     * 根据id删除药方
     *
     * @param ids
     * @return
     */
    @RequestMapping("/ajax/del")
    @ResponseBody
    public ServerResponse ajaxDel(@RequestParam Long[] ids) {
        if (ObjectUtils.isNull(ids)) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        //执行删除操作
        int row = prescriptionService.delPrescription(ids);
        if (row > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        } else {
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

    /**
     * 修改药方
     *
     * @param id
     * @return
     */
    @RequestMapping("/update")
    public String update(Model model, @RequestParam Long id) {
        Prescription prescription = prescriptionService.getPrescriptionById(id);
        model.addAttribute("prescription", prescription);
        return "admin-prescription-save";
    }

    /**
     * 修改药方名称
     * @param prescription
     * @return
     */
    @RequestMapping("/ajax/update")
    @ResponseBody
    public ServerResponse ajaxUpdate(Prescription prescription) {
        if (StringUtils.isBlank(prescription.getName())) {
            return ServerResponse.createByErrorMessage("请输入药方名称");
        }
        if (ObjectUtils.isNull(prescription.getId())) {
            return ServerResponse.createByErrorMessage("修改失败");
        }
        int row = prescriptionService.updatePrescriptionName(prescription);
        if (row > 0) {
            return ServerResponse.createBySuccessMessage("修改成功");
        } else {
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }
}
