package com.example.demo.service.Impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.demo.dao.UserDao;
import com.example.demo.listener.UserListener;
import com.example.demo.pojo.User;
import com.example.demo.pojo.query.UserQuery;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service   //  交由spring容器管理
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    /**
     * 导入
     * @param file 导入文件
     * @param updateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作人
     * @return 是否成功
     * @throws IOException
     */
    @Override
    public Boolean importJuLiData(MultipartFile file, boolean updateSupport, String operName) throws IOException {
        UserListener excelListener = new UserListener(userService, updateSupport, operName);
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        //EasyExcel.read(哪个文件).sheet(那张sheet表).head(表头什么样子).headRowNumber(表头占几行).registerReadListener(处理数据的监听器类).doRead()
        EasyExcel.read(file.getInputStream(), User.class, excelListener).sheet(0).head(User.class).headRowNumber(1).doRead();
        System.out.println(excelListener.getSet_fail());
        System.out.println(excelListener.getSuccess());
        System.out.println(excelListener.getFail());
        return true;
    }

    /**
     * 导出
     * @param response
     * @param operName 操作人
     * @return 是否成功
     * @throws IOException
     */
    @Override
    public void exportJuLiData(HttpServletResponse response, String operName) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //设置响应内容类型
        response.setCharacterEncoding("utf-8");//编码
        // 设置文件名, ps:把字符串中所有的'+'替换成'%20'，在URL中%20代表空格
        String fileName = URLEncoder.encode("用户表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");//设置响应头

        List<User> applicantByPage = userService.listUser();// 获取全部用户数据
        ExcelWriter writer = EasyExcel.write(response.getOutputStream(), User.class).build();//获取写出流
        WriteSheet sheet = EasyExcel.writerSheet("用户").build();//创建表格，设置表格页名称
        writer.write(applicantByPage, sheet);//读出
        writer.finish();//关闭流
    }

    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }

    @Override
    public PageInfo<User> listUserByName(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPageNum(),userQuery.getPageSize());
        return new PageInfo<User>(userDao.listUserByName(userQuery));
    }

    @Override
    public boolean deleteUserById(Integer id) {
        int i = userDao.deleteUserById(id);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User queryUserById(Integer id) {
        return userDao.queryUserById(id);
    }

    @Override
    public boolean updateUser(User user) {
        int i = userDao.updateUser(user);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user) > 0 ? true : false ;
    }

    @Override
    public int selectUserByName(String name) {
        return userDao.selectUserByName(name);
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public Boolean importCompositeItemData(MultipartFile file, boolean updateSupport, String operName) {
       /* UserListener excelListener = new UserListener(userService, updateSupport, operName);
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        //EasyExcel.read(哪个文件).sheet(那张sheet表).head(表头什么样子).headRowNumber(表头占几行).registerReadListener(处理数据的监听器类).doRead()
        EasyExcel.read(file.getInputStream(), User.class, excelListener).sheet(0).head(User.class).headRowNumber(1).doRead();
        System.out.println(excelListener.getSet_fail());
        System.out.println(excelListener.getSuccess());
        System.out.println(excelListener.getFail());*/
        return true;
    }


}
