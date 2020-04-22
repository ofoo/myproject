package com.guoguo.fengyulou.controller.project;

import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.project.Project;
import com.guoguo.fengyulou.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目管理
 */
@Controller
@RequestMapping("/fyl")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping("/project/list/page")
    public String list(HttpServletRequest request, Project project) {
        request.setAttribute("pageInfo", projectService.getProjectListPage(project));
        request.setAttribute("data", project);
        return "/project/project-list";
    }

    /**
     * 添加页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/project/insert")
    public String insert(HttpServletRequest request) {
        request.setAttribute("pageTitle", "添加项目");
        return "project/project-save";
    }

    /**
     * 修改页面
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/project/update/{id}")
    public String update(HttpServletRequest request, @PathVariable Long id) {
        request.setAttribute("pageTitle", "修改项目");
        // 查询项目
        request.setAttribute("data", projectService.getProjectById(id));
        return "project/project-save";
    }

    /**
     * 保存数据
     *
     * @param project
     * @return
     */
    @RequestMapping("/project/ajax/save")
    @ResponseBody
    public ServerResponse ajaxSave(Project project) {
        return projectService.saveProject(project);
    }

    /**
     * 删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping("/project/ajax/delete")
    @ResponseBody
    public ServerResponse ajaxSave(@RequestParam List<Long> ids) {
        return projectService.deleteProjectByIds(ids);
    }

    /**
     * 下拉选列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/project/ajax/list")
    public String ajaxList(HttpServletRequest request) {
        request.setAttribute("list", projectService.getProjectList(null));
        return "common/select-item";
    }
}
