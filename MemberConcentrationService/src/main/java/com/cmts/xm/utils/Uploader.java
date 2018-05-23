package com.cmts.xm.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadBase.InvalidContentTypeException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;

/**
 * UEditor文件上传辅助类
 *
 */
public class Uploader {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(Uploader.class);
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	//本平台自定义地址
	private String uurl = "";
	// 输出文件地址
	private String url = "";
	// 上传文件名
	private String fileName = "";
	// 状态
	private String state = "";
	// 文件类型
	private String type = "";
	// 原始文件名
	private String originalName = "";
	// 文件大小
	private String size = "";

	private HttpServletRequest request = null;
	private String title = "";

	// 保存路径
	private String savePath = "upload";
	// 文件允许格式
	private String[] allowFiles = { ".rar", ".doc", ".docx", ".zip", ".pdf",".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
	// 文件大小限制，单位KB
	private int maxSize = 10000;
	/**
	 * 服务器网络地址
	 */
	private static String WANURL = "";
	
	private HashMap<String, String> errorInfo = new HashMap<String, String>();

	public Uploader(HttpServletRequest request) {
		this.request = request;
		HashMap<String, String> tmp = this.errorInfo;
		tmp.put("SUCCESS", "SUCCESS"); //默认成功
		tmp.put("NOFILE", "未包含文件上传域");
		tmp.put("TYPE", "不允许的文件格式");
		tmp.put("SIZE", "文件大小超出限制");
		tmp.put("ENTYPE", "请求类型ENTYPE错误");
		tmp.put("REQUEST", "上传请求异常");
		tmp.put("IO", "IO异常");
		tmp.put("DIR", "目录创建失败");
		tmp.put("UNKNOWN", "未知错误");
		StringBuffer requestUrl = request.getRequestURL();  
		WANURL = requestUrl.delete(requestUrl.length() - request.getRequestURI().length(), 
				requestUrl.length()).toString()+request.getContextPath()+"/upload"; 
	}

	public void upload() throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(this.request);
		if (!isMultipart) {
			this.state = this.errorInfo.get("NOFILE");
			return;
		}	
		String tgName = this.getName();		
		DiskFileItemFactory dff = new DiskFileItemFactory();	
		String path  = this.savePath.substring(0,this.savePath.length()-1);
		String newfileName = CreatUuid.create(15);
		String savePath = request.getSession().getServletContext().getRealPath("/upload")+this.getFolder(path,"/"+newfileName);
		dff.setRepository(new File(savePath));
		try {
			ServletFileUpload sfu = new ServletFileUpload(dff);
			sfu.setSizeMax(this.maxSize * 1024);
			sfu.setHeaderEncoding("utf-8");
			FileItemIterator fii = sfu.getItemIterator(this.request);
			while (fii.hasNext()) {
				FileItemStream fis = fii.next();
				if (!fis.isFormField()) {
					this.originalName = fis.getName().substring(fis.getName().lastIndexOf(System.getProperty("file.separator")) + 1);
					if (!this.checkFileType(this.originalName)) {
						this.state = this.errorInfo.get("TYPE");
						continue;
					}
//					//新文件名   
					this.fileName = tgName + getFileExt(this.originalName);
					//logger.info("新的fileName："+fileName);
					this.url = savePath + this.fileName;
					//logger.info("存储url："+this.url);
					this.uurl = WANURL + this.getFolder(path,"/"+newfileName) + fileName;
					//logger.info("新的uurl："+uurl);
					BufferedInputStream in = new BufferedInputStream(fis.openStream());
					FileOutputStream out = new FileOutputStream(new File(this.url));
					BufferedOutputStream output = new BufferedOutputStream(out);
					Streams.copy(in, output, true);
					this.state=this.errorInfo.get("SUCCESS");
					//UE中只会处理单张上传，完成后即退出
					//break;
				} else {
					
					String fname = fis.getFieldName();
					System.out.println(fname);
					//只处理title，其余表单请自行处理
					if(!fname.equals("pictitle")){
						continue;
					}
                    BufferedInputStream in = new BufferedInputStream(fis.openStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer result = new StringBuffer();  
                    while (reader.ready()) {  
                        result.append((char)reader.read());  
                    }
                    this.title = new String(result.toString().getBytes(),"utf-8");
                    reader.close();  
                    
				}
			}
		} catch (SizeLimitExceededException e) {
			this.state = this.errorInfo.get("SIZE");
		} catch (InvalidContentTypeException e) {
			this.state = this.errorInfo.get("ENTYPE");
		} catch (FileUploadException e) {
			this.state = this.errorInfo.get("REQUEST");
		} catch (Exception e) {
			this.state = this.errorInfo.get("UNKNOWN");
		}
	}
	
	/**
	 * 文件类型判断
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean checkFileType(String fileName) {
		Iterator<String> type = Arrays.asList(this.allowFiles).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @return string
	 */
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 依据原始文件名生成新文件名
	 * @return
	 */
	private String getName() {
		Random random = new Random();
		return this.fileName = "" + create(10) + "_"
		+ "NEWS";
	}

	/**
	 * 根据字符串创建本地目录 并按照日期建立子目录返回
	 * @param path 
	 * @return 
	 */
	private String getFolder(String path,String name2) {
		//logger.info("getFolder_path:"+path);
		//logger.info("getFolder_name2:"+name2);
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		path += "/" + formater.format(new Date());
		//path += formater.format(new Date());
		//logger.info("ddddddddddddd:"+this.getPhysicalPath(path + name2));
		//logger.info("ddddddddddddd3:"+Messages.getString("imgroot")+path + name2);
		//news/source/2013/04/07/16/e/m/
		//File dir = new File(this.getPhysicalPath(path + name2));
		String realPath = request.getSession().getServletContext().getRealPath("/upload");
		File dir = new File(realPath+path + name2);
		if (!dir.exists()) {
			try {
				//logger.info("getFolder_name2:succesc");
				dir.mkdirs();
			} catch (Exception e) {
				//logger.info("getFolder_name2:not");
				this.state = this.errorInfo.get("DIR");
				return "";
			}
		}
		return path + name2;
	}

	/**
	 * 根据传入的虚拟路径获取物理路径
	 * 
	 * @param path
	 * @return
	 */
	private String getPhysicalPath(String path) {
		String servletPath = this.request.getServletPath();
		String realPath = this.request.getSession().getServletContext()
				.getRealPath(servletPath);
		return new File(realPath).getParent() +"/" +path;
	}
	
	/**
	 * 随机生成有数据有字母的串
	 * 
	 * @param scFor
	 *            生成位数
	 * @return
	 */
	public static String create(int scFor) {
		String[] a2 = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
				"b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
				"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

		SimpleDateFormat simFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = simFormat.format(new Date());

		Random r = new Random();
		String result = "";
		int i = 0;
		while (result.length() < scFor) {
			int temp = r.nextInt(a2.length);
			result = result + a2[temp];
			if (result.length() % 2 == 0) {
				if (i <= dateStr.length()) {
					result = result + dateStr.split("")[i];
				}
			}
			i++;
		}
		return result;
	}

	/**
	 * 根据文件名生成两层路径 例:文件名为 abcd.jpg 则反回路径为 /a/b/
	 * 
	 * @param fileName
	 * @return
	 */
	public static String nameFolder(String fileName) {

		return "/" + fileName.substring(0, 1) + "/" + fileName.substring(1, 2)
				+ "/";

	}
	
	

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public void setAllowFiles(String[] allowFiles) {
		this.allowFiles = allowFiles;
	}

	public void setMaxSize(int size) {
		this.maxSize = size;
	}

	public String getSize() {
		return this.size;
	}

	public String getUrl() {
		return this.url;
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getState() {
		return this.state;
	}
	
	public String getTitle() {
		return this.title;
	}

	public String getType() {
		return this.type;
	}

	public String getOriginalName() {
		return this.originalName;
	}

	public String getUurl() {
		return uurl;
	}

	public void setUurl(String uurl) {
		this.uurl = uurl;
	}
}
