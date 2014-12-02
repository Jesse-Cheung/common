package common;

import java.math.BigDecimal;

public class FanWanConfig {
	//短信URL
	public static String SMS_URL = "sandboxapp.cloopen.com";
	//短信端口
	public static String SMS_PORT = "8883";
	//短信账户SID
	public static String SMS_ACCOUNT_SID = "aaf98f89488d0aad0148a644071b0abc";
	//短信账户TOKEN
	public static String SMS_AUTH_TOKEN = "0f8842d41398449dbe230b005d3f2aa2";
	//饭碗儿APPID
	public static String SMS_APP_ID = "8a48b551488d07a80148a64ea4ad0aaf";
	//短信发送成功应返回的正确代码
	public static String SMS_SUCCESS_NUMBER = "000000";
	//饭碗短信注册模板ID
	public static String SMS_TEMPLET_REGISTER__NUMBER = "4907";
	//商户后台找回密码发送验证码模板ID
	public static String SMS_TEMPLET_SYS_SELLER_RESETPASSWORD__NUMBER = "8308";
	//饭碗短信注册成功发送密码模板ID
	public static String SMS_TEMPLET_RANDOM_PASSWORD_NUMBER = "4989";
	
	//文件存储时使用的本地本地目录
	public static String WEB_STATIC_SAVE	= "D:\\img"; // /Users/DQC/Pictures/images D:\\img
	//文件访问时使用的目录信息
	public static String WEB_STATIC_ACCESS	= "http://192.168.0.110:8080/img";
	//服务器存储 --用户-- 图片的路径信息
	public static String USER_IMAGE_PATH	= "user";
	//服务器存储 --商户-- 图片的路径信息
	public static String SELLERL_IMAGE_PATH	= "seller";
	//服务器存储 --商户-- 商户添加菜品临时文件夹
	public static String IMAGE_TEMPORARY_PATH = "temporary";
	//用户头像
	public static String USER_HEAD_PATH		= "head";
	//店铺logo
	public static String SELLER_LOGO_PATH	= "logo";
	//店铺菜品图片
	public static String SELLER_FOOD_PATH	= "food";
	//店铺新闻图片
	public static String SELLER_NEWS_PATH	= "user";
	

	
	//APP响应类型 0、系统错误 1、成功 2、传参错误，或者调用错误 3、第三方错误导致无法返回错误  4、查询重复结果或查询无结果 5、查询信息错误(密码错误)
	public static String APP_RESPOND_SYS_ERROR = "0";
	public static String APP_RESPOND_SUCCESS = "1";
	public static String APP_RESPOND_CALL_ERROR = "2";
	public static String APP_RESPOND_THIRD_ERROR = "3";
	public static String APP_QUERY_REPETITION = "4";
	public static String APP_QUERY_ERROR = "5";
	
	// 系统默认 一级 消费额度折扣信息 0.95
	public static BigDecimal DEF_DISCOUNT_LEVEL_ONE = new BigDecimal(0.95);
	// 系统默认 二级 消费额度折扣信息 0.90
	public static BigDecimal DEF_DISCOUNT_LEVEL_TWO = new BigDecimal(0.9);
	// 系统默认 三级 消费额度折扣信息 0.85
	public static BigDecimal DEF_DISCOUNT_LEVEL_THREE = new BigDecimal(0.85);
	
	// 平台预设：享受折扣的，第一档消费金额
	public static BigDecimal DEF_TOTAL_LEVEL_ONE = new BigDecimal(10);
	// 平台预设：享受折扣的，第二档消费金额
	public static BigDecimal DEF_TOTAL_LEVEL_TWO = new BigDecimal(100);
	// 平台预设：享受折扣的，第三档消费金额
	public static BigDecimal DEF_TOTAL_LEVEL_THREE = new BigDecimal(200);
	
	
	//客户端店铺列表，默认每页显示的店铺数量
	public static Integer APP_SHOPLIST_NUM	= 10;
	//客户端店铺菜品列表，默认每页显示的菜品数量
	public static Integer APP_DISHESLIST_NUM = 10;
	//客户端店铺令轮列表，默认每页显示的评论数量
	public static Integer APP_SHOPCOMMENT_NUM = 10;
	//客户端查询订单列表，默认每页显示订单数量
	public static Integer APP_ORDER_PAGE_COUNT = 10;
	
	// 商户后台订单每页默认显示条数
	public static Integer SELLER_ORDERLIST_NUM	= 10;
	
	//订单初始化状态标识 0、user	已下单
	public static Integer APP_ORDER_STATUS_INIT 	= 0;
	//订单已下单状态标识 1、seller	已接单
	public static Integer APP_ORDER_STATUS_ACCEPT	= 1;
	//订单已完成状态标识 2、seller	已拒绝
	public static Integer APP_ORDER_STATUS_DECLINE	= 2;
	//订单已完成状态标识 3、user	已到店
	public static Integer APP_ORDER_STATUS_ARRIVE	= 3;
	//订单已完成状态标识 4、seller	已上菜
	public static Integer APP_ORDER_STATUS_SERVING	= 4;
	//订单已完成状态标识 5、order	已完成
	public static Integer APP_ORDER_STATUS_FINISH	= 5;
	
	//订单未删除标识 0
	public static boolean APP_ORDER_DELETE_NO = false;
	//订单已删除标识 1
	public static boolean APP_ORDER_DELETE_YES = true;
	
	
	
	
	public static String MSG_01 = "系统错误，请稍后再试";
	public static String MSG_02 = "您的验证码已经发出，请接收";
	public static String MSG_03 = "用户名或密码错误";
	public static String MSG_04 = "格式不正确";
	public static String MSG_05 = "系统繁忙，请稍后再试";
	public static String MSG_06 = "系统维护，请稍后再试";
	public static String MSG_07 = "欢迎来到饭世界";
	public static String MSG_08 = "您的账号已经注册";
	public static String MSG_09 = "账号密码不正确";
	public static String MSG_10 = "查询成功";
	public static String MSG_11 = "删除成功";
	public static String MSG_12 = "订单生成成功";
	public static String MSG_13 = "用户资料修改成功";
	public static String MSG_14 = "头像上传成功";
	public static String MSG_15 = "用户存在";
	public static String MSG_16 = "用户不存在";
	public static String MSG_17 = "密码修改成功";
	public static String MSG_18 = "订单状态修改成功";
	public static String MSG_19 = "不存在未完成订单";
	public static String MSG_20 = "已存在未完成订单";
	
	
	
	
	
	
	public static String ADMIN_SUCCESS_NUMBER = "1";
	public static String ADMIN_ERROR_NUMBER = "2";
	public static String ADMIN_SYSERROR_NUMBER = "3";
	public static String ADMIN_MSG_03 = "登录成功";
	public static String ADMIN_MSG_04 = "账户格式不正确";
	public static String ADMIN_MSG_01 = "账号或密码错误";
	
	public static String ADMIN_MSG_02 = "系统繁忙，请稍后再试！";
	public static String ADMIN_MSG_05 = "添加成功";
	public static String ADMIN_MSG_06 = "添加失败，请稍后再试！";
	public static String ADMIN_MSG_07 = "请申请成为商户成功之后再使用本系统！";
	public static String ADMIN_MSG_08 = "信息加载错误，请稍后重试！";
	public static String ADMIN_MSG_09 = "上传成功！";
	public static String ADMIN_MSG_10 = "上传失败！";
	public static String ADMIN_MSG_11 = "修改成功！";
	public static String ADMIN_MSG_12 = "上架成功！";
	public static String ADMIN_MSG_14 = "下架成功！";
	public static String ADMIN_MSG_13 = "上架失败，请稍后再试！";
	public static String ADMIN_MSG_15 = "删除成功！";
	public static String ADMIN_MSG_16 = "删除失败，请稍后再试！";
	public static String ADMIN_MSG_17 = "添加成功！";
	public static String ADMIN_MSG_18 = "添加失败,请稍后再试！";
	public static String ADMIN_MSG_19 = "请申请商家合作之后在进行此操作！";
	public static String ADMIN_MSG_20 = "手机号码不正确";
	// 店铺状态：敬请期待
	public static Integer SELLER_STATUS_WAITING = 0;
	// 店铺状态：营业中
	public static Integer SELLER_STATUS_OPENING = 1;
	// 店铺状态：休息中
	public static Integer SELLER_STATUS_RESTING = 2;
	
}
