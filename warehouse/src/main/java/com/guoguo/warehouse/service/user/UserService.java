package com.guoguo.warehouse.service.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guoguo.warehouse.common.ResultJson;
import com.guoguo.warehouse.common.config.DataValue;
import com.guoguo.warehouse.common.constant.text.UserTextConstant;
import com.guoguo.warehouse.common.tool.ObjectTools;
import com.guoguo.warehouse.dao.user.UserDao;
import com.guoguo.warehouse.entity.user.User;
import com.guoguo.warehouse.service.record.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户
 *
 * @author GC
 */
@Service
public class UserService extends ServiceImpl<UserDao, User> {
    @Autowired
    private DataValue dataValue;
    @Autowired
    private RecordService recordService;

    /**
     * 验证用户是否正确
     *
     * @param user
     * @return
     */
    public ResultJson verifyUser(User user, String ip) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getName, user.getName());
        lambdaQueryWrapper.eq(User::getPwd, user.getPwd());
        User one = getOne(lambdaQueryWrapper, false);
        if (ObjectTools.isNotNull(one)) {
            String msg = UserTextConstant.NAME + "登录成功";
            recordService.loginRecord(msg, one, one.getId(), ip);
            return ResultJson.yes(msg, one);
        }
        return ResultJson.no("账号或密码错误");
    }
}
