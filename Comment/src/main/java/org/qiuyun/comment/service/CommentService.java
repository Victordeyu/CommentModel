package org.qiuyun.comment.service;

import org.qiuyun.comment.dao.entity.CommentDO;
import org.qiuyun.comment.dto.CommentReqDTO;

import java.util.List;
import java.util.Map;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/26 15:19
 */

public interface CommentService {
    void createComment(CommentReqDTO requestParam);

    void createReply(CommentReqDTO requestParam);//新建回复
    CommentDO findById(Long id);//通过id查找评论
    List<CommentDO> findByPid(Long pid);//通过pid查找子评论
    List<CommentDO> findByVideoid(Long videoId);//通过pid查找子评论
    void deleteComment(Long id);//删除评论
    List<CommentDO> findAll();
//    Page<CommentDO> findByPage(int type, Long rid, int pid, Pageable pageable);//分页查询评论
//    List<CommentDO> findSonCommentDOByPid(Integer pid, Map<String, String> commentDOMap);//分页查询子评论
//    List<CommentDO> findSonCommentDOByPid(Integer pid, Integer offset, Integer size);//分页查询子评论
    Integer countByPid(Integer pid);//统计父评论下子评论数目
    boolean isCorrectCommentDO(Map<String, String> commentDOMap, StringBuffer message);
}
