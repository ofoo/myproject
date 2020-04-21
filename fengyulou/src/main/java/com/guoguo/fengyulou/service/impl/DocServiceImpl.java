package com.guoguo.fengyulou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoguo.fengyulou.dao.DocDao;
import com.guoguo.fengyulou.entity.Doc;
import com.guoguo.fengyulou.service.DocService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("docService")
public class DocServiceImpl implements DocService {
    @Autowired
    private DocDao docDao;

    @Override
    public int addDoc(Doc doc) {
        return docDao.addDoc(doc);
    }

    @Override
    public int updateDoc(Doc doc) {
        return docDao.updateDoc(doc);
    }

    @Override
    public Doc getDocByCreateUserId(Long createUserId) {
        return docDao.getDocByCreateUserId(createUserId);
    }

    @Override
    public PageInfo<Doc> getDocList(Doc doc, int pageNum, int pageSize) {
        doc.setName(StringUtils.isBlank(doc.getName())?null:doc.getName());
        PageHelper.startPage(pageNum,pageSize,"id desc");
        List<Doc> list = docDao.getDocList(doc);
        PageInfo<Doc> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int updateDocNameByIdAndCreateUserId(Doc doc) {
        return docDao.updateDocNameByIdAndCreateUserId(doc);
    }

    @Override
    public Doc getDocByIdAndCreateUserId(Doc doc) {
        return docDao.getDocByIdAndCreateUserId(doc);
    }

    @Override
    public Doc getDocById(Long id) {
        return docDao.getDocById(id);
    }
}
