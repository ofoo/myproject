package com.guoguo.warehouse.dao.record;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guoguo.warehouse.entity.goods.Goods;
import com.guoguo.warehouse.entity.record.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GC
 */
public interface RecordDao extends BaseMapper<Record> {

}
