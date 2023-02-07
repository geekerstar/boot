/*
 Navicat Premium Data Transfer

 Source Server         : vm-master
 Source Server Type    : MySQL
 Source Server Version : 80029 (8.0.29)
 Source Host           : 192.168.0.120:3306
 Source Schema         : product_db1

 Target Server Type    : MySQL
 Target Server Version : 80029 (8.0.29)
 File Encoding         : 65001

 Date: 07/02/2023 20:17:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product_base
-- ----------------------------
DROP TABLE IF EXISTS `product_base`;
CREATE TABLE `product_base` (
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `shop_id` bigint DEFAULT NULL COMMENT '店铺ID',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `price` bigint DEFAULT NULL COMMENT '价格',
  `origin_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产地',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for product_description
-- ----------------------------
DROP TABLE IF EXISTS `product_description`;
CREATE TABLE `product_description` (
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '描述内容',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
