package org.qiuyun.identify.dao.dto.req;

import lombok.Getter;

import java.util.List;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 19:58
 */
@Getter
public class UserBlackListReqDTO {
    private Long uid;
    private List<Long> black_uid;
}
