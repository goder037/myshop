#DROP TABLE shop_item_category;
CREATE TABLE `shop_item_category` (
  `id_` int(11) NOT NULL AUTO_INCREMENT,
  `name_` varchar(40) NOT NULL,
  `status_` tinyint(1) NOT NULL DEFAULT '1' COMMENT '类别状态， 1正常 0删除',
  `create_time_` datetime DEFAULT  CURRENT_TIMESTAMP() COMMENT '创建时间',
  `update_time_` datetime DEFAULT CURRENT_TIMESTAMP() COMMENT '更新时间',
  `parent_id_` int(11) DEFAULT NULL COMMENT '父类目ID=null时，代表的是一级的类目',
  `items_num_` int(11) DEFAULT NULL COMMENT '该类别下面的商品数目',
  `rank_` smallint(6) DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  `level_` tinyint(2) DEFAULT 1 COMMENT '商品类别等级，默认为1级类别',
  `sub_category_num_` smallint(6) DEFAULT 0 COMMENT '子类别数目',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类目';