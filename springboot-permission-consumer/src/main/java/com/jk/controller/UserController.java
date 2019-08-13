package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.Permission;
import com.jk.model.Role;
import com.jk.model.User;
import com.jk.service.UserService;
import com.jk.util.CheckImgUtil;
import com.jk.util.ExportExcel;
import com.jk.util.TreeNoteUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Reference
    private UserService userService;

    @RequestMapping("getCode")
    @ResponseBody
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CheckImgUtil.checkImg(request, response);
    }



    @RequestMapping("login")
    @ResponseBody
    public String loginUser(String code, User user, HttpServletRequest request) {

        String str = "0";
        User user2 = userService.loginUser(user);

        String realCode = request.getSession().getAttribute("checkcode").toString();
        if(!realCode.toLowerCase().equals(code.toLowerCase())) {
            return "codeError";
        }

        if(user2==null){
            return "userError";
        }
        if(!user2.getPassword().equals(user.getPassword())){
            return "passError";
        }

       request.getSession().setAttribute("user",user2);

        str = "1";

        return str;

    }

    @RequestMapping("getTreeAll")
    @ResponseBody
    public List<Permission> getTreeAll(HttpServletRequest request){
        List<Permission> list = new ArrayList<>();
        User user = (User) request.getSession().getAttribute("user");
        //定义缓存key
        String key = "tree"+user.getId();
        //判断缓存是否存在
        if(redisTemplate.hasKey(key)){
            System.out.println("=====走缓存=======");
            list = (List<Permission>) redisTemplate.opsForValue().get(key);
        }else{
            System.out.println("====走数据库===");
            list = userService.getTreeAll(user.getId());
            //自己调自己
            list = TreeNoteUtil.getFatherNode(list);
            redisTemplate.opsForValue().set(key, list);
            //设置过期时间
            redisTemplate.expire(key, 1, TimeUnit.MINUTES);
        }
        return list;
    }

    @RequestMapping("queryUser")
    @ResponseBody
    public Map queryUser(Integer page, Integer rows){

        Map map = new HashMap<>();

        map.put("start",(page-1)*rows);
        map.put("end",rows);

        Integer count = userService.queryCount(map);
        List<User> list = userService.queryUser(map);

        Map map2 = new HashMap<>();

        map2.put("rows",list);
        map2.put("total",count);

        return map2;
    }

    @RequestMapping("getUserByUserid")
    @ResponseBody
    public List<Role> getUserByUserid(Integer userid){

        List<Role> list = userService.getUserByUserid(userid);

        return list;
    }

    @RequestMapping("updateUserRole")
    @ResponseBody
    public int updateUserRole(Integer[] roleids, Integer userid){

        int i = userService.updateUserRole(roleids,userid);

        return i;
    }

    @RequestMapping("queryRole")
    @ResponseBody
    public Map queryRole(Integer page, Integer rows){

        Map map = new HashMap<>();

        map.put("start",(page-1)*rows);
        map.put("end",rows);

        Integer count = userService.queryRoleCount(map);
        List<Role> list = userService.queryRole(map);

        Map map2 = new HashMap<>();

        map2.put("rows",list);
        map2.put("total",count);

        return map2;
    }

    @RequestMapping("getPerByid")
    @ResponseBody
    public List<Permission> getPerByid(Integer roleid){

        List<Permission> list = userService.getPerByid(roleid);

        list = TreeNoteUtil.getFatherNode(list);

        return list;
    }

    @RequestMapping("updateRolePremiss")
    @ResponseBody
    public int updateRolePremiss(Integer[] perids,Integer roleid){

        int i = userService.updateRolePremiss(perids,roleid);

        return i;
    }


    @RequestMapping("queryPer")
    @ResponseBody
    public Map queryPer(Integer page, Integer rows){


        Map map = new HashMap<>();

        map.put("start",(page-1)*rows);
        map.put("end",rows);

        Integer count = userService.queryPerCount(map);
        List<Permission> list = userService.queryPer(map);

        Map map2 = new HashMap<>();

        map2.put("rows",list);
        map2.put("total",count);

        return map2;
    }

    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletResponse response){
        //导出的Excel的列名
        String title = "用户管理";
        //导出的Excel的列名
        String[] rowName = {"id","用户名","密码"};
        //导出的Excel 数据
        List<Object[]> dataList = new ArrayList<Object[]>();

        //查询的数据路库用户信息
        List<User> list = userService.queryUser2();
        //循环用户信息
        for (User user:list){
            Object[] obj = new Object[rowName.length];
            obj[0] = user.getId();
            obj[1] = user.getUsername();
            obj[2] = user.getPassword();

            dataList.add(obj);
        }
        ExportExcel exportExcel = new ExportExcel(title,rowName,dataList,response);
        try {
            exportExcel.export();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("importExcel")
    public String importExcel(MultipartFile file, HttpServletResponse response){

        String contentType = file.getContentType();

        String fileName = file.getOriginalFilename();

        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        String filePath = "./src/main/resources/templates/fileupload/";

        List<User> list = new ArrayList<User>();

        try {
            uploadFile(file.getBytes(),filePath,fileName);
            FileInputStream fileInputStream = new FileInputStream(filePath + fileName);
            Workbook workbook = WorkbookFactory.create(fileInputStream);

            for (int i=0;i<workbook.getNumberOfSheets();i++){
                //获得没一个sheet对象接受Excel对象
                Sheet sheetAt = workbook.getSheetAt(i);
                for (int j=3;j<sheetAt.getPhysicalNumberOfRows();j++){
                    User user = new User();
                    //获得每一行的数据
                    Row row = sheetAt.getRow(j);

                    //获得每一个单元格的数据
                    if(row.getCell(1)!=null && !"".equals(row.getCell(1))){
                        user.setUsername(this.getCellValue(row.getCell(1)));
                    }
                    if(row.getCell(2)!=null && !"".equals(row.getCell(2))){
                        user.setPassword(this.getCellValue(row.getCell(2)));
                    }
                    list.add(user);
                }
            }
            userService.addUser(list);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "user";
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
