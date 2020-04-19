package com.guoguo.ynz.service.permission;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;
import com.guoguo.ynz.dao.permission.PermissionDao;
import com.guoguo.ynz.entity.permission.Permission;
import com.guoguo.ynz.tool.constant.PermissionConstant;
import com.guoguo.ynz.tool.result.DataJson;
import com.guoguo.ynz.tool.result.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 按id查询权限
     *
     * @param id
     * @return
     */
    public Permission getPermissionById(Long id) {
        return permissionDao.getPermissionById(id);
    }

    /**
     * 按id删除权限
     *
     * @param permissionList
     * @return
     */
    public ResultJson deletePermissionByIds(List<Permission> permissionList) {
        int rows = permissionDao.deletePermissionByIds(permissionList);
        if (rows > 0) {
            return ResultJson.yes(PermissionConstant.YES);
        }
        return ResultJson.no(PermissionConstant.NO);
    }

    /**
     * 保存权限
     *
     * @param permission
     * @return
     */
    public ResultJson savePermission(Permission permission) {
        if (ObjectUtil.isNotNull(permission.getId())) {
            // 修改权限
            int rows = permissionDao.updatePermissionById(permission);
            if (rows > 0) {
                return ResultJson.yes(PermissionConstant.YES);
            }
        } else {
            // 添加权限
            int rows = permissionDao.addPermission(permission);
            if (rows > 0) {
                return ResultJson.yes(PermissionConstant.YES);
            }
        }
        return ResultJson.no(PermissionConstant.NO);
    }

    /**
     * 分页查询权限
     *
     * @param permission
     * @return
     */
    public DataJson getPermissionList(Permission permission) {
        // 查询分页页数
        int[] ints = PageUtil.transToStartEnd(permission.getPage() - 1, permission.getLimit());
        permission.setPage(ints[0]);
        permission.setLimit(ints[1]);
        List<Permission> list = permissionDao.getPermissionList(permission);
        // 查询总页数
        Integer totalCount = permissionDao.getPermissionListCount(permission);
        int totalPage = PageUtil.totalPage(totalCount, permission.getLimit());

        return DataJson.list(totalPage, list);
    }
}
