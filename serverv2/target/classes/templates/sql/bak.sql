/*
 Navicat Premium Data Transfer

 Source Server         : xx
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : bak

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 28/08/2020 18:10:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `admin_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色ID',
  `admin_id` int(11) NOT NULL COMMENT '管理员ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `add_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '数据录入时间',
  PRIMARY KEY (`admin_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, 67, 2, '2020-03-23 09:56:10');
INSERT INTO `admin_role` VALUES (2, 67, 1, '2020-03-23 09:56:10');
INSERT INTO `admin_role` VALUES (3, 68, 2, '2020-03-23 11:54:42');
INSERT INTO `admin_role` VALUES (4, 68, 6, '2020-03-23 11:54:42');
INSERT INTO `admin_role` VALUES (5, 69, 2, '2020-03-23 12:10:54');
INSERT INTO `admin_role` VALUES (6, 69, 7, '2020-03-23 12:10:54');
INSERT INTO `admin_role` VALUES (7, 70, 2, '2020-03-23 14:53:44');
INSERT INTO `admin_role` VALUES (8, 70, 8, '2020-03-23 14:53:44');
INSERT INTO `admin_role` VALUES (12, 75, 2, '2020-03-25 14:08:32');
INSERT INTO `admin_role` VALUES (13, 75, 9, '2020-03-25 14:08:32');
INSERT INTO `admin_role` VALUES (14, 76, 2, '2020-03-25 14:09:34');
INSERT INTO `admin_role` VALUES (15, 76, 9, '2020-03-25 14:09:34');
INSERT INTO `admin_role` VALUES (26, 85, 10, '2020-03-25 18:53:10');
INSERT INTO `admin_role` VALUES (31, 88, 2, '2020-03-27 09:59:55');
INSERT INTO `admin_role` VALUES (32, 88, 10, '2020-03-27 09:59:56');
INSERT INTO `admin_role` VALUES (33, 89, 9, '2020-03-30 11:18:50');
INSERT INTO `admin_role` VALUES (36, 92, 2, '2020-04-01 09:32:51');
INSERT INTO `admin_role` VALUES (37, 92, 9, '2020-04-01 09:32:51');
INSERT INTO `admin_role` VALUES (38, 93, 9, '2020-04-01 09:54:50');
INSERT INTO `admin_role` VALUES (39, 94, 2, '2020-04-07 15:05:24');
INSERT INTO `admin_role` VALUES (40, 94, 16, '2020-04-07 15:05:25');
INSERT INTO `admin_role` VALUES (41, 95, 2, '2020-04-07 15:47:05');
INSERT INTO `admin_role` VALUES (42, 95, 10, '2020-04-07 15:47:05');
INSERT INTO `admin_role` VALUES (48, 98, 10, '2020-04-08 14:38:00');
INSERT INTO `admin_role` VALUES (53, 99, 15, '2020-04-08 16:05:04');
INSERT INTO `admin_role` VALUES (54, 86, 19, '2020-04-09 10:07:03');
INSERT INTO `admin_role` VALUES (55, 74, 19, '2020-04-09 10:07:18');
INSERT INTO `admin_role` VALUES (57, 100, 9, '2020-04-09 10:13:29');
INSERT INTO `admin_role` VALUES (58, 90, 19, '2020-04-09 10:13:51');
INSERT INTO `admin_role` VALUES (59, 87, 19, '2020-04-09 10:15:58');
INSERT INTO `admin_role` VALUES (61, 71, 19, '2020-04-09 10:16:30');
INSERT INTO `admin_role` VALUES (62, 101, 19, '2020-04-09 16:17:14');
INSERT INTO `admin_role` VALUES (63, 102, 19, '2020-04-17 14:51:49');
INSERT INTO `admin_role` VALUES (66, 103, 20, '2020-05-28 15:17:56');
INSERT INTO `admin_role` VALUES (67, 104, 20, '2020-05-28 15:18:59');
INSERT INTO `admin_role` VALUES (68, 81, 20, '2020-05-28 15:54:50');
INSERT INTO `admin_role` VALUES (69, 105, 20, '2020-05-29 10:32:15');
INSERT INTO `admin_role` VALUES (70, 104, 20, '2020-05-29 14:36:19');
INSERT INTO `admin_role` VALUES (71, 100, 15, '2020-05-29 14:36:51');
INSERT INTO `admin_role` VALUES (72, 106, 9, '2020-06-01 18:37:02');
INSERT INTO `admin_role` VALUES (73, 107, 20, '2020-06-10 09:09:52');
INSERT INTO `admin_role` VALUES (74, 108, 15, '2020-06-10 10:15:21');
INSERT INTO `admin_role` VALUES (75, 109, 15, '2020-06-10 11:13:49');
INSERT INTO `admin_role` VALUES (76, 110, 15, '2020-06-10 14:19:51');
INSERT INTO `admin_role` VALUES (77, 111, 15, '2020-06-10 16:05:58');
INSERT INTO `admin_role` VALUES (78, 114, 15, '2020-06-10 16:24:07');
INSERT INTO `admin_role` VALUES (79, 116, 15, '2020-06-10 16:29:10');
INSERT INTO `admin_role` VALUES (80, 117, 15, '2020-06-10 16:30:26');
INSERT INTO `admin_role` VALUES (81, 118, 9, '2020-06-10 16:47:06');
INSERT INTO `admin_role` VALUES (82, 119, 15, '2020-06-10 17:27:00');
INSERT INTO `admin_role` VALUES (83, 120, 15, '2020-06-10 17:32:09');
INSERT INTO `admin_role` VALUES (84, 121, 15, '2020-06-10 17:32:15');
INSERT INTO `admin_role` VALUES (87, 122, 15, '2020-06-11 10:22:44');
INSERT INTO `admin_role` VALUES (88, 123, 15, '2020-06-11 11:03:41');
INSERT INTO `admin_role` VALUES (89, 125, 15, '2020-06-11 11:10:12');
INSERT INTO `admin_role` VALUES (90, 126, 15, '2020-06-14 17:06:50');
INSERT INTO `admin_role` VALUES (91, 129, 15, '2020-06-14 17:07:43');
INSERT INTO `admin_role` VALUES (92, 130, 15, '2020-06-14 17:08:32');
INSERT INTO `admin_role` VALUES (93, 131, 15, '2020-06-14 17:19:08');
INSERT INTO `admin_role` VALUES (95, 132, 20, '2020-07-28 11:37:48');

-- ----------------------------
-- Table structure for auth_account_log
-- ----------------------------
DROP TABLE IF EXISTS `auth_account_log`;
CREATE TABLE `auth_account_log`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户账户操作日志主键',
  `LOG_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '日志名称(login,register,logout)',
  `USER_ID` int(11) UNSIGNED NOT NULL COMMENT '用户id',
  `CREATE_TIME` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `SUCCEED` tinyint(4) DEFAULT NULL COMMENT '是否执行成功(0失败1成功)',
  `MESSAGE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '具体消息',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 92 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录注册登出记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_account_log
-- ----------------------------
INSERT INTO `auth_account_log` VALUES (17, '用户登录日志', 1, '2018-04-22 13:22:39', 1, NULL, '10.0.75.2');

-- ----------------------------
-- Table structure for auth_resource
-- ----------------------------
DROP TABLE IF EXISTS `auth_resource`;
CREATE TABLE `auth_resource`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `CODE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源名称',
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源描述',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '父资源编码->菜单',
  `URI` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访问地址URL',
  `TYPE` smallint(4) DEFAULT NULL COMMENT '类型 1:菜单menu 2:资源element(rest-api) 3:资源分类',
  `METHOD` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访问方式 GET POST PUT DELETE PATCH',
  `ICON` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `STATUS` smallint(4) DEFAULT 1 COMMENT '状态   1:正常、9：禁用',
  `CREATE_TIME` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 168 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源信息表(菜单,资源)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_resource
-- ----------------------------
INSERT INTO `auth_resource` VALUES (101, 'ACCOUNT_LOGIN', '用户登录', 0, '/account/login', 2, 'POST', NULL, 1, NULL, NULL);
INSERT INTO `auth_resource` VALUES (102, 'ACCOUNT_REGISTER', '用户注册', 0, '/account/register', 2, 'POST', NULL, 1, '2018-04-07 16:21:45', '2018-04-07 16:21:45');
INSERT INTO `auth_resource` VALUES (103, 'USER_LOGOUT', '用户登出', 0, '/user/exit', 2, 'GET', NULL, 1, NULL, NULL);
INSERT INTO `auth_resource` VALUES (104, 'EQUIPMENT__MONITOR', '获取设备监控数据', 0, '/equipment/info', 2, 'GET', NULL, 1, NULL, NULL);
INSERT INTO `auth_resource` VALUES (105, 'USER_GET_MENU', '菜单数据', 0, '/menu/authMenuList', 2, 'POST', NULL, 1, NULL, NULL);
INSERT INTO `auth_resource` VALUES (106, 'ADD_MENU', '增加菜单', 0, '/MENU', 2, 'POST', NULL, 1, '2018-04-12 06:15:39', '2018-04-12 06:15:39');
INSERT INTO `auth_resource` VALUES (107, 'UPDATE_MENU', '修改菜单', 0, '/menu', 2, 'PUT', NULL, 1, NULL, NULL);
INSERT INTO `auth_resource` VALUES (108, 'DELETE_MENU', '删除菜单', 0, '/menu', 2, 'DELETE', NULL, 1, NULL, NULL);
INSERT INTO `auth_resource` VALUES (109, 'ADD_MENU_ROLE', '添加角色菜单', 0, '/rolemenu/menu', 2, 'POST', NULL, 1, NULL, NULL);
INSERT INTO `auth_resource` VALUES (110, 'UPDATE_MENU_ROLE', '修改角色菜单', 0, '/rolemenu', 2, 'PUT', NULL, 1, NULL, NULL);
INSERT INTO `auth_resource` VALUES (111, 'DELETE_MENU_ROLE', '删除角色菜单', 0, '/rolemenu', 2, 'DELETE', NULL, 1, NULL, NULL);
INSERT INTO `auth_resource` VALUES (154, 'QUERY_MENU_ROLE', '查看角色菜单', 0, '/rolemenu', 2, 'GET', NULL, 1, NULL, NULL);
INSERT INTO `auth_resource` VALUES (167, 'xxxAAA', '总览', NULL, 'xxx', 1, 'GET', NULL, 1, NULL, NULL);

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `CODE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `STATUS` smallint(4) DEFAULT 1 COMMENT '状态   1:正常、9：禁用',
  `CREATE_TIME` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES (100, 'role_admin', '管理员角色', 1, NULL, NULL);
INSERT INTO `auth_role` VALUES (102, 'role_user', '用户角色', 1, NULL, NULL);
INSERT INTO `auth_role` VALUES (103, 'role_guest', '访客角色', 1, NULL, NULL);
INSERT INTO `auth_role` VALUES (104, 'role_anon', '非角色', 1, NULL, NULL);

-- ----------------------------
-- Table structure for auth_role_dqpt
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_dqpt`;
CREATE TABLE `auth_role_dqpt`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `dqpt_id` int(11) DEFAULT NULL COMMENT '设备ID',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for auth_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_menu`;
CREATE TABLE `auth_role_menu`  (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_role_menu
-- ----------------------------
INSERT INTO `auth_role_menu` VALUES (100, 1);
INSERT INTO `auth_role_menu` VALUES (100, 2);
INSERT INTO `auth_role_menu` VALUES (100, 3);
INSERT INTO `auth_role_menu` VALUES (100, 4);
INSERT INTO `auth_role_menu` VALUES (100, 5);
INSERT INTO `auth_role_menu` VALUES (100, 6);
INSERT INTO `auth_role_menu` VALUES (100, 7);
INSERT INTO `auth_role_menu` VALUES (100, 8);
INSERT INTO `auth_role_menu` VALUES (100, 9);
INSERT INTO `auth_role_menu` VALUES (100, 10);
INSERT INTO `auth_role_menu` VALUES (100, 11);
INSERT INTO `auth_role_menu` VALUES (100, 12);
INSERT INTO `auth_role_menu` VALUES (100, 13);
INSERT INTO `auth_role_menu` VALUES (103, 14);
INSERT INTO `auth_role_menu` VALUES (100, 15);
INSERT INTO `auth_role_menu` VALUES (100, 16);
INSERT INTO `auth_role_menu` VALUES (100, 17);
INSERT INTO `auth_role_menu` VALUES (100, 18);
INSERT INTO `auth_role_menu` VALUES (100, 19);
INSERT INTO `auth_role_menu` VALUES (100, 20);
INSERT INTO `auth_role_menu` VALUES (100, 21);
INSERT INTO `auth_role_menu` VALUES (100, 25);
INSERT INTO `auth_role_menu` VALUES (100, 32);
INSERT INTO `auth_role_menu` VALUES (100, 33);
INSERT INTO `auth_role_menu` VALUES (102, 32);
INSERT INTO `auth_role_menu` VALUES (103, 14);
INSERT INTO `auth_role_menu` VALUES (102, 15);
INSERT INTO `auth_role_menu` VALUES (102, 16);
INSERT INTO `auth_role_menu` VALUES (102, 21);
INSERT INTO `auth_role_menu` VALUES (102, 25);
INSERT INTO `auth_role_menu` VALUES (102, 33);
INSERT INTO `auth_role_menu` VALUES (102, 9);
INSERT INTO `auth_role_menu` VALUES (102, 10);
INSERT INTO `auth_role_menu` VALUES (102, 11);
INSERT INTO `auth_role_menu` VALUES (102, 12);
INSERT INTO `auth_role_menu` VALUES (102, 13);
INSERT INTO `auth_role_menu` VALUES (102, 1);
INSERT INTO `auth_role_menu` VALUES (103, 32);
INSERT INTO `auth_role_menu` VALUES (103, 14);
INSERT INTO `auth_role_menu` VALUES (103, 15);
INSERT INTO `auth_role_menu` VALUES (103, 16);
INSERT INTO `auth_role_menu` VALUES (103, 21);
INSERT INTO `auth_role_menu` VALUES (103, 25);
INSERT INTO `auth_role_menu` VALUES (103, 33);
INSERT INTO `auth_role_menu` VALUES (102, 34);
INSERT INTO `auth_role_menu` VALUES (102, 35);
INSERT INTO `auth_role_menu` VALUES (102, 14);
INSERT INTO `auth_role_menu` VALUES (102, 2);
INSERT INTO `auth_role_menu` VALUES (102, 3);
INSERT INTO `auth_role_menu` VALUES (102, 4);
INSERT INTO `auth_role_menu` VALUES (102, 5);
INSERT INTO `auth_role_menu` VALUES (102, 6);
INSERT INTO `auth_role_menu` VALUES (102, 7);
INSERT INTO `auth_role_menu` VALUES (102, 8);
INSERT INTO `auth_role_menu` VALUES (102, 24);

-- ----------------------------
-- Table structure for auth_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_resource`;
CREATE TABLE `auth_role_resource`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ROLE_ID` int(11) NOT NULL COMMENT '角色ID',
  `RESOURCE_ID` int(11) NOT NULL COMMENT '资源ID',
  `CREATE_TIME` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `ROLE_ID`(`ROLE_ID`, `RESOURCE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1044 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_role_resource
-- ----------------------------
INSERT INTO `auth_role_resource` VALUES (1, 100, 101, NULL, NULL);
INSERT INTO `auth_role_resource` VALUES (2, 100, 102, NULL, NULL);
INSERT INTO `auth_role_resource` VALUES (3, 100, 103, NULL, NULL);
INSERT INTO `auth_role_resource` VALUES (4, 100, 104, NULL, NULL);
INSERT INTO `auth_role_resource` VALUES (5, 100, 105, NULL, NULL);
INSERT INTO `auth_role_resource` VALUES (6, 100, 106, NULL, NULL);
INSERT INTO `auth_role_resource` VALUES (13, 100, 107, NULL, NULL);
INSERT INTO `auth_role_resource` VALUES (14, 100, 108, NULL, NULL);
INSERT INTO `auth_role_resource` VALUES (1032, 103, 101, '2020-08-28 17:04:17', NULL);
INSERT INTO `auth_role_resource` VALUES (1037, 102, 102, '2020-08-28 17:12:49', NULL);
INSERT INTO `auth_role_resource` VALUES (1038, 102, 104, '2020-08-28 17:12:49', NULL);
INSERT INTO `auth_role_resource` VALUES (1039, 102, 105, '2020-08-28 17:12:49', NULL);
INSERT INTO `auth_role_resource` VALUES (1040, 102, 106, '2020-08-28 17:12:49', NULL);
INSERT INTO `auth_role_resource` VALUES (1041, 102, 107, NULL, NULL);
INSERT INTO `auth_role_resource` VALUES (1042, 102, 108, NULL, NULL);
INSERT INTO `auth_role_resource` VALUES (1043, 102, 103, NULL, NULL);

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user`  (
  `UID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'uid,用户账号,主键',
  `USERNAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名(nick_name)',
  `PASSWORD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码(MD5(密码+盐))',
  `SALT` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盐',
  `REAL_NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户真名',
  `AVATAR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话号码(唯一)',
  `EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮件地址(唯一)',
  `SEX` tinyint(4) DEFAULT NULL COMMENT '性别(1.男 2.女)',
  `STATUS` tinyint(4) DEFAULT NULL COMMENT '账户状态(1.正常 2.锁定 3.删除 4.非法)',
  `CREATE_TIME` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `CREATE_WHERE` tinyint(4) DEFAULT NULL COMMENT '创建来源(1.web 2.android 3.ios 4.win 5.macos 6.ubuntu)',
  `IS_ADMIN` int(1) DEFAULT 0 COMMENT '0管理员 1用户',
  `GROUP_IDS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分组ID列表',
  PRIMARY KEY (`UID`) USING BTREE,
  UNIQUE INDEX `phone`(`PHONE`) USING BTREE,
  UNIQUE INDEX `email`(`EMAIL`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES (1, 'admin', '8419233822896A77C98CBBA2720EA832', 'm0lps7', NULL, NULL, NULL, NULL, NULL, 1, '2020-01-20 22:51:43', NULL, NULL, 0, NULL);
INSERT INTO `auth_user` VALUES (2, 'xx', '8419233822896A77C98CBBA2720EA832', 'm0lps7', NULL, NULL, NULL, NULL, NULL, 1, '2020-01-20 22:51:43', NULL, NULL, 0, NULL);
INSERT INTO `auth_user` VALUES (3, 'test', '8419233822896A77C98CBBA2720EA832', 'm0lps7', NULL, NULL, NULL, NULL, NULL, 1, '2020-01-20 22:51:43', NULL, NULL, 0, NULL);
INSERT INTO `auth_user` VALUES (4, 'zs', '8419233822896A77C98CBBA2720EA832', 'm0lps7', NULL, NULL, NULL, NULL, NULL, 1, '2020-01-20 22:51:43', NULL, NULL, 0, NULL);

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户角色关联表主键',
  `USER_ID` int(11) UNSIGNED NOT NULL COMMENT '用户UID',
  `ROLE_ID` int(11) NOT NULL COMMENT '角色ID',
  `CREATE_TIME` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `USER_ID`(`USER_ID`, `ROLE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
INSERT INTO `auth_user_role` VALUES (2, 1, 100, '2020-01-20 22:51:43', '2020-01-20 22:51:43');
INSERT INTO `auth_user_role` VALUES (3, 1, 102, '2020-01-20 22:51:43', '2020-01-20 22:51:43');
INSERT INTO `auth_user_role` VALUES (4, 2, 102, NULL, NULL);
INSERT INTO `auth_user_role` VALUES (5, 3, 103, NULL, NULL);
INSERT INTO `auth_user_role` VALUES (6, 4, 104, NULL, NULL);

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment`  (
  `eqpt_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `device_id` int(11) DEFAULT NULL COMMENT 'onenet平台生成设备唯一ID',
  `eqpt_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '设备名称',
  `imei` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'imei号',
  `imsi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'imsi号',
  `eqpt_sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '设备序列号',
  `eqpt_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '设备类型',
  `power` float DEFAULT NULL COMMENT '限定功率',
  `voltage` smallint(3) DEFAULT NULL COMMENT '额定电压',
  `electricity` smallint(3) DEFAULT NULL COMMENT '额定电流',
  `register` tinyint(3) NOT NULL COMMENT '是否注册到onenet平台',
  `eqpt_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备详细地址',
  `latitude` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '纬度',
  `longitude` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '经度',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `site_id` int(11) DEFAULT NULL COMMENT '小区ID',
  `admin_id` int(11) DEFAULT NULL COMMENT '所属管理员ID',
  `group_id` int(11) DEFAULT NULL COMMENT '设备分组ID',
  `last_report_time` datetime(0) DEFAULT NULL COMMENT '设备上次上报时间（暂未使用）',
  `add_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据录入时间',
  `iccid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ICCID',
  `imgs` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '市',
  `district` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '区',
  PRIMARY KEY (`eqpt_id`) USING BTREE,
  UNIQUE INDEX `equipment_eqpt_sn_uindex`(`eqpt_sn`) USING BTREE,
  UNIQUE INDEX `equipment_imei_uindex`(`imei`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 244 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of equipment
-- ----------------------------
INSERT INTO `equipment` VALUES (2, 588930333, '用电告警器', '869060039787530', '869060039787530', 'DNPUU-1', '0', 2000, 220, 10, 1, NULL, '23.170117', '113.438926', 1, 3, 81, 1, NULL, '2020-03-23 10:55:59', NULL, NULL, '广东省', '广州市', '黄浦区');
INSERT INTO `equipment` VALUES (3, 588964212, '用电告警器', '869060039984350', '869060039984350', 'DNPUU-2', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 99, 81, 1, NULL, '2020-03-23 16:13:04', NULL, NULL, '广东省', '广州市', '海珠区');
INSERT INTO `equipment` VALUES (4, 588965079, '用电告警器', '869060039983659', '869060039983659', 'DNPUU-3', '0', NULL, NULL, NULL, 1, NULL, '23.170433', '113.438905', NULL, 115, 81, 1, NULL, '2020-03-23 16:22:59', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (5, 588965590, '用电告警器', '869060039983501', '869060039983501', 'DNPUU-4', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 97, 81, 1, NULL, '2020-03-23 16:29:06', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (6, 588965711, '用电告警器', '869060039974617', '869060039974617', 'DNPUU-5', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 101, 81, 1, NULL, '2020-03-23 16:30:23', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (7, 588965773, '用电告警器', '869060039974476', '869060039974476', 'DNPUU-6', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 88, 81, 1, NULL, '2020-03-23 16:31:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (8, 588965868, '用电告警器', '869060039984624', '869060039984624', 'DNPUU-7', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 119, 81, 1, NULL, '2020-03-23 16:32:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (9, 588965920, '用电告警器', '869060039984632', '869060039984632', 'DNPUU-8', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 90, 81, 1, NULL, '2020-03-23 16:32:36', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (10, 588966094, '用电告警器', '869060039983477', '869060039983477', 'DNPUU-9', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 92, 81, 1, NULL, '2020-03-23 16:34:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (11, 588966170, '用电告警器', '869060039983683', '869060039983683', 'DNPUU-10', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 121, 81, 1, NULL, '2020-03-23 16:35:33', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (12, 588966227, '用电告警器', '869060039983634', '869060039983634', 'DNPUU-11', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 1, 81, 1, NULL, '2020-03-23 16:36:12', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (13, 588966301, '用电告警器', '869060039974641', '869060039974641', 'DNPUU-12', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 21, 81, 1, NULL, '2020-03-23 16:37:06', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (14, 588966367, '用电告警器', '869060039983493', '869060039983493', 'DNPUU-13', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 98, 81, 1, NULL, '2020-03-23 16:37:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (15, 588966449, '用电告警器', '869060039983055', '869060039983055', 'DNPUU-14', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 110, 81, 1, NULL, '2020-03-23 16:38:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (16, 588966639, '用电告警器', '869060039982925', '869060039982925', 'DNPUU-15', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 111, 81, 1, NULL, '2020-03-23 16:41:10', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (18, 588966995, '用电告警器', '869060039982966', '869060039982966', 'DNPUU-16', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 22, 81, 1, NULL, '2020-03-23 16:44:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (19, 588967034, '用电告警器', '869060039984749', '869060039984749', 'DNPUU-17', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 87, 81, 1, NULL, '2020-03-23 16:45:22', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (20, 588967095, '用电告警器', '869060039983519', '869060039983519', 'DNPUU-18', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 91, 81, 1, NULL, '2020-03-23 16:45:58', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (22, 588967213, '用电告警器', '869060039984582', '869060039984582', 'DNPUU-19', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 89, 81, 1, NULL, '2020-03-23 16:47:17', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (24, 588967256, '用电告警器', '869060039974682', '869060039974682', 'DNPUU-20', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 113, 81, 1, NULL, '2020-03-23 16:47:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (25, 588967321, '用电告警器', '869060039983931', '869060039983931', 'DNPUU-21', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 94, 81, 1, NULL, '2020-03-23 16:48:23', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (26, 588967440, '用电告警器', '869060039974385', '869060039974385', 'DNPUU-22', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 104, 81, 1, NULL, '2020-03-23 16:49:36', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (27, 588967483, '用电告警器', '869060039983840', '869060039983840', 'DNPUU-23', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 107, 81, 1, NULL, '2020-03-23 16:50:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (28, 588967540, '用电告警器', '869060039983576', '869060039983576', 'DNPUU-24', '0', NULL, NULL, NULL, 1, NULL, '13.097311', '89.327581', NULL, 106, 81, 1, NULL, '2020-03-23 16:50:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (29, 588967596, '用电告警器', '869060039983436', '869060039983436', 'DNPUU-25', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 109, 81, 1, NULL, '2020-03-23 16:51:20', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (30, 588967655, '用电告警器', '869060039974583', '869060039974583', 'DNPUU-26', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 100, 81, 1, NULL, '2020-03-23 16:52:05', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (71, 590029583, '用电告警器', '869060039984301', '869060039984301', 'DNPUU-28', '0', NULL, NULL, NULL, 1, NULL, '23.170083', '113.438646', NULL, 118, 81, 1, NULL, '2020-03-31 11:48:12', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (75, 590031561, '用电告警器', '869060039982982', '869060039982982', 'DNPUU-29', '0', NULL, NULL, NULL, 1, NULL, '23.170083', '113.438646', NULL, 114, 81, 1, NULL, '2020-03-31 12:09:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (81, 590042913, '用电告警器', '869060039983386', '869060039983386', 'DNPUU-30', '0', NULL, NULL, NULL, 1, NULL, '23.170083', '113.438646', NULL, 108, 81, 1, NULL, '2020-03-31 13:40:10', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (82, 590042944, '用电告警器', '869060039984319', '869060039984319', 'DNPUU-31', '0', NULL, NULL, NULL, 1, NULL, '23.170083', '113.438646', NULL, 112, 81, 1, NULL, '2020-03-31 13:40:34', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (83, 590042981, '用电告警器', '869060039982990', '869060039982990', 'DNPUU-32', '0', NULL, NULL, NULL, 1, NULL, '23.170083', '113.438646', NULL, 96, 81, 1, NULL, '2020-03-31 13:40:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (84, 590043015, '用电告警器', '869060039983444', '869060039983444', 'DNPUU-33', '0', NULL, NULL, NULL, 1, NULL, '23.170083', '113.438646', NULL, 117, 81, 1, NULL, '2020-03-31 13:41:20', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (87, 590043154, '用电告警器', '869060039983527', '869060039983527', 'DNPUU-34', '0', NULL, NULL, NULL, 1, NULL, '23.170083', '113.438646', NULL, 116, 81, 1, NULL, '2020-03-31 13:42:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (88, 590043191, '用电告警器', '869060039984657', '869060039984657', 'DNPUU-35', '0', NULL, NULL, NULL, 1, NULL, '23.170083', '113.438646', NULL, 105, 81, 1, NULL, '2020-03-31 13:43:18', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (89, 590043230, '用电告警器', '869060039974708', '869060039974708', 'DNPUU-36', '0', NULL, NULL, NULL, 1, NULL, '23.170083', '113.438646', NULL, 3, 81, 1, NULL, '2020-03-31 13:43:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (109, 593383120, '功率追踪仪_三相', '867808042090181', '867808042090181', '867808042090181', '2', NULL, NULL, NULL, 1, NULL, '23.170083', '113.438646', NULL, 124, 81, 1, NULL, '2020-03-31 13:43:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (130, 600860390, '功率追踪仪_三相', '867808042087328', '867808042087328', '867808042087328', '2', NULL, NULL, NULL, 1, NULL, '23.170083', '113.438646', NULL, 124, 81, 1, NULL, '2020-03-31 13:43:47', '89860426101970163655', NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (131, 600860261, '功率追踪仪_三相', '867808042090272', '867808042090272', '867808042090272', '2', NULL, NULL, NULL, 1, NULL, '23.170083', '113.438646', NULL, 124, 81, 1, NULL, '2020-03-31 13:43:47', '89860426101970163652', NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (132, 600859829, '功率追踪仪_三相', '867808042088144', '867808042088144', '867808042088144', '2', NULL, NULL, NULL, 1, NULL, '23.170083', '113.438646', NULL, 124, 81, 1, NULL, '2020-03-31 13:43:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (173, NULL, '用电告警器', '867808042089654', '867808042089654', '867808042089654', '0', NULL, 220, NULL, 0, NULL, NULL, NULL, NULL, 173, 102, 1, NULL, '2020-06-17 10:01:09', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (175, NULL, '用电告警器', '867808042090520', '867808042090520', '867808042090520', '0', NULL, 220, 75, 0, NULL, NULL, NULL, NULL, 175, 102, 1, NULL, '2020-06-17 10:20:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (183, NULL, '三相用电安全监测终端', '867808042089779', '867808042089779', '867808042089779', '2', NULL, 380, NULL, 0, NULL, NULL, NULL, NULL, 178, 2, 1, NULL, '2020-06-28 12:17:52', '89860426101970163668', NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (188, NULL, '三相用电安全监测终端', '867808042087526', '867808042087526', '867808042087526', '2', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 181, 102, 1, NULL, '2020-06-29 15:07:13', '89860426101970163673', NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (189, NULL, '三相用电安全监测终端', '867808042087443', '867808042087443', '867808042087443', '2', NULL, 380, NULL, 0, NULL, NULL, NULL, NULL, 182, 102, 1, NULL, '2020-06-29 16:18:04', '89860426101970163670', NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (190, NULL, '三相用电安全监测终端', '867808042090256', '867808042090256', '867808042090256', '2', NULL, 380, NULL, 0, NULL, NULL, NULL, NULL, 199, 102, 1, NULL, '2020-06-29 18:37:23', '89860426101970163672', NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (191, NULL, '三相用电安全监测终端', '867808042088458', '867808042088458', '867808042088458', '2', NULL, 380, NULL, 0, NULL, NULL, NULL, NULL, 199, 102, 1, NULL, '2020-06-29 18:39:15', '89860426101970163666', NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (192, NULL, '三相用电安全监测终端', '867808042089092', '867808042089092', '867808042089092', '2', NULL, 380, NULL, 0, NULL, NULL, NULL, NULL, 200, 102, 1, NULL, '2020-06-29 18:42:03', '89860426101970163667', NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (193, NULL, '三相用电安全监测终端', '867808042087690', '867808042087690', '867808042087690', '2', NULL, 380, NULL, 0, NULL, NULL, NULL, NULL, 200, 102, 1, NULL, '2020-06-29 18:42:59', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (204, NULL, '用电告警器', '867808042114635', '867808042114635', '867808042114635', '2', NULL, 380, NULL, 0, NULL, NULL, NULL, NULL, 203, 102, 8, NULL, '2020-07-01 14:33:52', '89860426101970163669', NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (210, NULL, '测试单相2', '867808043283868', '867808043283868', '867808043283868', '0', NULL, 220, NULL, 0, NULL, NULL, NULL, 39, 205, 102, 1, NULL, '2020-07-10 12:07:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (211, NULL, '测试单相3', '867808043290616', '867808043290616', '867808043290616', '0', NULL, 220, NULL, 0, NULL, NULL, NULL, NULL, 205, 102, 1, NULL, '2020-07-10 12:09:24', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (212, NULL, '测试单相1', '867808043290459', '867808043290459', '867808043290459', '0', NULL, 220, NULL, 0, NULL, NULL, NULL, 39, 205, 102, 1, NULL, '2020-07-10 12:17:24', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (215, NULL, '测试三相2', '867808042114742', '867808042114742', '867808042114742', '2', NULL, 380, NULL, 1, NULL, NULL, NULL, 39, 175, 102, 5, NULL, '2020-07-14 13:44:14', '89860426101970163665', NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (216, NULL, '测试三相1', '867808042087385', '867808042087385', '867808042087385', '2', NULL, 220, NULL, 0, NULL, NULL, NULL, 39, 209, 102, 40, NULL, '2020-07-14 13:48:00', '89860426101970163674', NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (218, NULL, '测试单相4', '867808043290673', '867808043290673', '867808043290673', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 210, 102, 5, NULL, '2020-07-16 15:06:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (219, NULL, '测试单相5', '867808043290509', '867808043290509', '867808043290509', '0', NULL, 220, NULL, 1, NULL, NULL, NULL, NULL, 175, 102, 5, NULL, '2020-07-16 17:34:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (220, NULL, '测试单相6', '867808043290657', '867808043290657', '867808043290657', '0', NULL, 220, NULL, 1, NULL, NULL, NULL, 42, 175, 102, 5, NULL, '2020-07-16 17:36:08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (223, NULL, '用电安全监测终端', '867808042090132', '867808042090132', '20200728A649', '2', NULL, 380, NULL, 0, NULL, NULL, NULL, 102, 175, 102, 8, NULL, '2020-08-05 18:03:54', '89860470192071171344', NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES (233, NULL, '用电安全监测终端', '867808042114569', '867808042114569', '867808042114569', '0', NULL, NULL, NULL, 1, NULL, NULL, NULL, 102, 216, 102, 8, NULL, '2020-08-06 09:47:19', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary`  (
  `id` int(11) NOT NULL,
  `KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `VALUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES (1, 'LOGO', '{image:\'/images/logo.png\',title:\'LLLL\'}');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父ID',
  `order_num` int(4) DEFAULT 1 COMMENT '显示顺序',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求地址',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '是否隐藏 0显示 1隐藏',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字体图标',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `is_basic` int(1) DEFAULT 0 COMMENT '1、不需要权限 0、需要权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '总览3', 0, -1, '/admin/page/welcome-2', '0', NULL, 'fa fa-address-card-o', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES (2, '灾情预警4', 0, 2, NULL, '0', NULL, 'fa fa-address-book', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (3, '用电告警器2', 2, 1, NULL, '0', NULL, '', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (4, '设备监控', 3, 1, '/admin/page/equipment_monitor?eqptType=0', '0', NULL, 'fa fa-home', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (5, '告警信息', 3, 2, '/admin/page/alarm', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (6, '烟雾告警器', 2, 2, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (7, '设备监控', 6, 2, '/admin/page/equipment_monitor?eqptType=1', '0', NULL, 'fa fa-home', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (8, '告警信息', 6, 3, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (9, '生产维护', 0, 1, NULL, '0', NULL, 'fa fa-500px', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (10, '分组管理', 9, 1, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (11, '地址管理', 9, 2, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (12, '维修记录', 9, 3, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (13, '用户管理', 9, 4, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (14, '权限管理', 32, 3, NULL, '1', NULL, 'fa fa-home', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (15, '角色权限分配', 14, 1, '/admin/role/main', '1', NULL, 'fa fa-file-powerpoint-o', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (16, '角色管理', 14, 2, NULL, '1', NULL, 'fa fa-user-o', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (17, '大数据统计', 0, 4, NULL, '1', NULL, 'fa fa-bar-chart', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES (18, '数据样式1', 17, 1, '/admin/page/welcome-2', '0', NULL, 'fa fa-home', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES (19, '数据样式2', 17, 2, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES (20, '数据样式3', 17, 3, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES (21, '菜单管理', 32, 1, '/admin/menu/main', '1', NULL, 'fa fa-home', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (24, 'xx', 0, 1, '', '0', NULL, 'fa fa-home', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (25, '角色菜单权限', 21, 1, '/admin/role/menu/main', '1', NULL, 'fa fa-home', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (32, '系统管理', 0, 1, '', '1', NULL, 'fa fa-assistive-listening-systems', 'admin', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (33, '菜单分配', 21, 1, '/admin/menu/main', '1', NULL, 'fa fa-align-justify', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (34, '资源管理', 32, 1, '', '1', NULL, '', 'xx', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (35, '资源分配', 0, 1, '/admin/resource/main', '1', NULL, 'fa fa-adjust', 'xx', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (38, 'xc', 0, 1, '', '1', NULL, 'fa fa-address-card-o', 'xx', NULL, NULL, NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
