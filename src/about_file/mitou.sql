/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80022
Source Host           : localhost:3306
Source Database       : mitou

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2021-04-15 10:28:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_menu
-- ----------------------------
DROP TABLE IF EXISTS `base_menu`;
CREATE TABLE `base_menu` (
  `MENU_ID` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `PARENT_MENU_ID` bigint DEFAULT '0' COMMENT '菜单父ID',
  `MENU_NAME` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `MENU_CODE` varchar(50) NOT NULL DEFAULT '' COMMENT '菜单编码',
  `MENU_URL` varchar(50) NOT NULL DEFAULT '' COMMENT '菜单URL',
  `MENU_TYPE` tinyint NOT NULL COMMENT '菜单类型( 0:目录 1:菜单 2:按钮)',
  `ORDER_NUM` smallint DEFAULT '0' COMMENT '显示顺序',
  `CREATE_USER_NAME` varchar(20) DEFAULT '' COMMENT '创建人',
  `CREATE_USER_ID` bigint DEFAULT NULL COMMENT '创建人ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `DEL_FLAG` tinyint NOT NULL DEFAULT '1' COMMENT '删除状态( 0:已删除 1:正常)',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单';

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `ROLE_ID` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `ROLE_NAME` varchar(100) NOT NULL DEFAULT '' COMMENT '角色名称',
  `ROLE_DEFAULT` tinyint NOT NULL DEFAULT '0' COMMENT '是否默认角色( 0:非默认 1:默认)',
  `ORDER_NUM` smallint DEFAULT '0' COMMENT '显示顺序',
  `SYS_INIT` tinyint NOT NULL DEFAULT '0' COMMENT '是否内置角色( 0:非内置 1:内置)',
  `CREATE_USER_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建人',
  `CREATE_USER_ID` bigint DEFAULT NULL COMMENT '创建人ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `DEL_FLAG` tinyint NOT NULL DEFAULT '1' COMMENT '删除状态( 0:已删除 1:正常)',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色';

-- ----------------------------
-- Table structure for base_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `base_role_menu`;
CREATE TABLE `base_role_menu` (
  `ROLE_MENU_ID` bigint NOT NULL AUTO_INCREMENT COMMENT '角色菜单ID',
  `ROLE_ID` bigint NOT NULL COMMENT '角色ID',
  `MENU_ID` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`ROLE_MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关系';

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `USER_ID` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USER_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '姓名',
  `USER_PWD` varchar(100) DEFAULT '' COMMENT '密码',
  `PHONE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '电话',
  `EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '邮箱',
  `GENDER` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '性别',
  `USER_LOGO` varchar(100) DEFAULT '' COMMENT '用户头像',
  `BIRTHDAY` datetime DEFAULT NULL COMMENT '生日',
  `ORG_ID` bigint DEFAULT NULL COMMENT '单位(组织id)',
  `ORG_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '单位(组织)',
  `POSITION` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '职位',
  `DEPT` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '部门',
  `USER_TYPE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '用户类型',
  `CREATE_USER_ID` bigint DEFAULT NULL COMMENT '创建人ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `DEL_FLAG` tinyint NOT NULL DEFAULT '1' COMMENT '删除状态( 0:已删除 1:正常)',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Table structure for base_user_role
-- ----------------------------
DROP TABLE IF EXISTS `base_user_role`;
CREATE TABLE `base_user_role` (
  `USER_ROLE_ID` bigint NOT NULL AUTO_INCREMENT COMMENT '用户角色关系ID',
  `USER_ID` bigint NOT NULL COMMENT '用户ID',
  `ROLE_ID` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`USER_ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系';
