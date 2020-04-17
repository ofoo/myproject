# Host: localhost  (Version 5.7.27-log)
# Date: 2020-01-27 21:22:55
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "murongqing_doc"
#

DROP TABLE IF EXISTS `murongqing_doc`;
CREATE TABLE `murongqing_doc` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '文档名称',
  `markdown_text` text COMMENT 'markdown文本',
  `html_text` text COMMENT 'html文本',
  `create_user_id` int(11) DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='文档表';

#
# Data for table "murongqing_doc"
#

INSERT INTO `murongqing_doc` VALUES (1,'MySQL1122334455667788','# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf\n\n# dsadfsadfsadfsafsafsdfassdfasdfas\nsfsfsadfds\nasdfsdfsadf\nassdfsadfsaf\nasdfsafasdf\nsafsadfsadfsaf','<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n<h1 id=\"h1-dsadfsadfsadfsafsafsdfassdfasdfas\"><a name=\"dsadfsadfsadfsafsafsdfassdfasdfas\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>dsadfsadfsadfsafsafsdfassdfasdfas</h1><p>sfsfsadfds<br>asdfsdfsadf<br>assdfsadfsaf<br>asdfsafasdf<br>safsadfsadfsaf</p>\n',1,'2020-01-18 00:46:25','2020-01-27 12:52:51'),(2,'123','','',1,'2020-01-27 12:25:17','2020-01-27 13:23:28');

#
# Structure for table "murongqing_task"
#

DROP TABLE IF EXISTS `murongqing_task`;
CREATE TABLE `murongqing_task` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `sketch` varchar(255) NOT NULL DEFAULT '' COMMENT '简述',
  `type` varchar(255) NOT NULL DEFAULT '' COMMENT '所属类型',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '任务状态 1未完成 2已完成',
  `project` varchar(255) NOT NULL DEFAULT '' COMMENT '所属项目',
  `user_id` int(11) DEFAULT '0' COMMENT '负责人id',
  `create_user_id` int(11) DEFAULT '0' COMMENT '创建人id',
  `finish_time` datetime DEFAULT NULL COMMENT '任务完成时间',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='任务表';

#
# Data for table "murongqing_task"
#


#
# Structure for table "murongqing_user"
#

DROP TABLE IF EXISTS `murongqing_user`;
CREATE TABLE `murongqing_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名称',
  `pwd` varchar(255) NOT NULL DEFAULT '' COMMENT '用户密码',
  `image` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `position` varchar(255) DEFAULT NULL COMMENT '用户职位',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';

#
# Data for table "murongqing_user"
#

INSERT INTO `murongqing_user` VALUES (1,'郭超','58EB83818CF60E38358E041D4BD31569',NULL,'Java开发','0000-00-00 00:00:00','2020-01-17 23:59:29'),(2,'杨晨','791C4A538D3C7992187D1AD81FE14B06','/upload/6b8c0809-9115-4c40-8efb-b2f89480ebc0.jpg',NULL,'2020-01-16 01:08:22','2020-01-16 22:25:11'),(3,'杨文黎','3809531147D60F35085B0088CF6EF4E6',NULL,NULL,'2020-01-16 22:42:49','2020-01-16 22:42:49'),(4,'李强','F142167CB6286127A60C42CC72F897A5',NULL,NULL,'2020-01-16 22:44:22','2020-01-16 22:44:22'),(5,'何艳欧','7D3C27EE21D56E7566D82D9AB4CDE0E8',NULL,NULL,'2020-01-16 22:54:39','2020-01-17 01:03:46');
