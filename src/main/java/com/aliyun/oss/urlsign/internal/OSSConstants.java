package com.aliyun.oss.urlsign.internal;

/**
 * Miscellaneous constants used for oss client service.
 */
public final class OSSConstants {

    public static final String DEFAULT_OSS_ENDPOINT = "http://oss.aliyuncs.com";

    public static final String DEFAULT_CHARSET_NAME = "utf-8";
    public static final String DEFAULT_XML_ENCODING = "utf-8";
    
    public static final String DEFAULT_OBJECT_CONTENT_TYPE = "application/octet-stream";
    
    public static final int KB = 1024;
    public static final int DEFAULT_BUFFER_SIZE = 8 * KB;
    public static final int DEFAULT_STREAM_BUFFER_SIZE = 128 * KB;
    
    public static final long DEFAULT_FILE_SIZE_LIMIT = 5 * 1024 * 1024 * 1024L;

    public static final String RESOURCE_NAME_COMMON = "common";
    public static final String RESOURCE_NAME_OSS = "oss";
    
    public static final int OBJECT_NAME_MAX_LENGTH = 1024;
}
