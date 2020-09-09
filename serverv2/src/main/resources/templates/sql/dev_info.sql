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

 Date: 09/09/2020 18:25:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dev_info
-- ----------------------------
DROP TABLE IF EXISTS `dev_info`;
CREATE TABLE `dev_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_JSON` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `IMEI` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ADD_TIME` datetime(0) DEFAULT NULL,
  `TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dev_info
-- ----------------------------
INSERT INTO `dev_info` VALUES (1, '告警信息丢失', '', NULL, NULL);
INSERT INTO `dev_info` VALUES (2, '{\"csq\":19,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":1,\"line_temp\":21.6,\"pwr_fct\":0,\"leak_cur\":0}', '355667780001167', NULL, NULL);
INSERT INTO `dev_info` VALUES (3, '{\"csq\":13,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":72.6,\"line_temp\":0,\"pwr_fct\":0,\"leak_cur\":0}', '869060039787530', NULL, NULL);
INSERT INTO `dev_info` VALUES (4, '{\"csq\":11,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":0.2,\"line_temp\":0,\"pwr_fct\":0,\"leak_cur\":0}', '869060039974385', NULL, NULL);
INSERT INTO `dev_info` VALUES (5, '{\"cur\":0,\"leak\":0,\"vol\":\"1-371.4\",\"temp\":0,\"overload\":0}', '869060039974476', NULL, NULL);
INSERT INTO `dev_info` VALUES (6, '{\"csq\":31,\"cur\":6.80,\"vol\":222.9,\"pwr\":1507.5,\"kwh\":860.7,\"line_temp\":0,\"pwr_fct\":0.99,\"leak_cur\":0}', '869060039974583', NULL, NULL);
INSERT INTO `dev_info` VALUES (7, '{\"csq\":16,\"cur\":18.13,\"vol\":236.3,\"pwr\":4276.1,\"kwh\":524,\"line_temp\":0,\"pwr_fct\":0.99,\"leak_cur\":0}', '869060039974617', NULL, NULL);
INSERT INTO `dev_info` VALUES (8, '{\"csq\":7,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":7.5,\"line_temp\":0,\"pwr_fct\":0,\"leak_cur\":0}', '869060039974641', NULL, NULL);
INSERT INTO `dev_info` VALUES (9, '{\"csq\":23,\"cur\":3.06,\"vol\":232.1,\"pwr\":669.2,\"kwh\":79.3,\"line_temp\":0,\"pwr_fct\":0.94,\"leak_cur\":0}', '869060039974682', NULL, NULL);
INSERT INTO `dev_info` VALUES (10, '{\"csq\":18,\"cur\":74.98,\"vol\":219.9,\"pwr\":16493.8,\"kwh\":0.2,\"line_temp\":25.1,\"pwr_fct\":1,\"leak_cur\":0.4}', '869060039974708', NULL, NULL);
INSERT INTO `dev_info` VALUES (11, '{\"csq\":31,\"cur\":12.48,\"vol\":229.6,\"pwr\":2445.4,\"kwh\":464.8,\"line_temp\":0,\"pwr_fct\":0.85,\"leak_cur\":0}', '869060039982925', NULL, NULL);
INSERT INTO `dev_info` VALUES (12, '{\"csq\":23,\"cur\":1.10,\"vol\":222.7,\"pwr\":135.4,\"kwh\":27.2,\"line_temp\":0,\"pwr_fct\":0.54,\"leak_cur\":0}', '869060039982966', NULL, NULL);
INSERT INTO `dev_info` VALUES (13, '{\"csq\":31,\"cur\":9.19,\"vol\":225.5,\"pwr\":1951.1,\"kwh\":139.6,\"line_temp\":0,\"pwr_fct\":0.94,\"leak_cur\":0}', '869060039982982', NULL, NULL);
INSERT INTO `dev_info` VALUES (14, '{\"csq\":17,\"cur\":7.71,\"vol\":231.3,\"pwr\":1752.3,\"kwh\":771.5,\"line_temp\":0,\"pwr_fct\":0.98,\"leak_cur\":0}', '869060039982990', NULL, NULL);
INSERT INTO `dev_info` VALUES (15, '{\"csq\":12,\"cur\":2.42,\"vol\":225,\"pwr\":517.5,\"kwh\":214.3,\"line_temp\":0,\"pwr_fct\":0.94,\"leak_cur\":0}', '869060039983055', NULL, NULL);
INSERT INTO `dev_info` VALUES (16, '{\"csq\":10,\"cur\":10.12,\"vol\":224.5,\"pwr\":2247.9,\"kwh\":336.3,\"line_temp\":0,\"pwr_fct\":0.98,\"leak_cur\":0}', '869060039983386', NULL, NULL);
INSERT INTO `dev_info` VALUES (17, '{\"csq\":31,\"cur\":20.06,\"vol\":240,\"pwr\":4767.8,\"kwh\":1447.3,\"line_temp\":0,\"pwr_fct\":0.98,\"leak_cur\":0}', '869060039983436', NULL, NULL);
INSERT INTO `dev_info` VALUES (18, '{\"csq\":21,\"cur\":17.97,\"vol\":220.9,\"pwr\":3944.8,\"kwh\":3164.5,\"line_temp\":0,\"pwr_fct\":0.99,\"leak_cur\":0}', '869060039983444', NULL, NULL);
INSERT INTO `dev_info` VALUES (19, '{\"csq\":19,\"cur\":74.95,\"vol\":219.9,\"pwr\":16487,\"kwh\":2.6,\"line_temp\":25,\"pwr_fct\":1,\"leak_cur\":994}', '869060039983477', NULL, NULL);
INSERT INTO `dev_info` VALUES (20, '{\"cur\":0,\"leak\":0,\"vol\":0,\"temp\":0,\"overload\":0}', '869060039983493', NULL, NULL);
INSERT INTO `dev_info` VALUES (21, '{\"csq\":18,\"cur\":0.57,\"vol\":231.2,\"pwr\":84.4,\"kwh\":52.9,\"line_temp\":0,\"pwr_fct\":0.63,\"leak_cur\":0}', '869060039983501', NULL, NULL);
INSERT INTO `dev_info` VALUES (22, '{\"csq\":31,\"cur\":14.72,\"vol\":216.8,\"pwr\":3179.9,\"kwh\":386.4,\"line_temp\":0,\"pwr_fct\":0.99,\"leak_cur\":0}', '869060039983519', NULL, NULL);
INSERT INTO `dev_info` VALUES (23, '{\"csq\":31,\"cur\":8.63,\"vol\":227.8,\"pwr\":1942,\"kwh\":311.3,\"line_temp\":0,\"pwr_fct\":0.98,\"leak_cur\":0}', '869060039983527', NULL, NULL);
INSERT INTO `dev_info` VALUES (24, '{\"csq\":31,\"cur\":24.09,\"vol\":223.5,\"pwr\":5363.3,\"kwh\":3500.9,\"line_temp\":0,\"pwr_fct\":0.99,\"leak_cur\":0}', '869060039983576', NULL, NULL);
INSERT INTO `dev_info` VALUES (25, '{\"csq\":14,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":0,\"line_temp\":0,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983634', NULL, NULL);
INSERT INTO `dev_info` VALUES (26, '{\"csq\":19,\"cur\":5,\"vol\":220,\"pwr\":1100.2,\"kwh\":0.3,\"line_temp\":24.9,\"pwr_fct\":1,\"leak_cur\":991.5}', '869060039983659', NULL, NULL);
INSERT INTO `dev_info` VALUES (27, '{\"csq\":22,\"cur\":12.35,\"vol\":236.1,\"pwr\":2889.8,\"kwh\":614,\"line_temp\":0,\"pwr_fct\":0.99,\"leak_cur\":0}', '869060039983683', NULL, NULL);
INSERT INTO `dev_info` VALUES (28, '{\"csq\":30,\"cur\":13.19,\"vol\":239.3,\"pwr\":3003.4,\"kwh\":2214.1,\"line_temp\":0,\"pwr_fct\":0.95,\"leak_cur\":0}', '869060039983840', NULL, NULL);
INSERT INTO `dev_info` VALUES (29, '{\"csq\":14,\"cur\":0,\"vol\":0,\"pwr\":0,\"kwh\":1.1,\"line_temp\":0,\"pwr_fct\":0,\"leak_cur\":0}', '869060039983931', NULL, NULL);
INSERT INTO `dev_info` VALUES (30, '{\"cur\":0,\"leak\":0,\"vol\":\"2-0\",\"temp\":0,\"overload\":0}', '869060039984046', NULL, NULL);
INSERT INTO `dev_info` VALUES (31, '{\"csq\":13,\"cur\":0.36,\"vol\":223.9,\"pwr\":46.6,\"kwh\":0,\"line_temp\":0,\"pwr_fct\":0.56,\"leak_cur\":0}', '869060039984301', NULL, NULL);
INSERT INTO `dev_info` VALUES (32, '{\"csq\":31,\"cur\":9.02,\"vol\":214.1,\"pwr\":1926.8,\"kwh\":791.9,\"line_temp\":0,\"pwr_fct\":0.99,\"leak_cur\":0}', '869060039984319', NULL, NULL);
INSERT INTO `dev_info` VALUES (33, '{\"csq\":24,\"cur\":12.81,\"vol\":222.6,\"pwr\":2793.5,\"kwh\":1188.9,\"line_temp\":0,\"pwr_fct\":0.97,\"leak_cur\":0}', '869060039984350', NULL, NULL);
INSERT INTO `dev_info` VALUES (34, '{\"csq\":28,\"cur\":8.20,\"vol\":234.2,\"pwr\":1347.7,\"kwh\":244.9,\"line_temp\":0,\"pwr_fct\":0.70,\"leak_cur\":0}', '869060039984582', NULL, NULL);
INSERT INTO `dev_info` VALUES (35, '{\"csq\":31,\"cur\":12.65,\"vol\":231.5,\"pwr\":2916.3,\"kwh\":1800.1,\"line_temp\":0,\"pwr_fct\":0.99,\"leak_cur\":0}', '869060039984624', NULL, NULL);
INSERT INTO `dev_info` VALUES (36, '{\"csq\":31,\"cur\":11.53,\"vol\":237.1,\"pwr\":2719,\"kwh\":1133.9,\"line_temp\":0,\"pwr_fct\":0.99,\"leak_cur\":0}', '869060039984632', NULL, NULL);
INSERT INTO `dev_info` VALUES (37, '{\"csq\":31,\"cur\":21.28,\"vol\":204,\"pwr\":4332.7,\"kwh\":1027.4,\"line_temp\":0,\"pwr_fct\":0.99,\"leak_cur\":0}', '869060039984657', NULL, NULL);
INSERT INTO `dev_info` VALUES (38, '{\"csq\":31,\"cur\":28.99,\"vol\":224.4,\"pwr\":6488.8,\"kwh\":2279.8,\"line_temp\":0,\"pwr_fct\":0.99,\"leak_cur\":0}', '869060039984749', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
