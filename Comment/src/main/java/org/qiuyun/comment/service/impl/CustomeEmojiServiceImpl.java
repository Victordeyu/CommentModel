package org.qiuyun.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qiuyun.comment.dao.entity.CustomEmojiDO;
import org.qiuyun.comment.dao.entity.CustomEmojiSetDO;
import org.qiuyun.comment.dao.mapper.CustomEmojiMapper;
import org.qiuyun.comment.dao.mapper.CustomEmojiSetMapper;
import org.qiuyun.comment.dto.CustomEmojiSetReqDTO;
import org.qiuyun.comment.service.CustomeEmojiService;
import org.qiuyun.common.BeanUtil;
import org.qiuyun.common.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/31 14:34
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CustomeEmojiServiceImpl implements CustomeEmojiService {
    private final CustomEmojiMapper customEmojiMapper;
    private final CustomEmojiSetMapper customEmojiSetMapper;
    @Override
    @Transactional
    /**
     * add和create操作类似，直接放到同一函数去操作。
     */
    public void createEmojiSet(CustomEmojiSetReqDTO requestParam) {
        /**检查是否有重复
         *
         */
        String emojiSet= requestParam.getEmojiSet();
        List<CustomEmojiSetDO>customEmojiSetDOS=queryBySetName(emojiSet);
        Boolean createFlag=(customEmojiSetDOS==null||customEmojiSetDOS.size()==0);
        System.out.println("新建表情集合："+createFlag);

        if(customEmojiSetDOS==null&&customEmojiSetDOS.size()>1)
            throw new ServiceException("有重复表情集，请检查");

//        System.out.println(requestParam.getEmojiReqDTOS().toString());
        List<CustomEmojiDO> customEmojiDOS= BeanUtil.convert(requestParam.getEmojiReqDTOS(),CustomEmojiDO.class);
        System.out.println(customEmojiDOS.toString());
        customEmojiDOS.forEach(
                (each)->{
                    each.setEmojiSet(requestParam.getEmojiSet());
                    each.setAuthorId(requestParam.getAuthorId());
                    each.setDelFlag(false);
                }
        );

        /**
         * 分别插入到customEmojiMapper和customEmojiSetMapper中
         */
        try{
            customEmojiDOS.forEach(
                    (each)->{
                        int inserted=customEmojiMapper.insert(each);
                        if (!SqlHelper.retBool(inserted)) {
                            throw new ServiceException(String.format("[%s] 新建表情失败", each.getEmojiName()));
                        }
                    }
            );
            if(createFlag) {//如果是新建表情，还需要继续加入EmojiSet表中。
                CustomEmojiSetDO customEmojiSetDO = CustomEmojiSetDO.builder()
                        .authorId(requestParam.getAuthorId())
                        .emojiSet(requestParam.getEmojiSet())
                        .usageFreq(0).delFlag(false)
                        .build();
                int inserted = customEmojiSetMapper.insert(customEmojiSetDO);
                if (!SqlHelper.retBool(inserted)) {
                    throw new ServiceException(String.format("[%s] 新建表情集合失败", customEmojiSetDO.getEmojiSet()));
                }
            }

        }
        catch (Exception ex) {
            if (ex instanceof ServiceException) {
                log.error("{}，请求参数：{}", ex.getMessage(), requestParam.toString());
            } else {
                log.error("[{}] 新建表情包失败，请求参数：{}", requestParam.toString(), ex);
            }
            throw ex;
        }
    }

    /**
     *
     * @param requestParam
     *
     */
    @Override
    public void addEmoji(CustomEmojiSetReqDTO requestParam) {
        createEmojiSet(requestParam);
//        String emojiSet= requestParam.getEmojiSet();
//        List<CustomEmojiSetDO>customEmojiSetDOS=queryBySetName(emojiSet);
//        if(customEmojiSetDOS==null){
//            createEmojiSet(requestParam);
//        }
//        else{
//            if(customEmojiSetDOS.size()>1)
//                throw new ServiceException("有重复表情集，请检查");
//            CustomEmojiSetDO customEmojiSetDO=customEmojiSetDOS.get(0);
//            if(!Objects.equals(customEmojiSetDO.getAuthorId(), requestParam.getAuthorId())){
//                throw new ServiceException("作者不同！！无法添加");
//            }
//            List<CustomEmojiDO> customEmojiDOS= BeanUtil.convert(requestParam.getEmojiReqDTOS(),CustomEmojiDO.class);
//            customEmojiDOS.stream().forEach(
//                    (each)->{
//                        each.setEmojiSet(requestParam.getEmojiSet());
//                        each.setAuthorId(requestParam.getAuthorId());
//                        each.setDelFlag(false);
//                    }
//            );
//
//
//        }
    }

    @Override
    public List<CustomEmojiSetDO> queryBySetName(String emojiSet) {
        LambdaQueryWrapper<CustomEmojiSetDO>lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CustomEmojiSetDO::getEmojiSet,emojiSet)
                .eq(CustomEmojiSetDO::getDelFlag,false);
        return customEmojiSetMapper.selectList(lambdaQueryWrapper);
    }

}
