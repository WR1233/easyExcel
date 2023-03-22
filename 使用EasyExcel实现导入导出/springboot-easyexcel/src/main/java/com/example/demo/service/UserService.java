package com.example.demo.service;

import com.example.demo.pojo.User;
import com.example.demo.pojo.query.UserQuery;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UserService {
    /**
     * 导入本地用户userId与聚力对应userId
     * @param file 导入文件
     * @param updateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作人
     * @return 是否成功
     */
    Boolean importJuLiData(MultipartFile file, boolean updateSupport, String operName) throws IOException;

    /**
     *
     * @param response
     * @param operName 操作人
     * @return
     */
    void exportJuLiData(HttpServletResponse response, String operName) throws IOException;

    // 查询所有用户
    public List<User> listUser();

    // 根据用户名来查询用户  并分页展示
    public PageInfo<User> listUserByName(UserQuery userQuery);

    //根据id删除用户
    public boolean deleteUserById(Integer id);

    // 根据id查询用户
    public User queryUserById(Integer id);

    // 修改用户
    public boolean updateUser(User user);

    // 新增用户
    public boolean addUser(User user);

    public int selectUserByName(String name);

    int insert(User user);

    Boolean importCompositeItemData(MultipartFile file, boolean updateSupport, String operName);
}
