package com.guoguo.murongqing.service.impl.zhongyao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoguo.murongqing.dao.zhongyao.CategoryDao;
import com.guoguo.murongqing.dao.zhongyao.ZhongyaoDao;
import com.guoguo.murongqing.entity.common.Fenye;
import com.guoguo.murongqing.entity.zhongyao.Category;
import com.guoguo.murongqing.entity.zhongyao.Zhongyao;
import com.guoguo.murongqing.service.zhongyao.CategoryService;
import com.guoguo.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ZhongyaoDao zhongyaoDao;

    @Override
    public int addCategory(Category category) {
        return categoryDao.addCategory(category);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryDao.updateCategory(category);
    }

    @Override
    public PageInfo<Category> getCategoryList(Category category, Fenye fenye) {
        PageHelper.startPage(fenye.getPageNum(), fenye.getPageSize(),"id desc");
        List<Category> list = categoryDao.getCategoryList(category);
        PageInfo<Category> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int updateCategoryDeleteByIds(Category category) {
        return categoryDao.updateCategoryDeleteByIds(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryDao.getCategoryById(id);
    }

    @Override
    public List<Category> getCategoryAll() {
        List<Category> list = categoryDao.getCategoryAll();
        if (ObjectUtils.isNotNull(list)) {
            for (Category category : list) {
                List<Zhongyao> zhongyaoList = zhongyaoDao.getZhongyaoByCategoryId(category.getId());
                category.setZhongyaoList(zhongyaoList);
            }
        }
        return list;
    }
}
