-- ----------------------------
-- Table structure for ad
-- ----------------------------
DROP TABLE IF EXISTS `ad`;
CREATE TABLE `ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `img_file_name` varchar(100) DEFAULT NULL COMMENT '图片文件名',
  `link` varchar(200) DEFAULT NULL COMMENT '链接地址',
  `weight` int(11) DEFAULT NULL COMMENT '权重',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `img_file_name` varchar(100) DEFAULT NULL COMMENT '图片文件名',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `subtitle` varchar(100) DEFAULT NULL COMMENT '副标题',
  `price` decimal(11,2) DEFAULT NULL COMMENT '价格(单位：元)',
  `distance` int(11) DEFAULT NULL COMMENT '距离(单位：米)',
  `number` int(11) DEFAULT NULL COMMENT '已售数量',
  `desc` varchar(500) DEFAULT NULL COMMENT '描述',
  `city` varchar(16) DEFAULT NULL COMMENT '城市',
  `category` varchar(16) DEFAULT NULL COMMENT '类别',
  `star_total_num` int(11) DEFAULT NULL COMMENT '评论星星总数',
  `comment_total_num` int(11) DEFAULT NULL COMMENT '评论总次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orders_id` int(11) DEFAULT NULL COMMENT '用户名',
  `comment` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `star` int(1) DEFAULT NULL COMMENT '星级评分',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Normal_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for dic
-- ----------------------------
DROP TABLE IF EXISTS `dic`;
CREATE TABLE `dic` (
  `type` varchar(16) NOT NULL,
  `code` varchar(16) NOT NULL,
  `name` varchar(16) NOT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`type`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` bigint(13) DEFAULT NULL COMMENT '手机号',
  `name` varchar(16) DEFAULT NULL COMMENT '用户名',
  `password` char(32) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_unique` (`phone`),
  UNIQUE KEY `name_unique` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `business_id` int(11) DEFAULT NULL COMMENT '商户主键',
  `member_id` int(11) DEFAULT NULL COMMENT '会员主键',
  `num` int(11) DEFAULT NULL COMMENT '消费人数',
  `comment_state` int(1) DEFAULT NULL COMMENT '评论状态 -- 0：未评论 2：已评论',
  `price` decimal(11,2) DEFAULT NULL COMMENT '价格(消费金额)',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_action
-- ----------------------------
DROP TABLE IF EXISTS `sys_action`;
CREATE TABLE `sys_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '动作名称',
  `url` varchar(200) DEFAULT NULL COMMENT '动作地址',
  `menu_id` int(11) DEFAULT NULL,
  `method` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统表-操作表，权限拦截用，存放系统里全部动作。';


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `order_num` decimal(5,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_group_action
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_action`;
CREATE TABLE `sys_group_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL,
  `action_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_group_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_menu`;
CREATE TABLE `sys_group_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `param_key` varchar(20) DEFAULT NULL,
  `param_value` datetime DEFAULT NULL,
  `param_desc` varchar(200) DEFAULT NULL,
  UNIQUE KEY `Unique_key` (`param_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数表';


-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '登录密码',
  `ch_name` varchar(32) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Unique_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;