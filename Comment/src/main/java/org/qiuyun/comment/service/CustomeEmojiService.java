package org.qiuyun.comment.service;

import org.qiuyun.comment.dao.entity.CustomEmojiSetDO;
import org.qiuyun.comment.dto.CustomEmojiSetReqDTO;

import java.util.List;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/30 21:05
 */

public interface CustomeEmojiService {

    void createEmojiSet(CustomEmojiSetReqDTO requestParam);

    void addEmoji(CustomEmojiSetReqDTO requestParam);


    List<CustomEmojiSetDO> queryBySetName(String emojiSet);

//    void deletEmoji();
//
//    void deleteEmojiSet();
}
