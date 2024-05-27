package org.qiuyun.common.exception;

import org.qiuyun.common.errorcode.BaseErrorCode;
import org.qiuyun.common.errorcode.IErrorCode;

import java.util.Optional;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/26 16:15
 */

public class ServiceException extends AbstractException {

    public ServiceException(String message) {
        this(message, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServiceException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ServiceException(String message, Throwable throwable, IErrorCode errorCode) {
        super(Optional.ofNullable(message).orElse(errorCode.message()), throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}