/*
 Navicat Premium Data Transfer

 Source Server         : 47.106.179.209
 Source Server Type    : MySQL
 Source Server Version : 50648
 Source Host           : 47.106.179.209:3306
 Source Schema         : industry_model

 Target Server Type    : MySQL
 Target Server Version : 50648
 File Encoding         : 65001

 Date: 13/05/2020 16:36:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for evaluation_info
-- ----------------------------
DROP TABLE IF EXISTS `evaluation_info`;
CREATE TABLE `evaluation_info`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `industry_model_id` int(10) NOT NULL COMMENT '外键，官员模型表ID',
  `star` int(2) NOT NULL COMMENT '评价星级',
  `user_id` int(10) DEFAULT NULL COMMENT '所属 用户',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '所属 用户 名称',
  `nick_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '昵称',
  `adress` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户 所属省份地址',
  `content_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评价内容',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '评价' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for industry_model_info
-- ----------------------------
DROP TABLE IF EXISTS `industry_model_info`;
CREATE TABLE `industry_model_info`  (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
  `model_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '模型名称',
  `model_type` int(10) NOT NULL COMMENT '模型类型，对应模型分类表',
  `model_version` varchar(56) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '模型 版本号',
  `Industry` int(10) DEFAULT NULL COMMENT '所属行业',
  `product_introduction` text CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT '产品介绍',
  `price_explanation` text CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT '价格说明',
  `touse_explanation` text CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT '使用说明',
  `is_toll` int(2) DEFAULT 2 COMMENT '是否收费 1：收费  2：免费',
  `price` decimal(8, 2) DEFAULT NULL COMMENT '价格',
  `price_type` int(2) DEFAULT NULL COMMENT '价格类型1：按日 2：按月3：按年  4：终身 5：次数',
  `use_number` int(4) DEFAULT NULL COMMENT '使用次数，当价格类型为  5时使用',
  `model_log_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '模型log地址：图片地址',
  `pageviews` bigint(12) DEFAULT 0 COMMENT '浏览量',
  `model_status` int(2) DEFAULT 0 COMMENT '模型状态 0：正常  1：禁用',
  `use_status` int(2) DEFAULT 1 COMMENT '使用状态 1：上架  2：下架',
  `del` int(2) DEFAULT 0 COMMENT '是否 删除  0：正常 1：删除',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `current_version` int(10) DEFAULT 0 COMMENT '当前数据版本',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '模型信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for model_classification_info
-- ----------------------------
DROP TABLE IF EXISTS `model_classification_info`;
CREATE TABLE `model_classification_info`  (
  `classification_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '分类ID主键',
  `classification_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '分类名称',
  `classification_status` int(2) DEFAULT 1 COMMENT 'z状态 1：正常 2：禁用',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`classification_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '模型分类' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for model_group_info
-- ----------------------------
DROP TABLE IF EXISTS `model_group_info`;
CREATE TABLE `model_group_info`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `industry_model_id` int(10) NOT NULL COMMENT '外键，官员模型表ID',
  `group_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '组名称',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '模型分组表 ' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for online_test_info
-- ----------------------------
DROP TABLE IF EXISTS `online_test_info`;
CREATE TABLE `online_test_info`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `industry_model_id` int(10) NOT NULL COMMENT '外键，官员模型表ID',
  `group_id` int(10) NOT NULL COMMENT '外键，分組表ID',
  `online_model_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '在线模型名称',
  `interface_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '接口名称',
  `interface_explanation` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '接口说明',
  `access_address` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '接口访问地址',
  `request_type` varchar(36) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '请求方式',
  `protocol` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '协议',
  `encoding_format` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '编码格式',
  `result_format` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '返回格式',
  `request_params` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT '请求参数',
  `result_data_structure` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT '返回 数据结构',
  `result_error_structure` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT '返回码',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
