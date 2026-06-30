package util;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 系统通用工具类
 */
public class Common {

    /**
     * 如果因为某些原因（如 Tomcat 配置问题）导致 GET 请求中文乱码，可用此方法转换
     * @param str 转码前
     * @return String 转码后中文
     */
    public String convertChinese(String str) {
        if (str == null) return null;
        // 对获取的值进行二次编码转为字节数组，再解码为 UTF-8
        byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * 根据当前时间生成唯一的订单编号
     * 格式：年月日时分秒 (例如：20260626083849)
     * @return String 订单编号
     */
    public String GenerateOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
}