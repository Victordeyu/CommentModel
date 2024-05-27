package org.qiuyun.comment.dao.Enum;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/26 15:12
 */

public enum CommentType {
    SONG(0, "video"),
    SONG_LIST(1, "");

    private final int value;
    private final String reasonPhrase;

    private CommentType(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }
}
