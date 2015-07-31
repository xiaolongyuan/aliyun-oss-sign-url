package com.aliyun.oss.urlsign.internal;

import com.aliyun.oss.urlsign.common.utils.HttpUtil;
import com.aliyun.oss.urlsign.common.utils.ResourceManager;
import com.aliyun.oss.urlsign.model.ResponseHeaderOverrides;

import java.util.Map;

/**
 * Created by Administrator on 2015/7/30.
 */
public class OSSUtils {

    private static String BUCKET_NAMING_REGEX = "^[a-z0-9][a-z0-9_\\-]{1,61}[a-z0-9]$";


    public static final ResourceManager OSS_RESOURCE_MANAGER =
            ResourceManager.getInstance();

    /**
     * Encode object URI.
     */
    private static String urlEncodeKey(String key) {
        StringBuffer resultUri = new StringBuffer();

        String[] keys = key.split("/");
        resultUri.append(HttpUtil.urlEncode(keys[0], OSSConstants.DEFAULT_CHARSET_NAME));
        for (int i = 1; i < keys.length; i++) {
            resultUri.append("/").append(HttpUtil.urlEncode(keys[i], OSSConstants.DEFAULT_CHARSET_NAME));
        }

        if (key.endsWith("/")) {
            // String#split ignores trailing empty strings,
            // e.g., "a/b/" will be split as a 2-entries array,
            // so we have to append all the trailing slash to the uri.
            for (int i = key.length() - 1; i >= 0; i--) {
                if (key.charAt(i) == '/') {
                    resultUri.append("/");
                } else {
                    break;
                }
            }
        }

        return resultUri.toString();
    }

    /**
     * Make a resource path from the object key, used when the bucket name pearing in the endpoint.
     */
    public static String makeResourcePath(String key) {
        return key != null ? OSSUtils.urlEncodeKey(key) : null;
    }

    /**
     * Make a resource path from the bucket name and the object key.
     */
    public static String makeResourcePath(String bucket, String key) {
        if (bucket != null) {
            return bucket + (key != null ? "/" + OSSUtils.urlEncodeKey(key) : "");
        } else {
            return null;
        }
    }

    /**
     * Validate bucket name.
     */
    public static boolean validateBucketName(String bucketName) {
        if (bucketName == null) {
            return false;
        }
        return bucketName.matches(BUCKET_NAMING_REGEX);
    }

    public static void ensureBucketNameValid(String bucketName) {
        if (!validateBucketName(bucketName)) {
            throw new IllegalArgumentException(OSS_RESOURCE_MANAGER.getFormattedString(
                    "BucketNameInvalid", bucketName));
        }
    }

    public static void populateResponseHeaderParameters(Map<String, String> params,
                                                        ResponseHeaderOverrides responseHeaders) {
        if (responseHeaders != null) {
            if (responseHeaders.getCacheControl() != null) {
                params.put(ResponseHeaderOverrides.RESPONSE_HEADER_CACHE_CONTROL,
                        responseHeaders.getCacheControl());
            }

            if (responseHeaders.getContentDisposition() != null) {
                params.put(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_DISPOSITION,
                        responseHeaders.getContentDisposition());
            }

            if (responseHeaders.getContentEncoding() != null) {
                params.put(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_ENCODING,
                        responseHeaders.getContentEncoding());
            }

            if (responseHeaders.getContentLangauge() != null) {
                params.put(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_LANGUAGE,
                        responseHeaders.getContentLangauge());
            }

            if (responseHeaders.getContentType() != null) {
                params.put(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_TYPE,
                        responseHeaders.getContentType());
            }

            if (responseHeaders.getExpires() != null) {
                params.put(ResponseHeaderOverrides.RESPONSE_HEADER_EXPIRES,
                        responseHeaders.getExpires());
            }
        }
    }

//    /**
//     * Make a third-level domain by appending bucket name to front of original endpoint
//     * if no binding to CNAME, otherwise use original endpoint as second-level domain directly.
//     */
//    public static URI determineFinalEndpoint(URI endpoint, String bucket) {
//        try {
//            StringBuilder conbinedEndpoint = new StringBuilder();
//            conbinedEndpoint.append(String.format("%s://", endpoint.getScheme()));
//            conbinedEndpoint.append(buildCanonicalHost(endpoint, bucket, clientConfig));
//            conbinedEndpoint.append(endpoint.getPort() != -1 ? String.format(":%s", endpoint.getPort()) : "");
//            conbinedEndpoint.append(endpoint.getPath());
//            return new URI(conbinedEndpoint.toString());
//        } catch (URISyntaxException ex) {
//            throw new IllegalArgumentException(ex.getMessage(), ex);
//        }
//    }
//
//
//    private static String buildCanonicalHost(URI endpoint, String bucket, ClientConfiguration clientConfig) {
//        String host = endpoint.getHost();
//        boolean isCname = cnameExcludeFilter(host, clientConfig.getCnameExcludeList());
//
//        StringBuffer cannonicalHost = new StringBuffer();
//        if (bucket != null && !isCname) {
//            cannonicalHost.append(bucket);
//            cannonicalHost.append(".");
//            cannonicalHost.append(host);
//        } else {
//            cannonicalHost.append(host);
//        }
//
//        return cannonicalHost.toString();
//    }
//    private static boolean cnameExcludeFilter(String hostToFilter, List<String> excludeList) {
//        if (hostToFilter != null && !hostToFilter.trim().isEmpty()) {
//            String canonicalHost = hostToFilter.toLowerCase();
//            for (String excl : excludeList) {
//                if (canonicalHost.endsWith(excl)) {
//                    return false;
//                }
//            }
//            return true;
//        }
//        throw new  IllegalArgumentException("Host name can not be null.");
//    }
}
