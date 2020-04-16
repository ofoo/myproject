package com.guoguo.warehouse.dao.brand;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guoguo.warehouse.entity.brand.Brand;
import com.guoguo.warehouse.entity.goods.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GC
 */
public interface BrandDao extends BaseMapper<Brand> {

}
