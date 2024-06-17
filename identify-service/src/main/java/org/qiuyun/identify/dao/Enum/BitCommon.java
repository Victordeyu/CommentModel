package org.qiuyun.identify.dao.Enum;

import lombok.Getter;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 11:16
 */
@Getter
public enum BitCommon {
    BIT_COMMON_MAX_BIT(10,"BIT_COMMON_MAX_BIT");

    private final int value;
    private final String desc;

    private BitCommon(int value, String desc){
        this.value=value;
        this.desc=desc;
    }
}
