package com.guoguo.fengyulou.dao.project;

import com.guoguo.fengyulou.entity.project.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectDao {
    List<Project> getProjectList(Project project);

    Project getProjectById(@Param("id") Long id);

    int updateProjectById(Project project);

    int insertProject(Project project);

    int getProjectCountByName(@Param("name") String name);

    int deleteProjectByIds(List<Long> ids);

    int getProjectCountByProject(Project project);
}
