package util;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 系统共通工具类
 */
public class Common {

    /**
     * 如因其他原因中文乱码，用此函数转换为中文。
     * @param str 转码前
     * @return String 转码后中文
     */
    public String convertChinese(String str) {
        if (str == null) {
            return null;
        }
        // 对获取的值进行二次编码转为字节数组 (ISO-8859-1)
        byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
        // 对获得的字节数组进行解码 (UTF-8)
        str = new String(bytes, StandardCharsets.UTF_8);
        return str;
    }

    /**
     * 根据下订单时间生成订单编号
     * @return String 订单编号 (格式: 年月日时分秒)
     */
    public String GenerateOrderNo() {
        // 生成订单号
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
}