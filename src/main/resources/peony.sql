/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : peony

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-06-21 14:00:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for acg_class
-- ----------------------------
DROP TABLE IF EXISTS `acg_class`;
CREATE TABLE `acg_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classname` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `classurl` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `createtime` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `millisecond` int(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of acg_class
-- ----------------------------
INSERT INTO `acg_class` VALUES ('1', '1', '2', '2018', '22222');

-- ----------------------------
-- Table structure for acg_resources
-- ----------------------------
DROP TABLE IF EXISTS `acg_resources`;
CREATE TABLE `acg_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classid` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `torrent` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `magnet` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `createtime` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `millisecond` int(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of acg_resources
-- ----------------------------
