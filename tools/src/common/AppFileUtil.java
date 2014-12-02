package common;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.sun.imageio.plugins.common.ImageUtil;

/**
 * App：图片上传的共通方法
 * 
 * @author ZhangGD
 *
 */
public class AppFileUtil {
	
	/**
	 * 图片上传方法
	 * 
	 * @param session
	 * @param file
	 * @param filePath 文件保存路径
	 * @param fileName 文件名称
	 * @param target 源后缀
	 * @param dest 目标后缀
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @return
	 * @throws Exception
	 */
	public static String storeFile(
			MultipartFile file , String filePath , String fileName, String target, String dest,int width,int height) 
					throws Exception {
        FileOutputStream fos = null;
        try {
        	InputStream fis = file.getInputStream();
        	// 转换文件为png格式，并保存在同名目录下
        	File dir = new File(filePath);
        	// 判断文件夹是否存在,如果不存在则创建文件夹
        	if (!dir.exists() && !dir.isDirectory()){
        		dir.mkdirs();
        	}
        	fos = new FileOutputStream(filePath + fileName);
        	convertFormat(fis, fos, target, dest, width, height);
        	fos.flush();
        	fos.close();
        } catch (Exception ex) {
            System.out.println("文件取出失败，错误信息: " + ex.getMessage());
            if (fos != null)
                fos.close();
            throw ex;
        }
        return filePath;
    }
     
 
    /**
     * 图片转换
     * 
     * @param infile 输入文件
     * @param outfile 输出文件
     * @param srcFormat 源格式
     * @param destFormat 输出格式
	 * @param width 图片宽度
	 * @param height 图片高度
     * @return
     * @throws Exception
     */
    public static boolean convertFormat(InputStream infile,
            OutputStream outfile, String srcFormat, String destFormat, int width ,int height) throws Exception {
        boolean flag = false;
        BufferedImage src = ImageIO.read(infile);
        if(height > 0  && width > 0) {// compress the origin image if width and height are non-zero
            height = src.getHeight() > height ? height: src.getHeight();
            width = src.getWidth() > width ? width : src.getWidth();
            Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);//这个是用来进行图片大小调整的
     
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
     
            Graphics g = tag.getGraphics();
            //可在下面对图片进行绘制和更改
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
     
            g.dispose();
            tag.flush();
            flag = ImageIO.write(tag, destFormat, outfile);// 输出到经过缩放的文件流
        } else {
            flag = ImageIO.write(src, destFormat, outfile);//输出原分辨率的图片
        }
        Logger.getLogger(ImageUtil.class).info("图片转换成功: 从[" + srcFormat + "]到[" + destFormat + "]");
        return flag;
    }

}
