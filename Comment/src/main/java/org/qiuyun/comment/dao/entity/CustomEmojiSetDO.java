package org.qiuyun.comment.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/30 10:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("custom_emoji_set")
public class CustomEmojiSetDO {

    private Long id;

    private String emojiSet;

    private Long authorId;

    private Integer usageFreq;

    private Boolean delFlag;
}
