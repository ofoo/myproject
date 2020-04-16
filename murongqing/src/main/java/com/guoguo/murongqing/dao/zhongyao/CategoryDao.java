package com.guoguo.murongqing.dao.zhongyao;

import com.guoguo.murongqing.entity.zhongyao.Category;

import java.util.List;

public interface CategoryDao {

    int addCategory(Category category);

    int updateCategory(Category category);

    List<Category> getCategoryList(Category category);

    int updateCategoryDeleteByIds(Category category);

    Category getCategoryById(Long id);

    List<Category> getCategoryAll();

    Long getCategoryByName(String name);
}
