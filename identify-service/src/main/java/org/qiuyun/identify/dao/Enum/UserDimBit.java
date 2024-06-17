package org.qiuyun.identify.dao.Enum;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 11:16
 */

public enum UserDimBit {
    USER_DIM_BIT_NORMAL(0,"user-dim-bit-normal"),
    USER_DIM_BIT_BLACKED(1,"user-dim-bit-blacked"),
    USER_DIM_BIT_BANNED(2,"user-dim-bit-banned");

    private final int value;
    private final String commentDitDesc;

    private UserDimBit(int value,String commentDitDesc){
        this.value=value;
        this.commentDitDesc=commentDitDesc;
    }
}
