package org.qiuyun.identify.dao.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 19:54
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VideoBitQueryReqDTO {
    private Long videoId;//对于uid用户来说，uid_list下的bit数组。
}
