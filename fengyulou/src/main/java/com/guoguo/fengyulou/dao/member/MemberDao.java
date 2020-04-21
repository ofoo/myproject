package com.guoguo.fengyulou.dao.member;

import com.guoguo.fengyulou.entity.member.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberDao {
    List<Member> getMemberList(Member member);

    Member getMemberById(@Param("id") Long id);

    int updateMemberById(Member member);

    int getMemberCountByMobile(@Param("mobile") String mobile);

    int insertMember(Member member);

    int getMemberCountByMember(Member member);

    int deleteMemberByIds(List<Long> ids);
}
