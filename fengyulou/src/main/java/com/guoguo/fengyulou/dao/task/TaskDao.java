package com.guoguo.fengyulou.dao.task;

import com.guoguo.fengyulou.entity.task.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskDao {
    List<Task> getTaskList(Task task);

    int deleteTaskByIds(List<Long> ids);

    Task getTaskById(@Param("id") Long id);

    int updateTaskById(Task task);

    int insertTask(Task task);
}
