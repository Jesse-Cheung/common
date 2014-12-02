package common;

import java.util.regex.Pattern;

public class CheckUtil {
	public static Boolean isStringEmpty(String str) {
		if (null == str || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Boolean isMacString(String str) {
		String patternMac="^[a-fA-F0-9]{2}:[a-fA-F0-9]{2}:[a-fA-F0-9]{2}:[a-fA-F0-9]{2}:[a-fA-F0-9]{2}:[a-fA-F0-9]{2}$";
		return Pattern.matches(patternMac, str);
	}
	
	public static void main(String[] args) {
		String s = "00:26:a6:55:72:36";
		System.out.println(CheckUtil.isMacString(s));
	}
}
