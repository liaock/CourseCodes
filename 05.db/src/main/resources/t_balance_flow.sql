/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50625
 Source Host           : localhost:3306
 Source Schema         : ec

 Target Server Type    : MySQL
 Target Server Version : 50625
 File Encoding         : 65001

 Date: 13/06/2021 20:55:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_balance_flow
-- ----------------------------
DROP TABLE IF EXISTS `t_balance_flow`;
CREATE TABLE `t_balance_flow`  (
  `flow_id` int(11) NOT NULL COMMENT '流水ID',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '流水时间',
  `balance` decimal(10, 0) NOT NULL COMMENT '余额',
  `money` decimal(10, 0) NOT NULL COMMENT '金额',
  `direction` tinyint(1) NOT NULL COMMENT '方向，0-出金，1-入金',
  PRIMARY KEY (`flow_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
