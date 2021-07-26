/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80014
 Source Host           : localhost:3306
 Source Schema         : choy_ms

 Target Server Type    : MySQL
 Target Server Version : 80014
 File Encoding         : 65001

 Date: 26/07/2021 12:02:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin_user`;
CREATE TABLE `tb_admin_user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_admin_user
-- ----------------------------
INSERT INTO `tb_admin_user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', '小蔡', '2021-03-03 22:24:06');

-- ----------------------------
-- Table structure for tb_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods`  (
  `goods_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `goods_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `goods_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `goods_price` decimal(10, 2) NULL DEFAULT NULL,
  `goods_stock` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
INSERT INTO `tb_goods` VALUES (1, '连衣裙', '夏装长裙子仙女超仙森系法式复古方领泡泡袖雪纺赫本风碎花连衣裙', 'https://s11.mogucdn.com/mlcdn/1689c6/200622_24dbkl333jiclb82fc54g19a05hgf_640x640.jpg_400x400.v1cAC.40.webp', 0.00, 8, '2021-03-10 15:11:20');
INSERT INTO `tb_goods` VALUES (2, '灯笼袖连衣裙长袖', '2021春装新款韩版宽松显瘦不规则假两件拼色灯笼袖连衣裙长袖', 'https://s5.mogucdn.com/mlcdn/1689c6/210303_6k1l22cfffd2b79c9iej026905laj_640x640.jpg_400x400.v1cAC.40.webp', 49.90, 19, '2021-03-10 15:11:20');
INSERT INTO `tb_goods` VALUES (3, '富勒烯烟酰胺养肤提亮六件套', '护肤套装羊胎素蛋白焕颜补水保湿套装富勒烯烟酰胺养肤提亮六件套', 'https://s5.mogucdn.com/mlcdn/1689c6/201111_1hdkb7bj9eb2eb6bh447g4jii3ie6_640x640.jpg_400x400.v1cAC.40.webp', 73.00, 4, '2021-03-10 22:46:35');
INSERT INTO `tb_goods` VALUES (4, '休闲运动鞋', '跑步鞋网面透气老爹鞋网红同款鞋子女百搭厚底显瘦休闲运动鞋子女', 'https://s11.mogucdn.com/mlcdn/1689c6/200724_1cchglcieaaiclij5jf9i0jf9lhik_640x640.jpg_400x400.v1cAC.40.webp', 45.00, 9, '2021-03-10 22:48:05');
INSERT INTO `tb_goods` VALUES (5, '衬衣法式打底衫', '小众设计感长袖衬衫女宽松春季新款韩版慵懒风百搭衬衣法式打底衫', 'https://s5.mogucdn.com/mlcdn/1689c6/200911_7ckgei55d389i65aed06ah2d4j1fi_640x640.jpg_400x400.v1cAC.40.webp', 39.90, 9, '2021-03-10 22:48:07');
INSERT INTO `tb_goods` VALUES (6, '衬衣+吊带连衣裙两件套装', '防晒衬衫女春夏新款宽松百搭慵懒风衬衣+吊带连衣裙两件套装', 'https://s5.mogucdn.com/mlcdn/1689c6/210227_301eh802lfj2969d5g8e4edjgbfi8_640x640.jpg_400x400.v1cAC.40.webp', 79.90, 20, '2021-03-10 22:50:07');
INSERT INTO `tb_goods` VALUES (7, '白色卫衣', '长袖t恤男ins春季男士上衣宽松衣服纯棉纯色打底衫内搭潮白色卫衣', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i1/3587492410/O1CNA1i6BijE1Tfp8YrmWab_!!3587492410-0-psf.jpg_250x250.jpg_.webp', 49.00, 10, '2021-03-10 22:58:00');
INSERT INTO `tb_goods` VALUES (8, '春装上衣服', '假两件卫衣男生潮牌ins港风连帽外套春秋韩版潮流宽松春装上衣服', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i3/99016084/O1CN01PKBk2J1uoWKIcSFp9_!!0-saturn_solar.jpg_250x250.jpg_.webp', 78.00, 10, '2021-03-10 22:58:02');
INSERT INTO `tb_goods` VALUES (9, '男士宽松针织衫', '双股羊绒衫男100纯羊绒圆领加厚羊毛衫冬季保暖男士宽松针织衫my', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/446742436/O1CN01DRDN591TrjSYVHXir_!!446742436.jpg_250x250.jpg_.webp', 599.00, 5, '2021-03-10 22:58:06');
INSERT INTO `tb_goods` VALUES (10, '宽松长袖衬衣外套', '人像高级感衬衫男士春秋季韩版潮流百搭寸衫大码宽松长袖衬衣外套', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/i4/722317031/O1CN01zhZBi421oFOUBp5xQ_!!0-item_pic.jpg_250x250.jpg_.webp', 57.99, 10, '2021-03-10 22:58:08');
INSERT INTO `tb_goods` VALUES (52, '小米11', '【新品预定享6期免息】小米11青春版5G手机轻薄多彩手机骁龙780G小米官方旗舰店小米手机官网', 'http://cairunyu.oss-cn-shenzhen.aliyuncs.com/ms-img/1617956255235142979.jpg', 4299.00, 10, '2021-04-09 16:17:44');
INSERT INTO `tb_goods` VALUES (59, '笔记本', '笔记本', 'http://cairunyu.oss-cn-shenzhen.aliyuncs.com/ms-img/1622044666606987724.jpg', 5000.00, 9, '2021-05-26 23:57:56');

-- ----------------------------
-- Table structure for tb_ms_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_ms_goods`;
CREATE TABLE `tb_ms_goods`  (
  `ms_goods_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NULL DEFAULT NULL,
  `ms_price` decimal(10, 2) NULL DEFAULT NULL,
  `ms_goods_stock` int(11) NULL DEFAULT NULL,
  `start_time` datetime NULL DEFAULT NULL,
  `end_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `is_up` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`ms_goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_ms_goods
-- ----------------------------
INSERT INTO `tb_ms_goods` VALUES (3, 3, 19.99, 4, '2021-03-11 14:07:14', '2022-06-09 14:07:21', '2021-03-11 14:07:19', 1);
INSERT INTO `tb_ms_goods` VALUES (5, 5, 20.00, 9, '2021-03-11 14:07:14', '2022-07-08 00:00:00', '2021-03-11 14:07:19', 1);
INSERT INTO `tb_ms_goods` VALUES (6, 6, 20.00, 10, '2021-03-11 14:07:14', '2021-06-01 14:07:21', '2021-03-11 14:07:19', 1);
INSERT INTO `tb_ms_goods` VALUES (7, 7, 20.00, 10, '2021-03-11 14:07:14', '2021-06-01 14:07:21', '2021-03-11 14:07:19', 1);
INSERT INTO `tb_ms_goods` VALUES (8, 8, 20.00, 10, '2021-03-11 14:07:14', '2021-04-01 14:07:21', '2021-03-11 14:07:19', 1);
INSERT INTO `tb_ms_goods` VALUES (9, 9, 20.00, 5, '2021-03-11 14:07:14', '2021-04-02 14:07:21', '2021-03-11 14:07:19', 1);
INSERT INTO `tb_ms_goods` VALUES (10, 10, 20.00, 10, '2021-03-11 14:07:14', '2021-04-02 14:07:21', '2021-03-11 14:07:19', 1);
INSERT INTO `tb_ms_goods` VALUES (67, 52, 3999.00, 3, '2021-04-09 16:17:56', '2021-05-29 00:00:00', '2021-04-09 16:18:00', 1);
INSERT INTO `tb_ms_goods` VALUES (73, 59, 4899.00, 1, '2021-05-26 23:58:46', '2021-05-28 00:00:00', '2021-05-26 23:58:25', 1);

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ms_goods_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `goods_title` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `unit_price` decimal(10, 2) NULL DEFAULT NULL,
  `order_state` int(1) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `pay_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES (94, '59', 73, 1, '笔记本', 1, 4899.00, 0, '2021-05-26 23:59:09', '2021-05-26 23:59:12');
INSERT INTO `tb_order` VALUES (103, '3', 3, 1, '护肤套装羊胎素蛋白焕颜补水保湿套装富勒烯烟酰胺养肤提亮六件套', 1, 19.99, 0, '2021-07-25 11:10:40', '2021-07-25 11:10:41');
INSERT INTO `tb_order` VALUES (104, '3', 3, 1, '护肤套装羊胎素蛋白焕颜补水保湿套装富勒烯烟酰胺养肤提亮六件套', 1, 19.99, 2, '2021-07-25 11:25:32', NULL);
INSERT INTO `tb_order` VALUES (105, '3', 3, 1, '护肤套装羊胎素蛋白焕颜补水保湿套装富勒烯烟酰胺养肤提亮六件套', 1, 19.99, 0, '2021-07-25 11:46:40', '2021-07-25 11:46:43');
INSERT INTO `tb_order` VALUES (106, '3', 3, 1, '护肤套装羊胎素蛋白焕颜补水保湿套装富勒烯烟酰胺养肤提亮六件套', 1, 19.99, 0, '2021-07-25 13:57:42', '2021-07-25 13:57:43');
INSERT INTO `tb_order` VALUES (107, '5', 5, 1, '小众设计感长袖衬衫女宽松春季新款韩版慵懒风百搭衬衣法式打底衫', 1, 20.00, 0, '2021-07-25 14:14:43', '2021-07-25 14:14:44');
INSERT INTO `tb_order` VALUES (108, '3', 3, 1, '护肤套装羊胎素蛋白焕颜补水保湿套装富勒烯烟酰胺养肤提亮六件套', 1, 19.99, 0, '2021-07-25 16:17:27', '2021-07-25 16:17:28');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `is_enable` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'choy', '小菜', 'e353630030e39ca918801a41c42dba84', '2021-02-13 13:31:58', 1);
INSERT INTO `tb_user` VALUES (4, 'test1', 'test1', '5a105e8b9d40e1329780d62ea2265d8a', '2021-03-08 21:05:51', 1);
INSERT INTO `tb_user` VALUES (5, 'test2', 'test2', 'ad0234829205b9033196ba818f7a872b', '2021-03-08 21:07:22', 1);
INSERT INTO `tb_user` VALUES (6, 'test3', 'test3', '8ad8757baa8564dc136c1e07507f4a98', '2021-03-08 21:07:25', 1);
INSERT INTO `tb_user` VALUES (7, 'test4', 'test4', '86985e105f79b95d6bc918fb45ec7727', '2021-03-08 21:07:28', 1);
INSERT INTO `tb_user` VALUES (8, 'test5', 'test5', 'e3d704f3542b44a621ebed70dc0efe13', '2021-03-08 21:07:30', 1);
INSERT INTO `tb_user` VALUES (12, 'test7', 'test7', 'b04083e53e242626595e2b8ea327e525', '2021-03-08 21:07:34', 1);
INSERT INTO `tb_user` VALUES (20, 'test6', 'test6', '4cfad7076129962ee70c36839a1e3e15', '2021-03-09 23:20:18', 1);
INSERT INTO `tb_user` VALUES (34, 'test8', 'test8', '5e40d09fa0529781afd1254a42913847', '2021-04-09 16:03:15', 1);
INSERT INTO `tb_user` VALUES (36, 'test9', 'test9', '739969b53246b2c727850dbb3490ede6', '2021-04-09 16:08:10', 1);
INSERT INTO `tb_user` VALUES (41, 'test12', 'test12', '60474c9c10d7142b7508ce7a50acf414', '2021-05-27 09:52:24', 1);
INSERT INTO `tb_user` VALUES (42, 'test13', 'test13', '33fc3dbd51a8b38a38b1b85b6a76b42b', '2021-05-27 10:43:47', 1);

SET FOREIGN_KEY_CHECKS = 1;
