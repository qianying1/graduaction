# Host: localhost  (Version: 5.5.53)
# Date: 2017-10-21 00:48:55
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "tb_analized_message"
#

CREATE TABLE `tb_analized_message` (
  `msg_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `video_style` varchar(50) NOT NULL,
  `video_name` varchar(200) NOT NULL,
  `play_times` bigint(20) NOT NULL,
  `average_eval` varchar(6) NOT NULL,
  `most_like_user_location` varchar(20) NOT NULL,
  `video_comment_times` bigint(20) NOT NULL,
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "tb_analized_message"
#


#
# Structure for table "tb_grab_lib"
#

CREATE TABLE `tb_grab_lib` (
  `grab_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) DEFAULT NULL,
  `web_site_addr` text NOT NULL,
  `grab_sign` varchar(18) NOT NULL DEFAULT 'have grabed',
  `add_time` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`grab_id`),
  KEY `website_id` (`website_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "tb_grab_lib"
#


#
# Structure for table "tb_grab_message"
#

CREATE TABLE `tb_grab_message` (
  `msg_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `video_name` varchar(50) DEFAULT '' COMMENT '视屏标题',
  `play_count` varchar(15) DEFAULT '' COMMENT '播放数量',
  `like_count` int(11) DEFAULT '0' COMMENT '收藏数量',
  `comment_count` varchar(15) DEFAULT '' COMMENT '评论数量',
  `barrage` int(11) DEFAULT NULL COMMENT '弹幕数量',
  `banana_count` bigint(20) DEFAULT NULL COMMENT '投蕉数量',
  `video_add_time` varchar(80) DEFAULT NULL COMMENT '视频发布时间',
  `author_id` int(11) DEFAULT NULL COMMENT '作者',
  `video_type` varchar(90) DEFAULT NULL COMMENT '视频类型',
  PRIMARY KEY (`msg_id`),
  KEY `author_id` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "tb_grab_message"
#


#
# Structure for table "tb_video_author"
#

CREATE TABLE `tb_video_author` (
  `author_id` int(11) NOT NULL AUTO_INCREMENT,
  `signature` text COMMENT '用户签名',
  `video_count` int(11) DEFAULT NULL COMMENT '视频投稿数量',
  `attention_count` int(11) DEFAULT NULL COMMENT '关注数量',
  `audience_count` int(11) DEFAULT NULL COMMENT '粉丝数量',
  `author_page_url` text COMMENT '视频主主页',
  `author_pic` varchar(255) DEFAULT NULL COMMENT '作者头像',
  `author_name` varchar(90) DEFAULT NULL COMMENT '作者名称',
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频发布用户';

#
# Data for table "tb_video_author"
#


#
# Structure for table "tb_websites"
#

CREATE TABLE `tb_websites` (
  `website_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_name` varchar(30) NOT NULL DEFAULT '',
  `website_url` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`website_id`),
  UNIQUE KEY `website_name` (`website_name`),
  UNIQUE KEY `website_url` (`website_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "tb_websites"
#

