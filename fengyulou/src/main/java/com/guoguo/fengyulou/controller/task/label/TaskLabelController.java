package com.guoguo.fengyulou.controller.task.label;

import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.controller.BaseController;
import com.guoguo.fengyulou.entity.task.label.TaskLabel;
import com.guoguo.fengyulou.service.task.label.TaskLabelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 任务标签标签管理
 */
@Controller
@RequestMapping("/fyl")
public class TaskLabelController extends BaseController {

    @Autowired
    private TaskLabelService taskLabelService;

    /**
     * 列表页面
     *
     * @param request
     * @param taskLabel
     * @return
     */
    @RequestMapping("/taskLabel/list/page")
    public String list(HttpServletRequest request, TaskLabel taskLabel) {
        request.setAttribute("pageInfo", taskLabelService.getTaskLabelListPage(taskLabel));
        request.setAttribute("data", taskLabel);
        return "task/label/task-label-list";
    }

    /**
     * 添加页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/taskLabel/insert")
    public String insert(HttpServletRequest request) {
        request.setAttribute("pageTitle", "添加任务标签");
        return "task/label/task-label-save";
    }

    /**
     * 修改页面
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/taskLabel/update/{id}")
    public String update(HttpServletRequest request, @PathVariable Long id) {
        request.setAttribute("pageTitle", "修改任务标签");
        // 查询任务标签
        request.setAttribute("data", taskLabelService.getTaskLabelById(id));
        return "task/label/task-label-save";
    }

    /**
     * 保存数据
     *
     * @param taskLabel
     * @return
     */
    @RequestMapping("/taskLabel/ajax/save")
    @ResponseBody
    public ServerResponse ajaxSave(TaskLabel taskLabel) {
        if (StringUtils.isBlank(taskLabel.getName())) {
            return ServerResponse.createByErrorMessage("请输入任务标签名称");
        }
        return taskLabelService.saveTaskLabel(taskLabel);
    }

    /**
     * 删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping("/taskLabel/ajax/delete")
    @ResponseBody
    public ServerResponse ajaxDelete(@RequestParam List<Long> ids) {
        return taskLabelService.deleteTaskLabelByIds(ids);
    }

    /**
     * 下拉选子项
     *
     * @return
     */
    @RequestMapping("/taskLabel/ajax/list")
    public String ajaxList(HttpServletRequest request) {
        request.setAttribute("list", taskLabelService.getTaskLabelList(null));
        return "/common/select-item";
    }
}

