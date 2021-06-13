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

 Date: 13/06/2021 20:56:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品编号',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品名',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品分类',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `publish_status` tinyint(255) NOT NULL COMMENT '上下架状态：0下架1上架',
  `description` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '商品描述',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
