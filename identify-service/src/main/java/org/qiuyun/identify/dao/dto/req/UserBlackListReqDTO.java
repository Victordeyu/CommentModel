package org.qiuyun.identify.dao.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 19:58
 */
@Getter
@Setter
public class UserBlackListReqDTO {
    @NotNull
    private Long uid;
    private List<Long> black_uid;
}
