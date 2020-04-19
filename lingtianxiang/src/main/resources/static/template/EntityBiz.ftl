package com.base.cn.platform.os.service.${entity?lower_case};

import com.base.cn.platform.os.common.mybatis.BaseBiz;
import com.base.cn.platform.os.common.mybatis.Pagination;
import com.base.cn.platform.os.common.utils.DataUtil;
import com.base.cn.platform.os.common.utils.ObjectUtils;
import com.base.cn.platform.os.common.utils.StringUtils;
import com.base.cn.platform.os.dao.${entity?lower_case}.${entity?cap_first}Dao;
import com.base.cn.platform.os.entity.coupon.Coupon;
import com.base.cn.platform.os.entity.coupon.CouponCondition;
import com.base.cn.platform.os.entity.coupon.CouponLabel;
import com.base.cn.platform.os.entity.coupon.CouponLabelCondition;
import com.base.cn.platform.os.entity.${entity?lower_case}.${entity?cap_first};
import com.base.cn.platform.os.entity.${entity?lower_case}.${entity?cap_first}Condition;
import com.base.cn.platform.os.entity.${entity?lower_case}.${entity?cap_first};
import com.base.cn.platform.os.service.coupon.CouponBiz;
import com.base.cn.platform.os.service.coupon.CouponLabelBiz;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${explain}biz
 */
@Service
public class ${entity?cap_first}Biz extends BaseBiz<${entity?cap_first}, ${entity?cap_first}Dao> {

    /**
     * 查询${explain}带分页列表
     *
     * @param page
     * @param condition
     * @return
     */
    public PageInfo<${entity?cap_first}> get${entity?cap_first}ListByPage(${entity?cap_first}Condition condition, Pagination page) {
        PageInfo<${entity?cap_first}> ${entity?uncap_first}PageInfo = this.findPage(this.getWhere(condition), page, null);
        return ${entity?uncap_first}PageInfo;
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public ${entity?cap_first} get${entity?cap_first}ById(BigDecimal id) {
        return findById(id);
    }

    /**
     * 根据条件查询
     *
     * @param condition
     * @return
     */
    public ${entity?cap_first} get${entity?cap_first}(${entity?cap_first}Condition condition) {
        return findOne(getWhere(condition), null);
    }

    /**
     * 批量修改${explain}状态
     *
     * @param condition
     */
    public void update${entity?cap_first}StatusByIds(${entity?cap_first}Condition condition) {
        if (ObjectUtils.isNotEmpty(condition)) {
            String ids = StringUtils.subHeadTailString(condition.getIds(), ",");
            if (StringUtils.isNotEmpty(ids)) {
                ${entity?cap_first} ${entity?uncap_first} = new ${entity?cap_first}();
                if (ObjectUtils.isNotEmpty(condition.getStatus())) {
                    ${entity?uncap_first}.setStatus(condition.getStatus());
                }
                this.updateByWhereSql(${entity?uncap_first}, " id in (" + ids + ")");
            }
        }
    }

    /**
     * 后台保存${explain}
     *
     * @param ${entity?uncap_first}
     */
    public void save${entity?cap_first}(${entity?cap_first} ${entity?uncap_first}) {
        //修改
        if (DataUtil.idIsNotNull(${entity?uncap_first}.getId())) {
            this.updateById(${entity?uncap_first}, null);
        }
        //保存
        else {
            this.save(${entity?uncap_first});
        }
    }

    /**
     * 查询${explain}列表无分页
     *
     * @param condition
     * @return
     */
    public List<${entity?cap_first}> get${entity?cap_first}List(${entity?cap_first}Condition condition) {
        List<${entity?cap_first}> ${entity?uncap_first}List = this.find(this.getWhere(condition), null, null);
        return ${entity?uncap_first}List;
    }

    //******************************************************************************************************************

    /**
     * 获取查询SQL条件
     *
     * @param condition 条件参数对象
     * @return SQL语句
     */
    public String getWhere(${entity?cap_first}Condition condition) {
        StringBuilder whereSql = new StringBuilder();
        if (ObjectUtils.isNotEmpty(condition)) {
            if (ObjectUtils.isNotEmpty(condition.getInviteUserId())) {
                whereSql.append(" and inviteUserId = ").append(condition.getInviteUserId());
            }
            if (ObjectUtils.isNotEmpty(condition.getUserId())) {
                whereSql.append(" and userId = ").append(condition.getUserId());
            }
            if (ObjectUtils.isNotEmpty(condition.getStatus())) {
                whereSql.append(" and status = ").append(condition.getStatus());
            }
        }
        whereSql.append(" order by createTime desc");
        return whereSql.toString();
    }
}
