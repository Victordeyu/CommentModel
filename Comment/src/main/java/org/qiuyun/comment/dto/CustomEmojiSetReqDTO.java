package org.qiuyun.comment.dto;

import lombok.Data;
import lombok.Getter;


import java.util.List;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/30 21:06
 */
@Getter
@Data
public class CustomEmojiSetReqDTO {
//    private Long id;

    private Long authorId;

    private String emojiSet;

    private List<CustomEmojiReqDTO>emojiReqDTOS;
}
