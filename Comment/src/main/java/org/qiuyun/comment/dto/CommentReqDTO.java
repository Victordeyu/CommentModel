package org.qiuyun.comment.dto;

import lombok.Data;
import java.util.List;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/26 15:22
 */
@Data
public class CommentReqDTO {
//    private Integer id;    //评论id
    private Integer videoId;    //评论id
    private Integer uid ;    //父评论id,评论分两级,二级评论的pid为被回复的一级评论cid, 默认为一级评论-0
    private Integer pid ;    //父评论id,评论分两级,二级评论的pid为被回复的一级评论cid, 默认为一级评论-0
    private String content; //评论内容
    private List<String> picUrls; //评论中的图片url

}
