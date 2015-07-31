package com.aliyun.oss.urlsign.common.utils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Manager class to get localized resources.
 */
public class ResourceManager {


    private static Map<String, String> bundle = new HashMap<>();
    ;

    static {

        bundle.put("ConnectionError", "网络连接错误，详细信息：{0}");
        bundle.put("EncodingFailed", "编码失败： {0}");
        bundle.put("FailedToParseResponse", "返回结果无效，无法解析。");
        bundle.put("ParameterIsNull", "参数\"{0}\"为空指针。");
        bundle.put("ParameterStringIsEmpty", "参数\"{0}\"是长度为0的字符串。");
        bundle.put("ParameterIsInvalid", "参数\"{0}\"无效。");
        bundle.put("ServerReturnsUnknownError", "服务器返回未知错误。");

        bundle.put("BucketNameInvalid", "Bucket \"{0}\" 名称无效。Bucket 命名规范：1)只能包括小写字母，数字和短横线（-）；2)必须以小写字母或者数字开头；3)长度必须在 3-63 字节之间。");
        bundle.put("CannotReadContentStream", "无法读取Object内容的输入流。");
        bundle.put("EndpointProtocolInvalid", "仅支持http协议。Endpoint必须以http://开头。");
        bundle.put("FailedToEncodeObjectKey", "无法使用\"{1}\"编码Object key：\"{0}\"。");
        bundle.put("FailedToDecodeUrl", "Failed to decode url \"{0}\"");
        bundle.put("FailedToEncodeUri", "无法编码URI。");
        bundle.put("FileSizeOutOfRange", "输入流的字节数必须小于5G字节。");
        bundle.put("GroupGranteeNotSupportId", "GroupGrantee不支持设置identifier。");
        bundle.put("InvalidRangeValues", "参数start和end的值不能同时小于0。");
        bundle.put("MaxKeysOutOfRange", "maxKeys不能小于0且不能大于1000。");
        bundle.put("MaxPartsOutOfRange", "Max parts参数的最大值为{0}。");
        bundle.put("MaxUploadsOutOfRange", "The number of max multipart uploads should be no greater than {0}.");
        bundle.put("MustSetBucketName", "请设置Bucket名称。");
        bundle.put("MustSetExpiration", "请设置超时时间。");
        bundle.put("MustSetContentStream", "请设置上传的文件流。");
        bundle.put("MustSetUploadId", "必须指定Upload ID。");
        bundle.put("ObjectKeyInvalid", "Object Key \"{0}\" 无效。Object名称在使用UTF-8编码后长度必须在 1-1023字节之间，而且不能包含回车、换行、以及xml1 .0不支持的字符，同时也不能以“/”或者“\\”开头。");
        bundle.put("PartNumberMarkerOutOfRange", "Part number marker必须在1-10000之间（闭区间）。");
        bundle.put("PartNumberOutOfRange", "Part number必须在1-10000之间（闭区间）。");
        bundle.put("NoSuchKey", "Key不存在。");
        bundle.put("FailedToParseResponse", "返回结果无法正确解析。 : \"{0}\"");

    }

    ResourceManager() {
    }

    public static ResourceManager getInstance() {
        return new ResourceManager();
    }

    public String getString(String key) {
        return bundle.get(key);
    }

    public String getFormattedString(String key, Object... args) {
        return MessageFormat.format(getString(key), args);
    }
}
