/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : msdemo

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 30/08/2019 17:54:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for jc_member
-- ----------------------------
DROP TABLE IF EXISTS `jc_member`;
CREATE TABLE `jc_member`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '混淆码',
  `roles` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jc_member
-- ----------------------------
INSERT INTO `jc_member` VALUES (10, 'admin', '$2a$10$vuNHAkABAIy6FM1vAz5Wxu609KC5X2lIOMBC19suXOAWrbzaPoPmW', NULL, 'ROLE_ADMIN,ROLE_USER');
INSERT INTO `jc_member` VALUES (11, 'guest', 'pass123', NULL, 'ROLE_GUEST');

-- ----------------------------
-- Table structure for jc_order
-- ----------------------------
DROP TABLE IF EXISTS `jc_order`;
CREATE TABLE `jc_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单号',
  `member_id` bigint(20) NOT NULL COMMENT '所属会员',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jc_order
-- ----------------------------
INSERT INTO `jc_order` VALUES (1, '201908748591', 1, '订单8591');
INSERT INTO `jc_order` VALUES (2, '201908748591', 1, '订单8591');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'USER', '普通用户');
INSERT INTO `role` VALUES (2, 'ADMIN', '管理员');

-- ----------------------------
-- Table structure for t_logger
-- ----------------------------
DROP TABLE IF EXISTS `t_logger`;
CREATE TABLE `t_logger`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `unit_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `tag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_logger
-- ----------------------------
INSERT INTO `t_logger` VALUES (1, '4354aaa417537', '1ae42f08434e1bce2f25c02f91f6edce', 'Transaction', 'business code error', '2019-08-29 16:20:23 552', 'member-service:8005');
INSERT INTO `t_logger` VALUES (2, '4354aaa417537', '1ae42f08434e1bce2f25c02f91f6edce', 'Transaction', 'business code error', '2019-08-29 16:20:36 569', 'member-service:8005');
INSERT INTO `t_logger` VALUES (3, '4354aaa417537', '0342242ebdc1e06cfd8ca50d5923e5e3', 'Transaction', 'business code error', '2019-08-29 16:26:57 394', 'order-service:8006');
INSERT INTO `t_logger` VALUES (4, '43551e2235537', '0342242ebdc1e06cfd8ca50d5923e5e3', 'Transaction', 'business code error', '2019-08-29 16:27:32 760', 'order-service:8006');
INSERT INTO `t_logger` VALUES (5, 'non', 'non', 'JoinGroupExecuteService', 'attempts to join the non-existent transaction group. [1ae42f08434e1bce2f25c02f91f6edce@member-service]', '2019-08-29 16:28:02 040', 'txlcn-tm:7970');
INSERT INTO `t_logger` VALUES (6, 'non', 'non', '43551e2235537', '1ae42f08434e1bce2f25c02f91f6edce', '2019-08-29 16:28:02 135', 'member-service:8005');
INSERT INTO `t_logger` VALUES (7, '4355328755537', '0342242ebdc1e06cfd8ca50d5923e5e3', 'Transaction', 'business code error', '2019-08-29 16:28:55 941', 'order-service:8006');
INSERT INTO `t_logger` VALUES (8, 'non', 'non', 'JoinGroupExecuteService', 'attempts to join the non-existent transaction group. [1ae42f08434e1bce2f25c02f91f6edce@member-service]', '2019-08-29 16:28:57 819', 'txlcn-tm:7970');
INSERT INTO `t_logger` VALUES (9, 'non', 'non', '4355328755537', '1ae42f08434e1bce2f25c02f91f6edce', '2019-08-29 16:28:57 890', 'member-service:8005');
INSERT INTO `t_logger` VALUES (10, '43557a3bdb537', '0342242ebdc1e06cfd8ca50d5923e5e3', 'Transaction', 'business code error', '2019-08-29 16:34:03 198', 'order-service:8006');
INSERT INTO `t_logger` VALUES (11, '43565ebe29537', '0342242ebdc1e06cfd8ca50d5923e5e3', 'Transaction', 'business code error', '2019-08-29 16:49:48 219', 'order-service:8006');
INSERT INTO `t_logger` VALUES (12, '43567b53c9537', '0342242ebdc1e06cfd8ca50d5923e5e3', 'Transaction', 'business code error', '2019-08-29 16:52:16 880', 'order-service:8006');
INSERT INTO `t_logger` VALUES (13, '43568a1f99537', '0342242ebdc1e06cfd8ca50d5923e5e3', 'Transaction', 'business code error', '2019-08-29 16:53:04 229', 'order-service:8006');
INSERT INTO `t_logger` VALUES (14, '43574a2d6f537', '0342242ebdc1e06cfd8ca50d5923e5e3', 'Transaction', 'business code error', '2019-08-29 17:06:06 055', 'order-service:8006');
INSERT INTO `t_logger` VALUES (15, '4357652e0f537', '0342242ebdc1e06cfd8ca50d5923e5e3', 'Transaction', 'business code error', '2019-08-29 17:08:01 097', 'order-service:8006');
INSERT INTO `t_logger` VALUES (16, '4357745fdf537', '0342242ebdc1e06cfd8ca50d5923e5e3', 'Transaction', 'business code error', '2019-08-29 17:08:37 070', 'order-service:8006');

-- ----------------------------
-- Table structure for t_tx_exception
-- ----------------------------
DROP TABLE IF EXISTS `t_tx_exception`;
CREATE TABLE `t_tx_exception`  (
  `id` bigint(20) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `ex_state` smallint(6) NOT NULL,
  `group_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mod_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `registrar` smallint(6) NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transaction_state` int(11) NULL DEFAULT NULL,
  `unit_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'user', '123456');
INSERT INTO `user` VALUES (2, 'admin', '123456');

-- ----------------------------
-- Table structure for user_permission
-- ----------------------------
DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_permission
-- ----------------------------
INSERT INTO `user_permission` VALUES (1, 1, 1);
INSERT INTO `user_permission` VALUES (2, 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
