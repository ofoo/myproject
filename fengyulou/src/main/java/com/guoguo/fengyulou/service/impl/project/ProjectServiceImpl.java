package com.guoguo.fengyulou.service.impl.project;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoguo.common.ResponseCode;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.dao.project.ProjectDao;
import com.guoguo.fengyulou.entity.project.Project;
import com.guoguo.fengyulou.service.project.ProjectService;
import com.guoguo.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public List<Project> getProjectList(Project project) {
        return projectDao.getProjectList(project);
    }

    @Override
    public PageInfo<Project> getProjectListPage(Project project) {
        PageHelper.startPage(project.getPageNum()==null?1:project.getPageNum(), project.getPageSize()==null?10:project.getPageSize());
        List<Project> list = projectDao.getProjectList(project);
        PageInfo<Project> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Project getProjectById(Long id) {
        return projectDao.getProjectById(id);
    }

    @Override
    public ServerResponse saveProject(Project project) {
        // 去除空格
        project.setName(project.getName().trim());
        if (ObjectUtils.isNotNull(project.getId())) {
            // 验证数据是否重复
            int count = projectDao.getProjectCountByProject(project);
            if (count > 0) {
                return ServerResponse.createByError(ResponseCode.EXIST);
            }
            // 修改
            int rows = projectDao.updateProjectById(project);
            if (rows > 0) {
                return ServerResponse.createBySuccess();
            }
        } else {
            // 验证数据是否重复
            int count = projectDao.getProjectCountByName(project.getName());
            if (count > 0) {
                return ServerResponse.createByError(ResponseCode.EXIST);
            }
            // 添加
            int rows = projectDao.insertProject(project);
            if (rows > 0) {
                return ServerResponse.createBySuccess();
            }
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse deleteProjectByIds(List<Long> ids) {
        int rows = projectDao.deleteProjectByIds(ids);
        if (rows > 0) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
}
