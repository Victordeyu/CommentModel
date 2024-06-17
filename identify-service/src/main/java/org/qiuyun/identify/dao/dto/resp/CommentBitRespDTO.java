package org.qiuyun.identify.dao.dto.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 20:14
 */
@Data
//@Accessors(chain = true)
public class CommentBitRespDTO {
    private Long commentId;

    private Byte[] bit;
}
