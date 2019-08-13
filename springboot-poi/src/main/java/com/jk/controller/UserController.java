package com.jk.controller;

import com.jk.model.User;
import com.jk.service.UserService;
import com.jk.util.ExportExcel;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("query")
    @ResponseBody
    public Map query(Integer page, Integer rows){

       Map map = new HashMap<>();
       map.put("start",(page-1)*rows);
       map.put("end",rows);

       Integer count = userService.queryCount(map);
       List<User> list = userService.query(map);
       Map map2 = new HashMap();
       map2.put("rows",list);
       map2.put("total",count);

       return map2;
    }


    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletResponse response){
        //导出的Excel的标题
        String title = "用户管理";
        //导出的Excel的列名
        String[] rowName = {"id","类型id","年龄","用户名","密码","时间"};
        //导出的Excel 数据
        List<Object[]> dataList = new ArrayList<Object[]>();

        //查询的数据库用户的信息
        List<User> list = userService.query2();
        //循环用户信息
        for (User user:list) {
            Object[] obj = new Object[rowName.length];
            obj[0] = user.getUserid();

            obj[2] = user.getUserage();
            obj[3] = user.getUsername();
            obj[4] = user.getUserpassword();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(user.getUsertime());
            obj[5] = format;
            if(user.getTypeid() == 1){
                obj[1] = "超级用户";
            }else if(user.getTypeid() == 2){
                obj[1] = "普通用户";
            }else{
                obj[1] = "其他用户";
            }
            dataList.add(obj);
        }
       // ExportExcel exportExcel =new ExportExcel(title,rowName,dataList,response);
        ExportExcel exportExcel =new ExportExcel(title,rowName,dataList,response);
        try {
            exportExcel.export();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("exportExcel2")
    public void exportExcel2(HttpServletResponse response,String aa){
        System.err.println(aa);
        //导出的Excel的标题
        String title = "用户管理";
        //导出的Excel的列名
      /*  String[] rowName = {"id","类型id","年龄","用户名","密码","时间"};*/
        String[] rowName = aa.split(",");
        //导出的Excel 数据
        List<Object[]> dataList = new ArrayList<Object[]>();

        //查询的数据库用户的信息
        List<User> list = userService.query3();
        //循环用户信息
        int a = 1;
        for (User user:list) {
            Object[] obj = new Object[rowName.length];

            if(aa.equals("userid")){
                obj[0] = user.getUserid();
            }

            if(aa.equals("typeid")){

            if(user.getTypeid() == 1){
                obj[a] = "超级用户";
                a++;
            }else if(user.getTypeid() == 2){
                obj[a] = "普通用户";
                a++;
            }else{
                obj[a] = "其他用户";
                a++;
            }
            }
            if(aa.equals("userage")){
                obj[a] = user.getUserage();
                a++;
            }
            if(aa.equals("username")){
                obj[a] = user.getUsername();
                a++;
            }
           if(aa.equals("userpassword")){
               obj[a] = user.getUserpassword();
               a++;
           }
            if(aa.equals("usertime")){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String format = sdf.format(user.getUsertime());
                obj[a] = format;
                a++;
            }
            dataList.add(obj);
        }
        // ExportExcel exportExcel =new ExportExcel(title,rowName,dataList,response);
        ExportExcel exportExcel =new ExportExcel(title,rowName,dataList,response);
        try {
            exportExcel.export();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @RequestMapping("importExcel")
    public String importExcel(MultipartFile file,HttpServletResponse response) throws IOException {
  /*      System.err.println(123);
        String contentType = file.getContentType();

        String fileName = file.getOriginalFilename();

        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        String filePath = "./src/main/resources/templates/fileupload/";*/

        //获得上传文件上传的类型
        String contentType = file.getContentType();
        //上传文件的名称
        String fileName = file.getOriginalFilename();
        //获得文件的后缀名
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件的新的路径
        //生成新的文件名称
        String filePath = "./src/main/resources/templates/fileupload/";
        //创建list集合接收excel中读取的数据

        List<User> list = new ArrayList<User>();

        try {
            uploadFile(file.getBytes(), filePath, fileName);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            FileInputStream fileInputStream = new FileInputStream(filePath + fileName);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            //通过workbook对象获得sheet页 有可能不止一个sheet
            for (int i = 0;i<workbook.getNumberOfSheets();i++){
                //获得里面的每一个sheet对象
                Sheet sheetAt = workbook.getSheetAt(i);
                for (int j = 3;j<sheetAt.getPhysicalNumberOfRows();j++){
                    //创建一个user对象接收excel的数据
                    User user = new User();
                    //获得每一行的数据
                    Row row = sheetAt.getRow(j);

                    //获得每一个单元格的数据


                    if(row.getCell(1)!=null && !"".equals(row.getCell(1))){
                        String cellValue = this.getCellValue(row.getCell(1));
                        if("超级用户".equals(cellValue)){
                            user.setTypeid(1);
                        }else if("普通用户".equals(cellValue)){
                            user.setTypeid(2);
                        }else{
                            user.setTypeid(3);
                        }

                    }
                  if(row.getCell(2)!=null && !"".equals(row.getCell(2))){
                        user.setUserage(Integer.parseInt(this.getCellValue(row.getCell(2))));
                    }
                    if(row.getCell(3)!=null && !"".equals(row.getCell(3))){
                        user.setUsername(this.getCellValue(row.getCell(3)));
                    }
                    if(row.getCell(4)!=null && !"".equals(row.getCell(4))){
                        user.setUserpassword(this.getCellValue(row.getCell(4)));
                    }
                    if(row.getCell(5)!=null && !"".equals(row.getCell(5))){
                        user.setUsertime(sdf.parse(this.getCellValue(row.getCell(5))));
                    }

                    list.add(user);
                }

            }

            userService.addUser(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }

    //上传文件的方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(Cell cell){
        String value = null;
        //简单的查检列类型
        switch(cell.getCellType())
        {
            case Cell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC://数字
                long dd = (long)cell.getNumericCellValue();
                value = dd+"";
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }
    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(XSSFCell cell){
        String value = null;
        //简单的查检列类型
        switch(cell.getCellType())
        {
            case HSSFCell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC://数字
                long dd = (long)cell.getNumericCellValue();
                value = dd+"";
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }

}



