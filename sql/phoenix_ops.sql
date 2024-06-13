/*
 Navicat Premium Dump SQL

 Source Server         : 192.168.156.24_3306
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37-0ubuntu0.24.04.1)
 Source Host           : 192.168.156.24:3306
 Source Schema         : phoenix_ops

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37-0ubuntu0.24.04.1)
 File Encoding         : 65001

 Date: 13/06/2024 22:54:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for machine
-- ----------------------------
DROP TABLE IF EXISTS `machine`;
CREATE TABLE `machine`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机器名',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机器IP',
  `port` smallint UNSIGNED NOT NULL DEFAULT 22 COMMENT '用于SSH链接的端口',
  `operating_system` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主机操作系统',
  `is_virtual` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否为虚拟机（0：不是；1：是）',
  `enabled` tinyint UNSIGNED NOT NULL COMMENT '是否可用（0：不可用；1：可用）',
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注信息',
  `created_user` bigint UNSIGNED NOT NULL COMMENT '创建用户',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `latest_time` datetime NOT NULL COMMENT '最近登录时间',
  `updated_time` datetime NOT NULL COMMENT '修改时间',
  `updated_user` bigint NOT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '主机表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of machine
-- ----------------------------
INSERT INTO `machine` VALUES (1, 'Ubuntu-24', '192.168.156.24', 22, 'Linux', 1, 1, '', 1, '2024-05-14 10:49:34', '2024-06-13 20:33:05', '2024-06-13 20:33:12', 1);
INSERT INTO `machine` VALUES (2, 'Ubuntu-24', '192.168.156.24', 22, 'Linux', 1, 1, '', 1, '2024-05-14 10:50:25', '2024-06-13 20:33:08', '2024-06-13 20:33:16', 1);

-- ----------------------------
-- Table structure for resource_auth
-- ----------------------------
DROP TABLE IF EXISTS `resource_auth`;
CREATE TABLE `resource_auth`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `is_privileged` tinyint(1) NOT NULL COMMENT '是否为特权账号',
  `is_enabled` tinyint(1) NOT NULL COMMENT '是否可用',
  `machine_id` bigint UNSIGNED NOT NULL COMMENT '所属机器ID',
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注信息',
  `latest_conn_time` datetime NULL DEFAULT NULL COMMENT '上次连接时间',
  `effective_end_time` datetime NOT NULL DEFAULT ((now() + interval 1 year)) COMMENT '有效结束时间',
  `created_time` datetime NULL DEFAULT (now()) COMMENT '创建时间',
  `created_user` bigint NULL DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '主机账号' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource_auth
-- ----------------------------
INSERT INTO `resource_auth` VALUES (1, 'root账号', 'root', 'W&l1203@', 1, 1, 1, '', NULL, '2025-05-14 13:48:48', '2024-05-14 13:48:48', NULL);
INSERT INTO `resource_auth` VALUES (2, 'phoenix账号', 'phoenix', 'W&l1203@', 0, 0, 2, '', NULL, '2025-05-14 13:48:48', '2024-05-14 13:48:48', NULL);

SET FOREIGN_KEY_CHECKS = 1;
