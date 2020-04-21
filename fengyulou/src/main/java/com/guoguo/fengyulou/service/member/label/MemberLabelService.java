package com.guoguo.fengyulou.service.member.label;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.member.label.MemberLabel;

import java.util.List;

public interface MemberLabelService {
    /**
     * 按条件查询
     *
     * @param memberLabel
     * @return
     */
    List<MemberLabel> getMemberLabelList(MemberLabel memberLabel);

    /**
     * 按条件分页查询
     *
     * @param memberLabel
     * @return
     */
    PageInfo<MemberLabel> getMemberLabelListPage(MemberLabel memberLabel);

    /**
     * 按id查询
     * @param id
     * @return
     */
    MemberLabel getMemberLabelById(Long id);

    /**
     * 按id删除数据
     * @param ids
     * @return
     */
    ServerResponse deleteMemberLabelByIds(List<Long> ids);

    /**
     * 保存数据
     * @param memberLabel
     * @return
     */
    ServerResponse saveMemberLabel(MemberLabel memberLabel);
}
