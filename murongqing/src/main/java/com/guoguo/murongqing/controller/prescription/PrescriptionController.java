package com.guoguo.murongqing.controller.prescription;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.ServerResponse;
import com.guoguo.murongqing.controller.common.BaseController;
import com.guoguo.murongqing.entity.common.Fenye;
import com.guoguo.murongqing.entity.prescription.Prescription;
import com.guoguo.murongqing.entity.prescription.PrescriptionZhongyao;
import com.guoguo.murongqing.service.prescription.PrescriptionService;
import com.guoguo.util.DateUtils;
import com.guoguo.util.ObjectUtils;
import com.guoguo.util.StringUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
@Slf4j
public class PrescriptionController extends BaseController {

    @Autowired
    private PrescriptionService prescriptionService;

    /**
     * 药方列表
     *
     * @param model
     * @param fenye
     * @return
     */
    @RequestMapping("/plist")
    public String list(Model model, Fenye fenye) {
        fenye.setPageSize(Integer.MAX_VALUE);
        PageInfo<Prescription> pageInfo = prescriptionService.getPrescriptionList(null, fenye);
        model.addAttribute("pageInfo", pageInfo);
        return "prescription-list";
    }

    /**
     * 药方详情
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/pinfo/{id}")
    public String info(Model model, @PathVariable Long id) {
        Prescription prescription = prescriptionService.getPrescriptionById(id);
        model.addAttribute("p", prescription);
        return "prescription-info";
    }

    /**
     * 修改药方状态
     *
     * @param id
     * @return
     */
    @RequestMapping("/pu/status/{id}")
    public String updateStatus(@PathVariable Long id) {
        int row = prescriptionService.updateStatus(id);
        log.info("row: " + row);
        return redirectPage("/plist");
    }
}
