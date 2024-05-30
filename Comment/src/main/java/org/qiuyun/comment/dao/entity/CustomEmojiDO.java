package org.qiuyun.comment.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
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
@TableName("custom_emoji")
public class CustomEmojiDO {

    private Long id;

    private String emojiName;

    private String emojiSet;

    private String pic_url;

    private Integer order;
}
