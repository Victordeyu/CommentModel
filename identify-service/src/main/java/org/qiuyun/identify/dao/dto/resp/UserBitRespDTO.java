package org.qiuyun.identify.dao.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.*;

import java.util.List;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 19:50
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBitRespDTO {
    private Long Uid;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<List<Byte>> bit;
}
