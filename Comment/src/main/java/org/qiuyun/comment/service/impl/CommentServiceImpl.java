package org.qiuyun.comment.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qiuyun.comment.dao.entity.CommentDO;
import org.qiuyun.comment.dao.entity.UserInfoDO;
import org.qiuyun.comment.dao.mapper.CommentMapper;
import org.qiuyun.comment.dao.mapper.UserInfoMapper;
import org.qiuyun.comment.dto.CommentReqDTO;
import org.qiuyun.comment.service.CommentService;
import org.qiuyun.common.BeanUtil;
import org.qiuyun.common.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/26 15:31
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;
    private final UserInfoMapper userInfoMapper;
    @Override
    @Transactional
    public void createCommentDO(CommentReqDTO requestParam) {
//        String username = UserContext.getUsername();

        try{
            CommentDO commentDO= BeanUtil.convert(requestParam,CommentDO.class);
            commentDO.setPid(0);
            commentDO.setCreated(new Date());
            commentDO.setLikedCount(0);
            commentDO.setTotalCountOfSonComment(0);
            commentDO.setCommentList(new ArrayList<>());
            int inserted=commentMapper.insert(commentDO);
            if (!SqlHelper.retBool(inserted)) {
                throw new ServiceException(String.format("[%s] 新建评论失败", commentDO.getUser().getName()));
            }
        }
        catch (Exception ex) {
//            if (ex instanceof ServiceException) {
//                log.error("{}，请求参数：{}", ex.getMessage(), JSON.toJSONString(requestParam));
//            } else {
//                log.error("[{}] 新建评论失败，请求参数：{}", username, JSON.toJSONString(requestParam), ex);
//            }
//            throw ex;
            ex.fillInStackTrace();
        }
    }

    @Override
    @Transactional
    public void createReply(CommentReqDTO requestParam, int uid, int beRepliedId) {
        try{
            CommentDO commentDO= BeanUtil.convert(requestParam,CommentDO.class);
            commentDO.setPid(beRepliedId);
            commentDO.setCreated(new Date());
            commentDO.setLikedCount(0);
            commentDO.setTotalCountOfSonComment(0);
            commentDO.setCommentList(new ArrayList<>());

            CommentDO pComment = commentMapper.selectById(beRepliedId);
            pComment.setTotalCountOfSonComment(pComment.getTotalCountOfSonComment()+1);
            pComment.getCommentList().add(commentDO);

            int inserted=commentMapper.insert(commentDO);
            if (!SqlHelper.retBool(inserted)) {
                throw new ServiceException(String.format("[%s] 新建评论失败", commentDO.getUser().getName()));
            }
        }
        catch (Exception ex) {
//            if (ex instanceof ServiceException) {
//                log.error("{}，请求参数：{}", ex.getMessage(), JSON.toJSONString(requestParam));
//            } else {
//                log.error("[{}] 新建评论失败，请求参数：{}", username, JSON.toJSONString(requestParam), ex);
//            }
//            throw ex;
            ex.fillInStackTrace();
        }
    }

    @Override
    public CommentDO findById(int id) {
        return null;
    }

    @Override
    public void deleteCommentDO(CommentDO target) {

    }

    @Override
    public List<CommentDO> findAll() {
        return null;
    }

    @Override
    public List<CommentDO> findSonCommentDOByPid(Integer pid, Map<String, String> commentDOMap) {
        return null;
    }

    @Override
    public List<CommentDO> findSonCommentDOByPid(Integer pid, Integer offset, Integer size) {
        return null;
    }

    @Override
    public Integer countByPid(Integer pid) {
        return null;
    }

    @Override
    public boolean isCorrectCommentDO(Map<String, String> commentDOMap, StringBuffer message) {
        return false;
    }
}
