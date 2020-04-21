package com.guoguo.fengyulou.dao;

import com.guoguo.fengyulou.entity.Task;
import com.guoguo.fengyulou.entity.TaskVo;

import java.util.List;

public interface TaskDao {

    List<TaskVo> getTaskList(Task task);

    TaskVo getTaskByIdAndCreateUserId(Task task);

    int updateTask(Task task);

    int addTask(Task task);

    List<Long> getTaskIdsByIdsAndCreateUserId(TaskVo taskVo);

    int deleteTask(List<Long> taskIds);

    int updateTaskStatus(Task task);

    int updateTaskUserId(Task task);

    List<Long> getTaskIdsByCreateUserId(Long createUserId);

    int getTaskCountByIdAndCreateUserId(Task taskData);
}
