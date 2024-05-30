package org.qiuyun.comment.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/26 15:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_comment")
public class CommentDO{

    private Long id;    //评论id
    private Integer videoId;    //评论id
    private Integer uid ;    //父评论id,评论分两级,二级评论的pid为被回复的一级评论cid, 默认为一级评论-0
    private Integer pid ;    //父评论id,评论分两级,二级评论的pid为被回复的一级评论cid, 默认为一级评论-0
    private Date createTime;//评论创建时间

    private String content; //评论内容
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> picUrls; //评论中的图片url

    private Integer sonCommentNums;
    private Integer thumbNums = 0;//点赞
    private Boolean delFlag;
}
