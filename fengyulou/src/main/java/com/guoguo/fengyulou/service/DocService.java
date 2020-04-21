package com.guoguo.fengyulou.service;

import com.github.pagehelper.PageInfo;
import com.guoguo.fengyulou.entity.Doc;

public interface DocService {
    /**
     * 添加文档
     * @param doc
     * @return
     */
    int addDoc(Doc doc);

    /**
     * 修改文档
     * @param doc
     * @return
     */
    int updateDoc(Doc doc);

    /**
     * 查询文档
     * @param createUserId
     * @return
     */
    Doc getDocByCreateUserId(Long createUserId);

    /**
     * 文档列表
     * @param doc
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Doc> getDocList(Doc doc, int pageNum, int pageSize);

    /**
     * 按文档id和创建人id修改
     * @return
     */
    int updateDocNameByIdAndCreateUserId(Doc doc);

    /**
     * 根据id和创建人id搜索
     * @param doc
     * @return
     */
    Doc getDocByIdAndCreateUserId(Doc doc);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Doc getDocById(Long id);
}
