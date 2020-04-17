package com.guoguo.murongqing.service.impl.zhongyao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoguo.murongqing.dao.zhongyao.CategoryDao;
import com.guoguo.murongqing.dao.zhongyao.ZhongyaoDao;
import com.guoguo.murongqing.entity.common.Fenye;
import com.guoguo.murongqing.entity.zhongyao.Category;
import com.guoguo.murongqing.entity.zhongyao.Zhongyao;
import com.guoguo.murongqing.service.zhongyao.ZhongyaoService;
import com.guoguo.util.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("zhongyaoService")
public class ZhongyaoServiceImpl implements ZhongyaoService {
    @Autowired
    private ZhongyaoDao zhongyaoDao;
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public int addZhongyao(Zhongyao zhongyao) {
        return zhongyaoDao.addZhongyao(zhongyao);
    }

    @Override
    public int updateZhongyao(Zhongyao zhongyao) {
        return zhongyaoDao.updateZhongyao(zhongyao);
    }

    @Override
    public PageInfo<Zhongyao> getZhongyaoList(Zhongyao zhongyao, Fenye fenye) {
        PageHelper.startPage(fenye.getPageNum(), fenye.getPageSize(),"id desc");
        List<Zhongyao> list = zhongyaoDao.getZhongyaoList(zhongyao);
        PageInfo<Zhongyao> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int updateZhongyaoDeleteByIds(Zhongyao zhongyao) {
        return zhongyaoDao.updateZhongyaoDeleteByIds(zhongyao);
    }

    @Override
    public Zhongyao getZhongyaoById(Long id) {
        return zhongyaoDao.getZhongyaoById(id);
    }

    @Override
    public List<Zhongyao> getZhongyaoAll() {
        List<Zhongyao> list = zhongyaoDao.getZhongyaoAll();
        if (ObjectUtils.isNotNull(list)) {
            for (Zhongyao zhongyao : list) {
                //查询中药类别
                Category category = categoryDao.getCategoryById(zhongyao.getCategoryId());
                zhongyao.setCategory(category);
            }
        }
        return list;
    }
}
