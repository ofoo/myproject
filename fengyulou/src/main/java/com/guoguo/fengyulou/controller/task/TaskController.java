package com.guoguo.fengyulou.controller.task;

import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.controller.BaseController;
import com.guoguo.fengyulou.entity.project.Project;
import com.guoguo.fengyulou.entity.task.Task;
import com.guoguo.fengyulou.entity.task.label.TaskLabel;
import com.guoguo.fengyulou.service.member.MemberService;
import com.guoguo.fengyulou.service.project.ProjectService;
import com.guoguo.fengyulou.service.task.TaskService;
import com.guoguo.fengyulou.service.task.label.TaskLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 任务管理
 */
@Controller
@RequestMapping("/fyl")
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskLabelService taskLabelService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MemberService memberService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping("/task/list/page")
    public String list(HttpServletRequest request, Task task) {
        request.setAttribute("pageInfo", taskService.getTaskListPage(task));
        request.setAttribute("task", task);
        return "task/task-list";
    }

    /**
     * 添加页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/task/insert")
    public String insert(HttpServletRequest request) {
        request.setAttribute("pageTitle", "添加任务");
        // 查询项目列表
        request.setAttribute("projectList", projectService.getProjectList(null));
        // 查询任务标签列表
        request.setAttribute("taskLabelList", taskLabelService.getTaskLabelList(null));
        // 查询人员列表
        request.setAttribute("memberList", memberService.getMemberList(null));
        return "task/task-save";
    }

    /**
     * 修改页面
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/task/update/{id}")
    public String update(HttpServletRequest request, @PathVariable Long id) {
        request.setAttribute("pageTitle", "修改任务");
        // 查询任务
        Task task = taskService.getTaskById(id);
        request.setAttribute("data", task);
        // 查询项目列表
        List<Project> projectList = projectService.getProjectList(null);
        request.setAttribute("projectList", projectList);
        // 查询任务标签列表
        List<TaskLabel> taskLabelList = taskLabelService.getTaskLabelList(null);
        request.setAttribute("taskLabelList", taskLabelList);
        // 查询人员列表
        request.setAttribute("memberList", memberService.getMemberList(null));
        return "task/task-save";
    }

    /**
     * 保存数据
     *
     * @param task
     * @return
     */
    @RequestMapping("/task/ajax/save")
    @ResponseBody
    private ServerResponse ajaxSave(Task task) {
        return taskService.saveTask(task);
    }

    /**
     * 根据id删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/task/ajax/delete")
    @ResponseBody
    private ServerResponse ajaxDelete(@RequestParam List<Long> ids) {
        return taskService.deleteTaskByIds(ids);
    }

    /**
     * 修改任务状态完成
     */
    @RequestMapping("/task/ajax/updateStatus")
    @ResponseBody
    private ServerResponse ajaxUpdateStatus(@RequestParam List<Long> ids){
        return taskService.updateStatusByIds(ids);
    }
}
