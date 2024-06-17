package org.qiuyun.identify.service;

import org.qiuyun.identify.dao.dto.req.*;
import org.qiuyun.identify.dao.dto.resp.CommentBitRespDTO;
import org.qiuyun.identify.dao.dto.resp.UserBitRespDTO;
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
    public List<UserBitRespDTO> userDimIdentify(UserBitReqDTO userBitReqDTO);

    public void addUserBlackList(UserBlackListReqDTO userBlackListReqDTO);

    public void removeUserBlackList(UserBlackListReqDTO userBlackListReqDTO);

    public void banUserBlackList(UserBannedReqDTO userBannedReqDTO);

    /**
     * 查询视频维度下的标识Bit
     * @param videoBitQueryReqDTO
     * @return
     */
    public VideoBitRespDTO videoDimIdentify(VideoBitQueryReqDTO videoBitQueryReqDTO);

    public void updateVideoDimIdentify(VideoBitSetReqDTO videoBitSetReqDTO);


    public List<CommentBitRespDTO> commentDimIdentify(CommentBitReqDTO commentBitReqDTO);

    public void updateCommentDimIdentify(CommentBitSetReqDTO commentBitSetReqDTO);
}
