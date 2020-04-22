package com.guoguo.fengyulou.controller.member.label;

import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.member.label.MemberLabel;
import com.guoguo.fengyulou.service.member.label.MemberLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 人员标签管理
 */
@Controller
@RequestMapping("/fyl")
public class MemberLabelController {

    @Autowired
    private MemberLabelService memberLabelService;

    /**
     * 列表页面
     *
     * @param request
     * @param memberLabel
     * @return
     */
    @RequestMapping("/memberLabel/list/page")
    public String list(HttpServletRequest request, MemberLabel memberLabel) {
        request.setAttribute("data", memberLabel);
        request.setAttribute("pageInfo", memberLabelService.getMemberLabelListPage(memberLabel));
        return "/member/label/member-label-list";
    }

    /**
     * 添加页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/memberLabel/insert")
    public String insert(HttpServletRequest request) {
        request.setAttribute("pageTitle", "添加人员标签");
        return "memberLabel/memberLabel-save";
    }

    /**
     * 修改页面
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/memberLabel/update/{id}")
    public String update(HttpServletRequest request, @PathVariable Long id) {
        request.setAttribute("pageTitle", "修改人员标签");
        // 查询人员标签
        request.setAttribute("data", memberLabelService.getMemberLabelById(id));
        return "memberLabel/memberLabel-save";
    }

    /**
     * 保存数据
     *
     * @param memberLabel
     * @return
     */
    @RequestMapping("/memberLabel/ajax/save")
    @ResponseBody
    public ServerResponse ajaxSave(MemberLabel memberLabel) {
        return memberLabelService.saveMemberLabel(memberLabel);
    }

    /**
     * 按id删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping("/memberLabel/ajax/delete")
    @ResponseBody
    public ServerResponse ajaxDelete(List<Long> ids) {
        return memberLabelService.deleteMemberLabelByIds(ids);
    }

    /**
     * 下拉选列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/memberLabel/ajax/list")
    public String ajaxList(HttpServletRequest request) {
        request.setAttribute("list", memberLabelService.getMemberLabelList(null));
        return "common/select-item";
    }
}
