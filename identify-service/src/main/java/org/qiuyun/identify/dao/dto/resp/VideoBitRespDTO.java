package org.qiuyun.identify.dao.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 20:14
 */
@Builder
public class VideoBitRespDTO {
    private Long videoId;
    private List<Byte> bit;
}
