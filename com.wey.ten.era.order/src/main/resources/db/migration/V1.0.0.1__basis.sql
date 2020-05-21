/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : order

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 19/05/2020 14:14:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_detail_info
-- ----------------------------
DROP TABLE IF EXISTS `order_detail_info`;
CREATE TABLE `order_detail_info`  (
  `id` int(10) NOT NULL COMMENT '订单详情ID 主键',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '订单编号',
  `application_classification` int(10) NOT NULL COMMENT '应用分类',
  `buyer_user_id` int(10) NOT NULL COMMENT '采购商ID',
  `buyer_enterpris_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '采购商企业名称',
  `buyer_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '采购用户名称',
  `service_provider_user_id` int(10) NOT NULL COMMENT '服务商ID',
  `service_provider_enterpris_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '服务商企业名称',
  `service_provider_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '服务商用户名称',
  `software_id` int(10) NOT NULL COMMENT '产品id',
  `is_toll` int(2) NOT NULL DEFAULT 2 COMMENT '是否收费 1：收费  2：免费',
  `price` decimal(8, 2) NOT NULL COMMENT '产品总价格',
  `price_type` int(2) NOT NULL COMMENT '价格类型1：按日 2：按月3：按年  4：终身 5：次数',
  `use_number` int(4) NOT NULL COMMENT '使用次数，当价格类型为  5时使用',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` int(10) NOT NULL COMMENT '主键',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '订单编号',
  `buyer_user_id` int(10) NOT NULL COMMENT '采购商ID',
  `order_status` int(1) NOT NULL DEFAULT 1 COMMENT '订单状态  1：待支付  2：已支付',
  `order_total_amount` double(10, 2) NOT NULL DEFAULT 0.00 COMMENT '订单总金额',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shopping_cart_info
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart_info`;
CREATE TABLE `shopping_cart_info`  (
  `id` int(10) NOT NULL COMMENT '订单详情ID 主键',
  `application_classification` int(10) NOT NULL COMMENT '应用分类',
  `user_id` int(10) NOT NULL COMMENT '用户ID',
  `service_provider_user_id` int(10) NOT NULL COMMENT '服务商ID',
  `software_id` int(10) NOT NULL COMMENT '产品id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_purchase_history
-- ----------------------------
DROP TABLE IF EXISTS `user_purchase_history`;
CREATE TABLE `user_purchase_history`  (
  `id` int(10) NOT NULL COMMENT '订单详情ID 主键',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '订单编号',
  `application_classification` int(10) NOT NULL COMMENT '应用分类',
  `user_id` int(10) NOT NULL COMMENT '用户ID',
  `service_provider_user_id` int(10) NOT NULL COMMENT '服务商ID',
  `software_id` int(10) NOT NULL COMMENT '产品id',
  `is_toll` int(2) NOT NULL DEFAULT 2 COMMENT '是否收费 1：收费  2：免费',
  `price` decimal(8, 2) NOT NULL COMMENT '产品总价格',
  `price_type` int(2) NOT NULL COMMENT '价格类型1：按日 2：按月3：按年  4：终身 5：次数',
  `expiration_date` datetime(0) DEFAULT NULL COMMENT '有效期，当按次数，该值不用设置',
  `is_effective` int(1) NOT NULL DEFAULT 1 COMMENT '是否有效 1：有效   2无效',
  `use_number` int(4) NOT NULL COMMENT '使用次数，当价格类型为  5时使用',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
