package com.jk.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;



import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("basePackage")
@Namespace("/")
public class BaseAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(BaseAction.class);
	protected int page = 1;// 当前页
	protected int rows = 10;// 每页显示记录数
	protected String sort;// 排序字段
	protected String order = "asc";// asc/desc
	protected String q;// easyui的combo和其子类过滤时使用
	protected String ids;

	/**
	 * <pre>getRequest(获得request请求)   
	 * 创建人：杨志超 yangzhichao150@126.com    
	 * 创建时间：2016年2月24日 下午7:15:10    
	 * 修改人：杨志超 yangzhichao150@126.com     
	 * 修改时间：2016年2月24日 下午7:15:10    
	 * 修改备注： 
	 * @return</pre>
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	/**
	 * <pre>getResponse(获得response请求)   
	 * 创建人：杨志超 yangzhichao150@126.com    
	 * 创建时间：2016年2月24日 下午7:16:10    
	 * 修改人：杨志超 yangzhichao150@126.com     
	 * 修改时间：2016年2月24日 下午7:16:10    
	 * 修改备注： 
	 * @return</pre>
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	public void writeJson(Object object){
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected void outString(String json) {
		PrintWriter writer = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			writer = response.getWriter();
			writer.write(json);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != writer) {
				writer.close();
				writer = null;
			}
		}
	}
	
	protected void writeJson2(Object obj){
		JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"briday","hibernateLazyInitializer","updatetime","hibernateLazyInitializer","createtime","hibernateLazyInitializer"});      
        String jsonStr = JSONSerializer.toJSON(obj, jsonConfig).toString();
        System.out.println(jsonStr);
        ajaxOut(jsonStr);
	}
	
	protected void ajaxOut(String jsonStr){
		HttpServletResponse resp = getResponse();
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		try {
			resp.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
