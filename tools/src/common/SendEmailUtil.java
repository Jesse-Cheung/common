package common;

import org.apache.commons.mail.SimpleEmail;
public class SendEmailUtil {
	static SimpleEmail email;
	public static void send() {
		try {

			email = new SimpleEmail();
			email.setCharset("UTF-8");
			email.setFrom("wu.h@edusoft.cc", "爱丁云后台");
			email.setSubject("爱丁邮件发送测试");
			email.setHostName("smtp.exmail.qq.com");
			email.setSmtpPort(25);
			email.setAuthentication("wu.h@edusoft.cc", "1qaz2wsx!@");
			email.addTo("mouse11212@126.com");
			email.setMsg("测试邮件发送");
			email.send();
		} catch (Exception e) {
		}
	}
	public static void main(String[] args) {
		SendEmailUtil.send();
	}
}

