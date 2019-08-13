package com.jk.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class FileUtil {
	/*
	 * 
	 * 前台
	 * 1. form 表单中 需要 文件域    需要name值
	 * 2. 提交方式 必须是  post
	 * 3. 添加属性  enctype="multipart/form-data"
	 * 
	 * 后台
	 * 1. 接收文件实体  需要 File类型的变量   变量名  就是 表单中 file文件域的 name值
	 * 2. 接收文件名称   需要 String类型的变量  变量名  表单中 file文件域的 name值+FileName
	 * 3. 关于 文件名唯一性的 控制   1.UUID 2.时间戳 
	 * 
	 * 数据库
	 *  存放 上传文件后的 地址
	 * 
	 * 注意： struts2  上传文件 默认大小是 2M 
	 * 
	 * 
	 * */
	
	public static String upLoadFile(File img,String newFileName, String dir){
			String imgUpPath = null;
				BufferedInputStream in  = null;
				BufferedOutputStream out = null;
				try {
					// 字节缓冲输入流
					in = new BufferedInputStream(new FileInputStream(img));
					// 字节缓冲输出流
					// 1. 获取 项目的真实路径
					String path = ServletActionContext.getServletContext().getRealPath(dir);
					// 2. 判断 文件目录 是否存在  不存在就新建
					File newDir = new File(path);
					if(!newDir.exists()){ // 判断 目录是否存在
						newDir.mkdir();// 创建目录
					}
					// 3. 文件路径
					// 文件 名 相同的 冲突 解决   1. UUID  2. 时间戳
					String filePath = path+"/"+newFileName;
					
					out = new BufferedOutputStream(new FileOutputStream(filePath));
				
					int c = 0;
					byte[] b = new byte[1024];
					while( (c = in.read(b)) != (-1)){
						out.write(b);
					}
					
					imgUpPath =dir+"/"+newFileName;
					System.out.println(imgUpPath);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if(in!=null){
							in.close();
						}
						if(out!=null){
							out.close();
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
		return imgUpPath;
	}
 	
	
	
	// 下载文件
		public static void downFile(String imgPath){
			// 1. 获得文件的真实路径
			String newPath = ServletActionContext.getServletContext().getRealPath(imgPath);
			// 2. 实例化 文件对象  
			File f = new File(newPath);
			// 3. 获得文件名
			String fileName = f.getName();
			// 4. 定义输入输出流
			BufferedInputStream buffIn = null;
			BufferedOutputStream buffOut = null;
			try {
				// 5. 获得输入流
				buffIn = new BufferedInputStream(new FileInputStream(f));
				// 6. 获得 response  和  request (获得 request 和 response )
				HttpServletResponse response  = ServletActionContext.getResponse();
				HttpServletRequest request = ServletActionContext.getRequest();
				// 7. 获得输入流       response.getOutputStream() 是servlet的输出流  浏览器的输出流
				buffOut = new BufferedOutputStream(response.getOutputStream());
				
				// 8. 对浏览器进行设置========================================================
				//解决浏览器兼容问题
		        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
		        	fileName = new String(fileName.getBytes("GB2312"),"ISO-8859-1");
		        } else {
		        	// 对文件名进行编码处理中文问题
		        	fileName = java.net.URLEncoder.encode(fileName, "UTF-8");// 处理中文文件名的问题
		        	fileName = new String(fileName.getBytes("UTF-8"), "GBK");// 处理中文文件名的问题
		        }
		        response.reset();
		        response.setContentType("application/x-msdownload");// 不同类型的文件对应不同的MIME类型
			     // inline在浏览器中直接显示，不提示用户下载
			        // attachment弹出对话框，提示用户进行下载保存本地
			        // 默认为inline方式
		        response.setHeader("Content-Disposition", "attachment;filename="+fileName);
				//==========================================================================
				// 9. 循环 读写 赋值
				byte[] b = new byte[1024];
				int s = 0;
				while((s=buffIn.read(b))!=(-1)){
					buffOut.write(b);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try {
					if(buffIn!=null){
						buffIn.close();
					}
					if(buffOut!=null){
						buffOut.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		} 
}






//System.out.println(img);
//System.out.println(imgFileName);

/*页面上
 * 1. 表单 添加 enctype="multipart/form-data" 属性
 * 2. 必须使用  post 提交
 * 接收变量上
 * 1. 需要 File类型 变量   private File img; 接收文件实体
 * 2. 需要String 类型变量    private String imgFileName; 接收文件名
 * IO流中
 * 1. // 1. 获取 项目的真实路径
	String path = ServletActionContext.getServletContext().getRealPath("目录名称");
   2. 判断 目录是否存在  不存在新建
   3. 解决 文件名 重复     时间戳  UUID
 * */
