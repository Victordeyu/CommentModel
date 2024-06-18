package org.qiuyun.identify.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qiuyun.common.exception.ServiceException;
import org.qiuyun.identify.dao.Enum.BitCommon;
import org.qiuyun.identify.dao.Enum.UserDimBit;
import org.qiuyun.identify.dao.dto.req.*;
import org.qiuyun.identify.dao.dto.resp.CommentBitRespDTO;
import org.qiuyun.identify.dao.dto.resp.UserBitRespDTO;
import org.qiuyun.identify.dao.dto.resp.UserBlackListRespDTO;
import org.qiuyun.identify.dao.dto.resp.VideoBitRespDTO;
import org.qiuyun.identify.dao.entity.UserBlackListDO;
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
import java.util.Date;
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

    /**
     * 查询某一个用户视角下多个用户的用户维度标识位。
     * TODO 暂时没有实现封禁功能。
     * @param userBitReqDTO
     * @return
     */
    @Override
    public UserBitRespDTO userDimIdentify(UserBitReqDTO userBitReqDTO) {
        UserBlackListRespDTO userBlackListRespDTO=queryBlackList(userBitReqDTO.getUid());
        ArrayList<List<Byte>> user_bits=new ArrayList<>();
        for(Long query_uid:userBitReqDTO.getUid_list()) {
            Byte[]bit=new Byte[BitCommon.BIT_COMMON_MAX_BIT.getValue()];
            Arrays.fill(bit,(byte)0);
            if(userBlackListRespDTO.getBlackUsr().contains(query_uid))
                bit[UserDimBit.USER_DIM_BIT_BLACKED.getValue()]=(byte)1;
            else
                bit[UserDimBit.USER_DIM_BIT_NORMAL.getValue()]=(byte)1;
            List<Byte> user_bit = Arrays.stream(bit).toList();
            user_bits.add(user_bit);
        }
        return UserBitRespDTO.builder().Uid(userBitReqDTO.getUid()).bit(user_bits).build();
    }

    @Override
    public UserBlackListRespDTO queryBlackList(long uid) {
        LambdaQueryWrapper<UserBlackListDO>wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(UserBlackListDO::getUid,uid);
        List<UserBlackListDO>userBlackListDOList=userBlackListMapper.selectList(wrapper);
        List<Long> black_list=new ArrayList<>();
        userBlackListDOList.forEach(userBlackListDO -> {black_list.add(userBlackListDO.getBlackUsr());});
        return UserBlackListRespDTO.builder()
                        .uid(uid)
                        .blackUsr(black_list)
                        .build();
    }


    @Override
    @Transactional
    public void addUserBlackList(UserBlackListReqDTO userBlackListReqDTO) {
        List<Long> black_uids=userBlackListReqDTO.getBlack_uid();
        for(Long black_uid:black_uids) {
            int insert=userBlackListMapper.insert(UserBlackListDO.builder()
                    .uid(userBlackListReqDTO.getUid())
                    .blackUsr(black_uid)
                    .createTime(new Date())
                    .build());
            if (!SqlHelper.retBool(insert)) {
                throw new ServiceException(String.format("[%s] 新建黑名单用户失败", userBlackListReqDTO.getUid()));
            }
        }
    }

    @Override
    @Transactional
    public void removeUserBlackList(UserBlackListReqDTO userBlackListReqDTO) {
        List<Long> black_uids=userBlackListReqDTO.getBlack_uid();
        for(Long black_uid:black_uids) {
            LambdaQueryWrapper<UserBlackListDO>wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(UserBlackListDO::getUid,userBlackListReqDTO.getUid())
                            .eq(UserBlackListDO::getBlackUsr,black_uid);
            userBlackListMapper.delete(wrapper);
        }
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
            if(bits[i]==1) {
                int insert = videoBitMapper.insert(VideoBitDO.builder().videoId(videoId).type(i).val(true).build());
                if (!SqlHelper.retBool(insert)) {
                    throw new ServiceException(String.format("[%s] 添加视频维度标识符失败" , videoId));
                }
            }
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
