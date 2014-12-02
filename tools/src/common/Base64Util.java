package common;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
	public static String encodeURL(String s) throws UnsupportedEncodingException {
		String s1 = Base64.encodeBase64URLSafeString(s.getBytes("utf-8"));
		return s1;
	}
	public static String encode(String s) throws UnsupportedEncodingException {
		String s1 = Base64.encodeBase64String(s.getBytes("utf-8"));
		return s1;
	}
	public static String decode(String s) throws UnsupportedEncodingException {
		byte[] b = Base64.decodeBase64(s);
		return new String(b, "utf-8");
	}
}
