package org.qiuyun.comment.dto;

import lombok.Data;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/30 21:06
 */
@Data
public class CustomEmojiReqDTO {
//    private Long id;

    private String emojiName;

//    private String emojiSet;

    private String pic_url;

    private Integer displayOrder;

//    private Long authorId;
}
