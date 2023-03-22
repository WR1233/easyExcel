/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3307_mysql5.7.33
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : mybatis

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 29/06/2022 14:14:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_introduce` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'wuhobin1', '12345', '这是用户的个人介绍——修改');
INSERT INTO `user` VALUES (12, 'zhangsan', '12345', '这是个人介绍——修改');
INSERT INTO `user` VALUES (13, 'wuhobin123', '12345', '这是用户的个人介绍——修改');
INSERT INTO `user` VALUES (14, 'zhangsan23', '12345', '这是个人介绍——修改');

SET FOREIGN_KEY_CHECKS = 1;
