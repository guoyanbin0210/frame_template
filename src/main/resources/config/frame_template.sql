/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : frame_template

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 22/06/2020 09:31:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_about_us
-- ----------------------------
DROP TABLE IF EXISTS `tb_about_us`;
CREATE TABLE `tb_about_us`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单id',
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '继承菜单id',
  `us_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '标题',
  `us_contnt` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '新闻内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '关于我们' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_about_us
-- ----------------------------
INSERT INTO `tb_about_us` VALUES ('10ce40da80d44661a6c8ca687dcfb9cc', 'admin', '2020-02-29 17:15:55', '', NULL, '750524e4d2ca42cdab5f901fb864eada', '525b07ce14134364a2d6249eb9512935', '关于2', '<p>啊发顺丰擦</p>');
INSERT INTO `tb_about_us` VALUES ('59b9f7323b5b46c4a4d9a2154ed9f45b', 'admin', '2020-02-29 16:19:54', '', NULL, '750524e4d2ca42cdab5f901fb864eada', '8b05673fc60d425182c204b463a82e66', '关于我们', '<p>按时擦拭擦拭</p>');

-- ----------------------------
-- Table structure for tb_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `tb_type` int(4) NULL DEFAULT 0 COMMENT '类型',
  `tb_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '名称',
  `tb_company` int(4) NULL DEFAULT 0 COMMENT '是否属于本公司',
  `tb_status` int(4) NULL DEFAULT 0 COMMENT '状态',
  `tb_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '账号',
  `tb_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `tb_admin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '总管理员',
  `tb_operator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '总管理员',
  `tb_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '管理员手机号',
  `tb_appId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'appId',
  `tb_secret_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '秘钥',
  `tb_main_company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主体公司',
  `tb_remark` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `tb_over_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账号管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_account
-- ----------------------------
INSERT INTO `tb_account` VALUES ('0ca8ac7b538b4ed7bd29b37b3300c031', 'admin', '2020-02-20 18:05:24', '', NULL, 0, '测试', 0, 0, '123456', '1212122', '464511', '画出输出', '152121221221', 'cascascas', '454646', '0', 'sbdfbdfbfd', '2020-02-21 00:00:00');
INSERT INTO `tb_account` VALUES ('5b61c72280274b9ba4801261577d41d2', 'admin', '2020-02-20 14:42:18', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-20 14:59:57', 0, '测试名称', 0, 0, '123456', '9781', '464511', '黑粉', '545454454', '545455', '454646', '0', '1165165', '2020-02-22 00:00:00');
INSERT INTO `tb_account` VALUES ('ec7fbed1a3684d95865cacce6cb61f14', 'admin', '2020-02-20 17:54:59', '', NULL, 1, '测试', 1, 1, '123456', '1212122', '464511', '画出输出', '152121221221', 'cascascas', 'acascasc', '1', 'cuasuscuasub', '2020-02-20 00:00:00');

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_time',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '地址',
  `postal_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮政编码',
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '联系方式',
  `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '传真',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '电子邮件',
  `logo_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'logo路径',
  `picture_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '照片路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '底部信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_banner
-- ----------------------------
DROP TABLE IF EXISTS `tb_banner`;
CREATE TABLE `tb_banner`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `banner_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '照片链接',
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '对应链接id',
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '父级菜单id',
  `banner_sort` int(4) NULL DEFAULT 0 COMMENT '排序',
  `banner_type` int(4) NULL DEFAULT 0 COMMENT '分类0：banner图 1：图标logo',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'banner管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_banner
-- ----------------------------
INSERT INTO `tb_banner` VALUES ('62d379990678431b8d845aeb69360131', 'admin', '2020-02-28 12:26:33', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 21:14:43', '/uploads/1583154827383.jpg', '0cdd51ff96354f3ead8ec4e478c0dcf5', '7e8e8343893f4aa6ac321fcfb88516d1', 2, 0);
INSERT INTO `tb_banner` VALUES ('64c78cc301964f4d928f4e94b2fe0c05', 'admin', '2020-02-28 12:21:10', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-11 09:35:57', '/uploads/1583154891673.jpg', '853cfad0b7d9474992d7cb76c5691f6b', 'f6a312f5290e49fa9904492242531bdc', 3, 0);
INSERT INTO `tb_banner` VALUES ('73f62bec92ce4e0e89158879d860d0e0', 'admin', '2020-03-03 17:51:19', '', NULL, '/uploads/1583229078375.jpg', '', '', 0, 2);
INSERT INTO `tb_banner` VALUES ('837cba1012374831956fb96388701c3a', 'admin', '2020-03-03 17:51:32', '', NULL, '/uploads/1583229091635.jpg', '', '', 0, 3);
INSERT INTO `tb_banner` VALUES ('8767b5c40db546d2973ecddaa67d18b2', 'admin', '2020-02-28 15:25:54', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-03 14:47:38', '/uploads/1583154811211.jpg', '1c1fde1e6e8d4bdcb635ba19d39a57f3', '7e8e8343893f4aa6ac321fcfb88516d1', 1, 1);
INSERT INTO `tb_banner` VALUES ('8b887f3240e04fb6abd84443b3e9fe16', 'admin', '2020-02-28 12:18:43', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-11 09:36:10', '/uploads/1583154910478.jpg', '525b07ce14134364a2d6249eb9512935', '750524e4d2ca42cdab5f901fb864eada', 4, 0);
INSERT INTO `tb_banner` VALUES ('9936bcbf44a64937850daf0f12025aef', 'admin', '2020-02-28 11:24:51', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 21:15:38', '/uploads/1583154926950.jpg', '8b05673fc60d425182c204b463a82e66', '城管与市政', 5, 0);
INSERT INTO `tb_banner` VALUES ('acbd7552685d44a0a1f24e74e6013bb3', 'admin', '2020-03-02 22:09:32', '', NULL, '/uploads/1583158171410.jpg', '', 'ab7f96a17b8547b08c794c3ed3dff09a', 0, 2);
INSERT INTO `tb_banner` VALUES ('f21276ff46ba4ca497ed0d1a5058cb03', 'admin', '2020-03-03 15:30:39', '', NULL, '/uploads/1583220627148.jpg', 'dbb1391aa9324d94af5404be5895ef62', '7e8e8343893f4aa6ac321fcfb88516d1', 2, 1);

-- ----------------------------
-- Table structure for tb_base
-- ----------------------------
DROP TABLE IF EXISTS `tb_base`;
CREATE TABLE `tb_base`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` int(11) NULL DEFAULT 1 COMMENT 'status',
  `base_available` int(11) NULL DEFAULT 1 COMMENT 'available',
  `base_sort` int(11) NULL DEFAULT 1 COMMENT 'custom_sort',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  UNIQUE INDEX `id_2`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE,
  INDEX `create_time_2`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '基类' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_content`;
CREATE TABLE `tb_content`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `tb_menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单id',
  `tb_parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '继承菜单id',
  `tb_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '标题',
  `tb_about` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '简介',
  `tb_title_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '标题照片链接',
  `tb_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文本内容',
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '祖籍id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_content
-- ----------------------------
INSERT INTO `tb_content` VALUES ('05bb723610154b3cb7a1a6d553c95b20', 'admin', '2020-02-28 21:36:04', '', NULL, 'dbb1391aa9324d94af5404be5895ef62', '60fc2485867745fa8c62148ae17e56c9', '安全生产管理', '挨捶是大V', '/uploads/1582896955704.png', '<p>阿奴群殴次哦IC</p>', '7e8e8343893f4aa6ac321fcfb88516d1');
INSERT INTO `tb_content` VALUES ('0bc9bc12e6344580abef735ff812dbbd', 'admin', '2020-02-28 16:43:19', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 21:17:42', '0cdd51ff96354f3ead8ec4e478c0dcf5', '6895ef51f1ae4c5885ae881722713b5d', '市政综合管理', '是干啥的吧', '/uploads/1583155060770.jpg', '', '2bd9290f4b6d4e24a1b1f62cc6c47029');
INSERT INTO `tb_content` VALUES ('685498ea9b424f1d8111d0d97e49423c', 'admin', '2020-02-28 20:38:07', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 21:16:51', '1c1fde1e6e8d4bdcb635ba19d39a57f3', '51be1078b2b54e8687517be4b6748253', '停车管理', '测试简介', '/uploads/1583154983765.jpg', '<p><img src=\"http://39.104.118.198:8082/ue//upload/image/20200302/1583154994491091183.jpg\" title=\"1583154994491091183.jpg\" alt=\"u=369155909,4274396596&amp;fm=26&amp;gp=0.jpg\"/></p>', '2bd9290f4b6d4e24a1b1f62cc6c47029');
INSERT INTO `tb_content` VALUES ('6fb0cabcc41340d19ed6d9b5ef57acc8', 'admin', '2020-02-28 21:37:55', '', NULL, '7fc39ab1152d4bed9d6aa2b6ba94692d', '7212ed8e7208467381f547f642e69657', '物联网', '擦拭DVD', '/uploads/1582897068724.jpg', '<p>我擦擦擦</p>', '7e8e8343893f4aa6ac321fcfb88516d1');
INSERT INTO `tb_content` VALUES ('95c96af962574ac788c49b56f6f22b1d', 'admin', '2020-02-28 16:46:28', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 21:17:23', '0cdd51ff96354f3ead8ec4e478c0dcf5', '17a82bb9dc7040bd8f8d2b4ce2cd41d3', '市政综合管理', '就，就，基本', '/uploads/1583155025058.jpg', '<p><img src=\"http://localhost:8082/ue//upload/image/20200228/1582879584053056808.png\" title=\"1582879584053056808.png\" alt=\"0c1aa16a9ab7cf4370dd024cf5c3143.png\"/></p>', '2bd9290f4b6d4e24a1b1f62cc6c47029');
INSERT INTO `tb_content` VALUES ('996fedab29184ce499dc9d79a93735c5', 'admin', '2020-02-28 21:55:13', '', NULL, 'c6636002aa094707a595e40942ee9daf', 'a01a1f96b61642f699953ef078c15540', '北京城市管理', '是大V是大V速度', '/uploads/1582898105970.jpg', '<p>吧是大V速度</p>', 'f6a312f5290e49fa9904492242531bdc');
INSERT INTO `tb_content` VALUES ('b89d704abb594e98b7460e7cf863f067', 'admin', '2020-03-02 22:33:59', '', NULL, '1c1fde1e6e8d4bdcb635ba19d39a57f3', '9ec7333cabe747328bd3ea3e5631a682', '卡扣测试', '的士速递', '/uploads/1583159632125.jpg', '<p>csdcsd</p>', '2bd9290f4b6d4e24a1b1f62cc6c47029');
INSERT INTO `tb_content` VALUES ('da4e19c9aa57418785748dac48ff62a2', 'admin', '2020-02-28 22:02:31', '', NULL, 'c6636002aa094707a595e40942ee9daf', 'c2b42602d85f4a98b207b9614072ab50', '秦皇岛市', '简介', '/uploads/1582898433076.jpg', '<p><img src=\"http://localhost:8082/ue//upload/image/20200228/1582898438233094944.jpg\" title=\"1582898438233094944.jpg\" alt=\"微信图片_20200228215826.jpg\" width=\"636\" height=\"872\" style=\"width: 636px; height: 872px;\"/></p>', 'f6a312f5290e49fa9904492242531bdc');
INSERT INTO `tb_content` VALUES ('e95a8771dba849de991f7871589a38dc', 'admin', '2020-02-28 21:55:49', '', NULL, 'c6636002aa094707a595e40942ee9daf', '4474c3a9b1ac4200b153ba9101cab6d9', '四川武圣县城', '按时擦拭', '/uploads/1582898145488.png', '<p>是大V是大V速度</p>', 'f6a312f5290e49fa9904492242531bdc');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单名称',
  `menu_parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '父级id',
  `menu_level` int(4) NULL DEFAULT 0 COMMENT '层级',
  `menu_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `menu_sort` int(4) NULL DEFAULT NULL COMMENT '排序',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转连接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('057c68091e8f48e5bea9b842f85cfc6b', 'admin', '2020-03-02 23:09:59', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:10:13', '安全生产监督', 'b67683120b3b410f835aa0253ec512cf', 2, 'bg', 1, 'detail.html');
INSERT INTO `tb_menu` VALUES ('0cdd51ff96354f3ead8ec4e478c0dcf5', 'admin', '2020-02-27 11:40:53', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:15:26', '城管与市政', '2bd9290f4b6d4e24a1b1f62cc6c47029', 1, 'bg', 1, 'list.html');
INSERT INTO `tb_menu` VALUES ('13a87b72378742de90fb38b169fb7a8c', 'admin', '2020-03-02 23:09:43', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:09:52', '酒店', '28b0995107dc4b4780514f039408862e', 2, 'bg', 3, 'detail.html');
INSERT INTO `tb_menu` VALUES ('17a82bb9dc7040bd8f8d2b4ce2cd41d3', 'admin', '2020-02-27 11:42:11', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:46:15', '城管综合执法', '0cdd51ff96354f3ead8ec4e478c0dcf5', 2, 'bg', 2, 'detail.html');
INSERT INTO `tb_menu` VALUES ('1c1fde1e6e8d4bdcb635ba19d39a57f3', 'admin', '2020-02-27 13:57:44', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:11:33', '城市交通', '2bd9290f4b6d4e24a1b1f62cc6c47029', 1, 'bg', 2, 'list.html');
INSERT INTO `tb_menu` VALUES ('2723b63c39b349939cbd82060fa83c12', 'admin', '2020-02-28 21:52:22', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:16:19', '城市交通', 'f6a312f5290e49fa9904492242531bdc', 1, 'bg', 2, 'list.html');
INSERT INTO `tb_menu` VALUES ('28b0995107dc4b4780514f039408862e', 'admin', '2020-03-02 23:05:27', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:06:09', '智能建筑', '2bd9290f4b6d4e24a1b1f62cc6c47029', 1, 'bg', 3, 'list.html');
INSERT INTO `tb_menu` VALUES ('2bd9290f4b6d4e24a1b1f62cc6c47029', 'admin', '2020-02-27 11:40:40', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-27 14:10:32', '解决方案', '无', 0, '', 2, NULL);
INSERT INTO `tb_menu` VALUES ('2fffaf39e94a4bd08d556b8426fd53d3', 'admin', '2020-03-02 23:06:32', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:06:42', '其他', '2bd9290f4b6d4e24a1b1f62cc6c47029', 1, 'bg', 5, 'list.html');
INSERT INTO `tb_menu` VALUES ('39b79e5e194d4bb4968fc1c85963f716', 'admin', '2020-03-02 23:13:14', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:13:26', '智慧楼', '7fc39ab1152d4bed9d6aa2b6ba94692d', 2, 'bg', 2, 'detail.html');
INSERT INTO `tb_menu` VALUES ('4474c3a9b1ac4200b153ba9101cab6d9', 'admin', '2020-02-28 21:51:49', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:16:40', '四川武圣县城', 'c6636002aa094707a595e40942ee9daf', 2, 'bg', 3, 'detail.html');
INSERT INTO `tb_menu` VALUES ('51be1078b2b54e8687517be4b6748253', 'admin', '2020-02-28 20:36:10', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:49:00', '停车管理', '1c1fde1e6e8d4bdcb635ba19d39a57f3', 2, 'bg', 1, 'detail.html');
INSERT INTO `tb_menu` VALUES ('525b07ce14134364a2d6249eb9512935', 'admin', '2020-02-29 17:15:28', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:18:25', '招聘信息', '750524e4d2ca42cdab5f901fb864eada', 1, 'bg', 2, 'detail.html');
INSERT INTO `tb_menu` VALUES ('60fc2485867745fa8c62148ae17e56c9', 'admin', '2020-02-28 21:33:55', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:17:09', '安全生产管理', 'dbb1391aa9324d94af5404be5895ef62', 2, 'bg', 1, 'detail.html');
INSERT INTO `tb_menu` VALUES ('6895ef51f1ae4c5885ae881722713b5d', 'admin', '2020-02-27 11:42:24', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:46:09', '市政综合管理', '0cdd51ff96354f3ead8ec4e478c0dcf5', 2, 'bg', 1, 'detail.html');
INSERT INTO `tb_menu` VALUES ('7212ed8e7208467381f547f642e69657', 'admin', '2020-02-28 21:37:18', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 21:37:34', '物联网', '7fc39ab1152d4bed9d6aa2b6ba94692d', 2, 'bg', 1, NULL);
INSERT INTO `tb_menu` VALUES ('750524e4d2ca42cdab5f901fb864eada', 'admin', '2020-02-27 13:59:53', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:04:59', '关于我们', '无', 0, '', 6, NULL);
INSERT INTO `tb_menu` VALUES ('77153ca8edd3440fb5757abbf369b9cc', 'admin', '2020-03-02 23:12:22', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:12:32', '文物保护', '2fffaf39e94a4bd08d556b8426fd53d3', 2, 'bg', 1, 'detail.html');
INSERT INTO `tb_menu` VALUES ('7770ed8f64fb4999a3790272bb4d99ba', 'admin', '2020-03-02 23:10:47', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:11:00', '城市生命线', 'b67683120b3b410f835aa0253ec512cf', 2, 'bg', 3, 'detail.html');
INSERT INTO `tb_menu` VALUES ('78a2d211502748fe9a33fbe4589e61d2', 'admin', '2020-03-02 23:17:12', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:17:23', '物流运输', '853cfad0b7d9474992d7cb76c5691f6b', 2, 'bg', 2, 'detail.html');
INSERT INTO `tb_menu` VALUES ('7e8e8343893f4aa6ac321fcfb88516d1', 'admin', '2020-02-27 13:45:14', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-27 13:59:12', '产品与服务', '无', 0, '', 3, NULL);
INSERT INTO `tb_menu` VALUES ('7fc39ab1152d4bed9d6aa2b6ba94692d', 'admin', '2020-02-28 21:30:39', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:25:05', '平台软件', '7e8e8343893f4aa6ac321fcfb88516d1', 1, 'bg', 2, 'list.html');
INSERT INTO `tb_menu` VALUES ('80ded971d71a41d4abdb16ff22d92b8c', 'admin', '2020-02-27 14:02:36', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:18:41', '党群活动', 'caf724d3933c4a37820b8807dc9e370d', 1, 'bg', 1, 'item.html');
INSERT INTO `tb_menu` VALUES ('8281fe82533145f794ff870f4db081bd', 'admin', '2020-03-02 23:15:16', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:15:28', '咨询与设计', 'f37eddc144ad4823924dd9efa42c445d', 2, 'bg', 1, 'detail.html');
INSERT INTO `tb_menu` VALUES ('853cfad0b7d9474992d7cb76c5691f6b', 'admin', '2020-02-28 21:52:35', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:16:08', '城市安全', 'f6a312f5290e49fa9904492242531bdc', 1, 'bg', 3, 'list.html');
INSERT INTO `tb_menu` VALUES ('8b05673fc60d425182c204b463a82e66', 'admin', '2020-02-27 14:02:25', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:18:15', '关于通城', '750524e4d2ca42cdab5f901fb864eada', 1, 'bg', 1, 'detail.html');
INSERT INTO `tb_menu` VALUES ('92910c3ffacd40ba8af864d4f0ad80df', 'admin', '2020-03-02 23:13:37', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:14:26', '物联网产品', '7e8e8343893f4aa6ac321fcfb88516d1', 1, 'bg', 3, 'list.html');
INSERT INTO `tb_menu` VALUES ('9521771dd7e64966a4e8b2e3fff7af73', 'admin', '2020-03-02 23:18:44', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:18:55', '榜样先锋', 'caf724d3933c4a37820b8807dc9e370d', 1, 'bg', 2, 'item.html');
INSERT INTO `tb_menu` VALUES ('960d2fea784c4e1ca31a4629406e260d', 'admin', '2020-03-02 23:13:59', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:14:17', '通城产品', '92910c3ffacd40ba8af864d4f0ad80df', 2, 'bg', 1, 'detail.html');
INSERT INTO `tb_menu` VALUES ('9ec7333cabe747328bd3ea3e5631a682', 'admin', '2020-02-28 20:37:07', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:08:30', '卡口电警系统', '1c1fde1e6e8d4bdcb635ba19d39a57f3', 2, 'bg', 2, 'detail.html');
INSERT INTO `tb_menu` VALUES ('a01a1f96b61642f699953ef078c15540', 'admin', '2020-02-28 21:50:58', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:24:59', '北京城市管理', 'c6636002aa094707a595e40942ee9daf', 2, 'bg', 1, 'detail.html');
INSERT INTO `tb_menu` VALUES ('aa66051b2d8842e59646dc1b5bf518d6', 'admin', '2020-03-02 23:07:45', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:08:09', '环境卫生治理', '0cdd51ff96354f3ead8ec4e478c0dcf5', 2, 'bg', 3, 'detail.html');
INSERT INTO `tb_menu` VALUES ('ab7f96a17b8547b08c794c3ed3dff09a', 'admin', '2020-02-27 11:34:05', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-27 13:54:09', '首页', '无', 0, '', 1, NULL);
INSERT INTO `tb_menu` VALUES ('b310c14c9d0d404488b9179b3e29feff', 'admin', '2020-03-02 23:14:38', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:14:50', '其他类型产品', '92910c3ffacd40ba8af864d4f0ad80df', 2, 'bg', 2, 'detail.html');
INSERT INTO `tb_menu` VALUES ('b67683120b3b410f835aa0253ec512cf', 'admin', '2020-03-02 23:05:48', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:06:27', '城市安全', '2bd9290f4b6d4e24a1b1f62cc6c47029', 1, 'bg', 4, 'list.html');
INSERT INTO `tb_menu` VALUES ('bc0c977dc8c24b328b12f1290e8f2a4e', 'admin', '2020-02-28 21:34:10', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:16:58', '智慧城管系统', 'dbb1391aa9324d94af5404be5895ef62', 2, 'bg', 2, 'detail.html');
INSERT INTO `tb_menu` VALUES ('be5acd5560d34069a92315cf696b5aa5', 'admin', '2020-03-02 23:16:40', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:16:52', '城市管理', '2723b63c39b349939cbd82060fa83c12', 2, 'bg', 2, 'detail.html');
INSERT INTO `tb_menu` VALUES ('c2b42602d85f4a98b207b9614072ab50', 'admin', '2020-02-28 21:51:23', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:24:49', '秦皇岛市', 'c6636002aa094707a595e40942ee9daf', 2, 'bg', 2, 'detail.html');
INSERT INTO `tb_menu` VALUES ('c61bf1c1460a44b08e9bcda463dd8bcb', 'admin', '2020-03-02 23:17:55', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-03 09:20:28', '媒体报道', 'c709c2a01fa049bca7f6db36a6665c22', 1, 'bg', 2, 'item.html');
INSERT INTO `tb_menu` VALUES ('c6636002aa094707a595e40942ee9daf', 'admin', '2020-02-27 14:02:05', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:16:32', '城管与市政', 'f6a312f5290e49fa9904492242531bdc', 1, 'bg', 1, 'list.html');
INSERT INTO `tb_menu` VALUES ('c709c2a01fa049bca7f6db36a6665c22', 'admin', '2020-02-27 13:59:36', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-27 13:59:51', '新闻资讯', '无', 0, '', 5, NULL);
INSERT INTO `tb_menu` VALUES ('caf724d3933c4a37820b8807dc9e370d', 'admin', '2020-02-27 14:00:43', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-27 14:00:53', '党建工作', '无', 0, '', 7, NULL);
INSERT INTO `tb_menu` VALUES ('d2616a1dce264b28844f21665228335e', 'admin', '2020-03-02 23:15:35', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:15:48', '系统集成', 'f37eddc144ad4823924dd9efa42c445d', 2, 'bg', 2, 'detail.html');
INSERT INTO `tb_menu` VALUES ('d55b7533abcc4fdd93fd2e06330f7671', 'admin', '2020-02-27 14:02:15', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:17:52', '行业新闻', 'c709c2a01fa049bca7f6db36a6665c22', 1, 'bg', 1, 'item.html');
INSERT INTO `tb_menu` VALUES ('d8ba62c9928e408780430959a9a70f28', 'admin', '2020-03-02 23:09:04', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:09:17', '企业总部大楼', '28b0995107dc4b4780514f039408862e', 2, 'bg', 1, 'detail.html');
INSERT INTO `tb_menu` VALUES ('dbb1391aa9324d94af5404be5895ef62', 'admin', '2020-02-27 14:01:44', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 20:25:09', '行业应用软件', '7e8e8343893f4aa6ac321fcfb88516d1', 1, 'bg', 1, 'list.html');
INSERT INTO `tb_menu` VALUES ('e16fe5b4e6e143ba995a8f5c00d41348', 'admin', '2020-03-02 23:16:19', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:16:36', '城市生命线', '2723b63c39b349939cbd82060fa83c12', 2, 'bg', 1, 'detail.html');
INSERT INTO `tb_menu` VALUES ('e1af0d9962724eefb4e11b928721e61f', 'admin', '2020-03-02 23:08:36', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:08:53', '交通信号管理', '1c1fde1e6e8d4bdcb635ba19d39a57f3', 2, 'bg', 3, 'detail.html');
INSERT INTO `tb_menu` VALUES ('f11c2ba2df2c4fb1b0c77f26b08a3ac2', 'admin', '2020-03-02 23:09:24', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:09:39', '工厂', '28b0995107dc4b4780514f039408862e', 2, 'bg', 2, 'detail.html');
INSERT INTO `tb_menu` VALUES ('f37eddc144ad4823924dd9efa42c445d', 'admin', '2020-03-02 23:15:01', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:15:12', '专业服务', '7e8e8343893f4aa6ac321fcfb88516d1', 1, 'bg', 4, 'list.html');
INSERT INTO `tb_menu` VALUES ('f6a312f5290e49fa9904492242531bdc', 'admin', '2020-02-27 13:59:16', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-27 13:59:33', '经典案例', '无', 0, '', 4, NULL);
INSERT INTO `tb_menu` VALUES ('fc45f472a3704286a02540a82c306db2', 'admin', '2020-03-02 23:10:18', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:10:41', '电梯运行监管', 'b67683120b3b410f835aa0253ec512cf', 2, 'bg', 2, 'detail.html');
INSERT INTO `tb_menu` VALUES ('fc638035d07c499790b1973921f39093', 'admin', '2020-03-02 23:16:55', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 23:17:08', '交通运输', '853cfad0b7d9474992d7cb76c5691f6b', 2, 'bg', 1, 'detail.html');

-- ----------------------------
-- Table structure for tb_new_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_new_content`;
CREATE TABLE `tb_new_content`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单id',
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '继承菜单id',
  `news_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '标题',
  `title_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '标题照片',
  `publish_date` datetime(0) NULL DEFAULT NULL COMMENT '发布日期',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '作者',
  `news_contnt` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '新闻内容',
  `news_sort` int(4) NULL DEFAULT 0 COMMENT '是否推荐到首页 0：是 1：否',
  `news_about` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '新闻简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '新闻管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_new_content
-- ----------------------------
INSERT INTO `tb_new_content` VALUES ('09cba22df8f14211a4a7d7bcb27b528c', 'admin', '2020-03-03 10:22:10', '', NULL, 'caf724d3933c4a37820b8807dc9e370d', '80ded971d71a41d4abdb16ff22d92b8c', '新闻', '/uploads/1583202118385.jpg', '2020-03-03 00:00:00', '李涛', '<p><img src=\"http://39.104.118.198:8082/ue//upload/image/20200303/1583202125943059652.jpg\" title=\"1583202125943059652.jpg\" alt=\"u=2790694371,2013394464&amp;fm=11&amp;gp=0.jpg\"/></p>', 0, '4885');
INSERT INTO `tb_new_content` VALUES ('24670348b3a34d1a968307ced7317eb8', 'admin', '2020-02-28 22:56:59', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-03 09:17:30', 'c709c2a01fa049bca7f6db36a6665c22', 'd55b7533abcc4fdd93fd2e06330f7671', '新闻', '/uploads/1582901801468.jpg', '2020-02-28 00:00:00', '李涛', '<p>按时擦拭的<br/></p><p><img src=\"http://localhost:8082/ue//upload/image/20200228/1582901816644003958.jpg\" title=\"1582901816644003958.jpg\" alt=\"微信图片_20200228215919.jpg\"/></p>', 0, 'jianjie');
INSERT INTO `tb_new_content` VALUES ('370bc2698698402f88990a767a3cb2e6', 'admin', NULL, '', NULL, 'c709c2a01fa049bca7f6db36a6665c22', 'd55b7533abcc4fdd93fd2e06330f7671', '新闻资讯', '/uploads/1582901696268.jpg', NULL, '李涛', '<p>新闻内容</p><p><img src=\"http://localhost:8082/ue//upload/image/20200228/1582901717449071558.jpg\" title=\"1582901717449071558.jpg\" alt=\"微信图片_20200228215919.jpg\"/></p>', 0, NULL);
INSERT INTO `tb_new_content` VALUES ('77900b20346c4b2b8d83f1687e04d666', 'admin', '2020-02-29 18:22:09', '', NULL, 'caf724d3933c4a37820b8807dc9e370d', '80ded971d71a41d4abdb16ff22d92b8c', '党建', '/uploads/1582971721573.jpg', '2020-02-29 00:00:00', '李涛', '<p>啊啊菜市场</p>', 0, NULL);

-- ----------------------------
-- Table structure for tb_sys_concern
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_concern`;
CREATE TABLE `tb_sys_concern`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'status',
  `base_available` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'base_available',
  `base_sort` int(11) NULL DEFAULT 0 COMMENT 'base_sort',
  `sc_main_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主表id',
  `sc_main_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主表id描述',
  `sc_son_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '子表id',
  `sc_son_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '子表id描述',
  `sc_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '归属类型',
  `sc_son_data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `sc_main_data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_sys_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_file`;
CREATE TABLE `tb_sys_file`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'status',
  `base_available` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'base_available',
  `base_sort` int(11) NULL DEFAULT 0 COMMENT 'base_sort',
  `sf_show_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '显示名称',
  `sf_norm_local_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '本地路径',
  `sf_norm_url_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '网络路径',
  `sf_norm_size` bigint(32) NULL DEFAULT 0 COMMENT '文件大小',
  `sf_compress_local_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '压缩本地路径',
  `sf_compress_url_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '压缩网络路径',
  `sf_compress_size` int(11) NULL DEFAULT 0 COMMENT '压缩文件大小',
  `sf_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文件类型',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_sys_file
-- ----------------------------
INSERT INTO `tb_sys_file` VALUES ('01044bee962e432fbcd0503a62cc02d9', 'admin', '2020-02-29 21:04:48', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582981488965.png', '/uploads/1582981488965.png', 1711757, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('01ac1004ddef427c8367d56de57635a7', 'admin', '2020-03-02 21:12:03', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583154723506.jpg', '/uploads/1583154723506.jpg', 18990, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('0644008890a94e10a9593ad4cd856488', 'admin', '2020-02-29 18:22:01', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582971721573.jpg', '/uploads/1582971721573.jpg', 144153, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('0665bef543074c0da62387ec5fa8d4a7', 'admin', '2020-02-28 16:39:55', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582879195150.png', '/uploads/1582879195150.png', 118565, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('07bf07f7567a4d34b72f37b0cff85cca', 'admin', '2020-03-02 21:34:31', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583156071404.jpg', '/uploads/1583156071404.jpg', 384501, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('0a0701ae381f4b55a9adbd91f66791fa', 'admin', '2020-02-28 22:00:33', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582898433076.jpg', '/uploads/1582898433076.jpg', 144153, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('0bb70ffe08cd46078fc90c49fe0e7a9f', 'admin', '2020-02-29 18:41:05', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582972865222.png', '/uploads/1582972865222.png', 1711757, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('0c9659a7b4854defa61dc09883024d67', 'admin', '2020-03-02 21:15:26', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583154926950.jpg', '/uploads/1583154926950.jpg', 384501, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('133998fc51764a3c91cc8cff812ffc9a', 'admin', '2020-02-28 16:28:24', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582878504617.jpg', '/uploads/1582878504617.jpg', 51076, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('1a54c8bdc25342be89bc81df17f39084', 'admin', '2020-02-28 16:15:01', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582877701129.png', '/uploads/1582877701129.png', 23686, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('1d27546586f9411bbd57b84fbe40acec', 'admin', '2020-03-02 21:12:23', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583154743488.jpg', '/uploads/1583154743488.jpg', 18990, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('23ffcb1a89c14e2d89703a80a312a879', 'admin', '2020-03-02 21:10:45', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583154645210.jpg', '/uploads/1583154645210.jpg', 18990, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('26fd0d5a3e844f0ca0a6783c3909b9a8', 'admin', '2020-02-28 12:26:27', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582863987696.png', '/uploads/1582863987696.png', 1122140, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('281a6c1f83d749288a9d384d25118fa7', 'admin', '2020-02-28 12:21:04', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582863664170.jpg', '/uploads/1582863664170.jpg', 51076, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('2a1ad86ce1f241699cfce40a8d036315', 'admin', '2020-02-28 16:43:11', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582879391284.png', '/uploads/1582879391284.png', 1122140, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('2b88958a0a1a48d79e660ccc667e05aa', 'admin', '2020-03-03 17:51:31', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583229091635.jpg', '/uploads/1583229091635.jpg', 27684, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('2ee5dc34564b40519b6fe5543ec5d243', 'admin', '2020-03-02 21:37:28', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583156248741.jpg', '/uploads/1583156248741.jpg', 384501, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('2fc10f123275445a96715b1ded7196a2', 'admin', '2020-02-28 16:35:35', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582878935454.png', '/uploads/1582878935454.png', 118565, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('2ffccac9233944e8b8cf1da5e39bb61b', 'admin', '2020-03-03 10:21:58', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583202118385.jpg', '/uploads/1583202118385.jpg', 286040, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('32562367358241448ae5a9a38641251f', 'admin', '2020-02-28 12:16:53', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582863413114.jpg', '/uploads/1582863413114.jpg', 51076, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('36e42821a55748a19ea990cb413ce468', 'admin', '2020-02-28 15:25:50', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582874750243.jpg', '/uploads/1582874750243.jpg', 51076, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('4689d9c3cbb94ce0b010d55cd9324a27', 'admin', '2020-03-02 21:10:31', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583154631696.jpg', '/uploads/1583154631696.jpg', 18990, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('46e1042096f34b1e88c4fb7ef09057f2', 'admin', '2020-03-02 21:37:24', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583156244209.jpg', '/uploads/1583156244209.jpg', 18990, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('52569d278f3c41a084a619a2c927c446', 'admin', '2020-02-28 21:37:48', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582897068724.jpg', '/uploads/1582897068724.jpg', 51076, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('5821535875284060a7179ba3ddc23cb9', 'admin', '2020-03-02 21:35:18', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583156118516.jpg', '/uploads/1583156118516.jpg', 384501, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('5d46808001e643079b282d60b05b5812', 'admin', '2020-02-29 21:03:31', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582981411931.png', '/uploads/1582981411931.png', 1711757, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('5de17d56ea544348a00318de24b27676', 'admin', '2020-02-28 21:55:45', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582898145488.png', '/uploads/1582898145488.png', 118565, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('604091db8ff84d6b8e54d71728e884a6', 'admin', '2020-03-02 21:17:05', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583155025058.jpg', '/uploads/1583155025058.jpg', 24678, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('62ad282f60434d83a69181bc417a4934', 'admin', '2020-02-28 22:54:56', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582901696268.jpg', '/uploads/1582901696268.jpg', 134964, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('66d1b040935f46048ddd685ad2fdedd6', 'admin', '2020-03-02 21:17:40', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583155060770.jpg', '/uploads/1583155060770.jpg', 384501, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('6b5b75500bfb462497d88ef556bb3816', 'admin', '2020-02-29 18:20:57', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582971657799.jpg', '/uploads/1582971657799.jpg', 128084, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('73e67a5ffb484dcda1f024875f8ef468', 'admin', '2020-03-02 21:16:23', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583154983765.jpg', '/uploads/1583154983765.jpg', 18990, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('7fce7d12b9544bd7947bdc43dcda970a', 'admin', '2020-02-28 21:55:05', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582898105970.jpg', '/uploads/1582898105970.jpg', 51076, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('8053329a80e8419682e4f0d34ac37fa0', 'admin', '2020-03-02 21:14:51', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583154891673.jpg', '/uploads/1583154891673.jpg', 384501, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('87be63e054444df99b85e78a7286d09a', 'admin', '2020-03-03 17:51:18', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583229078375.jpg', '/uploads/1583229078375.jpg', 32465, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('8a33a638d1b6454c9a22dde07be7a082', 'admin', '2020-02-28 16:33:37', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582878817978.png', '/uploads/1582878817978.png', 1122140, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('8a60b99fe8bc43ddb403877a6ba8d723', 'admin', '2020-03-02 21:34:10', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583156050787.jpg', '/uploads/1583156050787.jpg', 24678, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('8b2493d99be6484ab864f86b87b1f1ec', 'admin', '2020-03-02 21:30:54', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583155854104.jpg', '/uploads/1583155854104.jpg', 18990, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('8d6eb6a9aa884f4b9d5de5eebeb8b95f', 'admin', '2020-03-02 21:34:20', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583156060205.jpg', '/uploads/1583156060205.jpg', 384501, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('8eb76dac6c76464ab2f702caae9902a5', 'admin', '2020-03-02 21:15:10', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583154910478.jpg', '/uploads/1583154910478.jpg', 18990, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('8f27977846b34ebeb927e0ee20a64460', 'admin', '2020-02-28 16:13:06', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582877586847.png', '/uploads/1582877586847.png', 1122140, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('9005a804bcee41659227bcf691df814b', 'admin', '2020-03-02 21:34:37', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583156077202.jpg', '/uploads/1583156077202.jpg', 18990, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('91ba525f70a045a0b7f6c8500686a24b', 'admin', '2020-03-02 21:35:13', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583156113604.jpg', '/uploads/1583156113604.jpg', 18990, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('9cc692a08a014ceda009e6dace119ad6', 'admin', '2020-03-02 21:34:26', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583156066981.jpg', '/uploads/1583156066981.jpg', 24678, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('9e4e5821dac14eee9fe1900bb19657c9', 'admin', '2020-03-02 21:15:57', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583154957518.jpg', '/uploads/1583154957518.jpg', 18990, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('9f344b856fd64821b36c848cb8714fda', 'admin', '2020-03-02 21:34:04', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583156044483.jpg', '/uploads/1583156044483.jpg', 24678, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('a12f93d493ba4ac085062a471e01b168', 'admin', '2020-02-28 16:46:18', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582879578518.png', '/uploads/1582879578518.png', 1122140, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('ae1624d55003426e87713f1e3774d6d8', 'admin', '2020-03-03 15:30:27', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583220627148.jpg', '/uploads/1583220627148.jpg', 286040, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('aff11d0b031c41678ac4e87d1780d9a2', 'admin', '2020-02-28 22:56:41', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582901801468.jpg', '/uploads/1582901801468.jpg', 128084, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('b66239efe1484228a418c7a3be43f701', 'admin', '2020-03-02 22:33:52', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583159632125.jpg', '/uploads/1583159632125.jpg', 18990, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('ba2ca26021ed4d7fbfc402a7fa2be9e0', 'admin', '2020-02-28 10:04:20', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582855460219.png', '/uploads/1582855460219.png', 118565, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('bc957bdb51714283adc71bcf64ca2a6d', 'admin', '2020-03-02 21:13:31', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583154811211.jpg', '/uploads/1583154811211.jpg', 18990, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('bd0a52eee8ef4f8db919c4578c9729c2', 'admin', '2020-03-02 21:13:47', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583154827383.jpg', '/uploads/1583154827383.jpg', 24678, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('c1f80736c1d346acbba4af8dea3c69ac', 'admin', '2020-02-28 20:37:47', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582893467568.jpg', '/uploads/1582893467568.jpg', 51076, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('d72a34f94e5b40569d5accf8dff5dd1b', 'admin', '2020-02-28 16:05:03', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582877103627.png', '/uploads/1582877103627.png', 118565, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('d98db67c2e764a9e9e27ef67adad8324', 'admin', '2020-02-28 11:24:37', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582860277176.png', '/uploads/1582860277176.png', 1122140, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('e1c39c523da4423f9b82a647a0ff5b22', 'admin', '2020-02-28 16:23:09', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582878189672.png', '/uploads/1582878189672.png', 1122140, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('ec3ecabe37234285ab74044577a730e4', 'admin', '2020-02-28 21:35:55', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582896955704.png', '/uploads/1582896955704.png', 1122140, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('f33c721737594ae8a6c8a03e8ce4d215', 'admin', '2020-02-28 16:10:16', '', NULL, '1', '1', 1, 'file', 'E:\\financial\\file\\ceshi\\1582877416199.jpg', '/uploads/1582877416199.jpg', 51076, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('f354c881f88447f497fa69976dd5508f', 'admin', '2020-03-02 22:09:31', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583158171410.jpg', '/uploads/1583158171410.jpg', 18990, '', '', 0, '');
INSERT INTO `tb_sys_file` VALUES ('f5a6cb0d570842e8b59c9d86be74b99b', 'admin', '2020-03-02 21:09:33', '', NULL, '1', '1', 1, 'file', 'E:\\comwinwin\\file\\uploads\\1583154573453.jpg', '/uploads/1583154573453.jpg', 18990, '', '', 0, '');

-- ----------------------------
-- Table structure for tb_sys_login
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_login`;
CREATE TABLE `tb_sys_login`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'status',
  `base_available` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'base_available',
  `base_sort` int(11) NULL DEFAULT 0 COMMENT 'base_sort',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `user_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号',
  `check_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '验证码',
  `is_locked` int(3) NULL DEFAULT 0 COMMENT '是否可用',
  `check_code_expire_date` datetime(0) NULL DEFAULT NULL COMMENT '验证码过期时间一般为5分钟后',
  `user_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1:镇级 2：村级',
  `nick_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '显示名称',
  `village_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '村庄Id',
  PRIMARY KEY (`user_name`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登陆-一般应用于后台' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_sys_login
-- ----------------------------
INSERT INTO `tb_sys_login` VALUES ('4fe8709c15ef4cc6a96a8e366797bb4c', 'sys', '2018-12-20 17:02:18', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-12-19 14:54:35', '1', '1', 1, 'admin', '3ef7164d1f6167cb9f2658c07d3c2f0a', '', '', 0, NULL, '1', '', NULL);

-- ----------------------------
-- Table structure for tb_sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_login_log`;
CREATE TABLE `tb_sys_login_log`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'status',
  `base_available` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'base_available',
  `base_sort` int(11) NULL DEFAULT 0 COMMENT 'base_sort',
  `sll_login_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录id',
  `sll_login_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录时间',
  `sll_login_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_sys_login_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_login_role`;
CREATE TABLE `tb_sys_login_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'status',
  `base_available` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'base_available',
  `base_sort` int(11) NULL DEFAULT 0 COMMENT 'base_sort',
  `login_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登陆id',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_sys_login_role
-- ----------------------------
INSERT INTO `tb_sys_login_role` VALUES ('1', '', NULL, '', NULL, '', '', 1, 'f49986368d024d688eb8f9124be37523', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('10', '', NULL, '', NULL, '', '', 1, 'f2837c97f199456ea6abee213e19726c', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('11', '', NULL, '', NULL, '', '', 1, '2f51388992054eab80d5709ec787a679', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('12', '', NULL, '', NULL, '', '', 1, '0c36b6ef3a534fde90d1f02ddaf9722b', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('13', '', NULL, '', NULL, '', '', 1, '2c9ccd6355f24cfda29adebc0a7b28d2', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('13894216F00D481286E598D67099A086', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-07-05 18:10:23', '', NULL, '1', '1', 1, '29A408C0B4C44AE4A876395AC32E18C8', '1DB7E998F05D468F9218C6464EF40727');
INSERT INTO `tb_sys_login_role` VALUES ('14', '', NULL, '', NULL, '', '', 1, '4b152047c37f4b4d8e5448bb2dc1c300', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('1482fc4448fe414789ac7843a17d1bfe', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-30 15:55:51', '', NULL, '1', '1', 1, '87d082e2380648b885dfec6227eba596', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('15', '', NULL, '', NULL, '', '', 1, '8298340618b44f22a5227b2b882b243b', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('16', '', NULL, '', NULL, '', '', 1, 'cad7d8f66d9e44f7a6261913cfe88b14', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('17', '', NULL, '', NULL, '', '', 1, '43c576fa0b17434389cce7b366c770f9', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('18', '', NULL, '', NULL, '', '', 1, '5a475f1bffc748e59c1dad22244c1380', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('19', '', NULL, '', NULL, '', '', 1, 'a48b8abc6f9a4ab18757c0fb31162fd2', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('1a1ab0ece43a4d5896ace648a305a559', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-31 10:14:06', '', NULL, '1', '1', 1, '14e6afe52f7e4cdca1fba5e255c265ce', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('2', '', NULL, '', NULL, '', '', 1, 'cbbd45cb7a944cb28f92e8ef8d129bdb', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('20', '', NULL, '', NULL, '', '', 1, 'aca50a60a033468d8bf73931a33705ed', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('21', '', NULL, '', NULL, '', '', 1, '7f558e04800840c6ad26734f5935ee9f', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('22', '', NULL, '', NULL, '', '', 1, 'c1b7edd4399c43edbe26e61672afd277', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('23', '', NULL, '', NULL, '', '', 1, '9b0dc44c4b5a408d8a95d3ac84cf861d', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('24', '', NULL, '', NULL, '', '', 1, '0d3ad1f3466d4c83aebed26501e09cf2', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('25', '', NULL, '', NULL, '', '', 1, '42d20d24a7d845cb8e869064008037fc', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('26', '', NULL, '', NULL, '', '', 1, '16372766ba414feda39d645cd31f81a1', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('26B515E6C75D4A9280B5B163B6EC3662', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-05-22 09:40:36', '', NULL, '1', '1', 1, '2A294F80EA0B46008FB894AF4F64A2AF', 'C67FAF6C12B0440DB105615A458A1E19');
INSERT INTO `tb_sys_login_role` VALUES ('26bb229ce21e464bb98a34ab4401ee41', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-30 18:00:42', '', NULL, '1', '1', 1, 'fabfe31ba38847a0adb0f589325c98ee', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('27', '', NULL, '', NULL, '', '', 1, 'c81ae49732bb49749484a6c7780775b0', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('28', '', NULL, '', NULL, '', '', 1, '0cb51b9c61a343eb82f8c9a883f4cd3f', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('29', '', NULL, '', NULL, '', '', 1, '9939c7d234d54ca48a22dc769ad81427', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('3', '', NULL, '', NULL, '', '', 1, 'd65a71634c504d18989bfb2df02121cb', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('30', '', NULL, '', NULL, '', '', 1, 'ab8a7ff689de4ea6aa4035bfd7bd5b17', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('31', '', NULL, '', NULL, '', '', 1, '54f2271a6bbc4ed5b159163da291dfec', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('32', '', NULL, '', NULL, '', '', 1, 'e3308461cc78400d8c6b3c08f83edf53', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('33', '', NULL, '', NULL, '', '', 1, 'daf66b0c96cc40a999fb1a4e30fbeff7', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('34', '', NULL, '', NULL, '', '', 1, 'e8f96f803ba942baa48cf0053d52ca78', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('34ec1ace5cb24f629c2f34a2a0ea8e67', 'admin', '2020-01-17 09:04:46', '', NULL, '1', '1', 1, '5edf646f652d4a9590bf5b0b514a8f32', '04ff00e0d52c4895beb6886ab86d15d2');
INSERT INTO `tb_sys_login_role` VALUES ('35', '', NULL, '', NULL, '', '', 1, '69a859b63a534741983be9e21c43945b', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('36', '', NULL, '', NULL, '', '', 1, '708ac5a5d1974ce89199c3451e54d9f2', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('37', '', NULL, '', NULL, '', '', 1, '1666e6805c204f5ba7e37bc6e82ee17f', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('37febb8ad181444e87df870b8c9c3139', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-31 10:07:54', '', NULL, '1', '1', 1, '2de0f422aad8462e831ece68974dd063', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('38', '', NULL, '', NULL, '', '', 1, '0512199b5fd3479d8cbc36c5490907e8', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('384eba8b09b04492b717d57e4d5ca080', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-02-18 16:12:35', '', NULL, '1', '1', 1, '12183eb14bf047b682c2e67444eb207d', 'bc03c9f429bf4a889d57a0699fb51019');
INSERT INTO `tb_sys_login_role` VALUES ('39', '', NULL, '', NULL, '', '', 1, '44369ad3aab24c78bce50c5ca3240e1e', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('3A798AE77A9B4B45A60FDEC66E7D3EB0', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-05-14 17:45:29', '', NULL, '1', '1', 1, 'E2888DDFA69547959F6DB1237FE740DA', 'E1082F2C029E49D283D41E066398E39C');
INSERT INTO `tb_sys_login_role` VALUES ('4', '', NULL, '', NULL, '', '', 1, '3acc4c3a8c114c55a0ffa793f24005ae', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('40', '', NULL, '', NULL, '', '', 1, '6e19ed9eef7e42a69a1449e5a44d2d72', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('41', '', NULL, '', NULL, '', '', 1, 'aee6e353dc024a39bf4dc493da83919a', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('41b59cb9423a499d9e058bc57bcdbdf1', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-30 13:45:06', '', NULL, '1', '1', 1, '487f714fd83543108acbec12b37d3b2e', 'C67FAF6C12B0440DB105615A458A1E19');
INSERT INTO `tb_sys_login_role` VALUES ('42', '', NULL, '', NULL, '', '', 1, 'fba3cc045cea4f5f938a709ca8e198af', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('43', '', NULL, '', NULL, '', '', 1, 'a1e9b5c831ad480586d65111d47924e6', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('44', '', NULL, '', NULL, '', '', 1, '61f005718a2142f8a93ca31f3b4796df', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('45', '', NULL, '', NULL, '', '', 1, 'f2e6461bffeb49e7bc82af54e87b24d0', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('46', '', NULL, '', NULL, '', '', 1, '45f99b608acc49efa351708c2c201adb', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('47', '', NULL, '', NULL, '', '', 1, 'cfe2c715c4854e38bf69767a68c469fe', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('47de899c8e464d009d4ef94abef5b333', 'admin', '2019-12-23 16:24:44', '', NULL, '1', '1', 1, 'dbd7cc31069f45e8ab6f73a3dc50379a', '04ff00e0d52c4895beb6886ab86d15d2');
INSERT INTO `tb_sys_login_role` VALUES ('48', '', NULL, '', NULL, '', '', 1, '377a1ee4f97e4549abb8383f00033a57', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('48fa86a2a84d49c893de74bae0ae42b9', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-30 18:01:44', '', NULL, '1', '1', 1, 'b9abeee349e847869a1c22f14ea8b4e5', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('49', '', NULL, '', NULL, '', '', 1, '095604a269004df1845be2cbf78cfab7', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('5', '', NULL, '', NULL, '', '', 1, '8eca81dad23241d1aa5d2ab0316e8aab', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('50', '', NULL, '', NULL, '', '', 1, '6ca97b5587a34e519061bef24c7af3ce', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('51', '', NULL, '', NULL, '', '', 1, '5957643e41da4c339de5950c12224f8f', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('52', '', NULL, '', NULL, '', '', 1, '5957643e41da4c339de5950c12224f8f', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('53', '', NULL, '', NULL, '', '', 1, '5957643e41da4c339de5950c12224f99', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('54', '', NULL, '', NULL, '', '', 1, 'ef63c28219174af3ae3a7b47c42f72c4', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('55', '', NULL, '', NULL, '', '', 1, '57ad78ba33494ba28b571c0c0a35f73a', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('56', '', NULL, '', NULL, '', '', 1, 'fbde06e386fc47cdbe51fbeda8d70069', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('57', '', NULL, '', NULL, '', '', 1, 'da5e8cccf3ab47debbfdc82bb4cb18f6', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('58', '', NULL, '', NULL, '', '', 1, 'ad6408012c6c4fe6ad321c92684ed66a', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('59', '', NULL, '', NULL, '', '', 1, '599df4b9d14c4cba84207a46dc8f62a7', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('5bccd74b9c5d4b01aa4bcc0b08827034', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-31 10:08:58', '', NULL, '1', '1', 1, '38788ed9fd664124b5ffd98e89924750', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('5da2234f6cc143c78fb04e67444aafa5', 'admin', '2020-01-06 09:12:16', '', NULL, '1', '1', 1, 'd5545bf8e30c47fb9230e32e6996c02b', '5083eb67223747ca86bcd431873813df');
INSERT INTO `tb_sys_login_role` VALUES ('5fe821ed3cf341fbaa7823aad9a79e73', 'admin', '2020-02-06 17:26:26', '', NULL, '1', '1', 1, 'b925a94f881e4d6b96dbe842f646b9fb', '92fb9ee318004c168c0b269f56965f80');
INSERT INTO `tb_sys_login_role` VALUES ('6', '', NULL, '', NULL, '', '', 1, 'b13128fdae9f4b9a983c656dc639235d', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('60', '', NULL, '', NULL, '', '', 1, '92ed07dab0dd4510a292814daafcf928', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('60b4890b9e234de9bd716f681cc3b5c4', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-30 15:51:44', '', NULL, '1', '1', 1, '7575efd330464b149184e1301af61055', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('61', '', NULL, '', NULL, '', '', 1, '593dda57a20944d2973c4c13a2b957e6', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('62', '', NULL, '', NULL, '', '', 1, '1362f8df9e8b4bc783c663031bed4ef8', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('623a031c718b4f1d92ca88f250ce11af', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-02-18 16:05:35', '', NULL, '1', '1', 1, '6558d0cb129f41349519daf9735e6180', '698ec741b7fb48399b3755e38115e75f');
INSERT INTO `tb_sys_login_role` VALUES ('62d358d5bc284560981fb17ae0e3ee7f', 'admin', '2019-12-26 11:24:41', '', NULL, '1', '1', 1, '35dab993dd8d4d4d8c74d1ce49760cba', '04ff00e0d52c4895beb6886ab86d15d2');
INSERT INTO `tb_sys_login_role` VALUES ('63', '', NULL, '', NULL, '', '', 1, '68006fb28bb342f8bb6adea61b7a4a56', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('64', '', NULL, '', NULL, '', '', 1, 'db49385c284e42b1aa1acb62035a10b8', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('65', '', NULL, '', NULL, '', '', 1, '52606f14b9714d72a52c66c28145ff6c', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('66', '', NULL, '', NULL, '', '', 1, 'bced2a186787488b9849305afb1c0ecd', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('661ab1f71c584aa09f70466c0751526f', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-30 18:05:02', '', NULL, '1', '1', 1, 'f91499a278f443f7beebbca1b26f81d7', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('67', '', NULL, '', NULL, '', '', 1, '550c876f083640389d9f0f09444a4960', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('68', '', NULL, '', NULL, '', '', 1, 'f707188bfcfd454aadf85c58c8bf2fc2', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('69', '', NULL, '', NULL, '', '', 1, 'fed646cd2b4344af8a55a682d1715047', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('7', '', NULL, '', NULL, '', '', 1, '3b83a77ae6af4ad2a161569ee59512ab', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('70', '', NULL, '', NULL, '', '', 1, 'c4087c94fb5241c4a0fb230a3e9fb87e', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('70921C2C76394BF9B2C23C324E162AD8', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-07-09 15:45:45', '', NULL, '1', '1', 1, '4350D8B0A5194B3DA151C5A7968B3788', 'A16379362DF84AC7AB0D14109C9C44A5');
INSERT INTO `tb_sys_login_role` VALUES ('71', '', NULL, '', NULL, '', '', 1, 'cd1352aae48b41cdb9dd5ab5315c7bfb', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('72', '', NULL, '', NULL, '', '', 1, 'bf57f5f9907f43039dd9f53334f88691', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('73', '', NULL, '', NULL, '', '', 1, '5810abedadae48a1ab43be6cd86384f6', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('73a4bd729c8e4249a514f8062868049c', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-31 10:09:49', '', NULL, '1', '1', 1, 'e3a3539f095a4e7883f31526409d30d0', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('74', '', NULL, '', NULL, '', '', 1, 'e6d856bad392486ba9096d5d2993187f', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('75', '', NULL, '', NULL, '', '', 1, 'fb21b617fd52405783b4bbaf0881b39d', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('76', '', NULL, '', NULL, '', '', 1, 'a6b4d9d719f94c549b1433f278873712', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('77', '', NULL, '', NULL, '', '', 1, '129ac00736904eeebbc9e3ac620eb04c', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('8', '', NULL, '', NULL, '', '', 1, 'add81b9dd32d4baababedfa056819e41', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('8cf021ca3cb64c819ff7756040d4ee29', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-31 10:08:31', '', NULL, '1', '1', 1, 'ef54981b839047b9a65bb25a92a84fc1', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('9', '', NULL, '', NULL, '', '', 1, '0bb3dd3ffe9f431dac0d46a859327468', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('9C0A9DBFD1BD4CE1A4BE509D88B2E397', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-07-09 15:45:42', '', NULL, '1', '1', 1, '3A90AFE9524447C3861BF4207F781AA3', 'A16379362DF84AC7AB0D14109C9C44A5');
INSERT INTO `tb_sys_login_role` VALUES ('a05e428462824d2b80fc9cea6104e831', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-01-10 16:39:29', '', NULL, '1', '1', 1, '4fe8709c15ef4cc6a96a8e366797bb4c', 'd8656a6e863a46be9179d45967e58d4d');
INSERT INTO `tb_sys_login_role` VALUES ('a11061e91696488c86563f4c53c7d0d8', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-31 10:24:13', '', NULL, '1', '1', 1, 'b575d930b4f84902bfe813786d50e296', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('a9b2a13cc35749cb9e559dd4da733330', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-31 10:23:38', '', NULL, '1', '1', 1, '349a56d37559463babd2ab15e9aac938', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('bb29fdb2409d42b29f249eb9fdb20b32', 'admin', '2020-01-14 14:54:37', '', NULL, '1', '1', 1, 'f24d3c5b2db4457680fc2c88282284eb', '5083eb67223747ca86bcd431873813df');
INSERT INTO `tb_sys_login_role` VALUES ('d2a72b7055484e93829d5f25b543fd54', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-31 10:08:22', '', NULL, '1', '1', 1, 'cbe8fba49fbf454eb60fa675880a4550', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('d80330ba2ee947af8d27e40895199917', 'admin', '2020-01-17 09:20:07', '', NULL, '1', '1', 1, 'f4392748065e4019becacd75763a373f', '04ff00e0d52c4895beb6886ab86d15d2');
INSERT INTO `tb_sys_login_role` VALUES ('e0d96d405d774fe49af5a850d1f4175f', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-30 18:04:32', '', NULL, '1', '1', 1, '6e41cc2003cf46228c95b14ab013f400', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('e0e8f7b137344540bfcf5a681b798814', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-31 10:08:36', '', NULL, '1', '1', 1, '6fb36d41fe5f497ea468dc1fd12b6bb9', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('e6e9a8c3784c4b2d94c30da54b09b214', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-30 18:04:03', '', NULL, '1', '1', 1, 'e9ddb1b4f6f942308f4284e94c048a79', 'bc694064464e40be903c4c2710f5fd72');
INSERT INTO `tb_sys_login_role` VALUES ('ec3550a6aa4b44d894ebeb1b051762a8', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-31 10:09:31', '', NULL, '1', '1', 1, '91decf8a05c94b68a6d367a40fb0266a', 'bc694064464e40be903c4c2710f5fd72');

-- ----------------------------
-- Table structure for tb_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_menu`;
CREATE TABLE `tb_sys_menu`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'status',
  `base_available` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'base_available',
  `base_sort` int(11) NULL DEFAULT 0 COMMENT 'base_sort',
  `menu_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '代码',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '名称',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'url地址',
  `menu_parent_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '父级id',
  `menu_level` int(11) NULL DEFAULT 0 COMMENT '级别',
  `menu_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述',
  `menu_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_sys_menu
-- ----------------------------
INSERT INTO `tb_sys_menu` VALUES ('14b7b024d4754d5b85fd2a62890dbfde', 'admin', '2020-02-28 22:50:36', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 22:51:12', '1', '1', 1, '', '新闻资讯', '/html/NewContent/NewContentList.html', 'b39a4f38bc844f8b9564d9ae9eb2a2e5', 1, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('188444f1052c426a9f63cf97ae776741', 'admin', '2020-02-28 10:03:37', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 10:03:47', '1', '1', 1, '', '首页管理', '', '无', 0, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('2433dbaa08684fb3aa7188c82b1ab5eb', 'admin', '2020-02-27 10:56:05', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-27 10:56:59', '1', '1', 1, '', '菜单管理', '/html/Menu/MenuList.html', '9d3e0018ae2b4d698f168cd7d5bc6a76', 1, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('338b7622cebf4a69b060fe669e1c59e6', 'admin', '2020-02-28 10:03:50', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 10:04:06', '1', '1', 1, '', 'Banner管理', '/html/Banner/BannerList.html', '188444f1052c426a9f63cf97ae776741', 1, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('38c912983ec14c9ea4d7310301db4d20', '4fe8709c15ef4cc6a96a8e366797bb4c', '2018-12-25 17:13:53', '4fe8709c15ef4cc6a96a8e366797bb4c', '2018-12-25 17:15:12', '1', '1', 1, '', '系统管理', '', '无', 0, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('54a622227046453a8061b9bc25ca419c', 'admin', '2020-02-20 14:28:49', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-20 14:29:29', '1', '1', 1, '', '账号管理', '/html/Account/AccountList.html', '8989deb6a80746918f4814830683c2f5', 1, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('66e2124dd3364ac68dc99baa20f8bbb5', '4fe8709c15ef4cc6a96a8e366797bb4c', '2018-11-25 17:20:20', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-06-05 09:10:08', '1', '1', 1, '', '角色管理', '/html/SysRole/SysRoleList.html', '38c912983ec14c9ea4d7310301db4d20', 1, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('718bc47fc0c44b45b41722fffb10a5c3', 'admin', '2020-02-28 23:21:43', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 23:22:11', '1', '1', 1, '', '关于我们', '/html/AboutUs/AboutUsList.html', 'b39a4f38bc844f8b9564d9ae9eb2a2e5', 1, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('79aa0eb58db44f9c805d8740841d2f29', 'admin', '2020-02-28 21:24:10', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 21:26:32', '1', '1', 1, '', '产品与服务', '/html/product/ProductList.html', 'b39a4f38bc844f8b9564d9ae9eb2a2e5', 1, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('8989deb6a80746918f4814830683c2f5', 'admin', '2020-02-20 14:28:29', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-20 14:28:47', '1', '1', 1, '', '账号管理', '', '无', 0, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('9d3e0018ae2b4d698f168cd7d5bc6a76', 'admin', '2020-02-27 10:53:27', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-27 10:56:10', '1', '1', 1, '', '导航栏菜单管理', '/html/Menu/MenuList.html', '无', 0, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('a03426f9ad254a90abcd1315764daa0f', 'admin', '2020-03-02 21:03:23', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 21:03:54', '1', '1', 1, '', '底部信息', '/html/Address/AddressList.html', 'b39a4f38bc844f8b9564d9ae9eb2a2e5', 1, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('b39a4f38bc844f8b9564d9ae9eb2a2e5', 'admin', '2020-02-28 15:28:41', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 21:20:55', '1', '1', 1, '', '文章管理', '', '无', 0, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('c6de3516ad4445d6abfe0b89e5bd3411', 'admin', '2020-02-28 23:09:13', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 23:09:54', '1', '1', 1, '', '党建工作', '/html/partyWork/PartWorkList.html', 'b39a4f38bc844f8b9564d9ae9eb2a2e5', 1, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('c83558e996b2474e99392f6f2a3d0181', 'admin', '2020-02-28 15:29:04', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 15:29:56', '1', '1', 1, '', '解决方案管理', '/html/Content/ContentList.html', 'b39a4f38bc844f8b9564d9ae9eb2a2e5', 1, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('D7A3AC9696C146DFBEB07641F9736931', '4fe8709c15ef4cc6a96a8e366797bb4c', '2018-11-08 16:40:05', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-06-04 14:05:41', '1', '1', 1, '', '注册用户管理', '/html/SysLogin/SysLoginList.html?user_type=2', '38c912983ec14c9ea4d7310301db4d20', 1, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('f19bdd922b62459a9089cb7240173f0a', 'admin', '2020-02-28 21:49:43', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 21:50:23', '1', '1', 1, '', '经典案例', '/html/classCase/CaseList.html', 'b39a4f38bc844f8b9564d9ae9eb2a2e5', 1, '', 'bg');
INSERT INTO `tb_sys_menu` VALUES ('f31d27f42316460a802f8c2b8c0b02a5', '4fe8709c15ef4cc6a96a8e366797bb4c', '2018-11-08 16:40:04', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-06-04 14:02:12', '1', '1', 1, '', '账户管理', '/html/SysLogin/SysLoginList.html', '38c912983ec14c9ea4d7310301db4d20', 1, '', 'bg');

-- ----------------------------
-- Table structure for tb_sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_operation_log`;
CREATE TABLE `tb_sys_operation_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'status',
  `base_available` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'base_available',
  `base_sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'base_sort',
  `sol_login_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录id',
  `sol_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_permission`;
CREATE TABLE `tb_sys_permission`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'status',
  `base_available` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'base_available',
  `base_sort` int(11) NULL DEFAULT 0 COMMENT 'base_sort',
  `permission_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '编码',
  `permission_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '名称',
  `permission_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'url',
  `permission_type` int(3) NULL DEFAULT 0 COMMENT '类型',
  `permission_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述',
  `permission_band_menu_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '绑定菜单的id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_sys_permission
-- ----------------------------
INSERT INTO `tb_sys_permission` VALUES ('0081af34e1ad491cb6f87cc15348e32d', 'admin', '2020-02-27 11:05:03', '', NULL, '1', '1', 1, 'PERMISSION_MENU_NEWNODE1', 'new node1', '', 2, '', '4c125671d6af49d2bcc95497bc79e634');
INSERT INTO `tb_sys_permission` VALUES ('166a07f41cee4729aab715e7c6e71f78', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-01-09 11:28:43', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-06-05 09:10:08', '1', '1', 1, 'PERMISSION_MENU_JSGL', '角色管理', '', 2, '', '66e2124dd3364ac68dc99baa20f8bbb5');
INSERT INTO `tb_sys_permission` VALUES ('2561c925d8cf4a1f9850d4c5f7e67735', 'admin', '2020-02-28 22:50:36', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 22:51:12', '1', '1', 1, 'PERMISSION_MENU_XWZX', '新闻资讯', '', 2, '', '14b7b024d4754d5b85fd2a62890dbfde');
INSERT INTO `tb_sys_permission` VALUES ('3023B243A81346F19C1D7C41C410DD77', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-03-19 14:21:21', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-06-04 14:05:41', '1', '1', 1, 'PERMISSION_MENU_ZCYHGL', '注册用户管理', '', 2, '', 'D7A3AC9696C146DFBEB07641F9736931');
INSERT INTO `tb_sys_permission` VALUES ('350eba072e3a4034a986a6d4e7a837a2', 'admin', '2020-02-28 23:21:43', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 23:22:11', '1', '1', 1, 'PERMISSION_MENU_GYWM', '关于我们', '', 2, '', '718bc47fc0c44b45b41722fffb10a5c3');
INSERT INTO `tb_sys_permission` VALUES ('419a1d5d1edd4aa0b72c4ec9a2486ca7', 'admin', '2020-02-28 10:03:37', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 10:03:47', '1', '1', 1, 'PERMISSION_MENU_SYGL', '首页管理', '', 2, '', '188444f1052c426a9f63cf97ae776741');
INSERT INTO `tb_sys_permission` VALUES ('52dd994441d7466798add739bf35b94a', 'admin', '2020-02-28 15:29:04', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 15:29:56', '1', '1', 1, 'PERMISSION_MENU_JJFAGL', '解决方案管理', '', 2, '', 'c83558e996b2474e99392f6f2a3d0181');
INSERT INTO `tb_sys_permission` VALUES ('7d6a461eb0924b97ba5f8706f1e535da', 'admin', '2020-03-02 21:03:23', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-03-02 21:03:54', '1', '1', 1, 'PERMISSION_MENU_DBXX', '底部信息', '', 2, '', 'a03426f9ad254a90abcd1315764daa0f');
INSERT INTO `tb_sys_permission` VALUES ('9a55ad5fa3dd40919d554f5b9299ee13', 'admin', '2020-02-28 15:28:41', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 21:20:55', '1', '1', 1, 'PERMISSION_MENU_WZGL', '文章管理', '', 2, '', 'b39a4f38bc844f8b9564d9ae9eb2a2e5');
INSERT INTO `tb_sys_permission` VALUES ('9f873a24dc7f47d18fa7c49f1c92b05f', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-01-09 11:28:43', '', NULL, '1', '1', 1, 'PERMISSION_MENU_XTGL', '系统管理', '', 2, '', '38c912983ec14c9ea4d7310301db4d20');
INSERT INTO `tb_sys_permission` VALUES ('a513e1981b2b424292a35b2009711644', 'admin', '2020-02-28 21:24:10', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 21:26:32', '1', '1', 1, 'PERMISSION_MENU_CPYFW', '产品与服务', '', 2, '', '79aa0eb58db44f9c805d8740841d2f29');
INSERT INTO `tb_sys_permission` VALUES ('af40d8b2327c4f45a8bdd809348314bb', 'admin', '2020-02-20 14:28:49', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-20 14:29:29', '1', '1', 1, 'PERMISSION_MENU_ZHGL', '账号管理', '', 2, '', '54a622227046453a8061b9bc25ca419c');
INSERT INTO `tb_sys_permission` VALUES ('b0b9a63ba2934c22bd45633497cb28b7', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-01-09 11:28:45', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-06-04 14:02:12', '1', '1', 1, 'PERMISSION_MENU_ZHGL', '账户管理', '', 2, '', 'f31d27f42316460a802f8c2b8c0b02a5');
INSERT INTO `tb_sys_permission` VALUES ('b5b9e122b4b747799c2d91f24459fdaf', 'admin', '2020-02-27 10:53:27', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-27 10:56:10', '1', '1', 1, 'PERMISSION_MENU_DHLCDGL', '导航栏菜单管理', '', 2, '', '9d3e0018ae2b4d698f168cd7d5bc6a76');
INSERT INTO `tb_sys_permission` VALUES ('b7d684647c944e9ba90fed285f9b301c', 'admin', '2020-02-28 10:03:50', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 10:04:06', '1', '1', 1, 'PERMISSION_MENU_BANNERGL', 'Banner管理', '', 2, '', '338b7622cebf4a69b060fe669e1c59e6');
INSERT INTO `tb_sys_permission` VALUES ('c0fb1f9de28c499fb2b40131427e3413', 'admin', '2020-02-27 10:56:05', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-27 10:56:59', '1', '1', 1, 'PERMISSION_MENU_CDGL', '菜单管理', '', 2, '', '2433dbaa08684fb3aa7188c82b1ab5eb');
INSERT INTO `tb_sys_permission` VALUES ('d8d4829ec8854eb79c38f8d662095a66', 'admin', '2020-02-28 21:49:43', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 21:50:23', '1', '1', 1, 'PERMISSION_MENU_JDAL', '经典案例', '', 2, '', 'f19bdd922b62459a9089cb7240173f0a');
INSERT INTO `tb_sys_permission` VALUES ('f4cd4daf680d44c29cdbc7c4fa68a0c4', 'admin', '2020-02-28 23:09:13', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-28 23:09:54', '1', '1', 1, 'PERMISSION_MENU_DJGZ', '党建工作', '', 2, '', 'c6de3516ad4445d6abfe0b89e5bd3411');
INSERT INTO `tb_sys_permission` VALUES ('f943556fca034fd58af96c72db02b200', 'admin', '2020-02-20 14:28:29', '4fe8709c15ef4cc6a96a8e366797bb4c', '2020-02-20 14:28:47', '1', '1', 1, 'PERMISSION_MENU_ZHGL', '账号管理', '', 2, '', '8989deb6a80746918f4814830683c2f5');

-- ----------------------------
-- Table structure for tb_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_role`;
CREATE TABLE `tb_sys_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'status',
  `base_available` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'base_available',
  `base_sort` int(11) NULL DEFAULT 0 COMMENT 'base_sort',
  `role_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '编码',
  `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '名称',
  `role_type` int(3) NULL DEFAULT 0 COMMENT '类型',
  `role_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述',
  `role_band_menu_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '绑定菜单的id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_sys_role
-- ----------------------------
INSERT INTO `tb_sys_role` VALUES ('04ff00e0d52c4895beb6886ab86d15d2', 'admin', '2019-12-23 16:24:31', '', NULL, '1', '1', 1, 'test', '村级管理', 0, '', '');
INSERT INTO `tb_sys_role` VALUES ('5083eb67223747ca86bcd431873813df', 'admin', '2020-01-06 09:11:15', '', NULL, '1', '1', 1, 'reportAdmin', '监督举报', 0, '监督举报管理员', '');
INSERT INTO `tb_sys_role` VALUES ('92fb9ee318004c168c0b269f56965f80', 'admin', '2020-02-06 17:26:06', '', NULL, '1', '1', 1, '0001', '金融系统管理员', 0, '', '');
INSERT INTO `tb_sys_role` VALUES ('d8656a6e863a46be9179d45967e58d4d', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-01-04 15:32:59', '', NULL, '1', '1', 1, 'admin', '管理员', 0, '', NULL);

-- ----------------------------
-- Table structure for tb_sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_role_permission`;
CREATE TABLE `tb_sys_role_permission`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'status',
  `base_available` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'base_available',
  `base_sort` int(11) NULL DEFAULT 0 COMMENT 'base_sort',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色id',
  `permission_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_sys_role_permission
-- ----------------------------
INSERT INTO `tb_sys_role_permission` VALUES ('04291f01958e4a46b1e2128289d22b3e', 'admin', '2020-02-28 10:03:50', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', 'b7d684647c944e9ba90fed285f9b301c');
INSERT INTO `tb_sys_role_permission` VALUES ('0984a8b2403f4f70aad197677c364376', 'admin', '2020-02-28 10:03:37', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', '419a1d5d1edd4aa0b72c4ec9a2486ca7');
INSERT INTO `tb_sys_role_permission` VALUES ('0df4e6ed8dfe470282cfd835ea535c73', 'admin', '2020-02-28 22:50:36', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', '2561c925d8cf4a1f9850d4c5f7e67735');
INSERT INTO `tb_sys_role_permission` VALUES ('15a7d2388f48410bbd7cc7984485a923', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-17 10:56:32', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', '9f873a24dc7f47d18fa7c49f1c92b05f');
INSERT INTO `tb_sys_role_permission` VALUES ('179bc93fceaa45718caa26605497e5ab', 'admin', '2020-03-02 21:03:23', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', '7d6a461eb0924b97ba5f8706f1e535da');
INSERT INTO `tb_sys_role_permission` VALUES ('26533900183b4a2184ab1292c7b00a50', 'admin', '2020-02-27 11:05:03', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', '0081af34e1ad491cb6f87cc15348e32d');
INSERT INTO `tb_sys_role_permission` VALUES ('3401a75d3a8742eea85956d66b3b1d84', 'admin', '2020-02-28 21:49:43', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', 'd8d4829ec8854eb79c38f8d662095a66');
INSERT INTO `tb_sys_role_permission` VALUES ('38341c27a2b74801b3343d4bcec1c93a', 'admin', '2020-02-20 14:28:49', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', 'af40d8b2327c4f45a8bdd809348314bb');
INSERT INTO `tb_sys_role_permission` VALUES ('41afe34a3ee14a6fbdceee3bd332d47f', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-17 10:56:31', '', NULL, '1', '1', 1, '79ada7185c504f968f4ebf9d828372b4', '3023B243A81346F19C1D7C41C410DD77');
INSERT INTO `tb_sys_role_permission` VALUES ('5bab2352348a4d718b253875beb09ebb', 'admin', '2020-02-28 15:28:41', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', '9a55ad5fa3dd40919d554f5b9299ee13');
INSERT INTO `tb_sys_role_permission` VALUES ('61c6237d60574d5a9f5c5e46749bb887', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-17 10:56:31', '', NULL, '1', '1', 1, '79ada7185c504f968f4ebf9d828372b4', 'b0b9a63ba2934c22bd45633497cb28b7');
INSERT INTO `tb_sys_role_permission` VALUES ('64d8f62e416b48feaf493157331afd9e', 'admin', '2020-02-27 10:56:05', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', 'c0fb1f9de28c499fb2b40131427e3413');
INSERT INTO `tb_sys_role_permission` VALUES ('70524808dd994adea104aef6b6cf47e2', 'admin', '2020-02-28 15:29:04', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', '52dd994441d7466798add739bf35b94a');
INSERT INTO `tb_sys_role_permission` VALUES ('7745de2f166d4381be00362bbbb51e7a', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-10-17 10:56:31', '', NULL, '1', '1', 1, '79ada7185c504f968f4ebf9d828372b4', '166a07f41cee4729aab715e7c6e71f78');
INSERT INTO `tb_sys_role_permission` VALUES ('830c029a662c40e6a9086d93d217655a', 'admin', '2020-02-20 14:28:29', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', 'f943556fca034fd58af96c72db02b200');
INSERT INTO `tb_sys_role_permission` VALUES ('9e6941f7b8dc4044b5811bbc0a859a86', 'admin', '2020-02-27 10:53:28', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', 'b5b9e122b4b747799c2d91f24459fdaf');
INSERT INTO `tb_sys_role_permission` VALUES ('a44a3b2423b748c78a861f7b9afba637', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-12-20 10:50:03', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', 'b0b9a63ba2934c22bd45633497cb28b7');
INSERT INTO `tb_sys_role_permission` VALUES ('a47625a0aa6047ffb56283b3c63616c0', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-12-20 10:50:03', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', '3023B243A81346F19C1D7C41C410DD77');
INSERT INTO `tb_sys_role_permission` VALUES ('A59CDC582EE54305B8449313BF00620E', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-05-14 17:44:49', '', NULL, '1', '1', 1, 'E1082F2C029E49D283D41E066398E39C', '9f873a24dc7f47d18fa7c49f1c92b05f');
INSERT INTO `tb_sys_role_permission` VALUES ('a59e0e66dcc3450e921714d2e3d42c54', 'admin', '2020-02-28 23:09:13', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', 'f4cd4daf680d44c29cdbc7c4fa68a0c4');
INSERT INTO `tb_sys_role_permission` VALUES ('dda7ca80877342d0b57bd721c06a76f1', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-12-20 10:50:04', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', '166a07f41cee4729aab715e7c6e71f78');
INSERT INTO `tb_sys_role_permission` VALUES ('de6915e9242a4b629cfe70bda36e3354', 'admin', '2020-02-28 21:24:10', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', 'a513e1981b2b424292a35b2009711644');
INSERT INTO `tb_sys_role_permission` VALUES ('ea448e11b3ea468d921af486497b0e2d', 'admin', '2020-02-28 23:21:43', '', NULL, '1', '1', 1, 'd8656a6e863a46be9179d45967e58d4d', '350eba072e3a4034a986a6d4e7a837a2');

-- ----------------------------
-- Table structure for tb_sys_sms_code
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_sms_code`;
CREATE TABLE `tb_sys_sms_code`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'status',
  `base_available` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'base_available',
  `base_sort` int(11) NULL DEFAULT 0 COMMENT 'base_sort',
  `ssc_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号',
  `ssl_code` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '验证码',
  `ssl_out_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_sys_sms_code
-- ----------------------------
INSERT INTO `tb_sys_sms_code` VALUES ('000C509C175E44BEBD9BAC68DDBB6C04', 'sys', '2019-07-02 21:09:35', '', NULL, '1', '1', 1, '18601943273', '739564', '2019-07-02 21:14:35');
INSERT INTO `tb_sys_sms_code` VALUES ('00460E91BC5D4263BC4C30031F9EA09A', 'sys', '2019-06-04 11:59:09', '', NULL, '1', '1', 1, '18710002331', '477878', '2019-06-04 12:04:09');
INSERT INTO `tb_sys_sms_code` VALUES ('00b797c3b332472a8dc339e3e7210377', 'sys', '2019-09-10 15:40:31', '', NULL, '1', '1', 1, '13911301227', '284714', '2019-09-10 15:45:32');
INSERT INTO `tb_sys_sms_code` VALUES ('00d253e5212c411592b1b451ee320732', 'sys', '2019-09-02 16:16:57', '', NULL, '1', '1', 1, '13381459998', '119921', '2019-09-02 16:21:57');
INSERT INTO `tb_sys_sms_code` VALUES ('00D3118E0EA045CDB9107033A18F0A43', 'sys', '2019-07-05 10:00:37', '', NULL, '1', '1', 1, '18703613965', '576379', '2019-07-05 10:05:38');
INSERT INTO `tb_sys_sms_code` VALUES ('0159CA62B8A54D49B44088F399631660', 'sys', '2019-05-17 15:21:49', '', NULL, '1', '1', 1, '18601943273', '774542', '2019-05-17 15:26:49');
INSERT INTO `tb_sys_sms_code` VALUES ('01772510a9974e9196770e0f90c23eab', 'sys', '2019-09-25 14:47:16', '', NULL, '1', '1', 1, '13601309746', '867987', '2019-09-25 14:52:17');
INSERT INTO `tb_sys_sms_code` VALUES ('018B168F10EC47A2A98F7703C73319DD', 'sys', '2019-06-11 17:06:40', '', NULL, '1', '1', 1, '15910234107', '937645', '2019-06-11 17:11:40');
INSERT INTO `tb_sys_sms_code` VALUES ('01d6472337e14f73861c6bc628841d4e', 'sys', '2019-09-20 19:53:06', '', NULL, '1', '1', 1, '13269078803', '727236', '2019-09-20 19:58:07');
INSERT INTO `tb_sys_sms_code` VALUES ('02AEC0AB2511410DB74EFA8837F55BA6', 'sys', '2019-06-24 09:31:28', '', NULL, '1', '1', 1, '13801086442', '481587', '2019-06-24 09:36:29');
INSERT INTO `tb_sys_sms_code` VALUES ('03044B9A82E94409B1027BF71B5A01CE', 'sys', '2019-05-31 14:56:00', '', NULL, '1', '1', 1, '18710002331', '758229', '2019-05-31 15:01:00');
INSERT INTO `tb_sys_sms_code` VALUES ('0383B334EC354590B001757E86AEE6B9', 'sys', '2019-07-15 14:53:21', '', NULL, '1', '1', 1, '18518302011', '683758', '2019-07-15 14:58:22');
INSERT INTO `tb_sys_sms_code` VALUES ('03BB1180B10A412D8C9E07302641BD20', 'sys', '2019-05-17 13:54:35', '', NULL, '1', '1', 1, '18601943273', '955189', '2019-05-17 13:59:36');
INSERT INTO `tb_sys_sms_code` VALUES ('04b237a22af640939469eadf168846b9', 'sys', '2019-08-28 19:11:10', '', NULL, '1', '1', 1, '15010301773', '227144', '2019-08-28 19:16:10');
INSERT INTO `tb_sys_sms_code` VALUES ('04F30355F73A4EBEAE722CB3A6D5A921', 'sys', '2019-07-13 16:32:49', '', NULL, '1', '1', 1, '15910234107', '615551', '2019-07-13 16:37:49');
INSERT INTO `tb_sys_sms_code` VALUES ('05AEFBFCC590452B935F623B654BE027', 'sys', '2019-06-11 17:02:53', '', NULL, '1', '1', 1, '15910234107', '213292', '2019-06-11 17:07:54');
INSERT INTO `tb_sys_sms_code` VALUES ('06CA994ED382481EA5C6BE30B14AA398', 'sys', '2019-07-09 15:08:00', '', NULL, '1', '1', 1, '18600977360', '573272', '2019-07-09 15:13:01');
INSERT INTO `tb_sys_sms_code` VALUES ('07F14436BCCC413C9A0DE7C7F9CC433D', 'sys', '2019-07-04 17:58:01', '', NULL, '1', '1', 1, '18703613965', '851378', '2019-07-04 18:03:02');
INSERT INTO `tb_sys_sms_code` VALUES ('0900144527CD419D8189D2C9066EE26B', 'sys', '2019-07-09 17:52:05', '', NULL, '1', '1', 1, '18703613965', '941985', '2019-07-09 17:57:05');
INSERT INTO `tb_sys_sms_code` VALUES ('0A581D4EA9A24BBCA18000048BB6E182', 'sys', '2019-07-02 21:22:06', '', NULL, '1', '1', 1, '17398982323', '876521', '2019-07-02 21:27:06');
INSERT INTO `tb_sys_sms_code` VALUES ('0BBC251A3608486DBBCD557CD8C8E958', 'sys', '2019-05-30 16:06:15', '', NULL, '1', '1', 1, '18710096183', '296854', '2019-05-30 16:11:15');
INSERT INTO `tb_sys_sms_code` VALUES ('0c9833dccdf144b0baadbdcf1af8d981', 'sys', '2019-09-04 10:22:46', '', NULL, '1', '1', 1, '18600697765', '416433', '2019-09-04 10:27:47');
INSERT INTO `tb_sys_sms_code` VALUES ('0cfe04589ea44205b9c148f96c76b037', 'sys', '2019-08-31 19:52:14', '', NULL, '1', '1', 1, '13661263818', '166187', '2019-08-31 19:57:15');
INSERT INTO `tb_sys_sms_code` VALUES ('0d133507a58c4ba593c939d398504fad', 'sys', '2019-09-28 20:20:12', '', NULL, '1', '1', 1, '17611780127', '374692', '2019-09-28 20:25:12');
INSERT INTO `tb_sys_sms_code` VALUES ('0da35bd2fb124013aeaecfaab8c4f208', 'sys', '2019-08-28 16:50:22', '', NULL, '1', '1', 1, '13770356178', '788814', '2019-08-28 16:55:23');
INSERT INTO `tb_sys_sms_code` VALUES ('0EE186275A3A47B4B67E33DE519AAC6A', 'sys', '2019-05-30 13:09:45', '', NULL, '1', '1', 1, '17638101635', '783411', '2019-05-30 13:14:45');
INSERT INTO `tb_sys_sms_code` VALUES ('0ee4a4b2a51a46db84d85e694f340623', 'sys', '2019-09-17 20:47:25', '', NULL, '1', '1', 1, '13911531628', '954769', '2019-09-17 20:52:26');
INSERT INTO `tb_sys_sms_code` VALUES ('0F1CE11DD39146BAB109FD3490ECE5C2', 'sys', '2019-07-04 12:51:17', '', NULL, '1', '1', 1, '18710096183', '886921', '2019-07-04 12:56:17');
INSERT INTO `tb_sys_sms_code` VALUES ('0f5524415f8d4560a8926da4252c79f5', 'sys', '2019-08-28 17:46:11', '', NULL, '1', '1', 1, '18811268260', '182738', '2019-08-28 17:51:11');
INSERT INTO `tb_sys_sms_code` VALUES ('0FA743C05A674E5D95736693DDDB4311', 'sys', '2019-07-09 17:39:16', '', NULL, '1', '1', 1, '18703613965', '526516', '2019-07-09 17:44:17');
INSERT INTO `tb_sys_sms_code` VALUES ('1047F28457254044A2DACF93EBB25FC2', 'sys', '2019-08-21 21:31:48', '', NULL, '1', '1', 1, '13241862233', '387685', '2019-08-21 21:36:49');
INSERT INTO `tb_sys_sms_code` VALUES ('105d39307526429da14b76a01b0b69fb', 'sys', '2019-08-28 19:48:36', '', NULL, '1', '1', 1, '18701313913', '892496', '2019-08-28 19:53:37');
INSERT INTO `tb_sys_sms_code` VALUES ('10BAA45FF28A4570B7A9F7E4B3C97F64', 'sys', '2019-05-23 09:58:38', '', NULL, '1', '1', 1, '15910234107', '328931', '2019-05-23 10:03:39');
INSERT INTO `tb_sys_sms_code` VALUES ('110083a7f5d445abaf88a4e904256a61', 'sys', '2019-09-16 08:30:23', '', NULL, '1', '1', 1, '15010177507', '577892', '2019-09-16 08:35:24');
INSERT INTO `tb_sys_sms_code` VALUES ('112CA293676D4C8BAC528608E87FBE2C', 'sys', '2019-08-12 15:43:54', '', NULL, '1', '1', 1, '18810297092', '653282', '2019-08-12 15:48:54');
INSERT INTO `tb_sys_sms_code` VALUES ('1132FF7BBE854A64828BF312D08E1C30', 'sys', '2019-05-28 10:24:21', '', NULL, '1', '1', 1, '13581881826', '383754', '2019-05-28 10:29:22');
INSERT INTO `tb_sys_sms_code` VALUES ('11d3b45c65344a7080feafa2949a3bfa', 'sys', '2019-08-29 16:02:19', '', NULL, '1', '1', 1, '15210720590', '172851', '2019-08-29 16:07:19');
INSERT INTO `tb_sys_sms_code` VALUES ('11df1590178e42468b5872ce5c8cff97', 'sys', '2019-10-12 12:24:33', '', NULL, '1', '1', 1, '17844603005', '835356', '2019-10-12 12:29:33');
INSERT INTO `tb_sys_sms_code` VALUES ('1245AD7942DA4D759CC33BF9D065442B', 'sys', '2019-05-31 14:01:23', '', NULL, '1', '1', 1, '15910234107', '511759', '2019-05-31 14:06:23');
INSERT INTO `tb_sys_sms_code` VALUES ('12857BB4C63A48D5B717CC35BBAE3E1E', 'sys', '2019-05-31 13:47:45', '', NULL, '1', '1', 1, '18519785936', '397438', '2019-05-31 13:52:46');
INSERT INTO `tb_sys_sms_code` VALUES ('136DBB1D1F2A41F99818DBD2C2BED7CC', 'sys', '2019-05-31 15:23:26', '', NULL, '1', '1', 1, '18710002331', '672311', '2019-05-31 15:28:27');
INSERT INTO `tb_sys_sms_code` VALUES ('13F007E407154B5BBA2F52DF6201F7B1', 'sys', '2019-07-05 10:49:53', '', NULL, '1', '1', 1, '17398982323', '984882', '2019-07-05 10:54:53');
INSERT INTO `tb_sys_sms_code` VALUES ('148A23D5CC904A838AC40C9A6ADA40D5', 'sys', '2019-07-02 08:32:11', '', NULL, '1', '1', 1, '13911301227', '128544', '2019-07-02 08:37:12');
INSERT INTO `tb_sys_sms_code` VALUES ('148b80c2f32e4d42957cedfef70461f9', 'sys', '2019-08-29 05:44:28', '', NULL, '1', '1', 1, '13911836364', '832767', '2019-08-29 05:49:29');
INSERT INTO `tb_sys_sms_code` VALUES ('14A2CA9C1EEC4B9898D4C9DC13C5DBC5', 'sys', '2019-06-12 10:31:09', '', NULL, '1', '1', 1, '18519785936', '198439', '2019-06-12 10:36:09');
INSERT INTO `tb_sys_sms_code` VALUES ('14dc87804e374b5c8ba7c06ad3f4ffde', 'sys', '2019-09-02 14:59:15', '', NULL, '1', '1', 1, '13269199776', '738432', '2019-09-02 15:04:16');
INSERT INTO `tb_sys_sms_code` VALUES ('14EA2880578043B5BF6101F93685097A', 'sys', '2019-06-12 10:28:57', '', NULL, '1', '1', 1, '18719785936', '893816', '2019-06-12 10:33:58');
INSERT INTO `tb_sys_sms_code` VALUES ('152febd6b59942b796a8525ef2707a2e', 'sys', '2019-08-29 05:58:08', '', NULL, '1', '1', 1, '13911165737', '656156', '2019-08-29 06:03:08');
INSERT INTO `tb_sys_sms_code` VALUES ('16795789D7004AF38D9BF1E57E269BD7', 'sys', '2019-08-05 09:00:46', '', NULL, '1', '1', 1, '15011291817', '215146', '2019-08-05 09:05:46');
INSERT INTO `tb_sys_sms_code` VALUES ('167FE962BF8E473BB8BB670105E3A586', 'sys', '2019-08-14 14:08:58', '', NULL, '1', '1', 1, '15201238455', '374548', '2019-08-14 14:13:58');
INSERT INTO `tb_sys_sms_code` VALUES ('169C53A3A97C4F2C93164C3AA1D517C7', 'sys', '2019-07-15 10:01:35', '', NULL, '1', '1', 1, '15910234107', '864263', '2019-07-15 10:06:35');
INSERT INTO `tb_sys_sms_code` VALUES ('1726a8bc44614ec1ad54a85deb2d8271', 'sys', '2019-08-29 08:52:25', '', NULL, '1', '1', 1, '13718467368', '641243', '2019-08-29 08:57:26');
INSERT INTO `tb_sys_sms_code` VALUES ('177335E0500D4CDEAF90AE0BAB3ECDA7', 'sys', '2019-06-11 16:01:47', '', NULL, '1', '1', 1, '13581881856', '558233', '2019-06-11 16:06:47');
INSERT INTO `tb_sys_sms_code` VALUES ('17f52a4408044d9a90bf4ebb8ea22573', 'sys', '2019-09-10 19:03:53', '', NULL, '1', '1', 1, '18601236177', '573455', '2019-09-10 19:08:54');
INSERT INTO `tb_sys_sms_code` VALUES ('1810de6e351d40419f6ec062f7fc3730', 'sys', '2019-08-28 19:11:49', '', NULL, '1', '1', 1, '15010301773', '939414', '2019-08-28 19:16:49');
INSERT INTO `tb_sys_sms_code` VALUES ('1823ED927BC04132B1A7FAC43CF8FEDA', 'sys', '2019-06-04 11:34:27', '', NULL, '1', '1', 1, '18710002331', '517889', '2019-06-04 11:39:27');
INSERT INTO `tb_sys_sms_code` VALUES ('186358B7D1BC488B8AA4A2ED85FE72BB', 'sys', '2019-07-02 21:19:35', '', NULL, '1', '1', 1, '13811080888', '864923', '2019-07-02 21:24:36');
INSERT INTO `tb_sys_sms_code` VALUES ('190ED2F956B044499FE899A216E38E72', 'sys', '2019-08-09 18:40:00', '', NULL, '1', '1', 1, '13311583163', '911126', '2019-08-09 18:45:01');
INSERT INTO `tb_sys_sms_code` VALUES ('19A2066A48C94E10B54318C50FE1F04E', 'sys', '2019-07-10 15:32:41', '', NULL, '1', '1', 1, '13801086442', '383745', '2019-07-10 15:37:41');
INSERT INTO `tb_sys_sms_code` VALUES ('19E35F3D14B34FA785FA652A5F6A1E04', 'sys', '2019-08-20 09:47:44', '', NULL, '1', '1', 1, '13801086442', '754664', '2019-08-20 09:52:44');
INSERT INTO `tb_sys_sms_code` VALUES ('1B27880CD9F44FF8865C7AA5248CD863', 'sys', '2019-07-11 21:09:05', '', NULL, '1', '1', 1, '13910590269', '325349', '2019-07-11 21:14:06');
INSERT INTO `tb_sys_sms_code` VALUES ('1b57fa95a9864f9c8ce2773ebd539f5d', 'sys', '2019-09-16 15:11:53', '', NULL, '1', '1', 1, '15101147149', '439887', '2019-09-16 15:16:53');
INSERT INTO `tb_sys_sms_code` VALUES ('1C1D81DE532A4F1D969AF9EA15DF615B', 'sys', '2019-07-02 22:34:35', '', NULL, '1', '1', 1, '18601943273', '783714', '2019-07-02 22:39:35');
INSERT INTO `tb_sys_sms_code` VALUES ('1c6dfebccca249748cfe215f3a0600b6', 'sys', '2019-09-30 17:46:58', '', NULL, '1', '1', 1, '13811971245', '123378', '2019-09-30 17:51:59');
INSERT INTO `tb_sys_sms_code` VALUES ('1CB7921AAD0F42B79FC22196FB7BACD5', 'sys', '2019-08-02 15:10:21', '', NULL, '1', '1', 1, '18551780142', '628778', '2019-08-02 15:15:22');
INSERT INTO `tb_sys_sms_code` VALUES ('1D42ABDD0B1B4B1AA24647E25395D9F5', 'sys', '2019-07-11 17:56:58', '', NULL, '1', '1', 1, '18703613965', '893385', '2019-07-11 18:01:58');
INSERT INTO `tb_sys_sms_code` VALUES ('1e185dec8cb14c3aad161cc78051f7e1', 'sys', '2019-08-30 10:26:12', '', NULL, '1', '1', 1, '13581680803', '124914', '2019-08-30 10:31:12');
INSERT INTO `tb_sys_sms_code` VALUES ('1E4F04300B96442FB430D8D5798E1EFE', 'sys', '2019-07-04 19:10:24', '', NULL, '1', '1', 1, '15736753571', '675286', '2019-07-04 19:15:25');
INSERT INTO `tb_sys_sms_code` VALUES ('1e62157b396b48be8e393cb7110f208e', 'sys', '2019-08-28 21:53:50', '', NULL, '1', '1', 1, '15801596921', '566426', '2019-08-28 21:58:50');
INSERT INTO `tb_sys_sms_code` VALUES ('1e72def7911e4bcf80468ac1782338ee', 'sys', '2019-09-29 19:46:57', '', NULL, '1', '1', 1, '13311371708', '793949', '2019-09-29 19:51:57');
INSERT INTO `tb_sys_sms_code` VALUES ('1ec4bd77e41543cc88d04c6c9a06854f', 'sys', '2019-09-17 18:50:25', '', NULL, '1', '1', 1, '13701308778', '868925', '2019-09-17 18:55:26');
INSERT INTO `tb_sys_sms_code` VALUES ('1fbc4b3a833f4a9aa2ae06bde513bb6c', 'sys', '2019-09-16 20:50:59', '', NULL, '1', '1', 1, '15901596871', '823594', '2019-09-16 20:56:00');
INSERT INTO `tb_sys_sms_code` VALUES ('1FC9A14A52DB4586A21498CDAFA03605', 'sys', '2019-07-16 17:58:26', '', NULL, '1', '1', 1, '15117978092', '994916', '2019-07-16 18:03:26');
INSERT INTO `tb_sys_sms_code` VALUES ('207f77b3294c42a5909113077591194e', 'sys', '2019-10-02 18:15:05', '', NULL, '1', '1', 1, '13910956423', '297271', '2019-10-02 18:20:05');
INSERT INTO `tb_sys_sms_code` VALUES ('20865487b28141a4a8a3665d3edca4de', 'sys', '2019-08-28 19:49:58', '', NULL, '1', '1', 1, '13810671357', '199742', '2019-08-28 19:54:58');
INSERT INTO `tb_sys_sms_code` VALUES ('20A8C809674949059F8320368205D7A9', 'sys', '2019-06-12 09:44:49', '', NULL, '1', '1', 1, '18710096183', '428349', '2019-06-12 09:49:49');
INSERT INTO `tb_sys_sms_code` VALUES ('20b6257058874fb081018d2a2d08aae8', 'sys', '2019-08-28 20:01:16', '', NULL, '1', '1', 1, '15110091527', '259998', '2019-08-28 20:06:17');
INSERT INTO `tb_sys_sms_code` VALUES ('20BD8D05C2D34D5B9D392857C594720B', 'sys', '2019-07-09 17:45:22', '', NULL, '1', '1', 1, '18703613965', '885366', '2019-07-09 17:50:22');
INSERT INTO `tb_sys_sms_code` VALUES ('20C1A40815654E3A950C22C624D20AD9', 'sys', '2019-07-02 22:08:23', '', NULL, '1', '1', 1, '18601943273', '571464', '2019-07-02 22:13:23');
INSERT INTO `tb_sys_sms_code` VALUES ('2121251C801D49C9B51AAA06FB92A33C', 'sys', '2019-08-12 15:50:35', '', NULL, '1', '1', 1, '18610728220', '799618', '2019-08-12 15:55:36');
INSERT INTO `tb_sys_sms_code` VALUES ('212835CA823A40EEB5AB2021CE5657E6', 'sys', '2019-07-02 20:52:15', '', NULL, '1', '1', 1, '13581881826', '724987', '2019-07-02 20:57:15');
INSERT INTO `tb_sys_sms_code` VALUES ('217c8868df7e4ffe9d506a5616d102f6', 'sys', '2019-10-02 13:10:47', '', NULL, '1', '1', 1, '15901500936', '797748', '2019-10-02 13:15:47');
INSERT INTO `tb_sys_sms_code` VALUES ('219C2775EA8E465884FAF271E2D6E0B8', 'sys', '2019-07-02 15:11:49', '', NULL, '1', '1', 1, '13801086442', '597725', '2019-07-02 15:16:50');
INSERT INTO `tb_sys_sms_code` VALUES ('2248F48DD0D5490483DF93CA5923D46F', 'sys', '2019-05-31 14:34:04', '', NULL, '1', '1', 1, '18710002331', '323255', '2019-05-31 14:39:04');
INSERT INTO `tb_sys_sms_code` VALUES ('229FDDC7CCD0408CAEC0D7E260CFC664', 'sys', '2019-07-10 07:16:54', '', NULL, '1', '1', 1, '17638101635', '787475', '2019-07-10 07:21:54');
INSERT INTO `tb_sys_sms_code` VALUES ('22BFBEE27E004E15B2CE23A0A894ED25', 'sys', '2019-07-02 20:43:43', '', NULL, '1', '1', 1, '13911301227', '169831', '2019-07-02 20:48:43');
INSERT INTO `tb_sys_sms_code` VALUES ('22C7355D5A2A4F23A892B56A80B322BD', 'sys', '2019-07-05 16:47:43', '', NULL, '1', '1', 1, '17398982323', '128744', '2019-07-05 16:52:44');
INSERT INTO `tb_sys_sms_code` VALUES ('23123496254d4350865a1189a5e5bb44', 'sys', '2019-08-28 23:22:25', '', NULL, '1', '1', 1, '13901212095', '191121', '2019-08-28 23:27:26');
INSERT INTO `tb_sys_sms_code` VALUES ('231ED1C5C79542CD8C19B246B12B3C07', 'sys', '2019-07-15 08:46:36', '', NULL, '1', '1', 1, '13683387578', '963378', '2019-07-15 08:51:36');
INSERT INTO `tb_sys_sms_code` VALUES ('238c1f6d828d407e8dcc45b7c780389d', 'sys', '2019-10-13 18:45:07', '', NULL, '1', '1', 1, '13311288603', '792813', '2019-10-13 18:50:07');
INSERT INTO `tb_sys_sms_code` VALUES ('24673679688f4b8482cfb0429b9d6158', 'sys', '2019-08-28 21:15:15', '', NULL, '1', '1', 1, '13811617827', '447857', '2019-08-28 21:20:16');
INSERT INTO `tb_sys_sms_code` VALUES ('247ab95aabd243018225dbc80126b3f5', 'sys', '2019-09-23 17:29:14', '', NULL, '1', '1', 1, '13520460747', '724147', '2019-09-23 17:34:15');
INSERT INTO `tb_sys_sms_code` VALUES ('24D13196927E4F65970F796F8ADC3CF0', 'sys', '2019-08-12 16:14:34', '', NULL, '1', '1', 1, '13716375591', '785893', '2019-08-12 16:19:35');
INSERT INTO `tb_sys_sms_code` VALUES ('26cf742f58a24ce28ce9aad231e1e428', 'sys', '2019-08-28 18:06:10', '', NULL, '1', '1', 1, '13552833485', '239167', '2019-08-28 18:11:10');
INSERT INTO `tb_sys_sms_code` VALUES ('27A19C6B56604A00BE8E4152E0AB17F4', 'sys', '2019-07-02 08:47:39', '', NULL, '1', '1', 1, '13911301227', '457831', '2019-07-02 08:52:40');
INSERT INTO `tb_sys_sms_code` VALUES ('281B3E8576A74466A6BD19D5D662B4BC', 'sys', '2019-07-05 20:00:06', '', NULL, '1', '1', 1, '15223509375', '226921', '2019-07-05 20:05:07');
INSERT INTO `tb_sys_sms_code` VALUES ('2897beea70b546ca80f8fd06559399f4', 'sys', '2019-09-29 15:19:58', '', NULL, '1', '1', 1, '13522979891', '311725', '2019-09-29 15:24:59');
INSERT INTO `tb_sys_sms_code` VALUES ('28FFFB150F5042D3B233376A12F1CC8A', 'sys', '2019-07-24 10:37:19', '', NULL, '1', '1', 1, '13522555152', '265498', '2019-07-24 10:42:19');
INSERT INTO `tb_sys_sms_code` VALUES ('2903F2C6D5944FBDBD359B39DE00F82F', 'sys', '2019-07-02 22:09:59', '', NULL, '1', '1', 1, '17638101635', '916682', '2019-07-02 22:15:00');
INSERT INTO `tb_sys_sms_code` VALUES ('294a508d69b940e78685f056e9b79650', 'sys', '2019-09-18 22:58:59', '', NULL, '1', '1', 1, '13683579129', '428354', '2019-09-18 23:04:00');
INSERT INTO `tb_sys_sms_code` VALUES ('29e9a65c0f2d4891a047dedd5e03cdb5', 'sys', '2019-08-30 02:51:51', '', NULL, '1', '1', 1, '15810147596', '467415', '2019-08-30 02:56:52');
INSERT INTO `tb_sys_sms_code` VALUES ('29F05C335FD4468C938FD5FD0054550B', 'sys', '2019-07-30 16:36:24', '', NULL, '1', '1', 1, '13911301227', '175615', '2019-07-30 16:41:24');
INSERT INTO `tb_sys_sms_code` VALUES ('2A0C77A5A74B48C0A4C24CA18FA553A3', 'sys', '2019-07-09 17:44:08', '', NULL, '1', '1', 1, '18703613965', '925168', '2019-07-09 17:49:08');
INSERT INTO `tb_sys_sms_code` VALUES ('2ABF5FEE401C4B26B549CFBBC23F6055', 'sys', '2019-07-11 11:04:43', '', NULL, '1', '1', 1, '18617109386', '668183', '2019-07-11 11:09:44');
INSERT INTO `tb_sys_sms_code` VALUES ('2B474DCEA513422DB6EC5E87D7F38A12', 'sys', '2019-07-09 11:38:13', '', NULL, '1', '1', 1, '15736753571', '266419', '2019-07-09 11:43:14');
INSERT INTO `tb_sys_sms_code` VALUES ('2b49697c84254f1aba6045f88908ea31', 'sys', '2019-08-29 05:54:42', '', NULL, '1', '1', 1, '13911165737', '253186', '2019-08-29 05:59:43');
INSERT INTO `tb_sys_sms_code` VALUES ('2b99e4122ccd40eb885941b73a8b6e41', 'sys', '2019-09-09 13:30:47', '', NULL, '1', '1', 1, '13717839411', '674467', '2019-09-09 13:35:47');
INSERT INTO `tb_sys_sms_code` VALUES ('2BE23276B86648E28F555964ED095121', 'sys', '2019-07-11 17:13:08', '', NULL, '1', '1', 1, '17337166086', '987161', '2019-07-11 17:18:09');
INSERT INTO `tb_sys_sms_code` VALUES ('2CD485DC5D8C4EFE9DDFC059DFDD8277', 'sys', '2019-06-09 16:36:28', '', NULL, '1', '1', 1, '18710096183', '779139', '2019-06-09 16:41:28');
INSERT INTO `tb_sys_sms_code` VALUES ('2cd92939a058402e9cce84df5a9eb6c8', 'sys', '2019-09-20 11:03:13', '', NULL, '1', '1', 1, '13522323207', '846895', '2019-09-20 11:08:13');
INSERT INTO `tb_sys_sms_code` VALUES ('2d39c6f3fd40469ca6dea085f9e9ae05', 'sys', '2019-08-28 18:08:44', '', NULL, '1', '1', 1, '18611231317', '743543', '2019-08-28 18:13:45');
INSERT INTO `tb_sys_sms_code` VALUES ('2DBF9D67AB07475B89C2CDDD011AA49B', 'sys', '2019-07-08 05:54:31', '', NULL, '1', '1', 1, '13911301227', '644717', '2019-07-08 05:59:32');
INSERT INTO `tb_sys_sms_code` VALUES ('2E3DA1CC5D0845349982A446D1BFE160', 'sys', '2019-07-09 10:05:22', '', NULL, '1', '1', 1, '18703613965', '187835', '2019-07-09 10:10:22');
INSERT INTO `tb_sys_sms_code` VALUES ('2F4253FF30CA413FB50445F018DBC9D5', 'sys', '2019-06-12 09:53:53', '', NULL, '1', '1', 1, '18710002331', '651866', '2019-06-12 09:58:54');
INSERT INTO `tb_sys_sms_code` VALUES ('2FAC39953246439F84A87948124525ED', 'sys', '2019-07-25 09:26:04', '', NULL, '1', '1', 1, '15910234107', '374999', '2019-07-25 09:31:04');
INSERT INTO `tb_sys_sms_code` VALUES ('30ae9ac553a1476b82a611a654307a7b', 'sys', '2019-10-11 21:16:37', '', NULL, '1', '1', 1, '13581899328', '732553', '2019-10-11 21:21:37');
INSERT INTO `tb_sys_sms_code` VALUES ('30C92DF98CF44157866BC34F178F2B79', 'sys', '2019-07-08 09:33:29', '', NULL, '1', '1', 1, '18703613965', '244263', '2019-07-08 09:38:29');
INSERT INTO `tb_sys_sms_code` VALUES ('30CF45A32D754E96A2B4524D5F4DC5B3', 'sys', '2019-06-12 10:20:30', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-06-12 10:23:39', '1', '1', 1, '18710002331', '111111', '2020-06-12 10:23:27');
INSERT INTO `tb_sys_sms_code` VALUES ('32510484f2ac49538d3ff71589839472', 'sys', '2019-10-02 18:12:43', '', NULL, '1', '1', 1, '13910955423', '377518', '2019-10-02 18:17:43');
INSERT INTO `tb_sys_sms_code` VALUES ('3252c8ff5ef34a3588b9b24fdd48f6be', 'sys', '2019-10-02 15:43:26', '', NULL, '1', '1', 1, '13601309746', '522671', '2019-10-02 15:48:27');
INSERT INTO `tb_sys_sms_code` VALUES ('327a9c25765e483ba0443e2711d953e2', 'sys', '2019-09-09 15:22:32', '', NULL, '1', '1', 1, '13717839411', '932663', '2019-09-09 15:27:33');
INSERT INTO `tb_sys_sms_code` VALUES ('3296325E0721424E95F22365477E4AA9', 'sys', '2019-07-16 17:54:03', '', NULL, '1', '1', 1, '15210598852', '562382', '2019-07-16 17:59:04');
INSERT INTO `tb_sys_sms_code` VALUES ('33DA593E339B4CA09647030CDDBAACF9', 'sys', '2019-07-10 07:11:37', '', NULL, '1', '1', 1, '17638101635', '516498', '2019-07-10 07:16:38');
INSERT INTO `tb_sys_sms_code` VALUES ('34B487D525D04AA0A441353230D089C0', 'sys', '2019-07-10 11:01:08', '', NULL, '1', '1', 1, '17398982323', '786986', '2019-07-10 11:06:08');
INSERT INTO `tb_sys_sms_code` VALUES ('352107140aaf46318b0bd891d1c72ac5', 'sys', '2019-08-29 09:22:52', '', NULL, '1', '1', 1, '13121120095', '841154', '2019-08-29 09:27:52');
INSERT INTO `tb_sys_sms_code` VALUES ('35401a02b422432d96c02d342ba2eefb', 'sys', '2019-09-03 15:45:24', '', NULL, '1', '1', 1, '15910331086', '426696', '2019-09-03 15:50:24');
INSERT INTO `tb_sys_sms_code` VALUES ('359f2adc57b741889c401e2ed9bd0673', 'sys', '2019-09-23 14:19:00', '', NULL, '1', '1', 1, '18519277229', '787135', '2019-09-23 14:24:00');
INSERT INTO `tb_sys_sms_code` VALUES ('35FF6E3D05CC4285B37BE2EEF5BF523D', 'sys', '2019-07-16 10:16:49', '', NULL, '1', '1', 1, '18601943273', '319349', '2019-07-16 10:21:50');
INSERT INTO `tb_sys_sms_code` VALUES ('364046102dc04c42833052e28c6c35ae', 'sys', '2019-09-04 18:18:54', '', NULL, '1', '1', 1, '13716375591', '113618', '2019-09-04 18:23:54');
INSERT INTO `tb_sys_sms_code` VALUES ('36c28015eef641aca43fc059aff1a00b', 'sys', '2019-10-02 20:27:33', '', NULL, '1', '1', 1, '13716775951', '925819', '2019-10-02 20:32:34');
INSERT INTO `tb_sys_sms_code` VALUES ('377C28A2A0164609B88E66B4023B0099', 'sys', '2019-07-17 17:43:27', '', NULL, '1', '1', 1, '15210598852', '646569', '2019-07-17 17:48:27');
INSERT INTO `tb_sys_sms_code` VALUES ('381C179FABFC485A82A5EB22FFDCC4CA', 'sys', '2019-07-12 18:47:44', '', NULL, '1', '1', 1, '15910234107', '974292', '2019-07-12 18:52:45');
INSERT INTO `tb_sys_sms_code` VALUES ('38593A829BF6430CBC5C3A3B50596A95', 'sys', '2019-06-12 09:19:27', '', NULL, '1', '1', 1, '15117978092', '622383', '2019-06-12 09:24:28');
INSERT INTO `tb_sys_sms_code` VALUES ('386C0EF4BDA44089B1D213CF3458C2A0', 'sys', '2019-08-07 09:07:28', '', NULL, '1', '1', 1, '13601011360', '966211', '2019-08-07 09:12:29');
INSERT INTO `tb_sys_sms_code` VALUES ('38a0c9f57d7e4d45a55319df8b00798a', 'sys', '2019-08-29 09:29:59', '', NULL, '1', '1', 1, '17071006851', '998988', '2019-08-29 09:34:59');
INSERT INTO `tb_sys_sms_code` VALUES ('38dd1ba2c1154d038dc3715c8bc2f6f0', 'sys', '2019-08-28 18:17:49', '', NULL, '1', '1', 1, '13910958611', '328859', '2019-08-28 18:22:50');
INSERT INTO `tb_sys_sms_code` VALUES ('395b05aace8146ea8508bbf85360fc9a', 'sys', '2019-09-29 22:07:18', '', NULL, '1', '1', 1, '13552178273', '214413', '2019-09-29 22:12:19');
INSERT INTO `tb_sys_sms_code` VALUES ('39b2e27559574f2da259ca38b8229260', 'sys', '2019-08-29 15:46:24', '', NULL, '1', '1', 1, '13911637391', '851319', '2019-08-29 15:51:25');
INSERT INTO `tb_sys_sms_code` VALUES ('39C07D21B0AB40C0B997E754901641FB', 'sys', '2019-07-11 10:52:10', '', NULL, '1', '1', 1, '18703613965', '465674', '2019-07-11 10:57:10');
INSERT INTO `tb_sys_sms_code` VALUES ('39cf69f5055b40b98780766b06b7d2ea', 'sys', '2019-08-28 11:04:44', '', NULL, '1', '1', 1, '18501197135', '487855', '2019-08-28 11:09:44');
INSERT INTO `tb_sys_sms_code` VALUES ('3a6a9d965bcc4807af2cecd57610c7b5', 'sys', '2019-09-27 21:28:44', '', NULL, '1', '1', 1, '13521299730', '186498', '2019-09-27 21:33:44');
INSERT INTO `tb_sys_sms_code` VALUES ('3A87908BCA0C443485A0B83FAE32530A', 'sys', '2019-06-29 15:30:22', '', NULL, '1', '1', 1, '18703613965', '157693', '2019-06-29 15:35:23');
INSERT INTO `tb_sys_sms_code` VALUES ('3a9be3acf7a74c3cbce3ed422a89490c', 'sys', '2019-09-23 16:09:36', '', NULL, '1', '1', 1, '13811667321', '997188', '2019-09-23 16:14:37');
INSERT INTO `tb_sys_sms_code` VALUES ('3aabc3ce3581411899136bb964d84e09', 'sys', '2019-09-18 11:13:44', '', NULL, '1', '1', 1, '17600017649', '463647', '2019-09-18 11:18:45');
INSERT INTO `tb_sys_sms_code` VALUES ('3AC371388A594CFDA36084B987374E4E', 'sys', '2019-06-10 11:40:09', '', NULL, '1', '1', 1, '15910234107', '357577', '2019-06-10 11:45:09');
INSERT INTO `tb_sys_sms_code` VALUES ('3C65338224CF411BA342C581BE7E9F7E', 'sys', '2019-05-31 15:01:01', '', NULL, '1', '1', 1, '18710002331', '386328', '2019-05-31 15:06:01');
INSERT INTO `tb_sys_sms_code` VALUES ('3D640267FABA4129A5446BDEDA6D00DD', 'sys', '2019-06-11 19:55:35', '', NULL, '1', '1', 1, '18600019997', '349222', '2019-06-11 20:00:35');
INSERT INTO `tb_sys_sms_code` VALUES ('3E06896AF0394782A09B2AB863A24C57', 'sys', '2019-07-09 17:39:50', '', NULL, '1', '1', 1, '18703613965', '164216', '2019-07-09 17:44:51');
INSERT INTO `tb_sys_sms_code` VALUES ('3E0A490D3CFF47CE9821DC959FA5B375', 'sys', '2019-08-12 15:48:41', '', NULL, '1', '1', 1, '13911836316', '954887', '2019-08-12 15:53:41');
INSERT INTO `tb_sys_sms_code` VALUES ('3e80e5639c0140b3bf876ab0825a8af3', 'sys', '2019-09-10 03:18:27', '', NULL, '1', '1', 1, '13521495906', '752669', '2019-09-10 03:23:27');
INSERT INTO `tb_sys_sms_code` VALUES ('3F3189EB3235486EB754430A433CAB04', 'sys', '2019-07-09 16:47:07', '', NULL, '1', '1', 1, '17398982323', '368416', '2019-07-09 16:52:08');
INSERT INTO `tb_sys_sms_code` VALUES ('4001B48038B746DB97369869E3E67AB4', 'sys', '2019-07-17 16:02:05', '', NULL, '1', '1', 1, '13683387578', '238619', '2019-07-17 16:07:06');
INSERT INTO `tb_sys_sms_code` VALUES ('401F1D14F3A8490FB2B6DE37D035F421', 'sys', '2019-05-23 09:38:18', '', NULL, '1', '1', 1, '15910234107', '674692', '2019-05-23 09:43:18');
INSERT INTO `tb_sys_sms_code` VALUES ('405e9675a33e4f41a3bd40a45521a828', 'sys', '2019-09-20 21:27:02', '', NULL, '1', '1', 1, '13051242412', '526493', '2019-09-20 21:32:03');
INSERT INTO `tb_sys_sms_code` VALUES ('4074FD5756B74BA4B9F904AB815770B7', 'sys', '2019-08-15 11:09:49', '', NULL, '1', '1', 1, '15110128905', '842175', '2019-08-15 11:14:49');
INSERT INTO `tb_sys_sms_code` VALUES ('40A6D555B478411FB07C390206558752', 'sys', '2019-07-01 16:30:58', '', NULL, '1', '1', 1, '17398982323', '979795', '2019-07-01 16:35:58');
INSERT INTO `tb_sys_sms_code` VALUES ('40B5B79C8EF74414A1A62BE8A3F35276', 'sys', '2019-07-02 21:32:45', '', NULL, '1', '1', 1, '17638101635', '317387', '2019-07-02 21:37:45');
INSERT INTO `tb_sys_sms_code` VALUES ('4276bd03a10649478f38a9600a4364b6', 'sys', '2019-09-23 14:21:34', '', NULL, '1', '1', 1, '13581739925', '347961', '2019-09-23 14:26:35');
INSERT INTO `tb_sys_sms_code` VALUES ('428D06FA9D924C7EBDC1C222372F27B2', 'sys', '2019-08-12 15:51:26', '', NULL, '1', '1', 1, '15801314181', '521529', '2019-08-12 15:56:27');
INSERT INTO `tb_sys_sms_code` VALUES ('4346f44d040e4fe79ab29a21b56f6de7', 'sys', '2019-10-13 21:12:07', '', NULL, '1', '1', 1, '13811856197', '429316', '2019-10-13 21:17:07');
INSERT INTO `tb_sys_sms_code` VALUES ('43D9ED25426F48CEBE980396AB03257D', 'sys', '2019-06-11 16:01:08', '', NULL, '1', '1', 1, '13581881856', '159872', '2019-06-11 16:06:08');
INSERT INTO `tb_sys_sms_code` VALUES ('43FEB748A07741A1839DECD067B48472', 'sys', '2019-05-31 15:02:34', '', NULL, '1', '1', 1, '18710002331', '292837', '2019-05-31 15:07:34');
INSERT INTO `tb_sys_sms_code` VALUES ('441d3f7c683f4f43a3d7e40c26091914', 'sys', '2019-09-23 21:01:09', '', NULL, '1', '1', 1, '15311960924', '976353', '2019-09-23 21:06:10');
INSERT INTO `tb_sys_sms_code` VALUES ('4516E1D3DF0F4D13876C4E79D964E350', 'sys', '2019-06-10 15:07:21', '', NULL, '1', '1', 1, '18210256171', '518713', '2019-06-10 15:12:22');
INSERT INTO `tb_sys_sms_code` VALUES ('465F429FB6734303974B9BEAEC3D9340', 'sys', '2019-07-17 11:25:00', '', NULL, '1', '1', 1, '13801086442', '561929', '2019-07-17 11:30:01');
INSERT INTO `tb_sys_sms_code` VALUES ('466674e8e58f4c3daf8f0d82b9b655bd', 'sys', '2019-09-27 17:19:42', '', NULL, '1', '1', 1, '13718753320', '299199', '2019-09-27 17:24:43');
INSERT INTO `tb_sys_sms_code` VALUES ('46977A4AAE7649478DCC2C398AB9C71A', 'sys', '2019-08-06 13:38:57', '', NULL, '1', '1', 1, '15117978092', '935714', '2019-08-06 13:43:57');
INSERT INTO `tb_sys_sms_code` VALUES ('46CC8BA9DF0F41D5922B735226FF35FB', 'sys', '2019-07-09 11:43:33', '', NULL, '1', '1', 1, '18703613965', '787182', '2019-07-09 11:48:34');
INSERT INTO `tb_sys_sms_code` VALUES ('476C587A975F4076902CD73534061FA1', 'sys', '2019-07-02 21:18:42', '', NULL, '1', '1', 1, '13811080888', '274382', '2019-07-02 21:23:42');
INSERT INTO `tb_sys_sms_code` VALUES ('47b830a4ff344734a47ff90933b873fb', 'sys', '2019-08-28 16:46:34', '', NULL, '1', '1', 1, '13811311903', '154561', '2019-08-28 16:51:34');
INSERT INTO `tb_sys_sms_code` VALUES ('48F15D17E0084503963BB69270D409F7', 'sys', '2019-06-11 18:17:35', '', NULL, '1', '1', 1, '15736753571', '995784', '2019-06-11 18:22:35');
INSERT INTO `tb_sys_sms_code` VALUES ('49071e56ffa44708bb147a6439d326fd', 'sys', '2019-09-26 16:02:01', '', NULL, '1', '1', 1, '13581876017', '396928', '2019-09-26 16:07:01');
INSERT INTO `tb_sys_sms_code` VALUES ('497a04133476407ab0f17502524e5a04', 'sys', '2019-10-01 17:01:27', '', NULL, '1', '1', 1, '13801086442', '141193', '2019-10-01 17:06:27');
INSERT INTO `tb_sys_sms_code` VALUES ('4A405C22244A40A58F7DB0C83C7D1DB4', 'sys', '2019-07-05 00:52:06', '', NULL, '1', '1', 1, '17638101635', '335898', '2019-07-05 00:57:06');
INSERT INTO `tb_sys_sms_code` VALUES ('4AB8D5B86C6743FE92921E1C5D644003', 'sys', '2019-06-11 17:11:35', '', NULL, '1', '1', 1, '18601943273', '911386', '2019-06-11 17:16:36');
INSERT INTO `tb_sys_sms_code` VALUES ('4AF4AF7933AF417FAC9258C94EAD495E', 'sys', '2019-07-09 11:46:19', '', NULL, '1', '1', 1, '15910234107', '385478', '2019-07-09 11:51:19');
INSERT INTO `tb_sys_sms_code` VALUES ('4BE913F70DB143C980DA5AC79E8E45A8', 'sys', '2019-07-11 09:34:00', '', NULL, '1', '1', 1, '18703613965', '528917', '2019-07-11 09:39:00');
INSERT INTO `tb_sys_sms_code` VALUES ('4c3be33a70d94df3be6b929a9f2d15a7', 'sys', '2019-09-21 09:33:26', '', NULL, '1', '1', 1, '13716228593', '193734', '2019-09-21 09:38:26');
INSERT INTO `tb_sys_sms_code` VALUES ('4D0BA78AA4F5403F8FF5DE9EB46D68F3', 'sys', '2019-06-12 10:16:38', '', NULL, '1', '1', 1, '18710002331', '165583', '2019-06-12 10:21:39');
INSERT INTO `tb_sys_sms_code` VALUES ('4f19b9ebdc05494d9c89e6349913eb4e', 'sys', '2019-09-27 11:02:14', '', NULL, '1', '1', 1, '13910678171', '855795', '2019-09-27 11:07:14');
INSERT INTO `tb_sys_sms_code` VALUES ('4F68B07868C941C9846188233704AADE', 'sys', '2019-07-09 17:42:30', '', NULL, '1', '1', 1, '18703613965', '229244', '2019-07-09 17:47:30');
INSERT INTO `tb_sys_sms_code` VALUES ('4f693d9d979b4a8cb6ec9d2ec7ec864d', 'sys', '2019-08-28 20:38:32', '', NULL, '1', '1', 1, '18911333005', '664254', '2019-08-28 20:43:32');
INSERT INTO `tb_sys_sms_code` VALUES ('4f6f5a8982954c57a372b641b2c8173d', 'sys', '2019-09-14 14:49:41', '', NULL, '1', '1', 1, '13810915194', '473855', '2019-09-14 14:54:41');
INSERT INTO `tb_sys_sms_code` VALUES ('4f7e327eecdb41ed83c760fcd9a4e0c5', 'sys', '2019-08-28 11:17:12', '', NULL, '1', '1', 1, '13701103773', '754157', '2019-08-28 11:22:12');
INSERT INTO `tb_sys_sms_code` VALUES ('501667E77F2F4D8CB146D5A7952E00A1', 'sys', '2019-07-04 15:02:55', '', NULL, '1', '1', 1, '18710096183', '716756', '2019-07-04 15:07:55');
INSERT INTO `tb_sys_sms_code` VALUES ('50d897c40b474064b0da891c808b2ff9', 'sys', '2019-09-04 10:12:38', '', NULL, '1', '1', 1, '15910301178', '179981', '2019-09-04 10:17:39');
INSERT INTO `tb_sys_sms_code` VALUES ('514D1C98F7D84011B181B77A45601A4A', 'sys', '2019-05-22 16:38:28', '', NULL, '1', '1', 1, '15910234107', '654859', '2019-05-22 16:43:29');
INSERT INTO `tb_sys_sms_code` VALUES ('51ae318ea4534a1bafc4d508fb3b424c', 'sys', '2019-09-18 20:39:07', '', NULL, '1', '1', 1, '13051860501', '595433', '2019-09-18 20:44:08');
INSERT INTO `tb_sys_sms_code` VALUES ('51DAA457E15E43CF8955963EA8F1A98D', 'sys', '2019-07-08 09:49:02', '', NULL, '1', '1', 1, '17398982323', '396972', '2019-07-08 09:54:03');
INSERT INTO `tb_sys_sms_code` VALUES ('529e65a8dd1342e49fc2af6547ecea5e', 'sys', '2019-08-29 00:52:44', '', NULL, '1', '1', 1, '13261790748', '152194', '2019-08-29 00:57:45');
INSERT INTO `tb_sys_sms_code` VALUES ('53798cb322d14e6c8389358e57d08091', 'sys', '2019-08-28 16:58:57', '', NULL, '1', '1', 1, '15801238973', '514436', '2019-08-28 17:03:58');
INSERT INTO `tb_sys_sms_code` VALUES ('5406F850DACC4180910052B42203E772', 'sys', '2019-06-23 12:48:49', '', NULL, '1', '1', 1, '13911301227', '415488', '2019-06-23 12:53:50');
INSERT INTO `tb_sys_sms_code` VALUES ('5440341689B841589B831AA6A8619AAE', 'sys', '2019-07-09 11:41:27', '', NULL, '1', '1', 1, '18703613965', '512864', '2019-07-09 11:46:27');
INSERT INTO `tb_sys_sms_code` VALUES ('549a4541929b4180a36220e599336b3b', 'sys', '2019-08-28 12:19:52', '', NULL, '1', '1', 1, '13651105091', '377398', '2019-08-28 12:24:53');
INSERT INTO `tb_sys_sms_code` VALUES ('5548B987126D4415BA15E94D5825009A', 'sys', '2019-07-11 14:49:59', '', NULL, '1', '1', 1, '18703613965', '521376', '2019-07-11 14:54:59');
INSERT INTO `tb_sys_sms_code` VALUES ('55953df72db141e6987998b8f0a9a2a6', 'sys', '2019-10-07 13:07:50', '', NULL, '1', '1', 1, '13241393737', '382577', '2019-10-07 13:12:51');
INSERT INTO `tb_sys_sms_code` VALUES ('55b363e6b0e94fb2beafb7b832799b7e', 'sys', '2019-08-31 05:23:36', '', NULL, '1', '1', 1, '15010918961', '821218', '2019-08-31 05:28:37');
INSERT INTO `tb_sys_sms_code` VALUES ('55f719c1675c40d4b32ec498cccded81', 'sys', '2019-09-08 22:44:40', '', NULL, '1', '1', 1, '15210057093', '765452', '2019-09-08 22:49:41');
INSERT INTO `tb_sys_sms_code` VALUES ('564def968f6843539b912c486fa741b9', 'sys', '2019-09-04 20:00:22', '', NULL, '1', '1', 1, '15810877941', '381385', '2019-09-04 20:05:22');
INSERT INTO `tb_sys_sms_code` VALUES ('5764ac0f768b4c3fbad4439d553a23c8', 'sys', '2019-09-01 17:17:08', '', NULL, '1', '1', 1, '13910001257', '446575', '2019-09-01 17:22:09');
INSERT INTO `tb_sys_sms_code` VALUES ('579a989a30df474497e473d2d60cf34e', 'sys', '2019-09-23 16:03:17', '', NULL, '1', '1', 1, '15011433309', '162918', '2019-09-23 16:08:17');
INSERT INTO `tb_sys_sms_code` VALUES ('5866F968C49F442EB83E45363D21AC6F', 'sys', '2019-06-19 11:35:49', '', NULL, '1', '1', 1, '17398982323', '286311', '2019-06-19 11:40:49');
INSERT INTO `tb_sys_sms_code` VALUES ('589B679B0C9E43DAAFC42B15F78492CB', 'sys', '2019-07-24 17:49:03', '', NULL, '1', '1', 1, '15910234107', '786627', '2019-07-24 17:54:04');
INSERT INTO `tb_sys_sms_code` VALUES ('590ddc57f6a74c369a2b14184368d0bd', 'sys', '2019-08-28 21:48:54', '', NULL, '1', '1', 1, '13466764477', '238365', '2019-08-28 21:53:54');
INSERT INTO `tb_sys_sms_code` VALUES ('592A223462B142B89FD470F907068C96', 'sys', '2019-06-10 10:20:03', '', NULL, '1', '1', 1, '18710002331', '185866', '2019-06-10 10:25:03');
INSERT INTO `tb_sys_sms_code` VALUES ('59691CE8580749B5A6DC47C234A23088', 'sys', '2019-08-22 08:56:45', '', NULL, '1', '1', 1, '18611497405', '716124', '2019-08-22 09:01:45');
INSERT INTO `tb_sys_sms_code` VALUES ('59B472EDA1EB43BFBD56EF836C62FC3E', 'sys', '2019-08-14 19:29:42', '', NULL, '1', '1', 1, '13716882996', '519955', '2019-08-14 19:34:43');
INSERT INTO `tb_sys_sms_code` VALUES ('5ABD1E0AF7814BD6B324D2B58C755E09', 'sys', '2019-06-12 15:27:03', '', NULL, '1', '1', 1, '15117978092', '756852', '2019-06-12 15:32:04');
INSERT INTO `tb_sys_sms_code` VALUES ('5AC6950B743F4F67BB0817FFED6A313C', 'sys', '2019-05-29 14:29:31', '', NULL, '1', '1', 1, '17638101635', '321296', '2019-05-29 14:34:31');
INSERT INTO `tb_sys_sms_code` VALUES ('5B72B6802BA84A75BF20CFF8F619C042', 'sys', '2019-06-10 10:09:13', '', NULL, '1', '1', 1, '18710002331', '761434', '2019-06-10 10:14:13');
INSERT INTO `tb_sys_sms_code` VALUES ('5C4DDEE566F54ACDAD367AF56796C6D4', 'sys', '2019-06-13 09:56:48', '', NULL, '1', '1', 1, '15910234107', '378853', '2019-06-13 10:01:49');
INSERT INTO `tb_sys_sms_code` VALUES ('5C745497604B4273A5633ABB27A735D9', 'sys', '2019-06-12 14:46:05', '', NULL, '1', '1', 1, '15910234107', '316933', '2019-06-12 14:51:05');
INSERT INTO `tb_sys_sms_code` VALUES ('5D6323A554104AA98C238ECE2A88BB62', 'sys', '2019-08-22 10:34:27', '', NULL, '1', '1', 1, '15910690912', '425513', '2019-08-22 10:39:27');
INSERT INTO `tb_sys_sms_code` VALUES ('5d7a5e1f7920495fa4af75a429f0ba8f', 'sys', '2019-09-10 21:34:09', '', NULL, '1', '1', 1, '13051852698', '484852', '2019-09-10 21:39:10');
INSERT INTO `tb_sys_sms_code` VALUES ('5eb074d6137a4275af8b073aaa984b5e', 'sys', '2019-09-27 15:25:49', '', NULL, '1', '1', 1, '13810707690', '425228', '2019-09-27 15:30:49');
INSERT INTO `tb_sys_sms_code` VALUES ('60FBB98BCE654A289EFFF8C8CFCF6C72', 'sys', '2019-07-10 10:54:36', '', NULL, '1', '1', 1, '17398982323', '727287', '2019-07-10 10:59:37');
INSERT INTO `tb_sys_sms_code` VALUES ('6169882811A5402D8E8F11C1F620D390', 'sys', '2019-07-23 10:25:37', '', NULL, '1', '1', 1, '18551640325', '264243', '2019-07-23 10:30:37');
INSERT INTO `tb_sys_sms_code` VALUES ('6190CE2BAA75477E9C74E984C60AC9AF', 'sys', '2019-07-11 14:49:33', '', NULL, '1', '1', 1, '15117978092', '541449', '2019-07-11 14:54:33');
INSERT INTO `tb_sys_sms_code` VALUES ('61b6b59c67674d7b8ac1bd0d840b7292', 'sys', '2019-09-04 18:31:15', '', NULL, '1', '1', 1, '13701103984', '427252', '2019-09-04 18:36:15');
INSERT INTO `tb_sys_sms_code` VALUES ('62e08f9349f0499690d6969f005e3c73', 'sys', '2019-08-31 18:43:52', '', NULL, '1', '1', 1, '13811505593', '661368', '2019-08-31 18:48:52');
INSERT INTO `tb_sys_sms_code` VALUES ('63B12C105FB748079B4E34FEAE3E876F', 'sys', '2019-07-13 16:18:42', '', NULL, '1', '1', 1, '18601085488', '365122', '2019-07-13 16:23:43');
INSERT INTO `tb_sys_sms_code` VALUES ('642238A04105402A9F82487901277CBA', 'sys', '2019-06-12 10:29:28', '', NULL, '1', '1', 1, '18519785936', '812152', '2019-06-12 10:34:29');
INSERT INTO `tb_sys_sms_code` VALUES ('649B675359454401B2ACEEE6BEAE5453', 'sys', '2019-05-22 16:49:43', '', NULL, '1', '1', 1, '15910234107', '397691', '2019-05-22 16:54:44');
INSERT INTO `tb_sys_sms_code` VALUES ('64A910E5F4D242AA8BEA904C92CFB3E2', 'sys', '2019-06-09 16:48:28', '', NULL, '1', '1', 1, '18601943273', '578678', '2019-06-09 16:53:29');
INSERT INTO `tb_sys_sms_code` VALUES ('652A706513214C9EAD87C23376E3A356', 'sys', '2019-07-30 10:38:37', '', NULL, '1', '1', 1, '13670091279', '322496', '2019-07-30 10:43:37');
INSERT INTO `tb_sys_sms_code` VALUES ('65312BFB6CB44AD1A56F487E068EADC6', 'sys', '2019-05-31 15:36:09', '', NULL, '1', '1', 1, '18710002331', '476779', '2019-05-31 15:41:10');
INSERT INTO `tb_sys_sms_code` VALUES ('662BB08085A54AFDBE77128643CC7B97', 'sys', '2019-07-10 09:25:31', '', NULL, '1', '1', 1, '17398982323', '814262', '2019-07-10 09:30:32');
INSERT INTO `tb_sys_sms_code` VALUES ('66C5E4FE4D4B49648DFC4C653A9C3CB2', 'sys', '2019-08-02 09:35:32', '', NULL, '1', '1', 1, '17816868903', '954365', '2019-08-02 09:40:33');
INSERT INTO `tb_sys_sms_code` VALUES ('67c4c857d3fc4a238a5b1943a730c667', 'sys', '2019-10-02 11:37:22', '', NULL, '1', '1', 1, '13552390283', '939626', '2019-10-02 11:42:22');
INSERT INTO `tb_sys_sms_code` VALUES ('67e5498a15974d36a26427d7f8982b9a', 'sys', '2019-09-18 20:24:24', '', NULL, '1', '1', 1, '13811192599', '195115', '2019-09-18 20:29:24');
INSERT INTO `tb_sys_sms_code` VALUES ('67ec11e244ad401db20a9cdbbe890de3', 'sys', '2019-09-04 16:34:43', '', NULL, '1', '1', 1, '13717793816', '482788', '2019-09-04 16:39:44');
INSERT INTO `tb_sys_sms_code` VALUES ('6876338A0C1D46F8AE6D648877D5C063', 'sys', '2019-07-11 14:31:20', '', NULL, '1', '1', 1, '18617109386', '265257', '2019-07-11 14:36:21');
INSERT INTO `tb_sys_sms_code` VALUES ('6939F140E1214ACEA28C411F1AA9EF03', 'sys', '2019-05-31 15:05:33', '', NULL, '1', '1', 1, '15910234107', '983494', '2019-05-31 15:10:33');
INSERT INTO `tb_sys_sms_code` VALUES ('6939f5625a9542fdb533c14f7d3ac5b4', 'sys', '2019-08-28 21:07:54', '', NULL, '1', '1', 1, '13426210852', '773392', '2019-08-28 21:12:54');
INSERT INTO `tb_sys_sms_code` VALUES ('694446235d0c4013abea4bb702a3321e', 'sys', '2019-08-28 23:13:58', '', NULL, '1', '1', 1, '17718568750', '333634', '2019-08-28 23:18:59');
INSERT INTO `tb_sys_sms_code` VALUES ('69F07DA9DE3B46FDB6A220643E749B83', 'sys', '2019-07-11 14:55:44', '', NULL, '1', '1', 1, '18703613965', '778852', '2019-07-11 15:00:45');
INSERT INTO `tb_sys_sms_code` VALUES ('69F7FAF4CABB4E0D96BF2CED21EACB4A', 'sys', '2019-08-27 09:53:34', '', NULL, '1', '1', 1, '15810017990', '687887', '2019-08-27 09:58:35');
INSERT INTO `tb_sys_sms_code` VALUES ('6a0eab833a8b47b8814a4aca46192c2c', 'sys', '2019-08-29 06:41:13', '', NULL, '1', '1', 1, '18612562748', '945675', '2019-08-29 06:46:13');
INSERT INTO `tb_sys_sms_code` VALUES ('6A70BFBEE1CD47B0956A547AD0E29133', 'sys', '2019-07-25 09:40:18', '', NULL, '1', '1', 1, '15910234107', '127587', '2019-07-25 09:45:19');
INSERT INTO `tb_sys_sms_code` VALUES ('6A777AEDD5154DC3AD31277F3788B04A', 'sys', '2019-07-24 09:38:07', '', NULL, '1', '1', 1, '15994823695', '126844', '2019-07-24 09:43:07');
INSERT INTO `tb_sys_sms_code` VALUES ('6AA71BCFA0F04FA395E9BFBE28159740', 'sys', '2019-08-13 19:22:47', '', NULL, '1', '1', 1, '15210808373', '158743', '2019-08-13 19:27:47');
INSERT INTO `tb_sys_sms_code` VALUES ('6B3CC8B421FB42418EDBAE40324EC7D5', 'sys', '2019-07-09 15:46:34', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-07-13 16:39:27', '1', '1', 1, '18601943273', '111111', '2019-08-10 16:39:24');
INSERT INTO `tb_sys_sms_code` VALUES ('6c698b1e00534a65888192248c6e7813', 'sys', '2019-08-28 17:51:24', '', NULL, '1', '1', 1, '18513609858', '935495', '2019-08-28 17:56:25');
INSERT INTO `tb_sys_sms_code` VALUES ('6C6D2852945F4614BEE951E5E78DBD7C', 'sys', '2019-07-13 17:09:02', '', NULL, '1', '1', 1, '15117978092', '352144', '2019-07-13 17:14:03');
INSERT INTO `tb_sys_sms_code` VALUES ('6c739e098d0546dda83757652be50b3b', 'sys', '2019-08-28 15:51:26', '', NULL, '1', '1', 1, '13716308323', '398765', '2019-08-28 15:56:27');
INSERT INTO `tb_sys_sms_code` VALUES ('6ca470dd391846ec87081f24d662a2a1', 'sys', '2019-08-28 21:01:08', '', NULL, '1', '1', 1, '18210189387', '439366', '2019-08-28 21:06:09');
INSERT INTO `tb_sys_sms_code` VALUES ('6CBACCBEF1AB4F15A952E2AA4F3EC679', 'sys', '2019-08-10 17:10:58', '', NULL, '1', '1', 1, '13241557051', '322562', '2019-08-10 17:15:58');
INSERT INTO `tb_sys_sms_code` VALUES ('6cdb136b4a1d4911b24bd82e982899bb', 'sys', '2019-10-11 22:25:54', '', NULL, '1', '1', 1, '15200108112', '784279', '2019-10-11 22:30:54');
INSERT INTO `tb_sys_sms_code` VALUES ('6e225a3b7f5d49beb79bf58bd449ed1a', 'sys', '2019-09-17 18:46:44', '', NULL, '1', '1', 1, '18002083029', '666717', '2019-09-17 18:51:44');
INSERT INTO `tb_sys_sms_code` VALUES ('6e426d02a8c24db1a2a852f198c077fb', 'sys', '2019-10-12 10:21:19', '', NULL, '1', '1', 1, '13581552099', '599498', '2019-10-12 10:26:19');
INSERT INTO `tb_sys_sms_code` VALUES ('6e5d0be786e6401aa3d0abf4860305fc', 'sys', '2019-09-26 14:01:37', '', NULL, '1', '1', 1, '15810819153', '676564', '2019-09-26 14:06:38');
INSERT INTO `tb_sys_sms_code` VALUES ('6EEED1A9642B4F359364E8024FAB0375', 'sys', '2019-07-11 11:24:43', '', NULL, '1', '1', 1, '13601011360', '362123', '2019-07-11 11:29:43');
INSERT INTO `tb_sys_sms_code` VALUES ('6F8CECB5CE5E4D3DACA2A61B49AC9880', 'sys', '2019-07-12 10:39:55', '', NULL, '1', '1', 1, '18703613965', '221514', '2019-07-12 10:44:56');
INSERT INTO `tb_sys_sms_code` VALUES ('7012B2368F7C474E8621997A28CDDE8A', 'sys', '2019-06-12 10:00:45', '', NULL, '1', '1', 1, '18710002331', '693763', '2019-06-12 10:05:46');
INSERT INTO `tb_sys_sms_code` VALUES ('702c41d2ca9943afa996f7c983ad6ac3', 'sys', '2019-08-30 00:15:34', '', NULL, '1', '1', 1, '13581675866', '982775', '2019-08-30 00:20:34');
INSERT INTO `tb_sys_sms_code` VALUES ('70f37a51a7be47229ed4577db7dbff61', 'sys', '2019-09-24 06:41:44', '', NULL, '1', '1', 1, '15611122679', '191876', '2019-09-24 06:46:44');
INSERT INTO `tb_sys_sms_code` VALUES ('71effdc3ac074707a4d3c23c073f707e', 'sys', '2019-09-20 10:03:14', '', NULL, '1', '1', 1, '15911187166', '999696', '2019-09-20 10:08:14');
INSERT INTO `tb_sys_sms_code` VALUES ('723ad9093c504da69745745ca4dd8244', 'sys', '2019-09-05 09:35:13', '', NULL, '1', '1', 1, '15117978092', '244483', '2019-09-05 09:40:14');
INSERT INTO `tb_sys_sms_code` VALUES ('723AF61799EA49FFB037A3E86673AD5D', 'sys', '2019-06-10 11:39:37', '', NULL, '1', '1', 1, '15117978092', '887673', '2019-06-10 11:44:37');
INSERT INTO `tb_sys_sms_code` VALUES ('728f8b8e9c6a48bbaa20d3b45baa44fe', 'sys', '2019-08-29 11:43:29', '', NULL, '1', '1', 1, '13520504286', '958982', '2019-08-29 11:48:30');
INSERT INTO `tb_sys_sms_code` VALUES ('73a2252f9ee94bf485ee2c86bf97e0bb', 'sys', '2019-08-28 18:29:00', '', NULL, '1', '1', 1, '15001159576', '651538', '2019-08-28 18:34:00');
INSERT INTO `tb_sys_sms_code` VALUES ('73d5ae66f15e445da866fcfbc9fc1249', 'sys', '2019-08-29 00:19:50', '', NULL, '1', '1', 1, '13716733358', '814668', '2019-08-29 00:24:50');
INSERT INTO `tb_sys_sms_code` VALUES ('760EF9F48C8B406FBDB63BC97E88C0AB', 'sys', '2019-07-09 14:59:07', '', NULL, '1', '1', 1, '15117978092', '612229', '2019-07-09 15:04:07');
INSERT INTO `tb_sys_sms_code` VALUES ('7683E2D65E6540FAB39FDDC76C379587', 'sys', '2019-07-13 16:44:56', '', NULL, '1', '1', 1, '17398982323', '443892', '2019-07-13 16:49:57');
INSERT INTO `tb_sys_sms_code` VALUES ('76C7B78D51124BA092346FA1D6DF5399', 'sys', '2019-06-24 16:05:35', '', NULL, '1', '1', 1, '15736753571', '933232', '2019-06-24 16:10:35');
INSERT INTO `tb_sys_sms_code` VALUES ('76D2F9EF9B794D22B027812A32612F16', 'sys', '2019-06-09 15:36:04', '', NULL, '1', '1', 1, '18710002331', '476793', '2019-06-09 15:41:04');
INSERT INTO `tb_sys_sms_code` VALUES ('7746afb998da4693a7d6b5b86a76c099', 'sys', '2019-09-25 17:56:58', '', NULL, '1', '1', 1, '18801161134', '876445', '2019-09-25 18:01:58');
INSERT INTO `tb_sys_sms_code` VALUES ('776A17D19942454CA800F23E2688C293', 'sys', '2019-06-18 14:32:40', '', NULL, '1', '1', 1, '17398982323', '389766', '2019-06-18 14:37:41');
INSERT INTO `tb_sys_sms_code` VALUES ('781197EE978844F689CF90588B2535CD', 'sys', '2019-07-16 10:19:06', '', NULL, '1', '1', 1, '18601943273', '968111', '2019-07-16 10:24:07');
INSERT INTO `tb_sys_sms_code` VALUES ('78bff36e888145eeb678ec988f31cc8d', 'sys', '2019-09-08 20:19:54', '', NULL, '1', '1', 1, '13683193117', '447286', '2019-09-08 20:24:55');
INSERT INTO `tb_sys_sms_code` VALUES ('796D308B61334CCAA2A74D97804B7A1D', 'sys', '2019-07-12 11:30:25', '', NULL, '1', '1', 1, '18703613965', '455659', '2019-07-12 11:35:26');
INSERT INTO `tb_sys_sms_code` VALUES ('7a1875a617f540eb9614f87689d5108f', 'sys', '2019-08-28 16:58:14', '', NULL, '1', '1', 1, '18601104738', '532718', '2019-08-28 17:03:14');
INSERT INTO `tb_sys_sms_code` VALUES ('7A3994D46736417DA7AA1ECAC9F433C1', 'sys', '2019-05-31 16:07:00', '', NULL, '1', '1', 1, '18710002331', '544198', '2019-05-31 16:12:00');
INSERT INTO `tb_sys_sms_code` VALUES ('7a815d7ebacb4408bf288b5e8a682306', 'sys', '2019-09-26 14:02:54', '', NULL, '1', '1', 1, '15810819153', '572124', '2019-09-26 14:07:55');
INSERT INTO `tb_sys_sms_code` VALUES ('7a90527ac81c4b239ce328538020d2b4', 'sys', '2019-08-29 09:35:44', '', NULL, '1', '1', 1, '18518938993', '653178', '2019-08-29 09:40:44');
INSERT INTO `tb_sys_sms_code` VALUES ('7ac1ff0f41354227a2be96203ec5f1c8', 'sys', '2019-09-17 20:51:16', '', NULL, '1', '1', 1, '13810420776', '757831', '2019-09-17 20:56:17');
INSERT INTO `tb_sys_sms_code` VALUES ('7c45a46c10df425db7cc313f0fc3bf3e', 'sys', '2019-08-28 17:35:27', '', NULL, '1', '1', 1, '15910353010', '895924', '2019-08-28 17:40:27');
INSERT INTO `tb_sys_sms_code` VALUES ('7CCE8AFB52E14CBBB8B13CB857CE8B34', 'sys', '2019-06-06 16:48:55', '', NULL, '1', '1', 1, '13581881826', '865768', '2019-06-06 16:53:56');
INSERT INTO `tb_sys_sms_code` VALUES ('7D445C9A62074D70ADA6999A5AD65DF2', 'sys', '2019-06-12 10:24:55', '', NULL, '1', '1', 1, '18719785936', '296737', '2019-06-12 10:29:56');
INSERT INTO `tb_sys_sms_code` VALUES ('7d4e18524d334f24ae3230cb69e040e1', 'sys', '2019-09-04 16:35:44', '', NULL, '1', '1', 1, '13717793816', '772919', '2019-09-04 16:40:44');
INSERT INTO `tb_sys_sms_code` VALUES ('7D6AAC98BB774F73B1C666452D34F8AE', 'sys', '2019-07-09 17:53:44', '', NULL, '1', '1', 1, '18703613965', '841512', '2019-07-09 17:58:44');
INSERT INTO `tb_sys_sms_code` VALUES ('7DCC399D96844B31A789C42672A54F4A', 'sys', '2019-05-31 15:34:45', '', NULL, '1', '1', 1, '18710002331', '312611', '2019-05-31 15:39:46');
INSERT INTO `tb_sys_sms_code` VALUES ('7E333DE656754550BF9E2810A8B5AD20', 'sys', '2019-07-24 17:59:59', '', NULL, '1', '1', 1, '15910234107', '792635', '2019-07-24 18:05:00');
INSERT INTO `tb_sys_sms_code` VALUES ('7E4E471AC3BB400B828905E8FE276A0F', 'sys', '2019-07-08 09:32:18', '', NULL, '1', '1', 1, '18703613965', '469652', '2019-07-08 09:37:18');
INSERT INTO `tb_sys_sms_code` VALUES ('7f9f8fd3c46d41ba9af3154b563ddb5e', 'sys', '2019-09-18 11:30:04', '', NULL, '1', '1', 1, '18701352322', '265631', '2019-09-18 11:35:05');
INSERT INTO `tb_sys_sms_code` VALUES ('801233B748F54E63BDC6E9BA2F0B56BF', 'sys', '2019-07-09 17:40:39', '', NULL, '1', '1', 1, '18703613965', '443491', '2019-07-09 17:45:39');
INSERT INTO `tb_sys_sms_code` VALUES ('805852667E5A49AA9E227F52EA61709B', 'sys', '2019-06-10 11:47:45', '', NULL, '1', '1', 1, '18710002331', '785494', '2019-06-10 11:52:46');
INSERT INTO `tb_sys_sms_code` VALUES ('806A8ABB1A374AF0A1BB7779AB545DE9', 'sys', '2019-06-09 18:01:56', '', NULL, '1', '1', 1, '15910234107', '357198', '2019-06-09 18:06:56');
INSERT INTO `tb_sys_sms_code` VALUES ('81777B483A3B439FBAAAA88BFC032C2F', 'sys', '2019-08-23 14:07:46', '', NULL, '1', '1', 1, '15910234107', '577968', '2019-08-23 14:12:47');
INSERT INTO `tb_sys_sms_code` VALUES ('819adedfda7c433e92582fa76fc60478', 'sys', '2019-08-28 17:25:44', '', NULL, '1', '1', 1, '13910300150', '185476', '2019-08-28 17:30:45');
INSERT INTO `tb_sys_sms_code` VALUES ('824ff448829e4660a970013ce454f437', 'sys', '2019-08-28 20:16:34', '', NULL, '1', '1', 1, '13701083389', '788183', '2019-08-28 20:21:34');
INSERT INTO `tb_sys_sms_code` VALUES ('8271af5192fe41c78eb88dec0574eab1', 'sys', '2019-09-05 09:42:59', '', NULL, '1', '1', 1, '15117978092', '188946', '2019-09-05 09:48:00');
INSERT INTO `tb_sys_sms_code` VALUES ('82f55835d2f247578ee4dfb539f82b6a', 'sys', '2019-09-25 18:10:06', '', NULL, '1', '1', 1, '18601943273', '175354', '2019-09-25 18:15:06');
INSERT INTO `tb_sys_sms_code` VALUES ('835e80c51b114e3b852d1276d5a00a32', 'sys', '2019-09-23 15:52:39', '', NULL, '1', '1', 1, '13716302720', '329599', '2019-09-23 15:57:39');
INSERT INTO `tb_sys_sms_code` VALUES ('8596f7a5f93d49d88a31965291a1e0b3', 'sys', '2019-08-28 17:22:16', '', NULL, '1', '1', 1, '13601076985', '176782', '2019-08-28 17:27:17');
INSERT INTO `tb_sys_sms_code` VALUES ('85C550A73F904317B398B0255BAB8C31', 'sys', '2019-05-29 14:20:51', '', NULL, '1', '1', 1, '17638101635', '165284', '2019-05-29 14:25:51');
INSERT INTO `tb_sys_sms_code` VALUES ('86555cf8f90f47e3848d3ffacc86b899', 'sys', '2019-09-04 10:13:43', '', NULL, '1', '1', 1, '15010102940', '987551', '2019-09-04 10:18:43');
INSERT INTO `tb_sys_sms_code` VALUES ('89638DB31C5D43F18C531CE575D55E73', 'sys', '2019-07-05 20:01:12', '', NULL, '1', '1', 1, '13523509375', '265669', '2019-07-05 20:06:12');
INSERT INTO `tb_sys_sms_code` VALUES ('896A6A3F3FC347438E15CF222C171534', 'sys', '2019-08-12 20:22:31', '', NULL, '1', '1', 1, '15611075200', '833951', '2019-08-12 20:27:31');
INSERT INTO `tb_sys_sms_code` VALUES ('8ADA25CD48734EC69C0A1AE6BB4A0716', 'sys', '2019-05-31 15:28:13', '', NULL, '1', '1', 1, '15910234107', '677422', '2019-05-31 15:33:14');
INSERT INTO `tb_sys_sms_code` VALUES ('8BAFE5F21A4244F0956DDD62CB4CCD0C', 'sys', '2019-07-11 14:48:55', '', NULL, '1', '1', 1, '18703613965', '296944', '2019-07-11 14:53:55');
INSERT INTO `tb_sys_sms_code` VALUES ('8bd0bb4b352f42068b6452016a79802c', 'sys', '2019-09-27 15:56:02', '', NULL, '1', '1', 1, '13801086442', '346693', '2019-09-27 16:01:03');
INSERT INTO `tb_sys_sms_code` VALUES ('8cc6f736552d41ab84e00831c2a38fbe', 'sys', '2019-08-28 20:02:32', '', NULL, '1', '1', 1, '15811216961', '424659', '2019-08-28 20:07:32');
INSERT INTO `tb_sys_sms_code` VALUES ('8D31979C5B0441D7BE3ECE68EA42A0CE', 'sys', '2019-08-16 06:41:05', '', NULL, '1', '1', 1, '13910295929', '268179', '2019-08-16 06:46:06');
INSERT INTO `tb_sys_sms_code` VALUES ('8D4FB68C3166491FB8B48699F69229E0', 'sys', '2019-06-10 11:54:00', '', NULL, '1', '1', 1, '18710002331', '566338', '2019-06-10 11:59:00');
INSERT INTO `tb_sys_sms_code` VALUES ('8D9CFE398BDA4775B3D24B2612A54D94', 'sys', '2019-06-18 11:17:31', '', NULL, '1', '1', 1, '17398982323', '584672', '2019-06-18 11:22:31');
INSERT INTO `tb_sys_sms_code` VALUES ('8e1cb30705fe49399b7a2b3c2fb85883', 'sys', '2019-09-04 10:38:31', '', NULL, '1', '1', 1, '13910708690', '751538', '2019-09-04 10:43:31');
INSERT INTO `tb_sys_sms_code` VALUES ('8e3b5cc0a0f047358f092eea57efdb24', 'sys', '2019-09-19 22:52:59', '', NULL, '1', '1', 1, '13810590781', '694735', '2019-09-19 22:58:00');
INSERT INTO `tb_sys_sms_code` VALUES ('8EC12231F48149878FDF52B4FCE8E3F5', 'sys', '2019-06-12 10:00:41', '', NULL, '1', '1', 1, '15910234107', '646641', '2019-06-12 10:05:41');
INSERT INTO `tb_sys_sms_code` VALUES ('8ede989a755a457696e16ebbce9cb7f3', 'sys', '2019-09-27 15:48:23', '', NULL, '1', '1', 1, '15210126360', '355218', '2019-09-27 15:53:23');
INSERT INTO `tb_sys_sms_code` VALUES ('8F9CFAA5FFA6464A87C93FCC93D868D8', 'sys', '2019-08-20 11:54:41', '', NULL, '1', '1', 1, '13701120677', '716199', '2019-08-20 11:59:41');
INSERT INTO `tb_sys_sms_code` VALUES ('8fa42e37c41747c395c9c2f2609cd864', 'sys', '2019-09-10 15:48:33', '', NULL, '1', '1', 1, '13301111360', '552163', '2019-09-10 15:53:33');
INSERT INTO `tb_sys_sms_code` VALUES ('907F1AC866C14540A82C5417696CD566', 'sys', '2019-06-04 15:31:18', '', NULL, '1', '1', 1, '18710002331', '311344', '2019-06-04 15:36:18');
INSERT INTO `tb_sys_sms_code` VALUES ('9098D2F2520B49529D4059882DF50060', 'sys', '2019-07-01 15:59:55', '', NULL, '1', '1', 1, '15210598852', '488619', '2019-07-01 16:04:56');
INSERT INTO `tb_sys_sms_code` VALUES ('936662AEBB6D45B49268DA4118D1C571', 'sys', '2019-08-12 15:49:52', '', NULL, '1', '1', 1, '13801086442', '974696', '2019-08-12 15:54:53');
INSERT INTO `tb_sys_sms_code` VALUES ('936E107CF939422AAF0C9F2D4FFD0289', 'sys', '2019-07-02 20:53:33', '', NULL, '1', '1', 1, '13581881826', '614848', '2019-07-02 20:58:34');
INSERT INTO `tb_sys_sms_code` VALUES ('93B9F32DAF1842CE8BCD34D37C6F7F20', 'sys', '2019-07-04 14:41:34', '', NULL, '1', '1', 1, '13581881826', '341194', '2019-07-04 14:46:35');
INSERT INTO `tb_sys_sms_code` VALUES ('93e7e71917b54c219168df4ec21cefc8', 'sys', '2019-08-29 10:06:17', '', NULL, '1', '1', 1, '15201623006', '941177', '2019-08-29 10:11:17');
INSERT INTO `tb_sys_sms_code` VALUES ('94b132735e97483c82b7f31b7856d632', 'sys', '2019-08-30 16:52:20', '', NULL, '1', '1', 1, '18701152634', '287328', '2019-08-30 16:57:20');
INSERT INTO `tb_sys_sms_code` VALUES ('94F5817CAAEF4CF9A1F773B7F804C0EE', 'sys', '2019-07-12 18:41:53', '', NULL, '1', '1', 1, '15910234107', '373581', '2019-07-12 18:46:53');
INSERT INTO `tb_sys_sms_code` VALUES ('953EC883D6074077B7256FE84764AB0F', 'sys', '2019-07-09 17:26:21', '', NULL, '1', '1', 1, '18703613965', '169238', '2019-07-09 17:31:21');
INSERT INTO `tb_sys_sms_code` VALUES ('95ee04dd4d314118bc90f95f95690987', 'sys', '2019-08-27 15:20:14', '', NULL, '1', '1', 1, '18810611699', '934163', '2019-08-27 15:25:14');
INSERT INTO `tb_sys_sms_code` VALUES ('961069446A1B4A1197D93C7CAA497F87', 'sys', '2019-07-09 17:48:43', '', NULL, '1', '1', 1, '18703613965', '597568', '2019-07-09 17:53:43');
INSERT INTO `tb_sys_sms_code` VALUES ('9620f22188a646d48fa58d457621d625', 'sys', '2019-08-28 17:19:30', '', NULL, '1', '1', 1, '13522456155', '189368', '2019-08-28 17:24:31');
INSERT INTO `tb_sys_sms_code` VALUES ('969FB93DB75B4C508BF086361077C2BE', 'sys', '2019-06-28 14:36:36', '', NULL, '1', '1', 1, '15910879637', '431125', '2019-06-28 14:41:37');
INSERT INTO `tb_sys_sms_code` VALUES ('97A4F2111B0549FF8BFBDD8900F75C06', 'sys', '2019-06-27 19:54:31', '', NULL, '1', '1', 1, '15736753571', '981894', '2019-06-27 19:59:32');
INSERT INTO `tb_sys_sms_code` VALUES ('97ab584d4d24487c8a94437611a66e12', 'sys', '2019-09-26 09:31:24', '', NULL, '1', '1', 1, '15910234107', '449712', '2019-09-26 09:36:25');
INSERT INTO `tb_sys_sms_code` VALUES ('9878A7DD2E224A79BEB793D000450EF6', 'sys', '2019-07-04 17:51:26', '', NULL, '1', '1', 1, '18601943273', '777572', '2019-07-04 17:56:27');
INSERT INTO `tb_sys_sms_code` VALUES ('99136D6075B5443F93DFB02737F4A072', 'sys', '2019-05-30 10:30:28', '', NULL, '1', '1', 1, '15910234107', '589626', '2019-05-30 10:35:28');
INSERT INTO `tb_sys_sms_code` VALUES ('99529be5d20c4f4bb4da055290fdd4fe', 'sys', '2019-08-28 16:36:39', '', NULL, '1', '1', 1, '13701299901', '243412', '2019-08-28 16:41:40');
INSERT INTO `tb_sys_sms_code` VALUES ('9ae398898d374def8a3fe4aed1f66b59', 'sys', '2019-08-30 16:10:59', '', NULL, '1', '1', 1, '13522555152', '835379', '2019-08-30 16:15:59');
INSERT INTO `tb_sys_sms_code` VALUES ('9af2412d50904118aaf3a55b0e95a58c', 'sys', '2019-09-16 16:33:25', '', NULL, '1', '1', 1, '13700020998', '667231', '2019-09-16 16:38:25');
INSERT INTO `tb_sys_sms_code` VALUES ('9b05ad4a5f6e46839fbf8ea05651e511', 'sys', '2019-09-27 13:51:28', '', NULL, '1', '1', 1, '18210158590', '548382', '2019-09-27 13:56:28');
INSERT INTO `tb_sys_sms_code` VALUES ('9B7FD71F5D6648BB97651FB3C3556513', 'sys', '2019-07-02 22:33:27', '', NULL, '1', '1', 1, '18601943273', '717412', '2019-07-02 22:38:28');
INSERT INTO `tb_sys_sms_code` VALUES ('9be41ac5866e444c99c24875dd3a2f53', 'sys', '2019-10-01 23:50:20', '', NULL, '1', '1', 1, '18146515233', '317117', '2019-10-01 23:55:21');
INSERT INTO `tb_sys_sms_code` VALUES ('9c658c7579dd4816a1cb59992c867b96', 'sys', '2019-09-22 23:57:49', '', NULL, '1', '1', 1, '18311210300', '853241', '2019-09-23 00:02:50');
INSERT INTO `tb_sys_sms_code` VALUES ('9D28FE0E17D94412BE04642C5FFE2583', 'sys', '2019-05-31 15:19:29', '', NULL, '1', '1', 1, '18710002331', '493859', '2019-05-31 15:24:29');
INSERT INTO `tb_sys_sms_code` VALUES ('9d70bdc05a18414d9006a77ff155ebd4', 'sys', '2019-09-01 09:06:30', '', NULL, '1', '1', 1, '15011363285', '183582', '2019-09-01 09:11:30');
INSERT INTO `tb_sys_sms_code` VALUES ('9E0B3A5A3C8B49F4BEC61B9C2A687AA9', 'sys', '2019-07-12 10:41:23', '', NULL, '1', '1', 1, '18703613965', '396616', '2019-07-12 10:46:24');
INSERT INTO `tb_sys_sms_code` VALUES ('9e8ee6eede7b43c0950c29833b3d886c', 'sys', '2019-09-02 17:09:32', '', NULL, '1', '1', 1, '15811201252', '576368', '2019-09-02 17:14:33');
INSERT INTO `tb_sys_sms_code` VALUES ('9eb68d9c669e4aae984db8e2fb347f47', 'sys', '2019-08-30 15:59:15', '', NULL, '1', '1', 1, '18901063536', '638435', '2019-08-30 16:04:15');
INSERT INTO `tb_sys_sms_code` VALUES ('9F08EA93919A4C71BEB5E0746A09C997', 'sys', '2019-07-11 17:20:49', '', NULL, '1', '1', 1, '18703913965', '678981', '2019-07-11 17:25:50');
INSERT INTO `tb_sys_sms_code` VALUES ('9F23B0FF4BD1413BA9234FDE4582DA6D', 'sys', '2019-07-08 13:36:34', '', NULL, '1', '1', 1, '17398982323', '726958', '2019-07-08 13:41:34');
INSERT INTO `tb_sys_sms_code` VALUES ('9FC51B7894F24A2D91EF9117F1B0DA68', 'sys', '2019-08-15 15:38:21', '', NULL, '1', '1', 1, '18600046631', '888197', '2019-08-15 15:43:22');
INSERT INTO `tb_sys_sms_code` VALUES ('9fd888496d55468c9e7609551af2cfd5', 'sys', '2019-10-13 12:31:06', '', NULL, '1', '1', 1, '13811505593', '168775', '2019-10-13 12:36:06');
INSERT INTO `tb_sys_sms_code` VALUES ('9FE6B1AA0F494DAAAB1AC4AFB40EEB78', 'sys', '2019-05-22 17:05:45', '', NULL, '1', '1', 1, '15910234107', '451266', '2019-05-22 17:10:46');
INSERT INTO `tb_sys_sms_code` VALUES ('a07ef280626a464caeae5b1748d8df39', 'sys', '2019-09-23 14:12:17', '', NULL, '1', '1', 1, '18800005261', '625299', '2019-09-23 14:17:17');
INSERT INTO `tb_sys_sms_code` VALUES ('A0C90D7D12504FE2AC228BA72D04CFC9', 'sys', '2019-07-10 09:27:07', '', NULL, '1', '1', 1, '17398982323', '471625', '2019-07-10 09:32:07');
INSERT INTO `tb_sys_sms_code` VALUES ('a13a8204aa934bfb901c74edabb88f31', 'sys', '2019-10-11 20:50:05', '', NULL, '1', '1', 1, '18831009110', '229263', '2019-10-11 20:55:05');
INSERT INTO `tb_sys_sms_code` VALUES ('A14D33E7615C498780BC7873CBB55206', 'sys', '2019-07-05 09:18:27', '', NULL, '1', '1', 1, '17638101635', '311558', '2019-07-05 09:23:27');
INSERT INTO `tb_sys_sms_code` VALUES ('a2b30494371c4c28b90710a27addc151', 'sys', '2019-09-17 09:53:00', '', NULL, '1', '1', 1, '13911752785', '457596', '2019-09-17 09:58:01');
INSERT INTO `tb_sys_sms_code` VALUES ('a3a4ff126fdc4658a3f5be13315b344b', 'sys', '2019-08-29 10:13:20', '', NULL, '1', '1', 1, '13581881826', '882797', '2019-08-29 10:18:20');
INSERT INTO `tb_sys_sms_code` VALUES ('A4368A8608EC4118B7F625CC6EA322B1', 'sys', '2019-07-02 06:53:39', '', NULL, '1', '1', 1, '13911301227', '484645', '2019-07-02 06:58:40');
INSERT INTO `tb_sys_sms_code` VALUES ('A46221D6CC4A46FE85B8304AE9465153', 'sys', '2019-05-31 13:40:11', '', NULL, '1', '1', 1, '18519785936', '535719', '2019-05-31 13:45:11');
INSERT INTO `tb_sys_sms_code` VALUES ('A4C68059C38D45459DC13ADCF09836E3', 'sys', '2019-05-30 16:05:03', '', NULL, '1', '1', 1, '18710096183', '988346', '2019-05-30 16:10:03');
INSERT INTO `tb_sys_sms_code` VALUES ('a57343eecbbe4ad38bea459d79b00ddd', 'sys', '2019-09-05 09:21:15', '', NULL, '1', '1', 1, '13801086442', '146411', '2019-09-05 09:26:15');
INSERT INTO `tb_sys_sms_code` VALUES ('a58bbae821d244f8a0f7bc1941bde0a5', 'sys', '2019-09-05 16:37:34', '', NULL, '1', '1', 1, '13601085197', '392543', '2019-09-05 16:42:34');
INSERT INTO `tb_sys_sms_code` VALUES ('A5D3997FF30E487A8078CD8B24650CB3', 'sys', '2019-08-16 11:24:47', '', NULL, '1', '1', 1, '18601304165', '434375', '2019-08-16 11:29:47');
INSERT INTO `tb_sys_sms_code` VALUES ('a5d95b78d20f4ab3a71201770121c808', 'sys', '2019-09-27 21:30:12', '', NULL, '1', '1', 1, '13521299730', '951666', '2019-09-27 21:35:13');
INSERT INTO `tb_sys_sms_code` VALUES ('A608815618864B608B8648B97B301FA6', 'sys', '2019-05-23 09:30:17', '', NULL, '1', '1', 1, '13601011360', '779443', '2019-05-23 09:35:18');
INSERT INTO `tb_sys_sms_code` VALUES ('A83B1341EA3148AFBA4E5BDBD920DFBB', 'sys', '2019-07-11 14:54:07', '', NULL, '1', '1', 1, '18703613965', '941657', '2019-07-11 14:59:08');
INSERT INTO `tb_sys_sms_code` VALUES ('A88E734D2E9143E1AF40A3594A38D6DD', 'sys', '2019-05-15 10:19:39', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-05-17 11:11:40', '1', '1', 1, '18888888888', '123456', '2020-06-17 11:11:31');
INSERT INTO `tb_sys_sms_code` VALUES ('AB65A613FDD4433392184BBB02B781EB', 'sys', '2019-07-08 11:03:51', '', NULL, '1', '1', 1, '18710002331', '861596', '2019-07-08 11:08:51');
INSERT INTO `tb_sys_sms_code` VALUES ('abafaca70db24f4f9a3e50b474c04dc1', 'sys', '2019-08-29 08:05:48', '', NULL, '1', '1', 1, '13911165737', '773431', '2019-08-29 08:10:49');
INSERT INTO `tb_sys_sms_code` VALUES ('abbb63351484408f9b4faca9c30f7e2c', 'sys', '2019-08-28 16:41:08', '', NULL, '1', '1', 1, '15011436223', '363315', '2019-08-28 16:46:09');
INSERT INTO `tb_sys_sms_code` VALUES ('abe24b88b89748b4b5fce04dfa35dcec', 'sys', '2019-08-28 23:55:57', '', NULL, '1', '1', 1, '18514735018', '151883', '2019-08-29 00:00:58');
INSERT INTO `tb_sys_sms_code` VALUES ('ac875d759e3d4c428b95ca6adc964827', 'sys', '2019-10-11 16:13:32', '', NULL, '1', '1', 1, '18601117706', '389379', '2019-10-11 16:18:32');
INSERT INTO `tb_sys_sms_code` VALUES ('AD3C629F7AF5465EA0D675BB5ACD6D65', 'sys', '2019-06-19 11:09:30', '', NULL, '1', '1', 1, '18537338023', '887836', '2019-06-19 11:14:31');
INSERT INTO `tb_sys_sms_code` VALUES ('ae16b3b5eed94682a4387312a83f1e37', 'sys', '2019-09-25 11:02:15', '', NULL, '1', '1', 1, '13141039522', '594936', '2019-09-25 11:07:16');
INSERT INTO `tb_sys_sms_code` VALUES ('ae753a7486f64909bb8b71551abf0673', 'sys', '2019-09-04 13:28:23', '', NULL, '1', '1', 1, '13699224524', '666255', '2019-09-04 13:33:24');
INSERT INTO `tb_sys_sms_code` VALUES ('AE7E0A3083574F1C90A9D61950A42729', 'sys', '2019-07-02 09:53:34', '', NULL, '1', '1', 1, '18600977360', '333478', '2019-07-02 09:58:35');
INSERT INTO `tb_sys_sms_code` VALUES ('AFA0DD5D901144E2B5BEB9E998580158', 'sys', '2019-05-27 13:59:33', '', NULL, '1', '1', 1, '15910234107', '648613', '2019-05-27 14:04:34');
INSERT INTO `tb_sys_sms_code` VALUES ('B02B1A6F1820497789D60DFB38607734', 'sys', '2019-06-13 11:44:41', '', NULL, '1', '1', 1, '13301111360', '823996', '2019-06-13 11:49:42');
INSERT INTO `tb_sys_sms_code` VALUES ('B08076AFAED542E88C0DC7CA61479145', 'sys', '2019-07-17 13:54:05', '', NULL, '1', '1', 1, '15910234107', '178712', '2019-07-17 13:59:06');
INSERT INTO `tb_sys_sms_code` VALUES ('B0E49CA539A44D14B4B27E2A73A32881', 'sys', '2019-07-09 17:47:16', '', NULL, '1', '1', 1, '18703613965', '146695', '2019-07-09 17:52:16');
INSERT INTO `tb_sys_sms_code` VALUES ('B0F072D7AD5C4438A2937C8CBA755D16', 'sys', '2019-06-10 15:07:09', '', NULL, '1', '1', 1, '15910234107', '745457', '2019-06-10 15:12:10');
INSERT INTO `tb_sys_sms_code` VALUES ('b124aa57729f4075816cb15b3f27709b', 'sys', '2019-09-11 22:44:02', '', NULL, '1', '1', 1, '13601376703', '747642', '2019-09-11 22:49:02');
INSERT INTO `tb_sys_sms_code` VALUES ('B129D71ABE244A2CAAC218A82D4E712C', 'sys', '2019-07-29 19:14:18', '', NULL, '1', '1', 1, '15736753571', '432541', '2019-07-29 19:19:19');
INSERT INTO `tb_sys_sms_code` VALUES ('B13C6AB640A5467F8F3194E28787E7AB', 'sys', '2019-06-13 17:20:10', '', NULL, '1', '1', 1, '13581881826', '343737', '2019-06-13 17:25:11');
INSERT INTO `tb_sys_sms_code` VALUES ('b1651ea5557e42a08cc002db186ef15f', 'sys', '2019-08-28 17:51:03', '', NULL, '1', '1', 1, '17610375908', '953666', '2019-08-28 17:56:04');
INSERT INTO `tb_sys_sms_code` VALUES ('b1761b9b197f47fab86bb0b7d6ab25f0', 'sys', '2019-09-04 16:37:26', '', NULL, '1', '1', 1, '13701139951', '763153', '2019-09-04 16:42:27');
INSERT INTO `tb_sys_sms_code` VALUES ('b1a9298da0cf4865a874a34494665dd1', 'sys', '2019-08-28 16:38:11', '', NULL, '1', '1', 1, '15901248180', '576771', '2019-08-28 16:43:11');
INSERT INTO `tb_sys_sms_code` VALUES ('b2495c94d7ff4cbf85c055ca934f2c51', 'sys', '2019-09-04 16:59:54', '', NULL, '1', '1', 1, '15117978092', '441663', '2019-09-04 17:04:54');
INSERT INTO `tb_sys_sms_code` VALUES ('b2894b9dc7a841fd8a9120646ab3428d', 'sys', '2019-10-02 18:13:45', '', NULL, '1', '1', 1, '13910956423', '373759', '2019-10-02 18:18:46');
INSERT INTO `tb_sys_sms_code` VALUES ('b2a43f3279a649678103efea081d477b', 'sys', '2019-08-28 17:35:56', '', NULL, '1', '1', 1, '15910328090', '322199', '2019-08-28 17:40:56');
INSERT INTO `tb_sys_sms_code` VALUES ('b3537e758bf64d04ac49570c89370c8c', 'sys', '2019-08-28 17:59:41', '', NULL, '1', '1', 1, '13811115342', '661864', '2019-08-28 18:04:41');
INSERT INTO `tb_sys_sms_code` VALUES ('b41ebc159f6c40a9a470801582af8c5e', 'sys', '2019-09-27 11:12:18', '', NULL, '1', '1', 1, '13801086442', '168353', '2019-09-27 11:17:18');
INSERT INTO `tb_sys_sms_code` VALUES ('B437EFC048CF49D39B6E1415DBB99284', 'sys', '2019-07-24 15:24:55', '', NULL, '1', '1', 1, '15910234107', '379224', '2019-07-24 15:29:56');
INSERT INTO `tb_sys_sms_code` VALUES ('B49B9F53774B4E11BD29A31F42AA2E58', 'sys', '2019-06-11 17:11:46', '', NULL, '1', '1', 1, '13581881826', '529622', '2019-06-11 17:16:47');
INSERT INTO `tb_sys_sms_code` VALUES ('B4A1120A74D1403AA1413C6A3D504177', 'sys', '2019-07-12 17:17:58', '', NULL, '1', '1', 1, '15910234107', '458667', '2019-07-12 17:22:59');
INSERT INTO `tb_sys_sms_code` VALUES ('b4eea68f3db34af1ba1c9fb40463ae4a', 'sys', '2019-09-26 17:30:24', '', NULL, '1', '1', 1, '13716003728', '388898', '2019-09-26 17:35:24');
INSERT INTO `tb_sys_sms_code` VALUES ('b52e0b71fa3241deac361c317dc635d2', 'sys', '2019-08-28 17:55:10', '', NULL, '1', '1', 1, '18611315400', '269938', '2019-08-28 18:00:10');
INSERT INTO `tb_sys_sms_code` VALUES ('B5A2E4729B3A4F8D9DECC17F056437A9', 'sys', '2019-06-29 22:04:29', '', NULL, '1', '1', 1, '15238634798', '578953', '2019-06-29 22:09:30');
INSERT INTO `tb_sys_sms_code` VALUES ('B5CB41E2AFE445EEB1CF33092C8258F6', 'sys', '2019-06-10 10:15:03', '', NULL, '1', '1', 1, '18710002331', '959181', '2019-06-10 10:20:04');
INSERT INTO `tb_sys_sms_code` VALUES ('B610CCB33992446B935AFB1F73C488A3', 'sys', '2019-05-31 13:52:03', '', NULL, '1', '1', 1, '18519785936', '829335', '2019-05-31 13:57:04');
INSERT INTO `tb_sys_sms_code` VALUES ('B66FB564693E47B59CF88743CD35AC1B', 'sys', '2019-06-27 15:55:29', '', NULL, '1', '1', 1, '15210598852', '631433', '2019-06-27 16:00:29');
INSERT INTO `tb_sys_sms_code` VALUES ('b80bd810e1f249eeb2a31fdaf27a9603', 'sys', '2019-08-29 12:26:57', '', NULL, '1', '1', 1, '13701009795', '179143', '2019-08-29 12:31:57');
INSERT INTO `tb_sys_sms_code` VALUES ('b85dccf84ac34960aeec7dab6fa89579', 'sys', '2019-08-28 16:42:40', '', NULL, '1', '1', 1, '18701315054', '927847', '2019-08-28 16:47:40');
INSERT INTO `tb_sys_sms_code` VALUES ('B85E430AA1734D5CAB0194A0302469E4', 'sys', '2019-07-03 17:43:06', '', NULL, '1', '1', 1, '18710096183', '438369', '2019-07-03 17:48:07');
INSERT INTO `tb_sys_sms_code` VALUES ('B9166DCEB17040B2801C4CB2735C3D6A', 'sys', '2019-06-04 15:28:38', '', NULL, '1', '1', 1, '18710002331', '427399', '2019-06-04 15:33:38');
INSERT INTO `tb_sys_sms_code` VALUES ('B9449AD8AA0E4780A17EDEB8A65ACB5B', 'sys', '2019-06-12 13:42:48', '', NULL, '1', '1', 1, '15910234107', '751633', '2019-06-12 13:47:49');
INSERT INTO `tb_sys_sms_code` VALUES ('b982c39afbff4c50a9aeaab210ae7ff1', 'sys', '2019-08-28 16:38:29', '', NULL, '1', '1', 1, '13426315929', '911144', '2019-08-28 16:43:30');
INSERT INTO `tb_sys_sms_code` VALUES ('b9f9e9e5dcd94439a00f358c247d20f2', 'sys', '2019-08-29 05:28:32', '', NULL, '1', '1', 1, '15910294844', '531545', '2019-08-29 05:33:32');
INSERT INTO `tb_sys_sms_code` VALUES ('ba395e6e101449049c7cc3eda5d9fbc8', 'sys', '2019-08-28 22:35:37', '', NULL, '1', '1', 1, '13501282040', '648766', '2019-08-28 22:40:38');
INSERT INTO `tb_sys_sms_code` VALUES ('BA7169AD2086408797AEF15F130CECC7', 'sys', '2019-06-30 13:12:09', '', NULL, '1', '1', 1, '17398982323', '373965', '2019-06-30 13:17:10');
INSERT INTO `tb_sys_sms_code` VALUES ('BA9A216F089646519ABCF2DBC46FAF0A', 'sys', '2019-07-09 17:41:38', '', NULL, '1', '1', 1, '18703613965', '998246', '2019-07-09 17:46:39');
INSERT INTO `tb_sys_sms_code` VALUES ('bab07b9209984d8888730bcb075d6ebb', 'sys', '2019-08-28 19:01:26', '', NULL, '1', '1', 1, '13910083360', '272132', '2019-08-28 19:06:27');
INSERT INTO `tb_sys_sms_code` VALUES ('bbb9c39234314403a61d5cf4b6e599ac', 'sys', '2019-08-28 22:19:22', '', NULL, '1', '1', 1, '18611741530', '497671', '2019-08-28 22:24:22');
INSERT INTO `tb_sys_sms_code` VALUES ('bbbf5ba1699348e8b446190e4f9e7503', 'sys', '2019-08-28 16:56:01', '', NULL, '1', '1', 1, '13641066216', '769874', '2019-08-28 17:01:02');
INSERT INTO `tb_sys_sms_code` VALUES ('BBE0046AC71549C991C8DFA7D0879D81', 'sys', '2019-07-09 17:30:34', '', NULL, '1', '1', 1, '18703613965', '766199', '2019-07-09 17:35:35');
INSERT INTO `tb_sys_sms_code` VALUES ('bbe4d5fdb4b14b378b28680ee838d635', 'sys', '2019-09-21 20:11:38', '', NULL, '1', '1', 1, '13671000918', '248872', '2019-09-21 20:16:39');
INSERT INTO `tb_sys_sms_code` VALUES ('BC1CE413CF5344418E5CD164CCFE7CA3', 'sys', '2019-07-25 09:44:01', '', NULL, '1', '1', 1, '15117978092', '471762', '2019-07-25 09:49:02');
INSERT INTO `tb_sys_sms_code` VALUES ('bc7490ab58cf4fee882112b6ff623845', 'sys', '2019-09-12 22:24:45', '', NULL, '1', '1', 1, '15811141792', '521874', '2019-09-12 22:29:45');
INSERT INTO `tb_sys_sms_code` VALUES ('BCFEA9471DE84B42841586BE54A3D0DA', 'sys', '2019-07-02 22:17:57', '', NULL, '1', '1', 1, '17638101635', '691866', '2019-07-02 22:22:57');
INSERT INTO `tb_sys_sms_code` VALUES ('bd362a5ef8954a83be5e9ed697e05cc6', 'sys', '2019-08-27 15:46:57', '', NULL, '1', '1', 1, '13683321128', '884799', '2019-08-27 15:51:57');
INSERT INTO `tb_sys_sms_code` VALUES ('BD8570BC3CA948E692610A044B731B4F', 'sys', '2019-07-09 17:43:30', '', NULL, '1', '1', 1, '18703613965', '543731', '2019-07-09 17:48:31');
INSERT INTO `tb_sys_sms_code` VALUES ('BF8269DA94A74E5CB191261E75CA6985', 'sys', '2019-07-05 11:06:32', '', NULL, '1', '1', 1, '13520451310', '831336', '2019-07-05 11:11:33');
INSERT INTO `tb_sys_sms_code` VALUES ('BFB71FF31DAB4394A4532F44DB031AF2', 'sys', '2019-05-28 10:25:39', '', NULL, '1', '1', 1, '13581881826', '336629', '2019-05-28 10:30:39');
INSERT INTO `tb_sys_sms_code` VALUES ('C314A81C1A50430B937B9CF7318B2C85', 'sys', '2019-07-11 17:21:51', '', NULL, '1', '1', 1, '18703613965', '759684', '2019-07-11 17:26:51');
INSERT INTO `tb_sys_sms_code` VALUES ('c3329e6a5870418697db47386bb817b5', 'sys', '2019-08-28 23:06:36', '', NULL, '1', '1', 1, '13001203949', '779373', '2019-08-28 23:11:37');
INSERT INTO `tb_sys_sms_code` VALUES ('c45eb418ffc64480a3874422e220d195', 'sys', '2019-09-20 10:05:55', '', NULL, '1', '1', 1, '18610759360', '733354', '2019-09-20 10:10:55');
INSERT INTO `tb_sys_sms_code` VALUES ('c485721a2e9d4baba494ec96aedfdc7d', 'sys', '2019-09-17 18:43:16', '', NULL, '1', '1', 1, '13717561680', '419875', '2019-09-17 18:48:16');
INSERT INTO `tb_sys_sms_code` VALUES ('c55fa5bba1cf4b98851a9a1e5524c618', 'sys', '2019-09-05 21:19:32', '', NULL, '1', '1', 1, '15801323943', '194626', '2019-09-05 21:24:33');
INSERT INTO `tb_sys_sms_code` VALUES ('C5D980CB98944CEAAD112B2EC65C5F17', 'sys', '2019-07-09 10:33:34', '', NULL, '1', '1', 1, '18710002331', '154813', '2019-07-09 10:38:34');
INSERT INTO `tb_sys_sms_code` VALUES ('C5DD1D2C12B146939BF211548C5F614D', 'sys', '2019-07-05 00:46:07', '', NULL, '1', '1', 1, '17638101635', '182177', '2019-07-05 00:51:07');
INSERT INTO `tb_sys_sms_code` VALUES ('C5FCBF2395DD4CE0B7923C8054C7491B', 'sys', '2019-06-18 18:03:36', '', NULL, '1', '1', 1, '17398982323', '731498', '2019-06-18 18:08:37');
INSERT INTO `tb_sys_sms_code` VALUES ('C6167DE6C8A44E2A936213ABC92F14C8', 'sys', '2019-07-05 16:58:45', '', NULL, '1', '1', 1, '17398982323', '922138', '2019-07-05 17:03:45');
INSERT INTO `tb_sys_sms_code` VALUES ('C62B6DD2B9C54CF69540276A74B4E481', 'sys', '2019-07-12 18:36:59', '', NULL, '1', '1', 1, '15910234107', '698861', '2019-07-12 18:42:00');
INSERT INTO `tb_sys_sms_code` VALUES ('C6B7A2AA0B5B476BB8E3F5DF045F7FDB', 'sys', '2019-07-03 16:33:20', '', NULL, '1', '1', 1, '18601943273', '578525', '2019-07-03 16:38:20');
INSERT INTO `tb_sys_sms_code` VALUES ('c72209480a764dfdbdf4ffdc0a90b6bf', 'sys', '2019-08-28 18:55:05', '', NULL, '1', '1', 1, '15910285726', '791443', '2019-08-28 19:00:05');
INSERT INTO `tb_sys_sms_code` VALUES ('C79FF1F64FE64207858EBED6D40C7835', 'sys', '2019-07-02 14:37:53', '', NULL, '1', '1', 1, '13683387578', '819555', '2019-07-02 14:42:53');
INSERT INTO `tb_sys_sms_code` VALUES ('c7e7a83ef9684fbb826acfe0d549d1e5', 'sys', '2019-08-28 17:00:39', '', NULL, '1', '1', 1, '13466584134', '714638', '2019-08-28 17:05:39');
INSERT INTO `tb_sys_sms_code` VALUES ('C833334529EA403F8C83EB79206DD6E7', 'sys', '2019-07-03 20:48:54', '', NULL, '1', '1', 1, '18710096183', '998442', '2019-07-03 20:53:54');
INSERT INTO `tb_sys_sms_code` VALUES ('C8BF4AAF003C4BDABE6856F86382E7AD', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-05-29 17:46:57', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-05-29 17:56:58', '1', '1', 1, '18601943273', '581855', '2019-05-30 17:56:52');
INSERT INTO `tb_sys_sms_code` VALUES ('c9049982d60e417e9d657f432f9abd5b', 'sys', '2019-09-27 15:31:44', '', NULL, '1', '1', 1, '13717839411', '215334', '2019-09-27 15:36:45');
INSERT INTO `tb_sys_sms_code` VALUES ('c9950140855a41c1b46949c10361330f', 'sys', '2019-08-28 17:02:32', '', NULL, '1', '1', 1, '13311394469', '778625', '2019-08-28 17:07:32');
INSERT INTO `tb_sys_sms_code` VALUES ('CA088A85B0F141AD8C9C0D3CCB794E2A', '4fe8709c15ef4cc6a96a8e366797bb4c', '2019-07-02 21:03:01', '', NULL, '1', '1', 1, '15736753571', '946474', '2019-07-02 21:08:01');
INSERT INTO `tb_sys_sms_code` VALUES ('CB373DD2C2E24CDC96791CC40CF7ECB2', 'sys', '2019-06-12 11:56:07', '', NULL, '1', '1', 1, '18710002331', '476898', '2019-06-12 12:01:08');
INSERT INTO `tb_sys_sms_code` VALUES ('CB5700F407484F828B34B9339DD35BE8', 'sys', '2019-07-12 10:45:54', '', NULL, '1', '1', 1, '18703613965', '643125', '2019-07-12 10:50:55');
INSERT INTO `tb_sys_sms_code` VALUES ('CB9901E2C715404B820884AAFD289B4E', 'sys', '2019-08-06 09:23:26', '', NULL, '1', '1', 1, '13520451310', '558616', '2019-08-06 09:28:27');
INSERT INTO `tb_sys_sms_code` VALUES ('CBC0609EC7B742ECA0037BF747FCC98A', 'sys', '2019-06-11 16:58:44', '', NULL, '1', '1', 1, '15910234107', '476516', '2019-06-11 17:03:44');
INSERT INTO `tb_sys_sms_code` VALUES ('cbdff81fb29d40f790bc49690f309904', 'sys', '2019-08-28 16:39:45', '', NULL, '1', '1', 1, '13911180192', '795662', '2019-08-28 16:44:46');
INSERT INTO `tb_sys_sms_code` VALUES ('CC1290CC18CB45D1905DD15D08A13355', 'sys', '2019-07-02 09:02:24', '', NULL, '1', '1', 1, '18601943273', '283548', '2019-07-02 09:07:24');
INSERT INTO `tb_sys_sms_code` VALUES ('cc3ec265a81b47d8a026e14c1f2c89ee', 'sys', '2019-09-20 19:50:45', '', NULL, '1', '1', 1, '13716308323', '415168', '2019-09-20 19:55:45');
INSERT INTO `tb_sys_sms_code` VALUES ('cd047ba3da5044cb80ca2aebeede8cb4', 'sys', '2019-08-28 16:36:51', '', NULL, '1', '1', 1, '18600598570', '257138', '2019-08-28 16:41:51');
INSERT INTO `tb_sys_sms_code` VALUES ('CD60F5BCB65746A7AA9FC6094FCF6D3D', 'sys', '2019-07-03 10:29:12', '', NULL, '1', '1', 1, '18710096183', '839281', '2019-07-03 10:34:12');
INSERT INTO `tb_sys_sms_code` VALUES ('CE41865356B54E499FC988B020D455CB', 'sys', '2019-07-02 21:10:54', '', NULL, '1', '1', 1, '18601943273', '442318', '2019-07-02 21:15:54');
INSERT INTO `tb_sys_sms_code` VALUES ('ce977b4b190640c4b6300ce11c64bd56', 'sys', '2019-09-02 18:02:46', '', NULL, '1', '1', 1, '13601369365', '843244', '2019-09-02 18:07:46');
INSERT INTO `tb_sys_sms_code` VALUES ('cfda2aaffb934d9da53117cf5ef513a9', 'sys', '2019-08-28 09:53:41', '', NULL, '1', '1', 1, '13521840674', '447877', '2019-08-28 09:58:42');
INSERT INTO `tb_sys_sms_code` VALUES ('d04c4aa613f4476a8c60bc1a250e9c3e', 'sys', '2019-08-30 08:49:30', '', NULL, '1', '1', 1, '13241636167', '625241', '2019-08-30 08:54:31');
INSERT INTO `tb_sys_sms_code` VALUES ('d05fa563e3e942c59882c616663c726e', 'sys', '2019-09-12 07:24:05', '', NULL, '1', '1', 1, '18610262011', '489969', '2019-09-12 07:29:06');
INSERT INTO `tb_sys_sms_code` VALUES ('D0BA3D4BE24F495E89E2CDEEDCAA6B37', 'sys', '2019-05-31 15:23:23', '', NULL, '1', '1', 1, '15910234107', '269854', '2019-05-31 15:28:23');
INSERT INTO `tb_sys_sms_code` VALUES ('D0FBC5E99B644293A9FB6FB5076B875A', 'sys', '2019-07-09 17:39:02', '', NULL, '1', '1', 1, '18703613965', '767872', '2019-07-09 17:44:03');
INSERT INTO `tb_sys_sms_code` VALUES ('D107E39D49EB423189512515ADA0304D', 'sys', '2019-08-19 21:37:39', '', NULL, '1', '1', 1, '13681251488', '157729', '2019-08-19 21:42:40');
INSERT INTO `tb_sys_sms_code` VALUES ('D1916EC401604B9A9BAF1AD7BADC03D1', 'sys', '2019-07-09 17:59:43', '', NULL, '1', '1', 1, '18703613965', '155954', '2019-07-09 18:04:43');
INSERT INTO `tb_sys_sms_code` VALUES ('D2431DAB863446D689B7E01FBAAC6465', 'sys', '2019-07-09 19:28:57', '', NULL, '1', '1', 1, '18601085488', '937928', '2019-07-09 19:33:58');
INSERT INTO `tb_sys_sms_code` VALUES ('D31208CEACD14710A38EA129CD06A0C6', 'sys', '2019-07-09 10:04:54', '', NULL, '1', '1', 1, '18703613965', '295149', '2019-07-09 10:09:54');
INSERT INTO `tb_sys_sms_code` VALUES ('D31BAAAA367F49DAACF731FADBF17BCE', 'sys', '2019-06-12 11:17:25', '', NULL, '1', '1', 1, '15910234107', '563463', '2019-06-12 11:22:25');
INSERT INTO `tb_sys_sms_code` VALUES ('d32af5caa8114989bc74539162e84757', 'sys', '2019-08-28 22:44:19', '', NULL, '1', '1', 1, '13621399363', '335915', '2019-08-28 22:49:19');
INSERT INTO `tb_sys_sms_code` VALUES ('D3D2BCEBC7E149A482C24AF4DE46D69C', 'sys', '2019-07-02 21:56:29', '', NULL, '1', '1', 1, '17638101635', '596822', '2019-07-02 22:01:29');
INSERT INTO `tb_sys_sms_code` VALUES ('d4b5b8ba6233449aae51b26bfbba5ddd', 'sys', '2019-09-17 19:12:16', '', NULL, '1', '1', 1, '13581972580', '577319', '2019-09-17 19:17:17');
INSERT INTO `tb_sys_sms_code` VALUES ('d53213b3b0d04e9897f76a653b57b7ef', 'sys', '2019-08-28 21:27:40', '', NULL, '1', '1', 1, '13439385431', '668984', '2019-08-28 21:32:41');
INSERT INTO `tb_sys_sms_code` VALUES ('d53d26de967c4ef0a3f5a1488a77ab60', 'sys', '2019-09-05 17:15:55', '', NULL, '1', '1', 1, '15910879637', '153572', '2019-09-05 17:20:55');
INSERT INTO `tb_sys_sms_code` VALUES ('D58585FFA0E040CCBDAC1D3C4B144171', 'sys', '2019-05-30 13:28:25', '', NULL, '1', '1', 1, '17638101635', '715856', '2019-05-30 13:33:25');
INSERT INTO `tb_sys_sms_code` VALUES ('d5c4881bc9eb4c20ada0b8ea6d81ea09', 'sys', '2019-08-28 17:53:37', '', NULL, '1', '1', 1, '13581500515', '539384', '2019-08-28 17:58:38');
INSERT INTO `tb_sys_sms_code` VALUES ('d5e6f8f7d5664699aaafc94d8c329963', 'sys', '2019-08-30 08:48:11', '', NULL, '1', '1', 1, '13241636167', '265119', '2019-08-30 08:53:11');
INSERT INTO `tb_sys_sms_code` VALUES ('D678B78D52E24C8BBED2A938FFB292EE', 'sys', '2019-06-09 14:47:02', '', NULL, '1', '1', 1, '15910234107', '649326', '2019-06-09 14:52:02');
INSERT INTO `tb_sys_sms_code` VALUES ('d6e79f4d8f4a46e18c18ad74c73e60e8', 'sys', '2019-08-30 00:14:18', '', NULL, '1', '1', 1, '13581675866', '787718', '2019-08-30 00:19:18');
INSERT INTO `tb_sys_sms_code` VALUES ('d789905410eb4ca4a97961820a712e22', 'sys', '2019-08-28 18:24:00', '', NULL, '1', '1', 1, '13716196129', '648837', '2019-08-28 18:29:00');
INSERT INTO `tb_sys_sms_code` VALUES ('D9BCAA6DEA9E4A3E9E97C2A67728F604', 'sys', '2019-06-06 17:06:35', '', NULL, '1', '1', 1, '13581881826', '718549', '2019-06-06 17:11:35');
INSERT INTO `tb_sys_sms_code` VALUES ('dadf9d11f6654efb8b9bf1d6c1aaff79', 'sys', '2019-08-29 01:01:25', '', NULL, '1', '1', 1, '13911566151', '836216', '2019-08-29 01:06:26');
INSERT INTO `tb_sys_sms_code` VALUES ('DADFE3753513471CB3814F2975808B39', '2A294F80EA0B46008FB894AF4F64A2AF', '2019-05-22 15:50:49', '', NULL, '1', '1', 1, '15910234107', '371747', '2019-05-22 15:55:50');
INSERT INTO `tb_sys_sms_code` VALUES ('db68a2b5fe2b411483876128a1766465', 'sys', '2019-09-03 07:05:14', '', NULL, '1', '1', 1, '13121455060', '577394', '2019-09-03 07:10:14');
INSERT INTO `tb_sys_sms_code` VALUES ('DBB092AEEFE14881A65FB4CEECF4E906', 'sys', '2019-08-20 14:59:21', '', NULL, '1', '1', 1, '18210173931', '979845', '2019-08-20 15:04:22');
INSERT INTO `tb_sys_sms_code` VALUES ('dc56ede00d924664a791c27ffc1707a0', 'sys', '2019-09-25 12:07:10', '', NULL, '1', '1', 1, '18610830191', '142725', '2019-09-25 12:12:11');
INSERT INTO `tb_sys_sms_code` VALUES ('dc9bc15c3d48424f813ea625497de462', 'sys', '2019-08-28 16:41:01', '', NULL, '1', '1', 1, '15011436223', '675948', '2019-08-28 16:46:02');
INSERT INTO `tb_sys_sms_code` VALUES ('DF2DE26FF1AB42DC9FAD7CD6EC19E7A9', 'sys', '2019-08-12 15:48:22', '', NULL, '1', '1', 1, '18610728220', '365945', '2019-08-12 15:53:23');
INSERT INTO `tb_sys_sms_code` VALUES ('DFE426705078480AB570AEF82AB13454', 'sys', '2019-06-10 10:34:17', '', NULL, '1', '1', 1, '18710002331', '173279', '2019-06-10 10:39:17');
INSERT INTO `tb_sys_sms_code` VALUES ('E038354312324670B059C73C80AD0916', 'sys', '2019-07-26 16:41:10', '', NULL, '1', '1', 1, '18751981837', '424178', '2019-07-26 16:46:11');
INSERT INTO `tb_sys_sms_code` VALUES ('E04793E69373409693B13569804AF04D', 'sys', '2019-07-15 09:12:52', '', NULL, '1', '1', 1, '18703613965', '671216', '2019-07-15 09:17:52');
INSERT INTO `tb_sys_sms_code` VALUES ('e1075e9747b34851992f772322faf503', 'sys', '2019-08-28 10:07:08', '', NULL, '1', '1', 1, '18610366606', '582589', '2019-08-28 10:12:09');
INSERT INTO `tb_sys_sms_code` VALUES ('E16DC593D21A4535BCA1C21AD68B7A30', 'sys', '2019-07-09 17:46:23', '', NULL, '1', '1', 1, '18703613965', '125198', '2019-07-09 17:51:24');
INSERT INTO `tb_sys_sms_code` VALUES ('E20976F6434849E08DB2F745B801D605', 'sys', '2019-07-09 15:59:21', '', NULL, '1', '1', 1, '15910234107', '387378', '2019-07-09 16:04:21');
INSERT INTO `tb_sys_sms_code` VALUES ('e26ac8636a864078bb9d739732eb8631', 'sys', '2019-08-29 11:52:45', '', NULL, '1', '1', 1, '13701398770', '664658', '2019-08-29 11:57:45');
INSERT INTO `tb_sys_sms_code` VALUES ('E2A2863F3F5E46E08926508DE6304E1C', 'sys', '2019-08-12 16:31:47', '', NULL, '1', '1', 1, '13716428226', '821798', '2019-08-12 16:36:47');
INSERT INTO `tb_sys_sms_code` VALUES ('E331A6A65E764152A5E3E7FAF485D3AF', 'sys', '2019-08-21 16:46:53', '', NULL, '1', '1', 1, '13051769521', '613812', '2019-08-21 16:51:53');
INSERT INTO `tb_sys_sms_code` VALUES ('E3622F3EDFF5480597AC9DAD0FD55203', 'sys', '2019-08-16 15:24:16', '', NULL, '1', '1', 1, '18610573599', '512198', '2019-08-16 15:29:17');
INSERT INTO `tb_sys_sms_code` VALUES ('E3750839222240269B2E11B0531EF4D7', 'sys', '2019-07-12 10:49:29', '', NULL, '1', '1', 1, '18703613965', '959587', '2019-07-12 10:54:29');
INSERT INTO `tb_sys_sms_code` VALUES ('e37968bddaac4a309e3bfd3c8731edd3', 'sys', '2019-08-29 02:59:54', '', NULL, '1', '1', 1, '15110097115', '838937', '2019-08-29 03:04:54');
INSERT INTO `tb_sys_sms_code` VALUES ('E38FE739620A4A0DB3053B3093DCB818', 'sys', '2019-07-09 11:45:04', '', NULL, '1', '1', 1, '15910334107', '292884', '2019-07-09 11:50:05');
INSERT INTO `tb_sys_sms_code` VALUES ('E3A9F9AD39E94AFB934FC414F5008661', 'sys', '2019-06-10 15:51:53', '', NULL, '1', '1', 1, '18600019997', '298693', '2019-06-10 15:56:53');
INSERT INTO `tb_sys_sms_code` VALUES ('e4bb70344f9942babcf43f6f57764f5f', 'sys', '2019-08-29 19:49:55', '', NULL, '1', '1', 1, '15001395716', '931499', '2019-08-29 19:54:55');
INSERT INTO `tb_sys_sms_code` VALUES ('e611ce8ebf8542029ad6e1d382285c4c', 'sys', '2019-09-23 14:49:58', '', NULL, '1', '1', 1, '13439038739', '937297', '2019-09-23 14:54:58');
INSERT INTO `tb_sys_sms_code` VALUES ('E665C8259F034C389F6E2DB9E8632910', 'sys', '2019-05-31 15:21:49', '', NULL, '1', '1', 1, '18710002331', '148463', '2019-05-31 15:26:50');
INSERT INTO `tb_sys_sms_code` VALUES ('E83E257F5A36476DB289C3AF5F71B6BB', 'sys', '2019-06-29 15:48:50', '', NULL, '1', '1', 1, '18837107731', '419143', '2019-06-29 15:53:51');
INSERT INTO `tb_sys_sms_code` VALUES ('ea2477dd6fef44f38c1926c9b018e964', 'sys', '2019-09-27 15:19:30', '', NULL, '1', '1', 1, '15011207324', '924758', '2019-09-27 15:24:31');
INSERT INTO `tb_sys_sms_code` VALUES ('EAF4FE1076134D31A849AC41B5F7A907', 'sys', '2019-08-23 15:33:53', '', NULL, '1', '1', 1, '13086718807', '811324', '2019-08-23 15:38:54');
INSERT INTO `tb_sys_sms_code` VALUES ('EB4F768A76EA4B9BA0200425C0EFEAAB', 'sys', '2019-07-04 17:56:00', '', NULL, '1', '1', 1, '18703613965', '596786', '2019-07-04 18:01:00');
INSERT INTO `tb_sys_sms_code` VALUES ('ebd98c7243744a65868123abf2a6ed76', 'sys', '2019-08-28 16:44:27', '', NULL, '1', '1', 1, '15291693422', '363956', '2019-08-28 16:49:27');
INSERT INTO `tb_sys_sms_code` VALUES ('ebea9542ef6741bbb1a0b1bc6f7df664', 'sys', '2019-08-28 17:10:07', '', NULL, '1', '1', 1, '15001250739', '219866', '2019-08-28 17:15:07');
INSERT INTO `tb_sys_sms_code` VALUES ('EC821A6AC8974A8E9D4E705D2DA1B73C', 'sys', '2019-06-13 17:18:25', '', NULL, '1', '1', 1, '13581881826', '985515', '2019-06-13 17:23:26');
INSERT INTO `tb_sys_sms_code` VALUES ('ED161171DB8C44009CBA26B82D8976C6', 'sys', '2019-06-11 16:01:38', '', NULL, '1', '1', 1, '13581881856', '394392', '2019-06-11 16:06:38');
INSERT INTO `tb_sys_sms_code` VALUES ('EDC5D07B65E146DD8F713F205AF0390E', 'sys', '2019-07-09 09:16:51', '', NULL, '1', '1', 1, '13810964769', '273714', '2019-07-09 09:21:52');
INSERT INTO `tb_sys_sms_code` VALUES ('EE47A82847424100B2ACE0F9035FF932', 'sys', '2019-07-09 14:00:37', '', NULL, '1', '1', 1, '15117978092', '255754', '2019-07-09 14:05:37');
INSERT INTO `tb_sys_sms_code` VALUES ('F01EB49C6F90477E89DA2A6CEE775889', 'sys', '2019-07-01 15:47:01', '', NULL, '1', '1', 1, '18601943273', '513712', '2019-07-01 15:52:02');
INSERT INTO `tb_sys_sms_code` VALUES ('F080DEB9FFC24A1F91369249E74D6363', 'sys', '2019-06-12 14:06:13', '', NULL, '1', '1', 1, '18710002331', '357583', '2019-06-12 14:11:14');
INSERT INTO `tb_sys_sms_code` VALUES ('F0D063EC076247E29F2B3047703DC148', 'sys', '2019-06-19 11:18:18', '', NULL, '1', '1', 1, '17398982323', '294669', '2019-06-19 11:23:18');
INSERT INTO `tb_sys_sms_code` VALUES ('F11EF0E7A09B425EA526DA2C495009F3', 'sys', '2019-07-03 09:16:12', '', NULL, '1', '1', 1, '17398982323', '418427', '2019-07-03 09:21:13');
INSERT INTO `tb_sys_sms_code` VALUES ('f1902943e80041edb7ef40bc2b7bc41b', 'sys', '2019-08-28 16:45:59', '', NULL, '1', '1', 1, '15201693422', '353383', '2019-08-28 16:51:00');
INSERT INTO `tb_sys_sms_code` VALUES ('F1ACAB8EAB1D4D0185FD27FCC9016FE9', 'sys', '2019-06-24 09:31:39', '', NULL, '1', '1', 1, '13520451310', '389456', '2019-06-24 09:36:40');
INSERT INTO `tb_sys_sms_code` VALUES ('F1FCB00327DF44C6B2FA23ECC2B97E0F', 'sys', '2019-06-11 16:01:43', '', NULL, '1', '1', 1, '13581881856', '355782', '2019-06-11 16:06:44');
INSERT INTO `tb_sys_sms_code` VALUES ('f23f1ae7e386460f8ad346dccf7f0ae2', 'sys', '2019-09-16 14:03:07', '', NULL, '1', '1', 1, '13146589960', '671371', '2019-09-16 14:08:07');
INSERT INTO `tb_sys_sms_code` VALUES ('f33a5356dfc74eb2acfc3b4beab702e4', 'sys', '2019-08-28 20:24:24', '', NULL, '1', '1', 1, '13811589903', '926884', '2019-08-28 20:29:25');
INSERT INTO `tb_sys_sms_code` VALUES ('f384bbfb174e475d9a56cede7257ffb0', 'sys', '2019-08-29 07:05:57', '', NULL, '1', '1', 1, '15201433674', '373168', '2019-08-29 07:10:57');
INSERT INTO `tb_sys_sms_code` VALUES ('F3881ABF37484BB485BF99D14F7B16B1', 'sys', '2019-07-08 09:32:27', '', NULL, '1', '1', 1, '18703613965', '599734', '2019-07-08 09:37:27');
INSERT INTO `tb_sys_sms_code` VALUES ('F3D108E6A7E4408CA31CCAB6170BC0D5', 'sys', '2019-07-10 10:59:57', '', NULL, '1', '1', 1, '17398982323', '747368', '2019-07-10 11:04:58');
INSERT INTO `tb_sys_sms_code` VALUES ('f444855a48b7435cb3864e58e1983eba', 'sys', '2019-08-31 19:50:41', '', NULL, '1', '1', 1, '13661263818', '313239', '2019-08-31 19:55:42');
INSERT INTO `tb_sys_sms_code` VALUES ('F4CC2B4DE45A451DA7A0543763597DD5', 'sys', '2019-07-24 10:27:08', '', NULL, '1', '1', 1, '13601011360', '917915', '2019-07-24 10:32:09');
INSERT INTO `tb_sys_sms_code` VALUES ('f5a2c54850e246d2a109264520ad027b', 'sys', '2019-08-28 21:24:57', '', NULL, '1', '1', 1, '18612256583', '961832', '2019-08-28 21:29:58');
INSERT INTO `tb_sys_sms_code` VALUES ('f5d516eaf6fe4ff1b1b338dec482692a', 'sys', '2019-09-04 18:29:08', '', NULL, '1', '1', 1, '13701193984', '951983', '2019-09-04 18:34:09');
INSERT INTO `tb_sys_sms_code` VALUES ('F60CE0E1EB074E59AA7C6FD47F05E47F', 'sys', '2019-05-22 16:36:05', '', NULL, '1', '1', 1, '15910234107', '921383', '2019-05-22 16:41:05');
INSERT INTO `tb_sys_sms_code` VALUES ('f72041f3227f4399a378bd1ec0bad1f8', 'sys', '2019-08-28 17:35:32', '', NULL, '1', '1', 1, '13552533612', '951651', '2019-08-28 17:40:33');
INSERT INTO `tb_sys_sms_code` VALUES ('f72880c3e0d143eaa88519360a3c8000', 'sys', '2019-09-20 10:54:29', '', NULL, '1', '1', 1, '18710002331', '981669', '2019-09-20 10:59:29');
INSERT INTO `tb_sys_sms_code` VALUES ('F79753CAEA9149D286314B1B28D2C17F', 'sys', '2019-06-10 11:57:55', '', NULL, '1', '1', 1, '18710002331', '699136', '2019-06-10 12:02:56');
INSERT INTO `tb_sys_sms_code` VALUES ('f8b7ac673be840109b687a323ef5d1ab', 'sys', '2019-09-05 18:41:26', '', NULL, '1', '1', 1, '15811141792', '422946', '2019-09-05 18:46:26');
INSERT INTO `tb_sys_sms_code` VALUES ('f8e8875a3b964d7fb36908bb4fd0c0ed', 'sys', '2019-09-28 12:07:55', '', NULL, '1', '1', 1, '18010162569', '643785', '2019-09-28 12:12:56');
INSERT INTO `tb_sys_sms_code` VALUES ('f911c341026e4423bd3255874faf450c', 'sys', '2019-09-23 14:47:34', '', NULL, '1', '1', 1, '13261137671', '316213', '2019-09-23 14:52:34');
INSERT INTO `tb_sys_sms_code` VALUES ('f9e5b4f04fe34e57841ac51a51ae28d0', 'sys', '2019-08-28 19:11:14', '', NULL, '1', '1', 1, '13701251659', '381376', '2019-08-28 19:16:15');
INSERT INTO `tb_sys_sms_code` VALUES ('FA55913A700F4B37BB85FD281521B5BA', 'sys', '2019-07-03 09:02:46', '', NULL, '1', '1', 1, '13911301227', '491481', '2019-07-03 09:07:47');
INSERT INTO `tb_sys_sms_code` VALUES ('fb491f2a945a4c2fbaf8d64429ddf9d3', 'sys', '2019-08-30 00:16:43', '', NULL, '1', '1', 1, '13581675866', '825489', '2019-08-30 00:21:43');
INSERT INTO `tb_sys_sms_code` VALUES ('fb643f8f95ec4435bee6cc59c9572189', 'sys', '2019-09-17 20:46:23', '', NULL, '1', '1', 1, '13911531628', '216342', '2019-09-17 20:51:23');
INSERT INTO `tb_sys_sms_code` VALUES ('FB6999A2D431427492C8791C6B5E5B44', 'sys', '2019-07-09 11:05:15', '', NULL, '1', '1', 1, '17398982323', '684642', '2019-07-09 11:10:16');
INSERT INTO `tb_sys_sms_code` VALUES ('FB8FA97A22FF4036BF8755D52A63A4A3', 'sys', '2019-06-10 10:16:33', '', NULL, '1', '1', 1, '18710002331', '172819', '2019-06-10 10:21:33');
INSERT INTO `tb_sys_sms_code` VALUES ('FC2AA449054A4B38A7358E78F9F5D4A3', 'sys', '2019-08-09 18:39:34', '', NULL, '1', '1', 1, '13311583166', '873683', '2019-08-09 18:44:35');
INSERT INTO `tb_sys_sms_code` VALUES ('fdd3d214feba432ea1c679ff6a19c23f', 'sys', '2019-09-23 14:42:22', '', NULL, '1', '1', 1, '13681351341', '893793', '2019-09-23 14:47:22');
INSERT INTO `tb_sys_sms_code` VALUES ('FDEB6E171ED740FD9D2CC7830375A338', 'sys', '2019-07-05 18:57:56', '', NULL, '1', '1', 1, '18710096183', '124646', '2019-07-05 19:02:56');
INSERT INTO `tb_sys_sms_code` VALUES ('FDEEC15005D944E6BC3B2C5CC7339DFF', 'sys', '2019-06-11 18:06:14', '', NULL, '1', '1', 1, '18513607360', '868299', '2019-06-11 18:11:14');
INSERT INTO `tb_sys_sms_code` VALUES ('FEB2E5F9B3DB4B78BE987CB0EF912A04', 'sys', '2019-07-23 11:22:22', '', NULL, '1', '1', 1, '18380264751', '294588', '2019-07-23 11:27:22');
INSERT INTO `tb_sys_sms_code` VALUES ('fefe32b1067b4da69e324324d52ff0f8', 'sys', '2019-10-13 12:54:41', '', NULL, '1', '1', 1, '13716196129', '317115', '2019-10-13 12:59:42');
INSERT INTO `tb_sys_sms_code` VALUES ('ff2188d7befc4f0da467f61b10d7943e', 'sys', '2019-09-05 20:24:08', '', NULL, '1', '1', 1, '13801036615', '135714', '2019-09-05 20:29:08');
INSERT INTO `tb_sys_sms_code` VALUES ('FF54E4E7045B418BA33E48C49E29E414', 'sys', '2019-07-08 09:32:36', '', NULL, '1', '1', 1, '18703613965', '593534', '2019-07-08 09:37:37');
INSERT INTO `tb_sys_sms_code` VALUES ('ff5b6aabe50745aabc61f3b69bba9cb8', 'sys', '2019-08-28 12:22:36', '', NULL, '1', '1', 1, '13651105091', '876694', '2019-08-28 12:27:37');
INSERT INTO `tb_sys_sms_code` VALUES ('FF964CD903A5422B89E5B4ADA515EDAF', 'sys', '2019-07-12 10:48:11', '', NULL, '1', '1', 1, '18703613965', '579462', '2019-07-12 10:53:12');
INSERT INTO `tb_sys_sms_code` VALUES ('FFD08B5CD48B4941B19D0A2F957E6FC0', 'sys', '2019-06-10 15:07:43', '', NULL, '1', '1', 1, '18710002331', '318557', '2019-06-10 15:12:44');

-- ----------------------------
-- Table structure for tb_sys_sms_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_sms_log`;
CREATE TABLE `tb_sys_sms_log`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'status',
  `base_available` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'base_available',
  `base_sort` int(11) NULL DEFAULT 0 COMMENT 'base_sort',
  `ssl_type` int(64) NULL DEFAULT NULL COMMENT '短信发送类型',
  `ssl_send_body` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '发送内容',
  `ssl_receive` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '接收者',
  `ssl_body_len` int(255) NULL DEFAULT 0 COMMENT '短信长度',
  `ssl_count` int(255) NULL DEFAULT 0 COMMENT '单条短信计数',
  `ssl_is_success` int(2) NULL DEFAULT 0 COMMENT '短信是否发送成功',
  `ssl_return_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求返回信息',
  `ssl_model_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模板代码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_sys_sms_model
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_sms_model`;
CREATE TABLE `tb_sys_sms_model`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'status',
  `base_available` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'base_available',
  `base_sort` int(11) NULL DEFAULT 0 COMMENT 'base_sort',
  `ssm_signature_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '签名名称',
  `ssm_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模板类型',
  `ssm_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模版CODE',
  `ssm_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模版名称',
  `ssm_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模版内容',
  `ssm_parameters` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数',
  `ssm_show_params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '预览参数',
  `ssl_model_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模板代码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_user_integral
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_integral`;
CREATE TABLE `tb_user_integral`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'status',
  `base_available` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '是否启用',
  `base_sort` int(11) NULL DEFAULT 0 COMMENT '排序(越大越靠前)',
  `ui_change` int(3) NULL DEFAULT 0 COMMENT '变化分数',
  `ui_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '类型',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '关联用户',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_user_main
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_main`;
CREATE TABLE `tb_user_main`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'create_by',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'create_time',
  `modify_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'modify_by',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT 'modify_time',
  `base_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'status',
  `base_available` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '是否启用',
  `base_sort` int(11) NULL DEFAULT 0 COMMENT '排序(越大越靠前)',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户名',
  `user_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `check_code` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '验证码',
  `check_code_out` datetime(0) NULL DEFAULT NULL COMMENT '验证码过期时间',
  `user_details_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户详情id',
  `user_head_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像',
  `qq_openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `wx_openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `wb_openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `user_curr_integral` int(1) NULL DEFAULT 0 COMMENT '用户当前积分',
  `user_his_integral` int(1) NULL DEFAULT 0 COMMENT '历史总积分 只加不减',
  `user_job_type_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户职业id',
  `wx_unionid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `wx_app_openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
