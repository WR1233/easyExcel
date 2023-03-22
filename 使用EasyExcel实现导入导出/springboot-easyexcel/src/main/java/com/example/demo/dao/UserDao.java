package com.example.demo.dao;


import com.example.demo.pojo.User;
import com.example.demo.pojo.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper  // 告诉springboot这是一个mybatis的mapepr类
@Repository   // 将userdao交由spring容器管理
public interface UserDao {
    // 查询所有用户
    public List<User> listUser();

    // 根据id查询用户
    public User queryUserById(Integer id);

    // 根据用户名来查询用户  并分页展示
    public List<User> listUserByName(UserQuery userQuery);

    //根据id删除用户
    public int deleteUserById(Integer id);

    // 修改用户
    public int updateUser(User user);

    // 新增用户
    public int addUser(User user);

    // 判断用户名是否存在
    public int selectUserByName(String name);

    int insert(User user);
}

