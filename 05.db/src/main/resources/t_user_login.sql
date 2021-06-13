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

 Date: 13/06/2021 20:56:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user_login
-- ----------------------------
DROP TABLE IF EXISTS `t_user_login`;
CREATE TABLE `t_user_login`  (
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户ID',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码：md5加密的密码',
  `user_status` int(1) NOT NULL COMMENT '用户状态：0-无效，1-有效',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
