package org.qiuyun.comment.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/26 15:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("userInfo")
public class UserInfoDO {

    private Integer uid;

    private String name;

//    private String headIcon;

    private String description;
}
