/*
 Navicat Premium Data Transfer

 Source Server         : xx
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : dev

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 08/09/2020 18:40:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dev_info
-- ----------------------------
DROP TABLE IF EXISTS `dev_info`;
CREATE TABLE `dev_info`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备数据ID',
  `DATA_JSON` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'json格式设备数据',
  `IMEI` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '设备IMEI号',
  `ADD_TIME` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据录入时间',
  `TYPE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备类型',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `IMEI_UNIQUE`(`IMEI`) USING BTREE COMMENT '唯一IMEI'
) ENGINE = InnoDB AUTO_INCREMENT = 4224224 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '告警信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dev_info
-- ----------------------------
INSERT INTO `dev_info` VALUES (4224224, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039787530', '2020-09-08 15:54:06', NULL);
INSERT INTO `dev_info` VALUES (4224225, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039984350', '2020-09-08 15:54:07', NULL);
INSERT INTO `dev_info` VALUES (4224226, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983659', '2020-09-08 15:54:08', NULL);
INSERT INTO `dev_info` VALUES (4224227, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039974617', '2020-09-08 15:54:09', NULL);
INSERT INTO `dev_info` VALUES (4224228, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039974476', '2020-09-08 15:54:09', NULL);
INSERT INTO `dev_info` VALUES (4224229, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039982925', '2020-09-08 15:54:10', NULL);
INSERT INTO `dev_info` VALUES (4224230, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039974682', '2020-09-08 15:54:11', NULL);
INSERT INTO `dev_info` VALUES (4224231, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983931', '2020-09-08 15:54:11', NULL);
INSERT INTO `dev_info` VALUES (4224232, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983493', '2020-09-08 15:54:12', NULL);
INSERT INTO `dev_info` VALUES (4224233, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039974385', '2020-09-08 15:54:16', NULL);
INSERT INTO `dev_info` VALUES (4224234, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983840', '2020-09-08 15:54:17', NULL);
INSERT INTO `dev_info` VALUES (4224235, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983634', '2020-09-08 15:54:18', NULL);
INSERT INTO `dev_info` VALUES (4224236, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983576', '2020-09-08 15:54:18', NULL);
INSERT INTO `dev_info` VALUES (4224237, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983477', '2020-09-08 15:54:19', NULL);
INSERT INTO `dev_info` VALUES (4224238, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983436', '2020-09-08 15:54:20', NULL);
INSERT INTO `dev_info` VALUES (4224239, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039974583', '2020-09-08 15:54:21', NULL);
INSERT INTO `dev_info` VALUES (4224240, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039974641', '2020-09-08 15:54:22', NULL);
INSERT INTO `dev_info` VALUES (4224241, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '355667780001167', '2020-09-08 15:54:23', NULL);
INSERT INTO `dev_info` VALUES (4224242, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039984046', '2020-09-08 15:54:24', NULL);
INSERT INTO `dev_info` VALUES (4224244, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039984301', '2020-09-08 15:55:43', NULL);
INSERT INTO `dev_info` VALUES (4224245, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039982982', '2020-09-08 15:55:44', NULL);
INSERT INTO `dev_info` VALUES (4224246, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983683', '2020-09-08 15:55:46', NULL);
INSERT INTO `dev_info` VALUES (4224247, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039984319', '2020-09-08 15:55:47', NULL);
INSERT INTO `dev_info` VALUES (4224248, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983386', '2020-09-08 15:55:49', NULL);
INSERT INTO `dev_info` VALUES (4224249, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983444', '2020-09-08 15:55:51', NULL);
INSERT INTO `dev_info` VALUES (4224250, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039982990', '2020-09-08 15:55:52', NULL);
INSERT INTO `dev_info` VALUES (4224251, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039982966', '2020-09-08 15:55:53', NULL);
INSERT INTO `dev_info` VALUES (4224252, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039984749', '2020-09-08 15:55:54', NULL);
INSERT INTO `dev_info` VALUES (4224254, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983519', '2020-09-08 15:56:00', NULL);
INSERT INTO `dev_info` VALUES (4224255, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983055', '2020-09-08 15:56:01', NULL);
INSERT INTO `dev_info` VALUES (4224256, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039984582', '2020-09-08 15:56:03', NULL);
INSERT INTO `dev_info` VALUES (4224257, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983501', '2020-09-08 15:56:05', NULL);
INSERT INTO `dev_info` VALUES (4224258, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039984632', '2020-09-08 15:56:06', NULL);
INSERT INTO `dev_info` VALUES (4224259, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983527', '2020-09-08 15:56:08', NULL);
INSERT INTO `dev_info` VALUES (4224260, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039984657', '2020-09-08 15:56:09', NULL);
INSERT INTO `dev_info` VALUES (4224261, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039974708', '2020-09-08 15:56:11', NULL);
INSERT INTO `dev_info` VALUES (4224262, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039984624', '2020-09-08 15:56:12', NULL);
INSERT INTO `dev_info` VALUES (4224263, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '869060039714583', '2020-09-08 15:56:13', NULL);
INSERT INTO `dev_info` VALUES (4224264, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '867808043281334', '2020-09-08 15:56:15', NULL);
INSERT INTO `dev_info` VALUES (4224265, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '867808042089654', '2020-09-08 15:56:17', NULL);
INSERT INTO `dev_info` VALUES (4224266, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '867808042089605', '2020-09-08 15:56:42', NULL);
INSERT INTO `dev_info` VALUES (4224267, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '867808042090520', '2020-09-08 15:56:44', NULL);
INSERT INTO `dev_info` VALUES (4224268, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":27.5,\"pwr_fct\":0,\"leak_cur\":0}', '867808043283868', '2020-09-08 15:56:45', NULL);

SET FOREIGN_KEY_CHECKS = 1;
