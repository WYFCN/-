/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : newspape

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-02-15 17:44:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `amount` decimal(11,0) NOT NULL,
  `address` varchar(255) NOT NULL,
  `state` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '下单中',
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`userId`),
  CONSTRAINT `user_id` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2879 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '1111111', '人性的弱点', '26', '上海市黄浦区', '收货成功', '5');
INSERT INTO `order` VALUES ('2', '2222222', '人性的弱点', '32', '陕西省西安市', '收货成功', '5');
INSERT INTO `order` VALUES ('4', '4444444', '人性的弱点', '32', '陕西省西安市', '库存不足，下单失败', '5');
INSERT INTO `order` VALUES ('5', '5555555', '人性的弱点', '32', '陕西省西安市', '收货成功', '5');
INSERT INTO `order` VALUES ('6', '6666666', '人性的弱点', '32', '陕西省西安市', '收货成功', '5');
INSERT INTO `order` VALUES ('7', '7777777', '人性的弱点', '32', '陕西省西安市', '收货成功', '5');
INSERT INTO `order` VALUES ('8', '8888888', '人性的弱点', '32', '陕西省西安市', '收货成功', '5');
INSERT INTO `order` VALUES ('9', '1010101', '人性的弱点', '32', '陕西省西安市', '收货成功', '5');
INSERT INTO `order` VALUES ('10', '9999999', '人性的弱点', '32', '陕西省西安市', '收货成功', '5');
INSERT INTO `order` VALUES ('13', '8988888', '人性的弱点', '32', '陕西省西安市', '下单中', '5');
INSERT INTO `order` VALUES ('14', '1110101', '人性的弱点', '32', '陕西省西安市', '下单中', '5');
INSERT INTO `order` VALUES ('15', '1113311', '人性的弱点', '32', '陕西省西安市', '下单中', '5');
INSERT INTO `order` VALUES ('16', '2212222', '人性的弱点', '32', '陕西省西安市', '下单中', '5');
INSERT INTO `order` VALUES ('17', '8100672', '简爱', '35', '北京市昌平区', '下单中', '5');
INSERT INTO `order` VALUES ('18', '5700205', '人性的弱点', '32', '北京市昌平区', '下单中', '5');
INSERT INTO `order` VALUES ('19', '4529687', '活', '50', '北京市昌平区', '下单中', '5');
INSERT INTO `order` VALUES ('20', '5020978', '人性的弱点', '32', '北京市昌平区', '下单中', '5');
INSERT INTO `order` VALUES ('22', '4811778', '活着1', '50', '北京市昌平区', '下单成功', '5');
INSERT INTO `order` VALUES ('2877', '5371825', '活', '50', '陕西省咸阳市', '库存不足，下单失败', '5');
INSERT INTO `order` VALUES ('2878', '7520730', '铁甲小宝', '100', '上海市黄浦区', '收货成功', '5');
