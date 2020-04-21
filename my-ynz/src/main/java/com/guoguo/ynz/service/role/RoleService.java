package com.guoguo.ynz.service.role;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;
import com.guoguo.ynz.dao.role.RoleDao;
import com.guoguo.ynz.entity.role.Role;
import com.guoguo.ynz.tool.constant.RoleConstant;
import com.guoguo.ynz.tool.result.DataJson;
import com.guoguo.ynz.tool.result.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 按id查询角色
     *
     * @param id
     * @return
     */
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    /**
     * 按id删除角色
     *
     * @param roleList
     * @return
     */
    public ResultJson deleteRoleByIds(List<Role> roleList) {
        int rows = roleDao.deleteRoleByIds(roleList);
        if (rows > 0) {
            return ResultJson.yes(RoleConstant.YES);
        }
        return ResultJson.no(RoleConstant.NO);
    }

    /**
     * 保存角色
     *
     * @param role
     * @return
     */
    public ResultJson saveRole(Role role) {
        if (ObjectUtil.isNotNull(role.getId())) {
            // 修改角色
            int rows = roleDao.updateRoleById(role);
            if (rows > 0) {
                return ResultJson.yes(RoleConstant.YES);
            }
        } else {
            // 添加角色
            int rows = roleDao.addRole(role);
            if (rows > 0) {
                return ResultJson.yes(RoleConstant.YES);
            }
        }
        return ResultJson.no(RoleConstant.NO);
    }

    /**
     * 分页查询角色
     *
     * @param role
     * @return
     */
    public DataJson getRoleList(Role role) {
        // 查询分页页数
        int[] ints = PageUtil.transToStartEnd(role.getPage() - 1, role.getLimit());
        role.setPage(ints[0]);
        role.setLimit(ints[1]);
        List<Role> list = roleDao.getRoleList(role);
        // 查询总页数
        Integer totalCount = roleDao.getRoleListCount(role);
        int totalPage = PageUtil.totalPage(totalCount, role.getLimit());

        return DataJson.list(totalPage, list);
    }
}
