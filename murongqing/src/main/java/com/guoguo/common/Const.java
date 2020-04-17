package com.guoguo.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Const {
    /**
     * 任务未完成
     */
    public static final int UN_FINISH = 1;
    /**
     * 任务完成
     */
    public static final int FINISH = 2;

    public enum RoleEnum {
        SYS_ADMIN(1, "系统管理员"),
        ADMIN(2, "管理员"),
        USER(3, "用户");
        private String value;
        private int code;

        RoleEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static RoleEnum codeOf(int code) {
            for (RoleEnum roleEnum : values()) {
                if (roleEnum.getCode() == code) {
                    return roleEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }

    public interface Time {
        /**
         * 一天
         */
        int ONE_DAY = 60 * 60 * 24;
        /**
         * 一小时
         */
        int ONE_HOUR = 60 * 60;
    }

    public interface UserRole {
        Set<Integer> USER_ROLE = Sets.newHashSet(RoleEnum.ADMIN.getCode(), RoleEnum.USER.getCode());
    }

    public interface User {
        /**
         * cookie名称
         */
        String UUID = "uuid";
        /**
         * session名称
         */
        String USERSTR = "loginUser";
        /**
         * 没有登录
         */
        int NO_LOGIN = -1;
        /**
         * 没有登录
         */
        int NO_ACCESS = -2;
        /**
         * 正常状态
         */
        int STATUS_YES=1;
        /**
         * 删除状态
         */
        int STATUS_NO=2;
    }
}
