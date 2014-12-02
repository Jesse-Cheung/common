package common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json {
	private Json() {
	}

	/**
	 * JSON发送画面数据方法
	 * 
	 * @param content
	 *  JSON数据
	 */
	public static void sendMsg(HttpServletResponse response, String content) {
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(content);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			//TODO 删除
			e.printStackTrace();
		}
	}
	/**
	 * 对象转换json字符串 
	 * @param o
	 * @return
	 */
	public static String tojson(Object o) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		if (o != null) {
			return gson.toJson(o);
		} else {
			return "";
		}
	}
	/**
	 * @param response
	 * @param content
	 * @param type 之定义返回类型
	 * type常用值|"application/json;charset=UTF-8"|"text/css;charset=UTF-8"|
	 */
	public static void sendMsg(HttpServletResponse response, String content ,String type) {
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType(type);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(content);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			//TODO 删除
			e.printStackTrace();
		}
	}
}
