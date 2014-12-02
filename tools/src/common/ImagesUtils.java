package common;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 工具类
 * 
 * 
 */
public abstract class ImagesUtils {
	private HttpSession session;
	private HttpServletRequest req;
	private HttpServletResponse res;

	public ImagesUtils() {
		req = ((ServletWebRequest) RequestContextHolder.getRequestAttributes())
				.getRequest();
		res = ((ServletWebRequest) RequestContextHolder.getRequestAttributes())
				.getResponse();
		session = req.getSession();
	}

	// 获得每个文件的扩展名
	public static String[] getExtention(String[] fileNames) {
		String[] extentions = new String[fileNames.length];
		for (int i = 0; i < extentions.length; i++) {
			int pos = fileNames[i].lastIndexOf(".");
			extentions[i] = fileNames[i].substring(pos);
		}
		return extentions;
	}

	public static String[] copy(File[] fl, File file) {
		if (!file.exists()) // 如果文件夹不存在
			file.mkdir(); // 建立新的文件夹
		String[] args = new String[fl.length];
		for (int i = 0; i < fl.length; i++) {
			if (fl[i].isFile()) { // 如果是文件类型就复制文件
				try {
					args[i] = fl[i].getName();
					FileInputStream fis = new FileInputStream(fl[i]);
					FileOutputStream out = new FileOutputStream(new File(
							file.getPath() + File.separator + fl[i].getName()));

					int count = fis.available();
					byte[] data = new byte[count];
					if ((fis.read(data)) != -1) {
						out.write(data); // 复制文件内容
					}
					out.close(); // 关闭输出流
					fis.close(); // 关闭输入流
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (fl[i].isDirectory()) { // 如果是文件夹类型
				File des = new File(file.getPath() + File.separator
						+ fl[i].getName());
				des.mkdir(); // 在目标文件夹中创建相同的文件夹
				copy(fl[i].listFiles(), des); // 递归调用方法本身
			}
		}
		return args;
	}

	public static boolean deletefile(String delpath) {
		try {
			File file = new File(delpath);
			if (!file.isDirectory()) {
				// System.out.println("1");
				file.delete();
			} else if (file.isDirectory()) {
				// System.out.println("2");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					OSProperties os = new OSProperties();
					File delfile = null;
					if (OSProperties.OSName.toLowerCase().contains("win")) {
						delfile = new File(delpath + "\\" + filelist[i]);
					} else {
						delpath.replace("\\", "/");
						delfile = new File(delpath + "/" + filelist[i]);
					}

					if (!delfile.isDirectory()) {
						// System.out.println("path=" + delfile.getPath());
						// System.out.println("absolutepath="+
						// delfile.getAbsolutePath());
						// System.out.println("name=" + delfile.getName());
						delfile.delete();
					} else if (delfile.isDirectory()) {
						if (OSProperties.OSName.toLowerCase().contains("win")) {
							deletefile(delpath + "\\" + filelist[i]);
						} else {
							deletefile(delpath + "/" + filelist[i]);
						}

					}
				}
				file.delete();
			}
		} catch (Exception e) {
		}
		return true;
	}

	public static String getlastIndexOf(String split) {
		int a = 0;
		String restult = "";
		try {
			new OSProperties();
			if (OSProperties.OSName.toLowerCase().contains("win")) {
				a = split.lastIndexOf("\\imagesTemp", split.length());
				restult = split.substring(a).replace("\\", "/");

			} else {
				a = split.lastIndexOf("/imagesTemp", split.length());
				restult = split.substring(a);
			}

		} catch (RuntimeException e) {
		}
		return restult;
	}

	public static void main(String[] args) {

		System.out.println("out ok");
	}

	/**
	 * 对图片进行放大
	 * 
	 * @param originalImage
	 *            原始图片
	 * @param times
	 *            放大倍数
	 * @return
	 */
	public static BufferedImage zoomInImage(BufferedImage originalImage,
			Integer times) {
		int width = originalImage.getWidth() * times;
		int height = originalImage.getHeight() * times;
		BufferedImage newImage = new BufferedImage(width, height,
				originalImage.getType());
		Graphics g = newImage.getGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return newImage;
	}

	/**
	 * 对图片进行放大
	 * 
	 * @param srcPath
	 *            原始图片路径(绝对路径)
	 * @param newPath
	 *            放大后图片路径（绝对路径）
	 * @param times
	 *            放大倍数
	 * @return 是否放大成功
	 */
	public static boolean zoomInImage(String srcPath, String newPath,
			Integer times) {
		BufferedImage bufferedImage = null;
		try {
			File of = new File(srcPath);
			if (of.canRead()) {
				bufferedImage = ImageIO.read(of);
			}
		} catch (IOException e) {
			// TODO: 打印日志
			return false;
		}
		if (bufferedImage != null) {
			bufferedImage = zoomInImage(bufferedImage, times);
			try {
				// TODO: 这个保存路径需要配置下子好一点
				ImageIO.write(bufferedImage, "JPG", new File(newPath)); // 保存修改后的图像,全部保存为JPG格式
			} catch (IOException e) {
				// TODO 打印错误信息
				return false;
			}
		}
		return true;
	}

	/**
	 * 对图片进行缩小
	 * 
	 * @param originalImage
	 *            原始图片
	 * @param times
	 *            缩小倍数
	 * @return 缩小后的Image
	 */
	public static BufferedImage zoomOutImage(BufferedImage originalImage,
			Integer times) {
		int width = originalImage.getWidth() / times;
		int height = originalImage.getHeight() / times;
		BufferedImage newImage = new BufferedImage(width, height,
				originalImage.getType());
		Graphics g = newImage.getGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return newImage;
	}

	/**
	 * 对图片进行缩小
	 * 
	 * @param srcPath
	 *            源图片路径（绝对路径）
	 * @param newPath
	 *            新图片路径（绝对路径）
	 * @param times
	 *            缩小倍数
	 * @return 保存是否成功
	 */
	public static boolean zoomOutImage(String srcPath, String newPath,
			Integer times) {
		BufferedImage bufferedImage = null;
		try {
			File of = new File(srcPath);
			if (of.canRead()) {
				bufferedImage = ImageIO.read(of);
			}
		} catch (IOException e) {
			// TODO: 打印日志
			return false;
		}
		if (bufferedImage != null) {
			bufferedImage = zoomOutImage(bufferedImage, times);
			try {
				// TODO: 这个保存路径需要配置下子好一点
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				ImageIO.write(bufferedImage, "JPG", bout); // 保存修改后的图像,全部保存为JPG格式
				byte[] b = bout.toByteArray();
				System.out.println(b);
				ByteArrayInputStream in = new ByteArrayInputStream(b); // 将b作为输入流
				BufferedImage image = ImageIO.read(in);
				ImageIO.write(image, "JPG", new File(newPath));
			} catch (IOException e) {
				// TODO 打印错误信息
				return false;
			}
		}
		return true;
	}

	public static boolean parseByteToImage(byte[] b, String url,
			String smallPhotoUrl) {
		ByteArrayInputStream in = new ByteArrayInputStream(b); // 将b作为输入流
		boolean flage = false;
		try {
			BufferedImage image = ImageIO.read(in);
			ImageIO.write(image, "JPG", new File(url));
			flage = zoomOutImage(url, smallPhotoUrl, 3);
		} catch (IOException e) {
			return flage;
		}
		return flage;
	}

	/**
	 * TODO 方法说明
	 * 
	 * @param b
	 *            图片的byte数组
	 * @param url
	 *            图片写入的位置
	 * @return 日 期:2014-3-4
	 */
	public static boolean parseByteToImageFile(byte[] b, String url) {
		ByteArrayInputStream in = new ByteArrayInputStream(b);
		boolean flag = true;
		try {
			BufferedImage image = ImageIO.read(in);
			ImageIO.write(image, "JPG", new File(url));
		} catch (IOException e) {
			flag = false;
			return flag;
		}
		return flag;
	}

	/**
	 * 复制文件
	 * @param oldPath	源文件路径+文件名
	 * @param newPath	新文件路径
	 * @return	源文件名
	 * @throws IOException
	 */
	public static String fileChannelCopy(String oldPath, String newPath)
			throws IOException {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		File s = new File(oldPath);
		File dir = new File(newPath);
		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdirs();
		}
		fi = new FileInputStream(s);
		StringBuffer newFile = new StringBuffer();
		newFile.append(newPath);
		newFile.append(File.separator);
		newFile.append(s.getName());
		fo = new FileOutputStream(newFile.toString());
		in = fi.getChannel();// 得到对应的文件通道
		out = fo.getChannel();// 得到对应的文件通道
		in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		fi.close();
		in.close();
		fo.close();
		out.close();
		return s.getName();
	}

}
