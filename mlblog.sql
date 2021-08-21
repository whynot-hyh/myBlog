/*
 Navicat Premium Data Transfer

 Source Server         : guoshun
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : mlblog

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 14/07/2021 22:03:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `imgUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `view` int(11) NOT NULL DEFAULT 0,
  `top` int(1) NULL DEFAULT 0 COMMENT '0不置顶1置顶',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type_id` int(11) NOT NULL,
  `status` int(1) NULL DEFAULT NULL COMMENT '0代表已发布，1代表保存',
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modify` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, 'edwfrgt', '', 'vcfb n', 'vfgbhn', 32, 0, 'vrefvff', 1, 0, NULL, NULL);
INSERT INTO `article` VALUES (2, 'vdfvfd', '', 'vfdvfd', 'fvefe', 8, 0, 'vrefvff', 1, 1, '2021-04-22 13:55:17', NULL);
INSERT INTO `article` VALUES (5, '赵国顺', 'http://localhost:8081/upload/b476c8c6-d51c-4594-930d-f92cfde678f8.jpg', 'ok', 'fvvvvedfrgt', 524, 1, 'vrefvff', 1, 0, '2021-04-22 16:00:58', NULL);
INSERT INTO `article` VALUES (7, 'haoan', 'http://localhost:8081/upload/86017a6f-660e-41d4-99e1-f9a284449802.jpg', 'cdnsjdscn', 'cdsvdfdfalhdaasda  test', 5, 0, 'hbgfvdcx', 3, 0, '2021-04-26 19:26:36', NULL);
INSERT INTO `article` VALUES (8, 'sdfvgb', '', 'cdvfbghg', 'cdvfgb', 0, 0, 'vrefvff', 1, 0, '2021-04-27 01:26:32', NULL);
INSERT INTO `article` VALUES (9, 'dfvdbg', '', 'vdfgb', 'vfdgb', 31, 0, 'vrefvff', 8, 0, '2021-04-27 01:26:39', NULL);
INSERT INTO `article` VALUES (10, 'dvfbg', '', 'svfdbgf', 'fvdbg', 3, 0, 'vrefvff', 9, 0, '2021-04-27 01:26:45', NULL);
INSERT INTO `article` VALUES (11, 'dvfbg ', '', 'vfgbfvbg', 'fvbg ', 0, 0, 'vrefvff', 6, 0, '2021-04-27 01:26:51', NULL);
INSERT INTO `article` VALUES (12, 'dfvdg', '', 'fvdbgf', 'vfbg ', 1, 0, 'vrefvff', 7, 0, '2021-04-27 01:26:57', NULL);
INSERT INTO `article` VALUES (13, 'dfvdbg ', '', 'vfbgf ', 'dfvdgb', 1, 0, 'vrefvff', 8, 0, '2021-04-27 01:27:03', NULL);
INSERT INTO `article` VALUES (14, 'sdvfgb', '', 'dfvdg', 'vdfgbvn', 0, 0, 'vrefvff', 5, 0, '2021-04-27 01:27:19', NULL);
INSERT INTO `article` VALUES (15, 'dvfbghnjm', '', 'cdsvfbghn', 'xscdvfbghnajdhaksd', 1, 0, 'vrefvff', 8, 0, '2021-04-27 01:30:11', NULL);
INSERT INTO `article` VALUES (16, 'JAVA', '', '1FASF', '1WAFA', 13, 0, 'hbgfvdcx', 3, 0, '2021-04-27 21:57:25', NULL);
INSERT INTO `article` VALUES (18, '哈哈哈', '', '121', '123123', 10, 0, 'vrefvff', 8, 0, '2021-04-28 12:29:10', NULL);
INSERT INTO `article` VALUES (19, 'java', '', 'vfdnjk', 'cdssdcds', 3, 0, 'vrefvff', 8, 0, '2021-04-28 13:45:33', NULL);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL,
  `article_id` int(11) NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `receiver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 0, 1, 'hbgfvdcx', 'vrefvff', 'cdcdssdc', NULL);
INSERT INTO `comment` VALUES (5, 0, 5, 'vrefvff', '', NULL, '2021-04-26 17:35:29');
INSERT INTO `comment` VALUES (6, 0, 5, 'vrefvff', '', 'vfvf', '2021-04-26 17:36:28');
INSERT INTO `comment` VALUES (7, 6, 5, 'vrefvff', '', 'xscdvf', '2021-04-26 17:52:37');
INSERT INTO `comment` VALUES (8, 5, 5, 'vrefvff', '', 'hao1', '2021-04-26 18:07:19');
INSERT INTO `comment` VALUES (9, 5, 5, 'vrefvff', '', 'vfgbhn', '2021-04-26 18:07:32');
INSERT INTO `comment` VALUES (10, 5, 5, 'vrefvff', '', 'dfvgb', '2021-04-26 18:07:43');
INSERT INTO `comment` VALUES (11, 0, 5, 'vrefvff', '', 'dfvgb', '2021-04-26 18:07:49');
INSERT INTO `comment` VALUES (13, 12, 5, 'vrefvff', '', 'cff', '2021-04-26 18:08:09');
INSERT INTO `comment` VALUES (14, 12, 5, 'vrefvff', '', 'ccd', '2021-04-26 18:15:34');
INSERT INTO `comment` VALUES (19, 0, 9, 'hbgfvdcx', 'vrefvff', 'haha', '2021-04-27 22:02:30');
INSERT INTO `comment` VALUES (20, 0, 16, 'vrefvff', 'hbgfvdcx', 'haha', '2021-04-27 22:08:40');
INSERT INTO `comment` VALUES (21, 19, 9, 'vrefvff', 'hbgfvdcx', '方法', '2021-04-27 22:08:56');
INSERT INTO `comment` VALUES (22, 19, 9, 'vrefvff', 'hbgfvdcx', '他', '2021-04-27 22:09:17');
INSERT INTO `comment` VALUES (23, 0, 13, 'vrefvff', '', '哈哈', '2021-04-27 22:09:35');
INSERT INTO `comment` VALUES (24, 19, 9, 'vrefvff', 'hbgfvdcx', '阿哲', '2021-04-28 11:37:29');
INSERT INTO `comment` VALUES (25, 0, 17, 'ed60fc8163ec4ae88846bb8ee4f1f9fb', '', '哈哈哈', '2021-04-28 11:46:18');
INSERT INTO `comment` VALUES (26, 0, 18, 'vrefvff', '', 'hello', '2021-07-01 14:57:17');

-- ----------------------------
-- Table structure for menus
-- ----------------------------
DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus`  (
  `id` int(11) NOT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  `menu_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `admin` int(1) NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menus
-- ----------------------------
INSERT INTO `menus` VALUES (1, 0, '系统管理', '', NULL, 0, '2021-04-22 10:06:04');
INSERT INTO `menus` VALUES (2, 1, '我的文章', '/article/myself', NULL, 0, '2021-04-22 10:06:48');
INSERT INTO `menus` VALUES (3, 1, '全部文章', '/admin/article', NULL, 1, '2021-04-22 10:07:28');
INSERT INTO `menus` VALUES (4, 1, '添加文章', '/article/insert', NULL, 0, '2021-04-22 11:09:53');
INSERT INTO `menus` VALUES (5, 0, '相册管理', '/photo', NULL, 0, '2021-04-22 11:11:03');
INSERT INTO `menus` VALUES (6, 5, '我的相册', '/photo/myself', NULL, 0, '2021-04-22 11:16:54');
INSERT INTO `menus` VALUES (7, 0, '评论管理', '/comment/', '', 0, '2021-04-22 11:17:39');
INSERT INTO `menus` VALUES (8, 7, '我的评论', '/comment/myself', NULL, 0, '2021-04-22 11:18:16');
INSERT INTO `menus` VALUES (9, 7, '评论我的', '/comment/commentMy', NULL, 0, '2021-04-22 11:19:45');
INSERT INTO `menus` VALUES (10, 1, '分类管理', '/admin/type', NULL, 1, '2021-04-25 21:34:44');
INSERT INTO `menus` VALUES (11, 1, '用户管理', '/admin/user', NULL, 1, '2021-04-25 21:39:32');
INSERT INTO `menus` VALUES (12, 5, '全部相册', '/admin/photo', NULL, 1, '2021-04-25 21:39:31');
INSERT INTO `menus` VALUES (13, 7, '全部评论', '/admin/comment', NULL, 1, '2021-04-25 21:40:02');
INSERT INTO `menus` VALUES (14, 1, '数据统计', '/admin/data', NULL, 1, '2021-04-27 00:24:45');

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo`  (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `imgUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of photo
-- ----------------------------
INSERT INTO `photo` VALUES (NULL, 'http://localhost:8081/upload/cc0aafd4-bb14-4b04-a535-6d2f992d86ed.jpg');
INSERT INTO `photo` VALUES ('hbgfvdcx', 'http://localhost:8081/upload/a15bcdd1-5795-45d3-9021-0b40a9ac8ca8.png');
INSERT INTO `photo` VALUES ('hbgfvdcx', 'http://localhost:8081/upload/5aba7849-4a68-4157-aab4-a849da4e4369.jpg');
INSERT INTO `photo` VALUES ('2eadaf4dd5824c899d6102b5783fc80e', 'http://localhost:8081/upload/4d7e4efd-f384-4006-a65d-d18cb2ae6c00.jpg');
INSERT INTO `photo` VALUES ('vrefvff', 'http://192.168.23.103:9005/api/upload/7e93ff38-6db7-4ce1-966c-f9d498ac38b5.jpg');

-- ----------------------------
-- Table structure for thumbup
-- ----------------------------
DROP TABLE IF EXISTS `thumbup`;
CREATE TABLE `thumbup`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of thumbup
-- ----------------------------
INSERT INTO `thumbup` VALUES (15, 7, 'vrefvff');
INSERT INTO `thumbup` VALUES (18, 9, 'vrefvff');
INSERT INTO `thumbup` VALUES (20, 13, 'vrefvff');
INSERT INTO `thumbup` VALUES (25, 5, 'vrefvff');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, 'Java', '2021-04-22 13:09:59');
INSERT INTO `type` VALUES (3, 'Go', '2021-04-26 01:19:25');
INSERT INTO `type` VALUES (5, 'xdcvfg', '2021-04-27 01:13:35');
INSERT INTO `type` VALUES (6, 'cdcd', '2021-04-27 01:13:37');
INSERT INTO `type` VALUES (7, 'cdcd', '2021-04-27 01:13:39');
INSERT INTO `type` VALUES (8, 'cdcd', '2021-04-27 01:13:41');
INSERT INTO `type` VALUES (9, 'cdcd', '2021-04-27 01:13:44');
INSERT INTO `type` VALUES (10, 'ccd', '2021-04-27 01:13:49');
INSERT INTO `type` VALUES (12, 'test', '2021-04-27 21:45:28');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `isAdmin` int(1) NULL DEFAULT NULL COMMENT '0代表普通用户1管理员用户',
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modify` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0bd4c7796e07460196603cae84c5faca', 'ashun', NULL, 'chdsj', '', 1, '2021-04-28 12:24:54', NULL);
INSERT INTO `user` VALUES ('89af55b0c2de4112957128bf0ad2af9a', 'xzl1', '123456', '嘎嘎嘎', NULL, 0, '2021-04-28 12:25:55', NULL);
INSERT INTO `user` VALUES ('a59a26b381ef4bb58ae4b5d97928c00d', 'xzl123', '123123', '哈哈', NULL, 1, '2021-04-28 12:24:55', NULL);
INSERT INTO `user` VALUES ('b7a060861990470c9f4cb5ad661436a3', 'guoshun66', '985211', 'guoshun', NULL, 0, '2021-06-29 15:00:42', NULL);
INSERT INTO `user` VALUES ('hbgfvdcx', 'xiaoshun', '123456', '小顺', 'http://localhost:8081/upload/0c538ee3-5d88-42cb-853b-b5409b0aa7b0.png', 0, '2021-04-22 10:50:46', NULL);
INSERT INTO `user` VALUES ('vrefvff', 'zhaoguoshun', '985211', '西d', 'http://192.168.23.103:9005/api/upload/5426cc55-4a18-431a-9405-970a3add8d66.png', 1, '2021-04-21 22:04:48', NULL);

SET FOREIGN_KEY_CHECKS = 1;
