package org.qiuyun.comment.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.qiuyun.comment.dao.Enum.CommentType;

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
@TableName("comment")
public class CommentDO{

    private Integer cid;    //评论id
    private Integer pid = 0;    //父评论id,评论分两级,二级评论的pid为被回复的一级评论cid, 默认为一级评论-0
    private Long resourceId;//对应资源id

    private String content; //评论内容

    private Integer likedCount = 0;//点赞

    private Date created;//评论创建时间
    private CommentType type;//评论类型——歌单, 歌曲
    private UserInfoDO user;//评论用户
    private UserInfoDO repliedUser;//被回复用户
    private Integer totalCountOfSonComment;
    private List<CommentDO> commentList;//二级评论列表
}
