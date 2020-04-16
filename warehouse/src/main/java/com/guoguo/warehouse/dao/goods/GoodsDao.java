package com.guoguo.warehouse.dao.goods;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guoguo.warehouse.entity.goods.Goods;
import com.guoguo.warehouse.entity.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GC
 */
public interface GoodsDao extends BaseMapper<Goods> {
    List<Goods> selectAll(@Param(Constants.WRAPPER) Wrapper<Goods> wrapper);

    IPage<Goods> selectGoodsPage(Page<Goods> page, @Param(Constants.WRAPPER) Wrapper<Goods> wrapper);

    IPage<Goods> getGoodsListPage(Page<Goods> page, @Param(Constants.WRAPPER) Wrapper<Goods> wrapper);
}
