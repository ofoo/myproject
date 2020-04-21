package com.guoguo.fengyulou.controller;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.CommonConstant;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.Task;
import com.guoguo.fengyulou.entity.TaskVo;
import com.guoguo.fengyulou.entity.User;
import com.guoguo.fengyulou.service.TaskService;
import com.guoguo.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/fyl")
public class AdminTaskController extends BaseController {

    @Autowired
    private TaskService taskService;

    /**
     * 用户列表页面
     *
     * @param request
     * @param session
     * @param task
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/task/list")
    public String list(HttpServletRequest request, HttpSession session, Task task, @RequestParam(required = false, defaultValue = "1") int pageNum, @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return "task/task-list";
    }
}
