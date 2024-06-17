package org.qiuyun.identify.dao.Enum;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 11:16
 */

public enum CommentDimBit {
    COMMENT_DIM_BIT_ONLYSELF(0,"comment-dim-bit-onlyself"),
    COMMENT_DIM_BIT_FRIEND(1,"comment-dim-bit-friend"),
    COMMENT_DIM_BIT_FANS(2,"comment-dim-bit-fans"),
    COMMENT_DIM_BIT_ATFRIEND(3,"comment-dim-bit-atfriend"),
    COMMENT_DIM_BIT_EVERYONE(4,"comment-dim-bit-everyone");


    private final int value;
    private final String commentDitDesc;

    private CommentDimBit(int value,String commentDitDesc){
        this.value=value;
        this.commentDitDesc=commentDitDesc;
    }
}
