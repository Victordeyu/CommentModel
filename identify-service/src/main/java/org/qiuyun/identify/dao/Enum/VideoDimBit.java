package org.qiuyun.identify.dao.Enum;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 11:15
 */

public enum VideoDimBit {
    VIDEO_DIM_BIT_ONLYSELF(0,"video-dim-bit-onlyself"),
    VIDEO_DIM_BIT_EVERYONE(1,"video-dim-bit-everyone");

    private final int value;
    private final String commentDitDesc;

    private VideoDimBit(int value,String commentDitDesc){
        this.value=value;
        this.commentDitDesc=commentDitDesc;
    }

}
