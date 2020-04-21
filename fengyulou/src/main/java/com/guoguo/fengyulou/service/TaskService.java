package com.guoguo.fengyulou.service;

import com.github.pagehelper.PageInfo;
import com.guoguo.fengyulou.entity.Task;
import com.guoguo.fengyulou.entity.TaskVo;

import java.util.List;

public interface TaskService {

    /**
     * 任务列表
     *
     * @param task
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<TaskVo> getTaskList(Task task, int pageNum, int pageSize);

    /**
     * 根据任务id和创建人id查询
     *
     * @param task
     * @return
     */
    TaskVo getTaskByIdAndCreateUserId(Task task);

    /**
     * 修改任务
     *
     * @param task
     * @return
     */
    int updateTask(Task task);

    /**
     * 添加任务
     *
     * @param task
     * @return
     */
    int addTask(Task task);

    /**
     * 过滤出用户的任务id
     *
     * @param taskVo
     * @return
     */
    List<Long> getTaskIdsByIdsAndCreateUserId(TaskVo taskVo);

    /**
     * 删除任务
     *
     * @param taskIds
     * @return
     */
    int deleteTask(List<Long> taskIds);

    /**
     * 修改任务状态
     *
     * @param task
     * @return
     */
    int updateTaskStatus(Task task);

    /**
     * 修改负责人、清除负责人、分配负责人
     *
     * @param task
     * @return
     */
    int updateTaskUserId(Task task);

    /**
     * 过滤出未完成的任务
     *
     * @param createUserId
     * @return
     */
    List<Long> getTaskIdsByCreateUserId(Long createUserId);

    /**
     * 过滤掉无权限的用户
     *
     * @param taskData
     * @return
     */
    int getTaskCountByIdAndCreateUserId(Task taskData);
}
