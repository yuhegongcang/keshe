package util;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Common {
	public String convertChinese(String str) {
		byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
		str = new String(bytes, StandardCharsets.UTF_8);
		return str;
	}
	public String GenerateOrderNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
}
