package com.guoguo.fengyulou.service.impl.task.label;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoguo.common.ResponseCode;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.dao.task.label.TaskLabelDao;
import com.guoguo.fengyulou.entity.task.Task;
import com.guoguo.fengyulou.entity.task.label.TaskLabel;
import com.guoguo.fengyulou.service.task.label.TaskLabelService;
import com.guoguo.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskLabelServiceImpl implements TaskLabelService {

    @Autowired
    private TaskLabelDao taskLabelDao;

    @Override
    public List<TaskLabel> getTaskLabelList(TaskLabel taskLabel) {
        return taskLabelDao.getTaskLabelList(taskLabel);
    }

    @Override
    public PageInfo<TaskLabel> getTaskLabelListPage(TaskLabel taskLabel) {
        PageHelper.startPage(taskLabel.getPageNum(), taskLabel.getPageSize(), "id desc");
        List<TaskLabel> list = taskLabelDao.getTaskLabelList(taskLabel);
        PageInfo<TaskLabel> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public TaskLabel getTaskLabelById(Long id) {
        return taskLabelDao.getTaskLabelById(id);
    }

    @Override
    public ServerResponse saveTaskLabel(TaskLabel taskLabel) {
        // 去除空格
        taskLabel.setName(taskLabel.getName().trim());
        if (ObjectUtils.isNotNull(taskLabel.getId())) {
            // 验证数据是否重复
            int count = taskLabelDao.getTaskLabelCountByTaskLabel(taskLabel);
            if (count > 0) {
                return ServerResponse.createByError(ResponseCode.EXIST);
            }
            // 修改
            int rows = taskLabelDao.updateTaskLabelById(taskLabel);
            if (rows > 0) {
                return ServerResponse.createBySuccess();
            }
        } else {
            // 验证数据是否重复
            int count = taskLabelDao.getTaskLabelCountByName(taskLabel.getName());
            if (count > 0) {
                return ServerResponse.createByError(ResponseCode.EXIST);
            }
            // 添加
            int rows = taskLabelDao.insertTaskLabel(taskLabel);
            if (rows > 0) {
                return ServerResponse.createBySuccess();
            }
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse deleteTaskLabelByIds(List<Long> ids) {
        int rows = taskLabelDao.deleteTaskLabelByIds(ids);
        if (rows > 0) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
}
