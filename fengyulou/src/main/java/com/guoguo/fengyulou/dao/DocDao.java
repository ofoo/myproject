package com.guoguo.fengyulou.dao;

import com.guoguo.fengyulou.entity.Doc;

import java.util.List;

public interface DocDao {

    int addDoc(Doc doc);

    int updateDoc(Doc doc);

    Doc getDocByCreateUserId(Long createUserId);

    List<Doc> getDocList(Doc doc);

    int updateDocNameByIdAndCreateUserId(Doc doc);

    Doc getDocByIdAndCreateUserId(Doc doc);

    Doc getDocById(Long id);
}
