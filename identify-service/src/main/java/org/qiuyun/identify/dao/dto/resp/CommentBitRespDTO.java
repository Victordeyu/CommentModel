package org.qiuyun.identify.dao.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.*;

import java.util.List;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 20:14
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
//@Accessors(chain = true)
public class CommentBitRespDTO {
    private Long commentId;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Byte> bit;
}
