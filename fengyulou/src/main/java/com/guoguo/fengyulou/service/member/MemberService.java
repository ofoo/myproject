package com.guoguo.fengyulou.service.member;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.member.Member;

import java.util.List;

public interface MemberService {
    /**
     * 按条件查询
     *
     * @param member
     * @return
     */
    List<Member> getMemberList(Member member);

    /**
     * 按条件分页查询
     *
     * @param member
     * @return
     */
    PageInfo<Member> getMemberListPage(Member member);

    /**
     * 按id查询
     *
     * @param id
     * @return
     */
    Member getMemberById(Long id);

    /**
     * 保存数据
     * @param member
     * @return
     */
    ServerResponse saveMember(Member member);

    /**
     * 按id删除数据
     * @param ids
     * @return
     */
    ServerResponse deleteMemberByIds(List<Long> ids);
}
