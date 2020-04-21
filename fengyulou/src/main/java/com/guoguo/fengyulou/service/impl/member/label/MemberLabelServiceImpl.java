package com.guoguo.fengyulou.service.impl.member.label;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoguo.common.ResponseCode;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.dao.member.label.MemberLabelDao;
import com.guoguo.fengyulou.entity.member.label.MemberLabel;
import com.guoguo.fengyulou.service.member.label.MemberLabelService;
import com.guoguo.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberLabelServiceImpl implements MemberLabelService {

    @Autowired
    private MemberLabelDao memberLabelDao;

    @Override
    public List<MemberLabel> getMemberLabelList(MemberLabel memberLabel) {
        return memberLabelDao.getMemberLabelList(memberLabel);
    }

    @Override
    public PageInfo<MemberLabel> getMemberLabelListPage(MemberLabel memberLabel) {
        PageHelper.startPage(memberLabel.getPageNum(), memberLabel.getPageSize(), "id desc");
        List<MemberLabel> list = memberLabelDao.getMemberLabelList(memberLabel);
        PageInfo<MemberLabel> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public MemberLabel getMemberLabelById(Long id) {
        return memberLabelDao.getMemberLabelById(id);
    }

    @Override
    public ServerResponse deleteMemberLabelByIds(List<Long> ids) {
        int rows = memberLabelDao.deleteMemberLabelByIds(ids);
        if (rows > 0) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse saveMemberLabel(MemberLabel memberLabel) {
        // 去除空格
        memberLabel.setName(memberLabel.getName().trim());
        if (ObjectUtils.isNotNull(memberLabel.getId())) {
            // 检查数据是否重复
            int count = memberLabelDao.getMemberLabelCountByMemberLabel(memberLabel);
            if (count > 0) {
                return ServerResponse.createByError(ResponseCode.EXIST);
            }
            // 修改
            int rows = memberLabelDao.updateMemberLabelById(memberLabel);
            if (rows > 0) {
                return ServerResponse.createBySuccess();
            }
        } else {
            // 检查数据是否重复
            int count = memberLabelDao.getMemberLabelCountByName(memberLabel.getName());
            if (count > 0) {
                return ServerResponse.createByError(ResponseCode.EXIST);
            }
            // 添加
            int rows = memberLabelDao.insertMemberLabel(memberLabel);
            if (rows > 0) {
                return ServerResponse.createBySuccess();
            }
        }
        return ServerResponse.createByError();
    }
}
