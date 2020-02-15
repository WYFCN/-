/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : newspape

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-02-15 17:44:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(10) NOT NULL DEFAULT '0' COMMENT '用户类型(1.管理员，2.普通用户)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'wyf', '123456', '111@qq.com', '王一帆', '1');
INSERT INTO `user` VALUES ('2', 'wzh', '321', '222@qq.com', '王泽浩', '0');
INSERT INTO `user` VALUES ('3', 'aaa', '123', '1513@qq.com', 'bbb', '0');
INSERT INTO `user` VALUES ('4', 'aaaa', '123456', '1270065107@qq.com', '海贼王', '0');
INSERT INTO `user` VALUES ('5', 'bbbb', '123', '1270065107@qq.com', '王一帆', '0');
INSERT INTO `user` VALUES ('6', 'wyf1', '123456', '111@qq.com', '王一帆', '1');
INSERT INTO `user` VALUES ('7', 'wzh1', '321', '222@qq.com', '王泽浩', '0');
INSERT INTO `user` VALUES ('8', 'aaa1', '123', '1513@qq.com', 'bbb', '0');
INSERT INTO `user` VALUES ('9', 'aaaa1', '123456', '1270065107@qq.com', '海贼王', '0');
INSERT INTO `user` VALUES ('10', 'bbbb1', '123456', '1270065107@qq.com', '王一帆', '0');
INSERT INTO `user` VALUES ('11', 'wyf2', '123456', '111@qq.com', '王一帆', '1');
INSERT INTO `user` VALUES ('12', 'wzh2', '321', '222@qq.com', '王泽浩', '0');
INSERT INTO `user` VALUES ('14', 'aaaa2', '123456', '1270065107@qq.com', '海贼王', '0');
