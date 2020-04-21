package com.guoguo.fengyulou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoguo.fengyulou.dao.TaskDao;
import com.guoguo.fengyulou.entity.Task;
import com.guoguo.fengyulou.entity.TaskVo;
import com.guoguo.fengyulou.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;

    @Override
    public PageInfo<TaskVo> getTaskList(Task task, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, "id desc");
        List<TaskVo> list = taskDao.getTaskList(task);
        PageInfo<TaskVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public TaskVo getTaskByIdAndCreateUserId(Task task) {
        return taskDao.getTaskByIdAndCreateUserId(task);
    }

    @Override
    public int updateTask(Task task) {
        return taskDao.updateTask(task);
    }

    @Override
    public int addTask(Task task) {
        return taskDao.addTask(task);
    }

    @Override
    public List<Long> getTaskIdsByIdsAndCreateUserId(TaskVo taskVo) {
        return taskDao.getTaskIdsByIdsAndCreateUserId(taskVo);
    }

    @Override
    public int deleteTask(List<Long> taskIds) {
        return taskDao.deleteTask(taskIds);
    }

    @Override
    public int updateTaskStatus(Task task) {
        return taskDao.updateTaskStatus(task);
    }

    @Override
    public int updateTaskUserId(Task task) {
        return taskDao.updateTaskUserId(task);
    }

    @Override
    public List<Long> getTaskIdsByCreateUserId(Long createUserId) {
        return taskDao.getTaskIdsByCreateUserId(createUserId);
    }

    @Override
    public int getTaskCountByIdAndCreateUserId(Task taskData) {
        return taskDao.getTaskCountByIdAndCreateUserId(taskData);
    }
}
