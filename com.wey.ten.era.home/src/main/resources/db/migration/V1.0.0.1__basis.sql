CREATE TABLE `ad_rotation` (
  `id` int(10) NOT NULL COMMENT 'id',
  `image_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '图片',
  `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `order_by` int(10) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;