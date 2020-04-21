package com.guoguo.fengyulou.service.project;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.project.Project;

import java.util.List;

public interface ProjectService {
    /**
     * 按条件查询
     *
     * @param project
     * @return
     */
    List<Project> getProjectList(Project project);

    /**
     * 按条件分页查询
     *
     * @param project
     * @return
     */
    PageInfo<Project> getProjectListPage(Project project);

    /**
     * 按id查询
     * @param id
     * @return
     */
    Project getProjectById(Long id);

    /**
     * 保存项目
     * @param project
     * @return
     */
    ServerResponse saveProject(Project project);

    /**
     * 按id删除数据
     * @param ids
     * @return
     */
    ServerResponse deleteProjectByIds(List<Long> ids);
}
