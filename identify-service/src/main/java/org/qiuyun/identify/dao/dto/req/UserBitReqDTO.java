package org.qiuyun.identify.dao.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 19:54
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserBitReqDTO {
    @NotNull
    private Long uid;//对于uid用户来说，uid_list下的bit数组。
    private List<Long> uid_list;
}
