package org.qiuyun.identify.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qiuyun.identify.dao.Enum.BitCommon;
import org.qiuyun.identify.dao.dto.req.*;
import org.qiuyun.identify.dao.dto.resp.CommentBitRespDTO;
import org.qiuyun.identify.dao.dto.resp.UserBitRespDTO;
import org.qiuyun.identify.dao.dto.resp.VideoBitRespDTO;
import org.qiuyun.identify.dao.entity.VideoBitDO;
import org.qiuyun.identify.dao.mapper.CommentBitMapper;
import org.qiuyun.identify.dao.mapper.UserBitMapper;
import org.qiuyun.identify.dao.mapper.UserBlackListMapper;
import org.qiuyun.identify.dao.mapper.VideoBitMapper;
import org.qiuyun.identify.service.IdentifyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 13:58
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class IdentifyServiceImpl implements IdentifyService {
    private final UserBitMapper userBitMapper;
    private final VideoBitMapper videoBitMapper;
    private final CommentBitMapper commentBitMapper;

    private final UserBlackListMapper userBlackListMapper;
    @Override
    public List<UserBitRespDTO> userDimIdentify(UserBitReqDTO userBitReqDTO) {
        return null;
    }

    @Override
    public void addUserBlackList(UserBlackListReqDTO userBlackListReqDTO) {

    }

    @Override
    public void removeUserBlackList(UserBlackListReqDTO userBlackListReqDTO) {

    }

    @Override
    public void banUserBlackList(UserBannedReqDTO userBannedReqDTO) {

    }

    @Override
    public VideoBitRespDTO videoDimIdentify(VideoBitQueryReqDTO videoBitQueryReqDTO) {
        Long videoId=videoBitQueryReqDTO.getVideoId();
        LambdaQueryWrapper<VideoBitDO> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(VideoBitDO::getVideoId,videoId);
        List<VideoBitDO>videoBitDOList=videoBitMapper.selectList(wrapper);
        Byte[]bits=new Byte[BitCommon.BIT_COMMON_MAX_BIT.getValue()];
        Arrays.fill(bits,(byte)0);
        //TODO 此处越界异常如何处理
        videoBitDOList.forEach(videoBitDO->{
            if(videoBitDO.getType()<BitCommon.BIT_COMMON_MAX_BIT.getValue()-1)//存储的tyep大于最大支持的Bit位数
                bits[videoBitDO.getType()]=1;
        });
        return VideoBitRespDTO.builder().videoId(videoId).bit(Arrays.stream(bits).toList()).build();
    }

    /**
     * 修改视频维度Bit
     * 由于访问较少，可以先删除，然后再新增。
     * @param videoBitSetReqDTO
     */
    @Override
    @Transactional
    public void updateVideoDimIdentify(VideoBitSetReqDTO videoBitSetReqDTO) {
        Long videoId=videoBitSetReqDTO.getVideoId();
        LambdaQueryWrapper<VideoBitDO> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(VideoBitDO::getVideoId,videoId);
        videoBitMapper.delete(wrapper);

        Byte[]bits=videoBitSetReqDTO.getBit();
        for(int i=0;i<bits.length;i++){
            if(bits[i]==1)
                videoBitMapper.insert(VideoBitDO.builder().videoId(videoId).type(i).val(true).build());
        }
    }

    @Override
    public List<CommentBitRespDTO> commentDimIdentify(CommentBitReqDTO commentBitReqDTO) {
        return null;
    }

    @Override
    public void updateCommentDimIdentify(CommentBitSetReqDTO commentBitSetReqDTO) {

    }
}
