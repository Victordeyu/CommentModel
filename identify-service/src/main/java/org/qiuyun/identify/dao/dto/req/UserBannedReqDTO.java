package org.qiuyun.identify.dao.dto.req;

import lombok.Getter;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 20:03
 */
@Getter
public class UserBannedReqDTO {
    private Long uid;
    private Long startTime;
    private Long endTime;
}
