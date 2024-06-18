package org.qiuyun.identify.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user_black_list")
public class UserBlackListDO {
    private Long id;
    private Long uid;
    private java.util.Date createTime;
    private Long blackUsr;
}
