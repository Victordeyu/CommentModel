package org.qiuyun.identify.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 11:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("video_bit")
public class VideoBitDO {
    private Long id;
    private Long videoId;
    private Integer type;
    private Boolean val;
}
