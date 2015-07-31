package com.aliyun.oss.urlsign.internal;

import com.aliyun.oss.urlsign.HttpMethod;
import com.aliyun.oss.urlsign.common.auth.ServiceSignature;
import com.aliyun.oss.urlsign.common.comm.RequestMessage;
import com.aliyun.oss.urlsign.common.exception.UrlSignException;
import com.aliyun.oss.urlsign.common.utils.HttpHeaders;
import com.aliyun.oss.urlsign.common.utils.HttpUtil;
import com.aliyun.oss.urlsign.model.ResponseHeaderOverrides;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 注意 目前采用sts方式暂不支持阿里云IMG服务（即cdn域名无法使用）
 * Created by 筱龙缘 on 2015/7/30.
 */
public class UrlSignUtils {

    /**
     * 主 A/K 方式获取
     * @param accessId
     * @param accessKey
     * @param method
     * @param expiration
     * @param bucketName
     * @param key
     * @param endpoint 阿里云三级域名、自定义的oss bucket域名 注意 目前采用sts方式暂不支持阿里云IMG服务（即cdn域名无法使用）
     * @return
     * @throws MalformedURLException
     */
    public static URL generatePresignedUrl(String accessId, String accessKey,
                                           HttpMethod method,
                                           Date expiration,
                                           String bucketName,
                                           String key,
                                           URI endpoint

    ){
        return generatePresignedUrl(accessId, accessKey, null, Boolean.FALSE, method, expiration,
                bucketName, key, endpoint, null, null, new HashMap<String, String>(), new HashMap<String, String>(), null);

    }

    /**
     * 主 A/K 方式获取
     * @param accessId
     * @param accessKey
     * @param expiration
     * @param bucketName
     * @param key
     * @param endpoint 阿里云三级域名、自定义的oss bucket域名 注意 目前采用sts方式暂不支持阿里云IMG服务（即cdn域名无法使用）
     * @return
     * @throws MalformedURLException
     */
    public static URL generatePresignedUrl(String accessId, String accessKey,
                                           Date expiration,
                                           String bucketName,
                                           String key,
                                           URI endpoint

    ) {

        HttpMethod method =  HttpMethod.GET;
        return generatePresignedUrl(accessId, accessKey, null, Boolean.FALSE, method, expiration,
                bucketName, key, endpoint, null, null, new HashMap<String, String>(), new HashMap<String, String>(), null);

    }

    /**
     * STS AK方式获取
     * @param accessId
     * @param accessKey
     * @param securityToken
     * @param method
     * @param expiration
     * @param bucketName
     * @param key
     * @param endpoint 阿里云三级域名、自定义的oss bucket域名 注意 目前采用sts方式暂不支持阿里云IMG服务（即cdn域名无法使用）
     * @return
     * @throws MalformedURLException
     */
    public static URL generatePresignedUrl(String accessId, String accessKey,
                                           String securityToken,
                                           HttpMethod method,
                                           Date expiration,
                                           String bucketName,
                                           String key,
                                           URI endpoint
    ){
        return generatePresignedUrl(accessId, accessKey, securityToken, Boolean.TRUE, method, expiration,
                bucketName, key, endpoint, null, null,new HashMap<String, String>(), new HashMap<String, String>(), null);

    }
    /**
     * STS AK方式获取
     * @param accessId
     * @param accessKey
     * @param securityToken
     * @param expiration
     * @param bucketName
     * @param key
     * @param endpoint 阿里云三级域名、自定义的oss bucket域名 注意 目前采用sts方式暂不支持阿里云IMG服务（即cdn域名无法使用）
     * @return
     * @throws MalformedURLException
     */
    public static URL generatePresignedUrl(String accessId, String accessKey,
                                           String securityToken,
                                           Date expiration,
                                           String bucketName,
                                           String key,
                                           URI endpoint
    ) {
        HttpMethod method =  HttpMethod.GET;
        return generatePresignedUrl(accessId, accessKey, securityToken, Boolean.TRUE, method, expiration,
                bucketName, key, endpoint, null, null,new HashMap<String, String>(), new HashMap<String, String>(), null);

    }

    /**
     *
     * @param accessId
     * @param accessKey
     * @param securityToken sts securityToken
     * @param useSecurityToken 是否使用sts
     * @param method 允许方法
     * @param expiration 过期时间
     * @param bucketName
     * @param key ObjectKey
     * @param endpoint 阿里云三级域名、自定义的oss bucket域名 注意 目前采用sts方式暂不支持阿里云IMG服务（即cdn域名无法使用）
     * @param contentType 内容类型
     * @param contentMD5 内容MD5
     * @param userMetadata 用户自定义metadata
     * @param queryParameter 查询参数
     * @param responseHeader 响应头
     * @return
     * @throws MalformedURLException
     */
    public static URL generatePresignedUrl(String accessId, String accessKey,
                                           String securityToken,
                                           boolean useSecurityToken,
                                           HttpMethod method,
                                           Date expiration,
                                           String bucketName,
                                           String key,
                                           URI endpoint
            , String contentType
            , String contentMD5
            , Map<String, String> userMetadata
            , Map<String, String> queryParameter
            , ResponseHeaderOverrides responseHeader
    ){


        OSSUtils.ensureBucketNameValid(bucketName);

        if (expiration == null) {
            throw new IllegalArgumentException("过期时间不可为空。");
        }

        String expires = String.valueOf(expiration.getTime() / 1000L);

        String resourcePath = OSSUtils.makeResourcePath(key);

        RequestMessage requestMessage = new RequestMessage();

        requestMessage.setEndpoint(endpoint);
        requestMessage.setMethod(method);
        requestMessage.setResourcePath(resourcePath);

        requestMessage.addHeader(HttpHeaders.DATE, expires);
        if (contentType != null && contentType.trim() != "") {
            requestMessage.addHeader(HttpHeaders.CONTENT_TYPE, contentType);
        }
        if (contentMD5 != null && contentMD5.trim() != "") {
            requestMessage.addHeader(HttpHeaders.CONTENT_MD5, contentMD5);
        }
        for (Map.Entry<String, String> h : userMetadata.entrySet()) {
            requestMessage.addHeader(OSSHeaders.OSS_USER_METADATA_PREFIX + h.getKey(), h.getValue());
        }

        Map<String, String> responseHeaderParams = new HashMap<String, String>();
        OSSUtils.populateResponseHeaderParameters(responseHeaderParams, responseHeader);
        if (responseHeaderParams.size() > 0) {
            requestMessage.setParameters(responseHeaderParams);
        }

        if (queryParameter != null && queryParameter.size() > 0) {
            for (Map.Entry<String, String> entry : queryParameter.entrySet()) {
                requestMessage.addParameter(entry.getKey(), entry.getValue());
            }
        }

        if (useSecurityToken) {
            requestMessage.addParameter(RequestParameters.SECURITY_TOKEN, securityToken);
        }

        String canonicalResource = "/" + ((bucketName != null) ? bucketName : "")
                + ((key != null ? "/" + key : ""));
        String canonicalString = SignUtils.buildCanonicalString(method.toString(), canonicalResource,
                requestMessage, expires);
        String signature = ServiceSignature.create().computeSignature(accessKey, canonicalString);

        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put(HttpHeaders.EXPIRES, expires);
        params.put(RequestParameters.OSS_ACCESS_KEY_ID, accessId);
        params.put(RequestParameters.SIGNATURE, signature);
        params.putAll(requestMessage.getParameters());

        String queryString = HttpUtil.paramToQueryString(params, OSSConstants.DEFAULT_CHARSET_NAME);

		/* Compse HTTP request uri. */
        String url = requestMessage.getEndpoint().toString();
        if (!url.endsWith("/")) {
            url += "/";
        }
        url += resourcePath + "?" + queryString;


        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new UrlSignException("加签出错",e);
        }

    }


}
