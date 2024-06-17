package org.qiuyun.framwork.starter.distributedid.core;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/3 16:23
 */

public interface IdGenerator {
    /**
     * 下一个 ID
     */
    default long nextId() {
        return 0L;
    }

    /**
     * 下一个 ID 字符串
     */
    default String nextIdStr() {
        return "";
    }
}
