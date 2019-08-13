/** 
 * <pre>项目名称:book 
 * 文件名称:EasyuiPage.java 
 * 包名:com.jk.gyh.utils 
 * 创建日期:2019年2月15日上午11:51:02 
 * Copyright (c) 2019, All Rights Reserved.</pre> 
 */  
package com.jk.util;

/** 
 * <pre>项目名称：book    
 * 类名称：EasyuiPage    
 * 类描述：    
 * 创建人：郭育辉  
 * 创建时间：2019年2月15日 上午11:51:02    
 * 修改人：郭育辉     
 * 修改时间：2019年2月15日 上午11:51:02    
 * 修改备注：       
 * @version </pre>    
 */
public class EasyuiPage {

	private Integer total;
	private Object rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "EasyuiPage [total=" + total + ", rows=" + rows + "]";
	}
	
}
