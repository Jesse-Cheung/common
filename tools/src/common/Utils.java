package common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * 工具类
 * 
 * 
 */
public abstract class Utils {

	/**
	 * 是否是null或空字符串
	 * 
	 * @param str
	 * @return
	 */
	public final static boolean isNull(String str) {
		return str == null || str.trim().length() == 0 || "null".equals(str);
	}
	/**
	 * json是否是null
	 * 
	 * @param str
	 * @return
	 */
	public final static String getPrecent(double precent) {
		NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
		format.setMinimumFractionDigits(0);// 设置小数位
		String pr = format.format(precent * 100 / 100.0);
		return pr;
	}

	public final static boolean isJsonNull(String str) {
		return str == null || str.trim().length() == 0 || "null".equals(str)
				|| "[]".equals(str);
	}
	public final static boolean isNotJson(String str) {
		return str == null || str.trim().length() == 0 || "null".equals(str)
				|| !str.startsWith("[");
	}

	/**
	 * 返回非null字符串，如果str=null则返回空字符串
	 * 
	 * @param str
	 * @return
	 */
	public final static String getString(String str) {
		return str == null ? "" : str;
	}

	/**
	 * 将字符数组转化成Int数组
	 * 
	 * @param str
	 * @return
	 */
	public static Serializable[] StringToInteger(String[] str) {
		if (str.length <= 0) {
			return null;
		}
		Serializable[] st = new Serializable[str.length];
		for (int i = 0; i < str.length; i++) {
			st[i] = Integer.parseInt(str[i]);
		}
		return st;
	}

	/**
	 * array以separator=“,”做为分割格式化成字符串如：“a,b,c”<br>
	 * array以separator=“','”做为分割格式化成字符串如：“a','b','c”
	 * 
	 * @param arr
	 * @param separator
	 *            分割符
	 * @return
	 */
	public static String arrayToString(String[] arr, String separator) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			if (i != arr.length - 1) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}
	public static String arrayToString2(String[] arr, String separator) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			if (i != arr.length) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}
	/**
	 * array以“,”做为分割格式化成字符串如：“a,b,c”
	 * 
	 * @param arr
	 * @return
	 */
	public static String arrayToString(String[] arr) {
		return arrayToString(arr, ",");
	}
	/**
	 * array以“,”做为分割格式化成字符串如：“a,b,c”
	 * 
	 * @param arr
	 * @return
	 */
	public static String arrayToSQLString(String[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i == arr.length - 1) {
				sb.append("'" + arr[i] + "'");
			} else {
				sb.append("'" + arr[i] + "',");
			}
		}
		return sb.toString();
	}

	public static String ascIIToHtml(String s) {
		// System.out.println(s);
		s = s.replaceAll("\"", "&quot;");
		// System.out.println(s);
		return s;
	}

	/**
	 * 字符形如：“,a,b,,c,”格式化成“a,b,c”
	 * 
	 * @param s
	 * @return
	 */
	public final static String formatString(String s) {
		s = s.replaceAll(",,", ",");
		s = s.replaceAll("^,", "");
		s = s.replaceAll(",$", "");
		return s;
	}

	/**
	 * 如果separator为“|”，字符形如：“|a|b||c|”格式化成“a|b|c”
	 * 
	 * @param s
	 * @param separator
	 *            分割符，不支持“\”
	 * @return
	 */
	public final static String formatString(String s, String separator) {
		s = s.replaceAll("\\" + separator + "\\" + separator, separator);
		s = s.replaceAll("^" + separator, "");
		s = s.replaceAll(separator + "$", "");
		return s;
	}

	/**
	 * 尝试把String s转换成int，如果转换异常则返回0;
	 * 
	 * @param s
	 * @return
	 */
	public final static int parseInt(String s) {
		try {
			int i = Integer.parseInt(s);
			return i;
		} catch (Exception e) {
			// System.out.println(">>>Util.parseInt()");
			// e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 尝试把String s转换成double，如果转换异常则返回0;
	 * 
	 * @param s
	 * @return
	 */
	public final static double parseDouble(String s) {
		try {
			double i = Double.parseDouble(s);
			return i;
		} catch (Exception e) {
			// System.out.println(">>>Util.parseInt()");
			// e.printStackTrace();
		}
		return 0.0;
	}

	/**
	 * 自动把request里的参数组装成形如：a1=vvv1&a2=vvv2...
	 * 
	 * @param request
	 * @param noAppendUrlParameterNames
	 *            不组装在字符串中的参数名列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final static String readRequestCreateUrl(HttpServletRequest request,
			Set<String> noAppendUrlParameterNames) {
		StringBuffer sb = new StringBuffer();
		Enumeration<String> req = request.getParameterNames();
		int i = 0;
		while (req.hasMoreElements()) {
			String n = req.nextElement();
			if (noAppendUrlParameterNames != null) {
				if (!noAppendUrlParameterNames.contains(n)) {
					if (i != 0) {
						sb.append("&");
					}
					sb.append(n + "=" + request.getParameter(n));
					i++;
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 自动把request里的参数组装成形如：a1=vvv1&a2=vvv2...
	 * 
	 * @param request
	 * @param noAppendUrlParameterName
	 *            不组装在字符串中的参数名
	 * @return
	 */
	public final static String readRequestCreateUrl(HttpServletRequest request,
			String noAppendUrlParameterName) {
		Set<String> set = new HashSet<String>();
		set.add(noAppendUrlParameterName);
		return readRequestCreateUrl(request, set);
	}

	/**
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public final static String format1(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * @param date
	 * @return yyyy年M月d日
	 */
	public final static String format2(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
			return sdf.format(date);
		}
		return "";
	}
	/**
	 * @param date
	 * @return yyyy-mm-dd hh:mm:ss
	 */
	public final static String format4(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * @param date
	 * @return M.d
	 */
	public final static String format3(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("M.d");
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * @param strDate
	 *            yyyy-MM-dd
	 * @return
	 */
	public final static Date toDate(String strDate) {
		if (strDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return sdf.parse(strDate);
			} catch (ParseException e) {
			}
		}
		return null;
	}
	/**
	 * @param strDate
	 *            yyyy-MM-dd hh:mm:ss
	 * @return
	 */
	public final static Date toIime(String strDate) {
		if (strDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(strDate);
			} catch (ParseException e) {
			}
		}
		return null;
	}
	/**
	 * 检测str是否在array里。
	 * 
	 * @param array
	 * @param str
	 * @return true：在，false：不在
	 */
	public static boolean inArray(String[] array, String str) {
		for (String s : array) {
			if (s.equals(str)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 检测str是否在array里。
	 * 
	 * @param array
	 * @param str
	 * @return true：在，false：不在
	 */
	public static String inArrayParseString(String[] array, String str) {
		String newStr = "";
		for (String s : array) {
			if (s.equals(str)) {
				continue;
			}
			if (!Utils.isNull(newStr)) {
				newStr = newStr + s + ",";
			} else {
				newStr = s + ",";
			}

		}
		return newStr;
	}

	/**
	 * 当字符串长度在1000-2000之间的时候补空格 (文本内容)
	 * 
	 * @param str
	 * @return
	 */
	public static String translate(String str) {
		if (null == str) {
			return "";
		}
		if (str.length() > 2000 || str.length() < 1000) {
			return str;
		}
		char[] c = new char[2001];
		Arrays.fill(c, ' ');
		char[] cs = str.toCharArray();
		System.arraycopy(cs, 0, c, 0, cs.length);
		return new String(c, 0, c.length);
	}
	/**
	 * 小时分钟转成秒数计算
	 * 
	 * @param str
	 * @return
	 */
	public static Integer translateToss(String str) {
		int ss = 0;
		if (null == str) {
			return ss;
		} else if (str.length() == 8) {
			String args[] = str.split(":");
			int hh = Utils.parseInt(args[0]) * 60 * 60;
			int mm = Utils.parseInt(args[1]) * 60;
			ss = Utils.parseInt(args[2]);
			ss = ss + hh + mm;
		}
		return ss;
	}
	/**
	 * 秒数计算成小时
	 * 
	 * @param str
	 * @return
	 */
	public static Double translateTohh(int ss) {
		double mm = 0;
		mm = ss / 3600.0;
		BigDecimal b = new BigDecimal(mm);
		double df = b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
		return df;
	}
	/**
	 * 秒数计算成分钟
	 * 
	 * @param str
	 * @return
	 */
	public static Double translateTomm(int ss) {
		double mm = 0;
		mm = ss / 60.0;
		BigDecimal b = new BigDecimal(mm);
		double df = b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
		return df;
	}
	/**
	 * 秒数计算成分秒
	 * 
	 * @param str
	 * @return
	 */
	public static String translateToMm(int ss) {
		int mm = 0;
		mm = ss / 60;
		ss = ss % 60;
		// BigDecimal b = new BigDecimal(mm);
		// double df = b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
		String v = mm + "分" + ss + "秒";
		return v;
	}

	public static String getlastIndexOf(String str) {
		String restult = "";
		int a = str.lastIndexOf("/", str.length());
		restult = str.substring(a + 1, str.length());
		return restult;
	}
	public static String listToString(String[] arrays, String separator) {
		List<String> list = new ArrayList<String>();
		for (String s : arrays) {
			list.add(s);
		}
		if (list.contains(separator)) {
			list.remove(separator);
		}
		String a = "";
		for (String stg : list) {
			a = a + stg + ",";
		}
		return a;
	}
	public static void main(String[] args) {
		//
		// String t1="1,2,3,,4,";
		// String t2="5,6,7,,9,";
		// String arr=t1+t2;
		// String[] arrays=arr.split(",");
		// String a=null;
		// String b=null;
		// String c=a+b;
		// System.out.println(c);
		// File file= new
		// File("C:\\Users\\zhoushiyang\\Documents\\My RTX Files\\周士阳\\chuanpiao.txt");
		// String charset=Utils.getCode(file);
		// try {
		// String content =FileUtils.readFileToString(file, charset);
		// System.out.println(content);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		double b = (double) 1 / 3;
		System.out.println(b);
		// ChuanpiaoBasicAcount c=new
		// ChuanpiaoBasicAcount("E:\\myworkspace\\EduApkManagerTest1.0\\WebRoot\\examExcelTemplate\\com.edu.traveltest\\传票.xls");
		// System.out.println(c.getValueJson());
		//
	}
	public static Integer length(String[] args) {
		int ss = 0;
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				if (args[i] != null && !args[i].equals("")) {
					ss++;
				}
			}

		}
		return ss;
	}
	public static String getCode(File file1) {
		String charset = "GBK";
		byte[] first3Bytes = new byte[3];
		try {
			boolean checked = false;
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file1));
			bis.mark(0);
			int read = bis.read(first3Bytes, 0, 3);
			if (read == -1) {
				return charset; // 文件编码为 ANSI
			} else if (first3Bytes[0] == (byte) 0xFF
					&& first3Bytes[1] == (byte) 0xFE) {
				charset = "UTF-16LE"; // 文件编码为 Unicode
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE
					&& first3Bytes[1] == (byte) 0xFF) {
				charset = "UTF-16BE"; // 文件编码为 Unicode big endian
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xEF
					&& first3Bytes[1] == (byte) 0xBB
					&& first3Bytes[2] == (byte) 0xBF) {
				charset = "UTF-8"; // 文件编码为 UTF-8
				checked = true;
			}
			bis.reset();
			if (!checked) {
				int loc = 0;
				while ((read = bis.read()) != -1) {
					loc++;
					if (read >= 0xF0)
						break;
					if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
						break;
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
							// (0x80
							// - 0xBF),也可能在GB编码内
							continue;
						else
							break;
					} else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else
								break;
						} else
							break;
					}
				}
			}
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(charset);
		return charset;

	}
	/**
	 * @param request
	 * @return 返回页面参数map
	 */
	public static Map<String, Object> toMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		Map<String, String[]> rMap = request.getParameterMap();
		for (Map.Entry<String, String[]> i : rMap.entrySet()) {
			String key = i.getKey();
			String values[] = i.getValue();
			if (values.length == 1) {
				map.put(key, (!"".equals(values[0]) && values[0] != null)
						? values[0]
						: null);
			} else {
				throw new RuntimeException(" 页面参数重复 ! ");
			}
		}
		return map;
	}

}
