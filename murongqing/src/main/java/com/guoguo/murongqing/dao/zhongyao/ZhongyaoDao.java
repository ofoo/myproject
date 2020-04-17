package com.guoguo.murongqing.dao.zhongyao;

import com.guoguo.murongqing.entity.zhongyao.Zhongyao;

import java.util.List;

public interface ZhongyaoDao {

    int addZhongyao(Zhongyao zhongyao);

    int updateZhongyao(Zhongyao zhongyao);

    List<Zhongyao> getZhongyaoList(Zhongyao zhongyao);

    int updateZhongyaoDeleteByIds(Zhongyao zhongyao);

    Zhongyao getZhongyaoById(Long id);

    List<Zhongyao> getZhongyaoAll();

    List<Zhongyao> getZhongyaoByCategoryId(Long categoryId);
}
