package common;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author spd 返回上传附件的本地路径
 */
public class FilePath {
	public static final String MATERIAL_PATH = "materials";
	public static final String COMPONENT_PATH = "components";
	public static final String TEMP_WEBSITE_PATH = "tempwebsites";
	public static final String WEBSITE_PATH = "webSites";
	public static final String TEMPLATE_PIC_PATH = "templatepic";
	public static final String TEMPLATE = "templates";
	public static final String CLASSIFY = "classify";
	public static String getFilepath() throws Exception {
		InputStream in = null;
		//TODO 换方式
		String flieName = "/jdbc.properties";
		String name = null;
		File file = null;
		try {
			in = FilePath.class.getResource(flieName).openStream();
			Properties pro = new Properties();
			pro.load(in);
			name = pro.getProperty("filepath");
			file = new File(name);
			if (!file.isDirectory()) {
				//TODO　删除
				// System.out.println("目录存在");
				file.mkdir();
			}

		} finally {
			in.close();
		}
		return name == null ? "" : name;
	}

	/**
	 * 文件下载
	 * @param fileName文件路径文件名称
	 * @param showName显示名
	 * @param by缓存区大小
	 * */
	public static void download(HttpServletResponse response, String fileName, String showName, Integer by) throws Exception {
		InputStream in = null;
		File file = null;
		OutputStream out = null;
		try {
			file = new File(fileName);
			// 以流的形式下载文件。
			in = new BufferedInputStream(new FileInputStream(file));
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(showName.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("application/octet-stream");
			response.setCharacterEncoding("UTF-8");
			out = new BufferedOutputStream(response.getOutputStream());
			byte[] b = null;
			if (by == null) {
				b = new byte[8192];
			} else if (by <= 1024) {
				b = new byte[1024];
			} else if (by >= 8192) {
				b = new byte[8192];
			}else{
				b = new byte[4096];
			}
			int len;
			while ((len = in.read(b)) > 0) 
				out.write(b, 0, len);				
		}  finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {		
				out.close();
			}
		}

	}

	/**
	 * 上传1份文件
	 * 
	 * @param request
	 * @param 文件标签subname
	 * @return 返回新文件名时间搓
	 * @throws Exception
	 */
	public static String fileUpload(HttpServletRequest request, String subName) throws Exception {
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = mRequest.getFile(subName);
		String filename = null;
		if (!file.isEmpty()) {
			String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
			filename = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			File outFile = new File(FilePath.getFilepath(), filename + ext);
			FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
			///FileUtils.copyFile(input, output)
			filename = filename + ext;
		} else {

		}
		return filename;
	}

	/**
	 * 上传1份文件
	 * 
	 * @param request
	 * @param 文件标签subname
	 * @return 返回新文件名时间搓 path 指定路径 默认upload 文件夹
	 * @throws Exception
	 */
	public static String fileUploadPath(HttpServletRequest request, String subName, String path) throws Exception {
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = mRequest.getFile(subName);
		String filename = null;
		if (!file.isEmpty()) {
			String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
			filename = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			File outFile = null;
			if (path != null && !"".equals(path)) {
				outFile = new File(path, filename + ext);
			} else {
				outFile = new File(FilePath.getFilepath(), filename + ext);
			}

			FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
			filename = filename + ext;
		} else {
			filename = "";
		}
		return filename;
	}
	
	/**
	 * @param request
	 * @param subName上传标签name
	 * @return map 安页面顺序上传文件返回 文件名称
	 * @throws Exception
	 */
	public static Map<Integer, String> fileUploads(HttpServletRequest request, String subName) throws Exception {
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = mRequest.getFiles(subName);
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (int i = 0; i < fileList.size(); i++) {
			String filename = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			if (!fileList.get(i).isEmpty()) {
				String ext = fileList.get(i).getOriginalFilename().substring(fileList.get(i).getOriginalFilename().lastIndexOf(".")).toLowerCase();
				File outFile = new File(FilePath.getFilepath(), filename + ext);
				map.put(i, filename + ext);
				FileUtils.copyInputStreamToFile(fileList.get(i).getInputStream(), outFile);
			} else {
				map.put(i, "");
			}
		}
		return map;
	}

	/**
	 * @param request
	 * @param subName上传标签name
	 * @param path指定上传路径
	 * @return map安页面顺序上传文件返回 文件名称
	 * @throws Exception
	 */
	public static Map<Integer, String> fileUploadPaths(HttpServletRequest request, String subName, String path) throws Exception {
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = mRequest.getFiles(subName);
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (int i = 0; i < fileList.size(); i++) {
			String filename = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date())+i;
			if (!fileList.get(i).isEmpty()) {
				String ext = fileList.get(i).getOriginalFilename().substring(fileList.get(i).getOriginalFilename().lastIndexOf(".")).toLowerCase();
				File outFile = null;
				map.put(i, filename + ext);
				if (path != null && !"".equals(path)) {
					outFile = new File(path, filename + ext);
				} else {
					outFile = new File(FilePath.getFilepath(), filename + ext);
				}
				FileUtils.copyInputStreamToFile(fileList.get(i).getInputStream(), outFile);
			} else {
				map.put(i, "");
			}
		}
		return map;
	}

	/** 
	 * @Method: fileUpload 
	 * @Description: 上传文件
	 * @param: request 页面请求
	 * @param: subName 页面file标签name
	 * @param: despath 上传文件的目标路径
	 * @param: name 上传文件的名称
	 * @return void
	 * @throws
	 */
	public static boolean fileUpload(HttpServletRequest request, String subName,String despath, String name) throws Exception {
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = mRequest.getFile(subName);
		try {
			if (!file.isEmpty()) {
				File outFile = new File(despath, name);
				FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/** 
	 * @Method: createDir 
	 * @Description: 按照路径创建文件夹
	 * @param destDirName:文件夹路径
	 * @return
	 * @throws 
	 */
	public static void createDir(String destDirName) throws IOException {
		File dir = new File(destDirName);
		if (!dir.exists()) {
			if (!destDirName.endsWith(File.separator)) {
				destDirName = destDirName + File.separator;
			}
			// 创建单个目录
			dir.mkdirs();
		}
	}
	
	/** 
	 * @Method: deleteFolder 
	 * @Description: 根据路径删除指定的目录或文件，无论存在与否
	 * @param destDirName:文件夹路径
	 */
	public static void deleteFolder(String path) throws IOException {
	    File file = new File(path);
	    // 判断目录或文件是否存在
		if (file.exists()) {
		    // 判断是否为文件
		    if (file.isFile()) {
		    	file.delete(); 
		    } else {
		    	FileUtils.deleteDirectory(file);
		    }
		}
	}
	
	/** 
	 * @Method: copydir
	 * @Description: 复制文件或者目录,复制前后文件完全一样
	 * @param resFilePath 源文件路径
     * @param distFolder  目标文件夹
	 * @throws IOException 当操作发生异常时抛出
	 */
    public static void copydir(String resFilePath, String distFolder) throws IOException {
       
        File resFile = new File(resFilePath);
        File distFile = new File(distFolder); 
        
        if (resFile.isDirectory()) {
                FileUtils.copyDirectoryToDirectory(resFile, distFile);
        } else if (resFile.isFile()) {
                FileUtils.copyFileToDirectory(resFile, distFile, true);
        }
    }
	
    /**
     * 
     * @method: copyDirectory
     * @Description: 复制文件或者目录，生成目录结构不包含当前文件夹名称与copydir方法生成目录不一样。
     * @param sourceDir 源文件
     * @param targetDir   目标文件
     * @throws IOException
     * @return void
     * @throws
     */
	public static void copyDirectory(String oldpath,String newpath)throws IOException{
		
		File newp = new File(newpath);
		newp.mkdirs(); //创建文件夹
		File oldp = new File(oldpath);
		String [] f = oldp.list();
		File tem = null;
		for (int i = 0; i < f.length; i++) {
			if(oldpath.endsWith(File.separator)){
				tem=new File(oldpath+f[i]);
			}else{
				tem=new File(oldpath+File.separator+f[i]);
			}
			
			if(tem.isFile()){
				FileInputStream fs = new FileInputStream(tem);
				FileOutputStream fo = new FileOutputStream(newpath+File.separator+(tem.getName().toString()));
				
				byte[] b = new byte[1024*5];
				int len;
				while((len=fs.read(b))!=-1){
					fo.write(b, 0, len);
				}
				fo.flush();
				fo.close();
				fs.close();
			}
			if(tem.isDirectory()){
				copyDirectory(oldpath+File.separator+f[i],newpath+File.separator+f[i]);
			}
		}
	}
    
	/**读取
	 * @param 文件名+文件路径
	 * @return组件内容
	 */
	public static String componentReadln(String path) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			return "";
		}
		BufferedReader in = null;
		StringBuilder str = new StringBuilder("");
		try {
			in=new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
			//in = new BufferedReader(new FileReader(file));
			String data = in.readLine();
			while (data != null) {
				str.append(data);
				str.append("\n");
				data = in.readLine();
			}

		} finally {
			if (in != null)
				in.close();
		}
		return str.toString();
	}
	
	/**写入
	 * @param 文件名+文件路径
	 * @count组件内容
	 */
	public static void componentReadout(String path, String count) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		BufferedWriter out = null;
		try {
			//out = new BufferedWriter(new FileWriter(file));
			
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));
			out.write(count);
		} finally {
			if(out!=null)
			out.close();
		}
	}
}
