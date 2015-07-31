阿里云Oss（针对私有bucket）的加签工具、外链工具 不依赖不依赖阿里云OSS sdk、第三方工具包 Java、Android平台都可以使用


    /**
     * STS 方式生成外链
     * @throws URISyntaxException
     */
    @Test
    public  void generatePresignedUrl() throws URISyntaxException {

        String bucketName="test";
        String keyId="STS.lNPyUWj3Cw4RljIWj915";
        String keySecret="wbbeqWA72EdvpWsOXctBNRENbSwpB5NfyZq0Paz3";
        String securityToken="CAES0AMIARKAAV0xbMc5QxHSaRCJquxvE8EiT+nKdG/ngHS98ywDkUmsTcLCyIyjFdYBLrShCWizY3" +
                "/0iMO9yUFQDB4FteiXFR38HdKCFGe35xj6TmNFbPHclMcAIO9feKMcdIh9oHkiSv8OY98K9oe+vy" +
                "+XYJetK6skjqmnJUBhawfOYEQayNrvGhhTVFMubE5QeVVXajNDdzRSbGpJV2o5MTUiEDEyMTc5Mzc1NTQwNjg3NjcqCGxvbmd5dWFuMIzT6pDuKToGUnNhTUQ1QoMCCgExGm8KBUFsbG93EikKDEFjdGlvbkVxdWFscxIGQWN0aW9uGhEKD29zczpMaXN0T2JqZWN0cxI7Cg5SZXNvdXJjZUVxdWFscxIIUmVzb3VyY2UaHwodYWNzOm9zczoqOio6cW0taW1nLWlmaXR0aW5nLyoajAEKBUFsbG93EicKDEFjdGlvbkVxdWFscxIGQWN0aW9uGg8KDW9zczpHZXRPYmplY3QSOwoOUmVzb3VyY2VFcXVhbHMSCFJlc291cmNlGh8KHWFjczpvc3M6KjoqOnFtLWltZy1pZml0dGluZy8qEh0KClN0cmluZ0xpa2USCm9zczpQcmVmaXgaAwoBKg==";
                        Date expiration=  new Date(System.currentTimeMillis() + (60 * 60 * 1000));

        URI endpoint = new URI("http://test.ifitting.com");

        URL url = UrlSignUtils.generatePresignedUrl(keyId, keySecret, securityToken, expiration, bucketName, "1.jpg", endpoint);
        System.out.println(url.toString());
    }
    /**
     * 主A/K 方式生成外链
     * @throws URISyntaxException
     */
    @Test
    public  void generatePresignedUrl2() throws URISyntaxException {

        String bucketName="test";
        String keyId="lNPyUWj3Cw4RljIWj915";
        String keySecret="wbbeqWA72EdvpWsOXctBNRENbSwpB5NfyZq0Paz3";
        Date expiration=  new Date(System.currentTimeMillis() + (60 * 60 * 1000));

        URI endpoint = new URI("http://test.ifitting.com");

        URL url = UrlSignUtils.generatePresignedUrl(keyId, keySecret, expiration, bucketName, "1.jpg", endpoint);
        System.out.println(url.toString());
    }








		筱龙缘
		2015-07-31
