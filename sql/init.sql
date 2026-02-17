/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80404 (8.4.4)
 Source Host           : localhost:3306
 Source Schema         : fast_server

 Target Server Type    : MySQL
 Target Server Version : 80404 (8.4.4)
 File Encoding         : 65001

 Date: 17/02/2026 21:32:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `val` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of dict
-- ----------------------------
BEGIN;
INSERT INTO `dict` (`name`, `val`) VALUES ('CAPTCHA_EXPIRE_TIME', '300');
INSERT INTO `dict` (`name`, `val`) VALUES ('DEBUG_MODE', 'true');
INSERT INTO `dict` (`name`, `val`) VALUES ('NEW_USER_ROLE', '2023039982976638977');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint NOT NULL COMMENT '角色ID（雪花算法生成）',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `value` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`) COMMENT '角色名称唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`id`, `name`, `value`, `create_time`) VALUES (2023039787761156097, '管理员', 'ROLE_ADMIN', '2026-02-15 22:20:46');
INSERT INTO `role` (`id`, `name`, `value`, `create_time`) VALUES (2023039982976638977, '用户', 'ROLE_USER', '2026-02-15 22:21:33');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT '用户ID（雪花算法生成）',
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码（加密存储）',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户头像',
  `role_id` bigint NOT NULL COMMENT '角色ID，关联role表',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`) COMMENT '用户名唯一索引',
  KEY `idx_role_id` (`role_id`) COMMENT '角色ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `username`, `password`, `avatar`, `role_id`, `create_time`) VALUES (2023382185997582338, 'test001', '$2a$10$pzWjF11hjmj/7jY3MNjl0e/UAT9X5EuHV0OwD5qJ0FLS.E8oFF.sK', NULL, 2023039982976638977, '2026-02-16 21:01:20');
INSERT INTO `user` (`id`, `username`, `password`, `avatar`, `role_id`, `create_time`) VALUES (2023428611989409793, 'test002', '$2a$10$W6H/9pJ9n9wTrO2Qr68tBO2QuBgE7a5HmxlnHxSz74ZAc5TALSPxe', NULL, 2023039982976638977, '2026-02-17 00:05:49');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
