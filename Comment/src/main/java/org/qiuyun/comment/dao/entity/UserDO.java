package org.qiuyun.comment.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/27 11:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@TableName("t_user")
public class UserDO {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 注销时间戳
     */
    private Long deletionTime;
}
