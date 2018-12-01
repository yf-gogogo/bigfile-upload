/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : upload

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-01 19:41:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for res
-- ----------------------------
DROP TABLE IF EXISTS `res`;
CREATE TABLE `res` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `r_md5` varchar(255) DEFAULT NULL,
  `r_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for res_mark
-- ----------------------------
DROP TABLE IF EXISTS `res_mark`;
CREATE TABLE `res_mark` (
  `mark_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `mark_title` varchar(255) DEFAULT NULL,
  `r_md5` varchar(255) DEFAULT NULL,
  `r_status` enum('uploaded','uploading') DEFAULT 'uploaded',
  PRIMARY KEY (`mark_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for res_tmp
-- ----------------------------
DROP TABLE IF EXISTS `res_tmp`;
CREATE TABLE `res_tmp` (
  `tmp_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `tmp_title` varchar(255) DEFAULT NULL,
  `tmp_md5` varchar(255) DEFAULT NULL,
  `tmp_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tmp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
