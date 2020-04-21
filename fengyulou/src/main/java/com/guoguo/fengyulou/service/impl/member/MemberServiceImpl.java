package com.guoguo.fengyulou.service.impl.member;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoguo.common.ResponseCode;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.dao.member.MemberDao;
import com.guoguo.fengyulou.entity.member.Member;
import com.guoguo.fengyulou.entity.project.Project;
import com.guoguo.fengyulou.service.member.MemberService;
import com.guoguo.util.ObjectUtils;
import com.sun.deploy.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public List<Member> getMemberList(Member member) {
        return memberDao.getMemberList(member);
    }

    @Override
    public PageInfo<Member> getMemberListPage(Member member) {
        PageHelper.startPage(member.getPageNum(), member.getPageSize(), "id desc");
        List<Member> list = memberDao.getMemberList(member);
        PageInfo<Member> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Member getMemberById(Long id) {
        return memberDao.getMemberById(id);
    }

    @Override
    public ServerResponse saveMember(Member member) {
        // 去除空格
        member.setName(member.getName().trim());
        member.setMobile(member.getMobile().trim());
        if (ObjectUtils.isNotNull(member.getId())) {
            // 验证数据是否重复
            int count = memberDao.getMemberCountByMember(member);
            if (count > 0) {
                return ServerResponse.createByError(ResponseCode.EXIST);
            }
            // 修改
            int rows = memberDao.updateMemberById(member);
            if (rows > 0) {
                ServerResponse.createBySuccess();
            }
        } else {
            // 验证数据是否重复
            int count = memberDao.getMemberCountByMobile(member.getMobile());
            if (count > 0) {
                return ServerResponse.createByError(ResponseCode.EXIST);
            }
            // 添加
            int rows = memberDao.insertMember(member);
            if (rows > 0) {
                ServerResponse.createBySuccess();
            }
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse deleteMemberByIds(List<Long> ids) {
        int rows = memberDao.deleteMemberByIds(ids);
        if (rows > 0) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
}
