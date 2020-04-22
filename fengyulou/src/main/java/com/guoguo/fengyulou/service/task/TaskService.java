package com.guoguo.fengyulou.service.task;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.task.Task;

import java.util.List;

/**
 * 任务
 */
public interface TaskService {
    /**
     * 按条件查询查询
     * @param task
     * @return
     */
    List<Task> getTaskList(Task task);

    /**
     * 按条件分页查询
     * @param task
     * @return
     */
    PageInfo<Task> getTaskListPage(Task task);

    /**
     * 根据id删除
     * @param ids
     * @return
     */
    ServerResponse deleteTaskByIds(List<Long> ids);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Task getTaskById(Long id);

    /**
     * 保存数据
     * @param task
     * @return
     */
    ServerResponse saveTask(Task task);

    /**
     * 修改任务状态完成
     * @param ids
     * @return
     */
    ServerResponse updateStatusByIds(List<Long> ids);
}
