package org.qiuyun.identify.service;

import org.qiuyun.identify.dao.dto.req.*;
import org.qiuyun.identify.dao.dto.resp.CommentBitRespDTO;
import org.qiuyun.identify.dao.dto.resp.UserBitRespDTO;
import org.qiuyun.identify.dao.dto.resp.UserBlackListRespDTO;
import org.qiuyun.identify.dao.dto.resp.VideoBitRespDTO;

import java.util.List;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 13:58
 */

public interface IdentifyService {

    /**
     * 查询用户维度的标识Bit
     * @param userBitReqDTO
     * @return
     */
    UserBitRespDTO userDimIdentify(UserBitReqDTO userBitReqDTO);

    UserBlackListRespDTO queryBlackList(long uid);
    void addUserBlackList(UserBlackListReqDTO userBlackListReqDTO);

    void removeUserBlackList(UserBlackListReqDTO userBlackListReqDTO);

    void banUserBlackList(UserBannedReqDTO userBannedReqDTO);

    /**
     * 查询视频维度下的标识Bit
     * @param videoBitQueryReqDTO
     * @return
     */
    VideoBitRespDTO videoDimIdentify(VideoBitQueryReqDTO videoBitQueryReqDTO);

    void updateVideoDimIdentify(VideoBitSetReqDTO videoBitSetReqDTO);


    CommentBitRespDTO commentDimIdentify(Long commentId);

    void updateCommentDimIdentify(CommentBitSetReqDTO commentBitSetReqDTO);

    List<CommentBitRespDTO> commentDimBatchIdentify(CommentBitBatchReqDTO commentBitBatchReqDTO);
}
