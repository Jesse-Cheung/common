package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author 邓骐辰
 * 
 */
public class Common {
	/**
	 * 整数随机数生成
	 * 
	 * @return
	 */
	public static int random() {
		return (int) (Math.random() * 9000 + 1000);
	}
	public static int randomSex() {
		return (int) (Math.random() * 900000 + 100000);
	}
	/**
	 * 验证手机号
	 * @param mobiles 手机号
	 * @return
	 */
	public static boolean isMobileNumber(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[^1^4,\\D]))\\d{8}$");  
		Matcher m = p.matcher(mobiles);  
		return m.matches();    
	}
	/**
	 * 正则判断
	 * @param reg 正则表达式
	 * @param str 匹配的字符串
	 * @return
	 */
	public static boolean regex(String reg,String str){
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		boolean b= matcher.matches();
		return b;
	} 
}
