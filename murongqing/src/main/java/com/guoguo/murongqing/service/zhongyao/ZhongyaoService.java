package com.guoguo.murongqing.service.zhongyao;

import com.github.pagehelper.PageInfo;
import com.guoguo.murongqing.entity.common.Fenye;
import com.guoguo.murongqing.entity.zhongyao.Zhongyao;

import java.util.List;

public interface ZhongyaoService {
    /**
     * 添加中药
     * @param zhongyao
     * @return
     */
    int addZhongyao(Zhongyao zhongyao);

    /**
     * 修改中药
     * @param zhongyao
     * @return
     */
    int updateZhongyao(Zhongyao zhongyao);

    /**
     * 中药列表
     * @param zhongyao
     * @return
     */
    PageInfo<Zhongyao> getZhongyaoList(Zhongyao zhongyao, Fenye fenye);

    /**
     * 删除中药
     * @param zhongyao
     * @return
     */
    int updateZhongyaoDeleteByIds(Zhongyao zhongyao);

    /**
     * 根据id查询中药
     * @param id
     * @return
     */
    Zhongyao getZhongyaoById(Long id);

    /**
     * 查询全部中药数据
     * @return
     */
    List<Zhongyao> getZhongyaoAll();

}
