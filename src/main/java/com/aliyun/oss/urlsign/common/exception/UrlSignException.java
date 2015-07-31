package com.aliyun.oss.urlsign.common.exception;

/**
 * 项目：aliyun-parent
 * 包名：com.aliyun.oss.urlsign.common.exception
 * Author: 筱龙缘
 * Update: 筱龙缘(2015-07-31 10:10)
 */
public class UrlSignException extends RuntimeException {
    public UrlSignException() {
    }

    public UrlSignException(String message) {
        super(message);
    }

    public UrlSignException(String message, Throwable cause) {
        super(message, cause);
    }

    public UrlSignException(Throwable cause) {
        super(cause);
    }

    public UrlSignException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
