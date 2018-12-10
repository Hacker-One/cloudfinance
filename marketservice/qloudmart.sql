/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.145
Source Server Version : 50505
Source Host           : 192.168.1.145:31306
Source Database       : qloutmart

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-11-26 17:55:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_account`
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account` (
  `id` varchar(32) NOT NULL COMMENT '账户ID',
  `name` varchar(64) DEFAULT NULL COMMENT '账户名',
  `hook` varchar(256) DEFAULT NULL COMMENT '通知地址',
  `contact_num` varchar(24) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(128) DEFAULT NULL COMMENT '电子邮箱',
  `status` varchar(1) DEFAULT 'N' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_account
-- ----------------------------
INSERT INTO `tb_account` VALUES ('10001', '测试账户', 'http://192.168.1.145:32091/deployer/notify', '123456', '123456@139.com', 'N');
INSERT INTO `tb_account` VALUES ('10002', 'test2', '', '123456', '123456@139.com', 'N');
INSERT INTO `tb_account` VALUES ('MYCMB', 'MYCMB', 'http://192.168.1.12:30650/deployer/notify', '123456', '123456@139.com', 'N');
INSERT INTO `tb_account` VALUES ('Test', '测试账户', 'http://192.168.1.145:32091/deployer/notify', null, null, 'N');

-- ----------------------------
-- Table structure for `tb_account_attr`
-- ----------------------------
DROP TABLE IF EXISTS `tb_account_attr`;
CREATE TABLE `tb_account_attr` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(32) NOT NULL,
  `attr_key` varchar(32) NOT NULL,
  `attr_value` varchar(256) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`,`account_id`,`attr_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_account_attr
-- ----------------------------
INSERT INTO `tb_account_attr` VALUES ('1', '10001', 'buy_addr', 'http://192.168.1.145:32091/deployer/notify', 'N');
INSERT INTO `tb_account_attr` VALUES ('1', '10001', 'try_addr', 'http://192.168.1.145:32091/deployer/notify', 'N');

-- ----------------------------
-- Table structure for `tb_artifact`
-- ----------------------------
DROP TABLE IF EXISTS `tb_artifact`;
CREATE TABLE `tb_artifact` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL COMMENT '组件链接',
  `product_id` varchar(32) DEFAULT NULL,
  `artifact_type` int(12) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  `tag` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_id` (`product_id`),
  KEY `fk_type` (`artifact_type`),
  CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`),
  CONSTRAINT `fk_type` FOREIGN KEY (`artifact_type`) REFERENCES `tb_artifact_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_artifact
-- ----------------------------
INSERT INTO `tb_artifact` VALUES ('6', 'ID-SERVICE', 'qloud/id-service', '571911509110689792', '1', 'N', '1.1.4');
INSERT INTO `tb_artifact` VALUES ('7', 'CAS', 'qloudid/cas', '571911509110689792', '1', 'N', '1.0');
INSERT INTO `tb_artifact` VALUES ('8', 'gerrit', 'qlouddop/gerrit', '571828364302618630', '1', 'N', '2.15.3');
INSERT INTO `tb_artifact` VALUES ('9', 'jenkins', 'qlouddop/jenkins', '571828364302618629', '1', 'N', '1.0');
INSERT INTO `tb_artifact` VALUES ('12', 'DOP', 'qlouddop/qloudhub', '571828364302618624', '1', 'N', '2.1.04');
INSERT INTO `tb_artifact` VALUES ('13', 'arrange', 'qloudtest/arrange', '571829434831605760', '1', 'N', '1.0.4');
INSERT INTO `tb_artifact` VALUES ('14', 'pluginUI', 'qloudtest/standalone-chrome', '571910296180887552', '1', 'N', 'latest');
INSERT INTO `tb_artifact` VALUES ('15', 'engine', 'qloudtest/engine', '571829128651608064', '1', 'N', '1.0.22');
INSERT INTO `tb_artifact` VALUES ('16', 'qloudtest/web', 'qloudtest/web', '571829434831605760', '1', 'N', '1.0.30');
INSERT INTO `tb_artifact` VALUES ('17', 'qloud-itg-mock', 'qloudtest/store', '571829434831605760', '1', 'N', '1.0.5');
INSERT INTO `tb_artifact` VALUES ('19', 'jenkinsChart', 'jenkins-1.0.1.tgz', '571828364302618629', '2', 'N', null);
INSERT INTO `tb_artifact` VALUES ('20', 'DOPChart', 'qloud-dop-1.0.1.tgz', '571828364302618624', '2', 'N', null);
INSERT INTO `tb_artifact` VALUES ('21', 'ssoChart', 'qloudapi-qloudsso-1.0.0.tgz', '571911509110689792', '2', 'N', null);
INSERT INTO `tb_artifact` VALUES ('22', 'testChart', 'qloud-test-1.0.1.tgz', '571829434831605760', '2', 'N', null);
INSERT INTO `tb_artifact` VALUES ('23', 'engineChart', 'qloudtest-engine-1.0.1.tgz', '571829128651608064', '2', 'N', null);
INSERT INTO `tb_artifact` VALUES ('24', 'uiChart', 'qloudtest-ui-1.0.1.tgz', '571910296180887552', '2', 'N', null);
INSERT INTO `tb_artifact` VALUES ('25', 'gerritChart', 'gerrit-1.0.1.tgz', '571828364302618630', '2', 'N', null);
INSERT INTO `tb_artifact` VALUES ('26', 'qloud/consul', 'qloud/consul', '575852507633291264', '1', 'N', '1.2.2');
INSERT INTO `tb_artifact` VALUES ('27', 'qloud/vault', 'qloud/vault', '575852507633291264', '1', 'N', '0.11.0');
INSERT INTO `tb_artifact` VALUES ('28', 'qloud/security', 'qloud/security', '575852507633291264', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('29', 'qloud/config', 'qloud/config', '575852507633291264', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('30', 'qloudservice', 'qloudservice-1.0.0.tgz', '575852507633291264', '2', 'N', null);
INSERT INTO `tb_artifact` VALUES ('31', 'qloudapi', 'qloudapi-1.0.0.tgz', '575888592824438784', '2', 'N', null);
INSERT INTO `tb_artifact` VALUES ('32', 'qloud/conductor-bridge', 'qloud/conductor-bridge', '575888592824438784', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('33', 'qloud/conductor-bridge', 'qloud/conductor-bridge', '575852507633291264', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('34', 'qloudcdc-mariadb', 'qloudservice/qloudcdc-mariadb', '575852507633291264', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('35', 'qloudsaga-reliability-rollback', 'qloudservice/qloudsaga-reliability-rollback', '575852507633291264', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('36', 'qloudconductor-sagabridge', 'qloudservice/qloudconductor-sagabridge', '575852507633291264', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('37', 'qloudsaga-reliability-start', 'qloudservice/qloudsaga-reliability-start', '575852507633291264', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('38', 'qloudsaga-reliability-task1', 'qloudservice/qloudsaga-reliability-task1', '575852507633291264', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('39', 'qloudsaga-reliability-task2', 'qloudservice/qloudsaga-reliability-task2', '575852507633291264', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('40', 'qloudconfig', 'qloudservice/qloudconfig', '575852507633291264', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('41', 'qlouddiscovery', 'qloudservice/qlouddiscovery', '575852507633291264', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('42', 'qloudsecurity', 'qloudservice/qloudsecurity', '575852507633291264', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('43', 'qloudstomp', 'qloudservice/qloudstomp', '575852507633291264', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('44', 'qloudapi/qloudwebui-onlyapi', 'qloudapi/qloudwebui-onlyapi', '575852507633291264', '1', 'N', 'latest');
INSERT INTO `tb_artifact` VALUES ('45', 'qloud/qloudapi', 'qloud/qloudapi', '575888592824438784', '1', 'N', '4.0.0');
INSERT INTO `tb_artifact` VALUES ('46', 'qloud/qloudauth', 'qloud/qloudauth', '575888592824438784', '1', 'N', 'chart.0.1');
INSERT INTO `tb_artifact` VALUES ('47', 'qloud/qloudgrid', 'qloud/qloudgrid', '575888592824438784', '1', 'N', 'chart.0.1');
INSERT INTO `tb_artifact` VALUES ('48', 'qloud/qloudmnt', 'qloud/qloudmnt', '575888592824438784', '1', 'N', '4.0.0');
INSERT INTO `tb_artifact` VALUES ('49', 'qloud/consul', 'qloud/consul', '578416429041192960', '1', 'N', '1.2.2');
INSERT INTO `tb_artifact` VALUES ('52', 'qloudservice/qlouddiscovery', 'qloudservice/qlouddiscovery', '578416429041192960', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('53', 'qloudservice/qloudconfig', 'qloudservice/qloudconfig', '578416429041192960', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('54', 'qloudservice/qloudcdc-mariadb', 'qloudservice/qloudcdc-mariadb', '578416429041192960', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('55', 'qloudapi/qloudview', 'qloudapi/qloudview', '578416429041192960', '1', 'N', 'stable');
INSERT INTO `tb_artifact` VALUES ('56', 'qloud/qloudgrid', 'qloud/qloudgrid', '578416429041192960', '1', 'N', 'chart.0.1');
INSERT INTO `tb_artifact` VALUES ('57', 'qloud/qloudapi', 'qloud/qloudapi', '578416429041192960', '1', 'Y', '4.0.0');
INSERT INTO `tb_artifact` VALUES ('58', 'qloud/qloudmnt', 'qloud/qloudmnt', '578416429041192960', '1', 'N', '4.0.0');
INSERT INTO `tb_artifact` VALUES ('59', 'qloudapi/qloudwebui-onlyapi', 'qloudapi/qloudwebui-onlyapi', '578416429041192960', '1', 'N', 'latest');
INSERT INTO `tb_artifact` VALUES ('60', 'qloud/qloudauth', 'qloud/qloudauth', '578416429041192960', '1', 'N', 'chart.0.1');
INSERT INTO `tb_artifact` VALUES ('61', 'qloud-view-1.2.1.tgz', 'qloud-view-1.2.1.tgz', '578416429041192960', '2', 'N', null);
INSERT INTO `tb_artifact` VALUES ('62', 'qloudobp/cdcview:latest', 'qloudobp/cdcview', '578416429041192960', '1', 'N', 'latest');

-- ----------------------------
-- Table structure for `tb_artifact_attr`
-- ----------------------------
DROP TABLE IF EXISTS `tb_artifact_attr`;
CREATE TABLE `tb_artifact_attr` (
  `artifact_id` int(12) DEFAULT NULL,
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `attr_name` varchar(64) DEFAULT NULL,
  `attr_value` varchar(128) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_artifact_attr
-- ----------------------------
INSERT INTO `tb_artifact_attr` VALUES ('8', '1', 'tag', '2.15.3', 'N');
INSERT INTO `tb_artifact_attr` VALUES ('9', '2', 'tag', '1.0', 'N');

-- ----------------------------
-- Table structure for `tb_artifact_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_artifact_type`;
CREATE TABLE `tb_artifact_type` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `des` varchar(128) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_artifact_type
-- ----------------------------
INSERT INTO `tb_artifact_type` VALUES ('1', 'docker', '镜像', 'N');
INSERT INTO `tb_artifact_type` VALUES ('2', 'chart', 'chart文件', 'N');

-- ----------------------------
-- Table structure for `tb_license`
-- ----------------------------
DROP TABLE IF EXISTS `tb_license`;
CREATE TABLE `tb_license` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '证书ID',
  `expired_date` varchar(24) NOT NULL COMMENT '失效时间',
  `content` varchar(256) NOT NULL COMMENT '证书内容',
  `product_id` varchar(32) NOT NULL COMMENT '商品ID',
  `account_id` varchar(32) NOT NULL COMMENT '账户ID',
  `status` varchar(1) NOT NULL DEFAULT 'N' COMMENT '是否删除',
  PRIMARY KEY (`product_id`,`account_id`,`expired_date`),
  UNIQUE KEY `idx_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=330 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_license
-- ----------------------------
INSERT INTO `tb_license` VALUES ('326', '20501231', 'testtesttesttesttesttesttesttest', '571828364302618630', '10001', 'N');
INSERT INTO `tb_license` VALUES ('327', '2018-11-26 14:30:49', '233', '571910296180887552', '10001', 'N');
INSERT INTO `tb_license` VALUES ('328', '20501231', 'testtesttesttesttesttesttesttest', '571910296180887552', '10001', 'N');

-- ----------------------------
-- Table structure for `tb_order`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `order_status` varchar(12) DEFAULT NULL COMMENT '订单状态',
  `purchase_code` varchar(64) NOT NULL COMMENT '购买编码',
  `account_id` varchar(32) DEFAULT NULL COMMENT '账户ID',
  `product_id` varchar(32) DEFAULT NULL COMMENT '商品ID',
  `license_id` int(12) DEFAULT NULL,
  `product_name` varchar(64) NOT NULL,
  `deploy_msg` longtext COMMENT '部署信息',
  `order_type` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_purchase_code` (`purchase_code`),
  KEY `fk_tb_order_tb_license1` (`license_id`),
  KEY `fk_order_account` (`account_id`),
  KEY `fk_order_product` (`product_id`),
  CONSTRAINT `fk_order_account` FOREIGN KEY (`account_id`) REFERENCES `tb_account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_order_tb_license1` FOREIGN KEY (`license_id`) REFERENCES `tb_license` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('1e3e075f-73a0-4738-9328-887dd2f1e872', '2018-11-26 07:17:31', 'deployed', '444', '10001', '571828364302618630', '326', 'Gerrit', '{\"msg\":\"the service will  deploy on your cloud\",\"data\":{\"reg_qlouddop/gerrit_2.15.3\":{\"msg\":\"success\",\"type\":\"registry\",\"name\":\"qlouddop/gerrit:2.15.3\"},\"method\":\"notify\",\"DEPLOY_INFO\":{\"msg\":\"succeed\",\"code\":\"000\"},\"LICENSE_INFO\":\"SAVE\",\"orderInfo\":{\"purchaseCode\":\"444\",\"productId\":\"571828364302618630\",\"orderStatus\":\"paid\",\"productName\":\"Gerrit\",\"accountId\":\"10001\",\"id\":\"1e3e075f-73a0-4738-9328-887dd2f1e872\",\"licenseId\":326,\"createDate\":\"2018-11-26 07:17:09.0\"}},\"succeed\":true}', 'buy');
INSERT INTO `tb_order` VALUES ('421ba5f1-8369-4c97-b27e-0834b4f1701d', '2018-11-26 04:18:19', 'pending', '665', '10001', '571828364302618624', null, 'QloudDOP', null, 'buy');
INSERT INTO `tb_order` VALUES ('4a81298b-ef99-4d39-8ca6-35bf7b3bda3b', '2018-11-26 07:31:01', 'deployed', 'test-31b1ec36-c84c-48fa-b612-fbf1784fcecd', '10001', '571910296180887552', '327', 'QloudTest - Selenium', '{\"msg\":\"the service will  deploy on your cloud\",\"data\":{\"method\":\"notify\",\"DEPLOY_INFO\":{\"msg\":\"succeed\",\"code\":\"000\"},\"reg_qloudtest/standalone-chrome_latest\":{\"msg\":\"success\",\"type\":\"registry\",\"name\":\"qloudtest/standalone-chrome:latest\"},\"LICENSE_INFO\":\"SAVE\",\"orderInfo\":{\"purchaseCode\":\"test-31b1ec36-c84c-48fa-b612-fbf1784fcecd\",\"productId\":\"571910296180887552\",\"orderStatus\":\"paid\",\"productName\":\"QloudTest - Selenium\",\"accountId\":\"10001\",\"id\":\"4a81298b-ef99-4d39-8ca6-35bf7b3bda3b\",\"licenseId\":327,\"createDate\":\"2018-11-26 07:30:49.0\"}},\"succeed\":true}', 'try');
INSERT INTO `tb_order` VALUES ('7c3e5fe1-90c1-4d05-97ea-572c16a2e07b', '2018-11-26 07:32:31', 'unDeployed', 'ddd', '10001', '571910296180887552', '327', 'QloudTest - Selenium', '{\"msg\":\"exeption on your programs.\",\"data\":{\"method\":\"notify\",\"reg_qloudtest/standalone-chrome_latest\":{\"msg\":\"success\",\"type\":\"registry\",\"name\":\"qloudtest/standalone-chrome:latest\"},\"orderInfo\":{\"purchaseCode\":\"ddd\",\"productId\":\"571910296180887552\",\"orderStatus\":\"paid\",\"productName\":\"QloudTest - Selenium\",\"accountId\":\"10001\",\"id\":\"7c3e5fe1-90c1-4d05-97ea-572c16a2e07b\",\"licenseId\":327,\"createDate\":\"2018-11-26 07:32:15.0\"}},\"succeed\":false}', 'buy');
INSERT INTO `tb_order` VALUES ('f2b3c97f-05d5-436b-ad9a-c599e677901d', '2018-11-26 08:13:11', 'pending', 'p9', '10001', '571910296180887552', null, 'QloudTest - Selenium', null, 'buy');

-- ----------------------------
-- Table structure for `tb_product`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `id` varchar(32) NOT NULL COMMENT '商品ID',
  `name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `short_desc` varchar(256) DEFAULT NULL COMMENT '短描述',
  `icon` varchar(256) DEFAULT NULL COMMENT '图标地址',
  `long_desc` longtext COMMENT '长描述',
  `picture_path` varchar(256) DEFAULT NULL COMMENT '商品图片地址',
  `desc_url` varchar(256) DEFAULT NULL COMMENT '商品描述url',
  `type_id` int(12) DEFAULT NULL COMMENT '商品类型ID',
  `vendor_id` int(12) DEFAULT NULL COMMENT '供应商ID',
  `status` varchar(1) DEFAULT 'N' COMMENT '是否删除',
  `category_id` int(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_category` (`category_id`),
  KEY `fk_product_type` (`type_id`),
  KEY `fk_product_vendor` (`vendor_id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `tb_product_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_type` FOREIGN KEY (`type_id`) REFERENCES `tb_product_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_vendor` FOREIGN KEY (`vendor_id`) REFERENCES `tb_vendor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES ('571828364302618624', 'QloudDOP', '端到端DevOps平台，提供全自动流水线，支持可插拔工具集', 'http://mart.csftgroup.com:30024/images/icon/dop.jpg', 'long desc', '', 'http://www.qloudmart.com', '2', '1', 'N', '1');
INSERT INTO `tb_product` VALUES ('571828364302618629', 'Jenkins', 'QloudDOP的CI/CD(持续集成和持续部署)插件', 'http://mart.csftgroup.com:30024/images/icon/jenkins.jpg', '', null, 'https://jenkins.io', '1', '1', 'N', '1');
INSERT INTO `tb_product` VALUES ('571828364302618630', 'Gerrit', 'QloudDOP的代码评审插件', 'http://mart.csftgroup.com:30024/images/icon/gerrit.jpg', '', null, 'http://qloudsso.io', '1', '1', 'N', '1');
INSERT INTO `tb_product` VALUES ('571829128651608064', 'QloudTest - Engine', 'QloudTest测试执行引擎插件', 'http://mart.csftgroup.com:30024/images/icon/qloudtest-engine.jpg', '', '', 'http://www.qloudmart.com', '1', '1', 'N', '1');
INSERT INTO `tb_product` VALUES ('571829434831605760', 'QloudTest', '分布式自动化测试平台，支持API, UI等多种测试场景，可视化的案例编排与执行', 'http://mart.csftgroup.com:30024/images/icon/qloudtest.jpg', '', '', 'http://www.qloudmart.com', '2', '1', 'N', '1');
INSERT INTO `tb_product` VALUES ('571910296180887552', 'QloudTest - Selenium', 'QloudTest UI 自动测试的驱动插件', 'http://mart.csftgroup.com:30024/images/icon/qloudtest-pluginuidriver.jpg', '', '', 'http://www.qloudmart.com', '1', '1', 'N', '1');
INSERT INTO `tb_product` VALUES ('571911509110689792', 'QloudSSO', '统一认证和单点登录服务，支持OAuth2, OpenID, OpenID Connector等协议', 'http://mart.csftgroup.com:30024/images/icon/qloudsso.jpg', '', '', 'http://www.qloudmart.com', '9', '1', 'N', '1');
INSERT INTO `tb_product` VALUES ('575852507633291264', 'QloudService', '金融级微服务平台，提供事件级安全，动态配置管理。交易所级高性能、高并发、一致性事务处理', 'http://mart.csftgroup.com:30024/images/icon/qloudservice.jpg', '', 'http://mart.csftgroup.com:30024/images/icon/qloudservice.jpg', 'http://mart.csftgroup.com:30024', '9', '1', 'N', '2');
INSERT INTO `tb_product` VALUES ('575888592824438784', 'QloudAPI', 'API服务平台，移动后台服务和数据的安全统一对外网关接口', 'http://mart.csftgroup.com:30024/images/icon/qloudapi.jpg', '', 'http://mart.csftgroup.com:30024/images/icon/qloudapi.jpg', 'http://mart.csftgroup.com:30024', '4', '1', 'N', '2');
INSERT INTO `tb_product` VALUES ('578416429041192960', 'QloudView', '随需而见，快速构建Schemaless数据视图', 'http://mart.csftgroup.com:30024/images/icon/qloudview.jpg', '', 'http://mart.csftgroup.com:30024/images/icon/qloudview.jpg', 'http://mart.csftgroup.com:30024', '4', '1', 'N', '2');

-- ----------------------------
-- Table structure for `tb_product_attr`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_attr`;
CREATE TABLE `tb_product_attr` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `product_id` varchar(32) NOT NULL,
  `attr_key` varchar(32) NOT NULL,
  `attr_value` varchar(32) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`,`attr_key`,`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_attr
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_product_category`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_category`;
CREATE TABLE `tb_product_category` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `des` varchar(256) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_category
-- ----------------------------
INSERT INTO `tb_product_category` VALUES ('1', '银行开发管理', '银行开发管理', 'N');
INSERT INTO `tb_product_category` VALUES ('2', '金融产品系统', '金融产品系统', 'N');
INSERT INTO `tb_product_category` VALUES ('3', '银行IT治理', '银行IT治理', 'N');

-- ----------------------------
-- Table structure for `tb_product_picture`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_picture`;
CREATE TABLE `tb_product_picture` (
  `product_id` varchar(32) NOT NULL,
  `url` varchar(256) NOT NULL,
  `status` varchar(1) NOT NULL DEFAULT 'N',
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `sort` int(12) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_picture
-- ----------------------------
INSERT INTO `tb_product_picture` VALUES ('571826561171329024', 'http://pic2.ooopic.com/10/50/25/52b1OOOPICa3.jpg', 'N', '3', '1');
INSERT INTO `tb_product_picture` VALUES ('571828364302618624', 'http://mart.csftgroup.com:30024/images/icon/dop.jpg', 'N', '4', '1');
INSERT INTO `tb_product_picture` VALUES ('571828364302618624', 'http://mart.csftgroup.com:30024/images/icon/qloudsso.jpg', 'N', '5', '1');
INSERT INTO `tb_product_picture` VALUES ('571829128651608064', 'http://mart.csftgroup.com:30024/images/icon/qloudtest-engine.jpg', 'N', '6', '1');
INSERT INTO `tb_product_picture` VALUES ('571829434831605760', 'http://mart.csftgroup.com:30024/images/icon/qloudtest.jpg', 'N', '7', '1');
INSERT INTO `tb_product_picture` VALUES ('571826561171329024', '\nhttp://static.qloudmart.com/images/icon/jenkins.png\n\n', 'N', '8', '2');

-- ----------------------------
-- Table structure for `tb_product_tag`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_tag`;
CREATE TABLE `tb_product_tag` (
  `product_id` varchar(32) DEFAULT NULL COMMENT '商品ID',
  `name` varchar(32) NOT NULL COMMENT '商品标签',
  `id` int(12) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_tag_product` (`product_id`),
  CONSTRAINT `fk_tag_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_tag
-- ----------------------------
INSERT INTO `tb_product_tag` VALUES ('571828364302618629', 'oss', '1');
INSERT INTO `tb_product_tag` VALUES ('571828364302618624', 'qloud', '2');
INSERT INTO `tb_product_tag` VALUES ('571829128651608064', 'qloud', '3');
INSERT INTO `tb_product_tag` VALUES ('571829434831605760', 'qloud', '4');
INSERT INTO `tb_product_tag` VALUES ('571910296180887552', 'qloud', '5');
INSERT INTO `tb_product_tag` VALUES ('571911509110689792', 'qloud', '6');
INSERT INTO `tb_product_tag` VALUES ('571828364302618630', 'oss', '10');
INSERT INTO `tb_product_tag` VALUES ('575852507633291264', 'qloud', '11');
INSERT INTO `tb_product_tag` VALUES ('575888592824438784', 'qloud', '12');
INSERT INTO `tb_product_tag` VALUES ('578416429041192960', 'qloud', '13');

-- ----------------------------
-- Table structure for `tb_product_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_type`;
CREATE TABLE `tb_product_type` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `des` varchar(256) DEFAULT NULL COMMENT '短描述',
  `status` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_type
-- ----------------------------
INSERT INTO `tb_product_type` VALUES ('1', '插件', '插件', 'N');
INSERT INTO `tb_product_type` VALUES ('2', '应用', '应用', 'N');
INSERT INTO `tb_product_type` VALUES ('3', '系统', '系统', 'N');
INSERT INTO `tb_product_type` VALUES ('4', 'API', 'API', 'N');
INSERT INTO `tb_product_type` VALUES ('5', '微服务', '微服务', 'N');
INSERT INTO `tb_product_type` VALUES ('6', '算法', '算法', 'N');
INSERT INTO `tb_product_type` VALUES ('7', '平台', '平台', 'N');
INSERT INTO `tb_product_type` VALUES ('8', 'SDK', 'SDK', 'N');
INSERT INTO `tb_product_type` VALUES ('9', '服务', '服务', 'N');

-- ----------------------------
-- Table structure for `tb_recomand_product`
-- ----------------------------
DROP TABLE IF EXISTS `tb_recomand_product`;
CREATE TABLE `tb_recomand_product` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `account_id` varchar(32) NOT NULL COMMENT '账户ID',
  `status` varchar(1) NOT NULL DEFAULT 'N' COMMENT '是否删除N为存在 Y为删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_recomand_product
-- ----------------------------
INSERT INTO `tb_recomand_product` VALUES ('1', '571828364302618624', '10001', 'N');

-- ----------------------------
-- Table structure for `tb_sys_attr_key`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_attr_key`;
CREATE TABLE `tb_sys_attr_key` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(32) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_key` (`key`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sys_attr_key
-- ----------------------------
INSERT INTO `tb_sys_attr_key` VALUES ('1', 'IS_SALE', '是否可买', 'N');
INSERT INTO `tb_sys_attr_key` VALUES ('2', 'deploy_addr_1', '部署地址1', 'N');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `id` varchar(32) NOT NULL COMMENT '用户ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '账户ID',
  `status` varchar(1) DEFAULT 'N' COMMENT '是否删除',
  `pwd` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_account` (`account_id`),
  CONSTRAINT `fk_user_account` FOREIGN KEY (`account_id`) REFERENCES `tb_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('管理员', 'admin', '10001', 'N', 'E10ADC3949BA59ABBE56E057F20F883E');
INSERT INTO `tb_user` VALUES ('陈晓稷', 'chenxiaoji', '10001', 'N', 'E10ADC3949BA59ABBE56E057F20F883E');
INSERT INTO `tb_user` VALUES ('海盛', 'haisheng', '10001', 'N', 'E10ADC3949BA59ABBE56E057F20F883E');
INSERT INTO `tb_user` VALUES ('管理员', 'MYSYYH', '10001', 'N', 'C33367701511B4F6020EC61DED352059');
INSERT INTO `tb_user` VALUES ('POC', 'POC', 'MYCMB', 'N', '42974CF927AD7559328109582B7D7F84');
INSERT INTO `tb_user` VALUES ('测试用户', 'test', 'Test', 'N', 'B4C0F78449FB5193A970A80C519AD3A5');
INSERT INTO `tb_user` VALUES ('管理员', 'testUser', '10001', 'N', 'E10ADC3949BA59ABBE56E057F20F883E');
INSERT INTO `tb_user` VALUES ('张三', 'zhangsan', '10001', 'N', 'E10ADC3949BA59ABBE56E057F20F883E');

-- ----------------------------
-- Table structure for `tb_vendor`
-- ----------------------------
DROP TABLE IF EXISTS `tb_vendor`;
CREATE TABLE `tb_vendor` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  `name` varchar(64) DEFAULT NULL COMMENT '供应商名称',
  `email` varchar(128) DEFAULT NULL COMMENT '供应商电子邮箱',
  `contact_phone` varchar(24) DEFAULT NULL COMMENT '联系电话',
  `url` varchar(256) DEFAULT NULL COMMENT '网站地址',
  `hook_addr` varchar(256) DEFAULT NULL COMMENT 'hook 地址',
  `status` varchar(1) DEFAULT 'N' COMMENT '是否删除',
  `display_name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_vendor
-- ----------------------------
INSERT INTO `tb_vendor` VALUES ('1', '华软金科', '1234567@139.com', '1234567', 'www.testvendor.com', 'http://192.168.1.145:32100/purchase', 'N', '华软');
INSERT INTO `tb_vendor` VALUES ('3', 'testVendor', '1234567@139.com', '1234567', 'www.testvendor.com', '192.168.10.131', 'N', 'displayVen22');
