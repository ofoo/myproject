package com.guoguo.fengyulou.controller.member;

import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.member.Member;
import com.guoguo.fengyulou.service.member.MemberService;
import com.guoguo.fengyulou.service.member.label.MemberLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 人员管理
 */
@Controller
@RequestMapping("/fyl")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberLabelService memberLabelService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping("/member/list/page")
    public String list(HttpServletRequest request, Member member) {
        request.setAttribute("member", member);
        request.setAttribute("pageInfo", memberService.getMemberListPage(member));
        return "/member/member-list";
    }

    /**
     * 添加页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/member/insert")
    public String insert(HttpServletRequest request) {
        request.setAttribute("pageTitle", "添加人员");
        // 查询人员标签列表
        request.setAttribute("memberLabelList", memberLabelService.getMemberLabelList(null));
        return "member/member-save";
    }

    /**
     * 修改页面
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/member/update/{id}")
    public String update(HttpServletRequest request, @PathVariable Long id) {
        request.setAttribute("pageTitle", "修改人员");
        // 查询人员
        request.setAttribute("data", memberService.getMemberById(id));
        // 查询人员标签列表
        request.setAttribute("memberLabelList", memberLabelService.getMemberLabelList(null));
        return "member/member-save";
    }

    /**
     * 保存数据
     *
     * @param member
     * @return
     */
    @RequestMapping("/member/ajax/save")
    @ResponseBody
    public ServerResponse ajaxSave(Member member) {
        return memberService.saveMember(member);
    }

    /**
     * 删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping("/member/ajax/delete")
    @ResponseBody
    public ServerResponse ajaxDelete(List<Long> ids) {
        return memberService.deleteMemberByIds(ids);
    }

    /**
     * 下拉选列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/member/ajax/list")
    public String ajaxList(HttpServletRequest request) {
        request.setAttribute("list", memberService.getMemberList(null));
        return "common/select-item";
    }
}
