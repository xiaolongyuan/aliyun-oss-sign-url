package com.aliyun.oss.urlsign.test;

import com.aliyun.oss.urlsign.internal.UrlSignUtils;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

/**
 * 项目：aliyun-parent
 * 包名：com.aliyun.oss.urlsign.test
 * Author: 筱龙缘
 * Update: 筱龙缘(2015-07-31 10:09)
 */
public class UrlSignTest {

    @Test
    public  void generatePresignedUrl() throws URISyntaxException {

        String bucketName="qm-img-ifitting";
        String keyId="STS.lNPyUWj3Cw4RljIWj915";
        String keySecret="wbbeqWA72EdvpWsOXctBNRENbSwpB5NfyZq0Paz3";
        String securityToken="CAES0AMIARKAAV0xbMc5QxHSaRCJquxvE8EiT+nKdG/ngHS98ywDkUmsTcLCyIyjFdYBLrShCWizY3" +
                "/0iMO9yUFQDB4FteiXFR38HdKCFGe35xj6TmNFbPHclMcAIO9feKMcdIh9oHkiSv8OY98K9oe+vy" +
                "+XYJetK6skjqmnJUBhawfOYEQayNrvGhhTVFMubE5QeVVXajNDdzRSbGpJV2o5MTUiEDEyMTc5Mzc1NTQwNjg3NjcqCGxvbmd5dWFuMIzT6pDuKToGUnNhTUQ1QoMCCgExGm8KBUFsbG93EikKDEFjdGlvbkVxdWFscxIGQWN0aW9uGhEKD29zczpMaXN0T2JqZWN0cxI7Cg5SZXNvdXJjZUVxdWFscxIIUmVzb3VyY2UaHwodYWNzOm9zczoqOio6cW0taW1nLWlmaXR0aW5nLyoajAEKBUFsbG93EicKDEFjdGlvbkVxdWFscxIGQWN0aW9uGg8KDW9zczpHZXRPYmplY3QSOwoOUmVzb3VyY2VFcXVhbHMSCFJlc291cmNlGh8KHWFjczpvc3M6KjoqOnFtLWltZy1pZml0dGluZy8qEh0KClN0cmluZ0xpa2USCm9zczpQcmVmaXgaAwoBKg==";
                        Date expiration=  new Date(System.currentTimeMillis() + (60 * 60 * 1000));

        URI endpoint = new URI("http://ifitimg.ifitting.me");

        URL url = UrlSignUtils.generatePresignedUrl(keyId, keySecret, securityToken, expiration, bucketName, "1.jpg", endpoint);
        System.out.println(url.toString());
    }
}
