# Host: localhost  (Version 5.7.27-log)
# Date: 2020-04-17 03:06:05
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "ynz_permission"
#

DROP TABLE IF EXISTS `ynz_permission`;
CREATE TABLE `ynz_permission` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '' COMMENT '权限名称',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `status` tinyint(3) DEFAULT '0' COMMENT '状态 0正常 1删除',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='权限表';

#
# Data for table "ynz_permission"
#

INSERT INTO `ynz_permission` VALUES (1,'insert','2020-04-16 23:57:56',0),(2,'delete','2020-04-16 23:57:56',0),(3,'update','2020-04-16 23:57:56',0),(4,'select','2020-04-16 23:57:56',0);

#
# Structure for table "ynz_role"
#

DROP TABLE IF EXISTS `ynz_role`;
CREATE TABLE `ynz_role` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '' COMMENT '角色名称',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `status` tinyint(3) DEFAULT '0' COMMENT '状态 0正常 1删除',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

#
# Data for table "ynz_role"
#

INSERT INTO `ynz_role` VALUES (1,'role1','2020-04-16 23:57:56',0),(2,'role2','2020-04-16 23:57:56',0),(3,'role3','2020-04-16 23:57:56',0);

#
# Structure for table "ynz_role_permission"
#

DROP TABLE IF EXISTS `ynz_role_permission`;
CREATE TABLE `ynz_role_permission` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT '0' COMMENT '角色id',
  `permission_id` int(11) DEFAULT '0' COMMENT '权限id',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `status` tinyint(3) DEFAULT '0' COMMENT '状态 0正常 1删除',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

#
# Data for table "ynz_role_permission"
#

INSERT INTO `ynz_role_permission` VALUES (1,1,1,'2020-04-16 23:57:56',0),(2,1,2,'2020-04-16 23:57:56',0),(3,1,3,'2020-04-16 23:57:56',0),(4,1,4,'2020-04-16 23:57:56',0),(5,2,1,'2020-04-16 23:57:56',0),(6,2,2,'2020-04-16 23:57:56',0),(7,2,3,'2020-04-16 23:57:56',0),(8,2,4,'2020-04-16 23:57:56',0),(9,3,1,'2020-04-16 23:57:56',0),(10,3,2,'2020-04-16 23:57:56',0),(11,3,3,'2020-04-16 23:57:56',0),(12,3,4,'2020-04-16 23:57:56',0);

#
# Structure for table "ynz_user"
#

DROP TABLE IF EXISTS `ynz_user`;
CREATE TABLE `ynz_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(255) DEFAULT '' COMMENT '手机号',
  `name` varchar(255) DEFAULT '' COMMENT '姓名',
  `password` varchar(255) DEFAULT '' COMMENT '密码',
  `add_time` datetime DEFAULT NULL COMMENT '添加事件',
  `status` tinyint(3) DEFAULT '0' COMMENT '状态 0正常 1删除',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Data for table "ynz_user"
#

INSERT INTO `ynz_user` VALUES (1,'13333333333','user1','123','2020-04-16 23:57:56',0);

#
# Structure for table "ynz_user_role"
#

DROP TABLE IF EXISTS `ynz_user_role`;
CREATE TABLE `ynz_user_role` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT '0' COMMENT '用户id',
  `role_id` int(11) DEFAULT '0' COMMENT '角色id',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `status` tinyint(3) DEFAULT '0' COMMENT '状态 0正常 1删除',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

#
# Data for table "ynz_user_role"
#

INSERT INTO `ynz_user_role` VALUES (1,1,1,'2020-04-16 23:57:56',0),(2,1,2,'2020-04-16 23:57:56',0),(3,1,3,'2020-04-16 23:57:56',0),(4,2,1,'2020-04-16 23:57:56',0),(5,2,2,'2020-04-16 23:57:56',0),(6,2,3,'2020-04-16 23:57:56',0);
