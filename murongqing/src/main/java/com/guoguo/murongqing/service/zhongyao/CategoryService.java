package com.guoguo.murongqing.service.zhongyao;

import com.github.pagehelper.PageInfo;
import com.guoguo.murongqing.entity.common.Fenye;
import com.guoguo.murongqing.entity.zhongyao.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 添加中药
     * @param category
     * @return
     */
    int addCategory(Category category);

    /**
     * 修改中药
     * @param category
     * @return
     */
    int updateCategory(Category category);

    /**
     * 中药列表
     * @param category
     * @return
     */
    PageInfo<Category> getCategoryList(Category category, Fenye fenye);

    /**
     * 删除中药
     * @param category
     * @return
     */
    int updateCategoryDeleteByIds(Category category);

    /**
     * 根据id查询中药
     * @param id
     * @return
     */
    Category getCategoryById(Long id);

    /**
     * 查询全部中药数据
     * @return
     */
    List<Category> getCategoryAll();

}
