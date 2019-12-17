DROP TABLE IF EXISTS `common_task`;
CREATE TABLE `common_task`
(
    `id`             int(11) unsigned NOT NULL AUTO_INCREMENT,
    `task_no`        varchar(64)      NOT NULL COMMENT '任务编号',
    `task_type`      varchar(64)      not null COMMENT '任务类型',
    `task_status`    varchar(32)      not null COMMENT '任务状态',
    `task_context`   text              DEFAULT NULL COMMENT '任务上下文',
    `exec_times`     int(4)           NOT NULL DEFAULT 0 COMMENT '执行次数',
    `last_exec_time` datetime                  default null comment '上次执行时间',
    `next_exec_time` datetime                  default null comment '下次执行时间',
    `create_time`    datetime                  DEFAULT NULL COMMENT '创建时间',
    `update_time`    datetime                  default null comment '更新时间',
    `memo`           text              default null comment '备注',
    `env`            varchar(64)               default null comment '环境',
    `last_host`      varchar(64)               default null comment '上次执行主机',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = COMPACT COMMENT ='通用任务表';


DROP TABLE IF EXISTS `host_info`;
CREATE TABLE `host_info`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT,
    `host_ip`    varchar(128)     NOT NULL COMMENT '主机ip',
    `host_name`  varchar(128)     not null COMMENT '主机名称',
    `last_boot`  datetime         not null COMMENT '上次启动时间',
    `last_heart` datetime         not NULL COMMENT '上次心跳时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = COMPACT COMMENT ='主机信息表';