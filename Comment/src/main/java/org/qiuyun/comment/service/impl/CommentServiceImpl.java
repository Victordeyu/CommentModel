package org.qiuyun.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qiuyun.comment.dao.entity.CommentDO;
import org.qiuyun.comment.dao.mapper.CommentMapper;
import org.qiuyun.comment.dao.mapper.UserInfoMapper;
import org.qiuyun.comment.dto.CommentReqDTO;
import org.qiuyun.comment.service.CommentService;
import org.qiuyun.common.BeanUtil;
import org.qiuyun.common.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


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
    public void createComment(CommentReqDTO requestParam) {
//        String username = UserContext.getUsername();

        try{
            System.out.println(requestParam);
            CommentDO commentDO= BeanUtil.convert(requestParam,CommentDO.class);
            commentDO.setCreateTime(new Date());
            commentDO.setThumbNums(0);
            commentDO.setSonCommentNums(0);
            commentDO.setDelFlag(false);
            System.out.println(commentDO);
            int inserted=commentMapper.insert(commentDO);
            System.out.println("insert完毕"+inserted);
            if (!SqlHelper.retBool(inserted)) {
                throw new ServiceException(String.format("[%s] 新建评论失败", commentDO.getUid()));
            }
        }
        catch (Exception ex) {
            if (ex instanceof ServiceException) {
                log.error("{}，请求参数：{}", ex.getMessage(), requestParam.toString());
            } else {
                log.error("[{}] 新建评论失败，请求参数：{}", requestParam.toString(), ex);
            }
            throw ex;
        }
    }

    @Override
    @Transactional
    public void createReply(CommentReqDTO requestParam) {
        try{
            CommentDO commentDO= BeanUtil.convert(requestParam,CommentDO.class);

            commentDO.setCreateTime(new Date());
            commentDO.setThumbNums(0);
            commentDO.setSonCommentNums(0);
            commentDO.setDelFlag(false);

            CommentDO pComment = commentMapper.selectById(commentDO.getPid());
            pComment.setSonCommentNums(pComment.getSonCommentNums()+1);
            LambdaUpdateWrapper<CommentDO>updateWrapper= Wrappers.lambdaUpdate(CommentDO.class)
                    .eq(CommentDO::getId,pComment.getId());
            int updated=commentMapper.update(pComment,updateWrapper);

            int inserted=commentMapper.insert(commentDO);
            if (!SqlHelper.retBool(inserted)) {
                throw new ServiceException(String.format("[%s] 新建评论失败", commentDO.getId()));
            }
        }
        catch (Exception ex) {
            if (ex instanceof ServiceException) {
                log.error("{}，请求参数：{}", ex.getMessage(), (requestParam.toString()));
            } else {
                log.error("[{}] 新建评论失败，请求参数：{}", requestParam.getUid(), (requestParam).toString(), ex);
            }
            throw ex;
        }
    }

    @Override
    public CommentDO findById(Long id) {
        CommentDO commentDO=commentMapper.selectById(id);
        return commentDO;
    }

    @Override
    public List<CommentDO> findByPid(Long pid){
        LambdaUpdateWrapper<CommentDO> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(CommentDO::getPid,pid)
                .eq(CommentDO::getDelFlag,false);
        return commentMapper.selectList(wrapper);
    }

    @Override
    public List<CommentDO> findByVideoid(Long videoId) {
        LambdaUpdateWrapper<CommentDO> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(CommentDO::getVideoId,videoId)
                .eq(CommentDO::getDelFlag,false);
        return commentMapper.selectList(wrapper);
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
