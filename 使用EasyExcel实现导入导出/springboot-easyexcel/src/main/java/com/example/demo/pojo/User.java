package com.example.demo.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.example.demo.convertor.SexConvertor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
*
* 加了@Data注解的类，编译后会自动给我们加上下列方法：
    所有属性的get和set方法
    toString 方法
    hashCode方法
    equals方法
*
* @AllArgsConstructor ：有参构造方法
* @NoArgsConstructor ：无参构造方法
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ContentRowHeight(18)//内容行高
@HeadRowHeight(25)//标题行高
@ColumnWidth(20)//列宽，可设置成员变量上
public class User implements Serializable {
    //index表示第几列（从0开始，表示第一列），value表示标题（表头）
    @ExcelProperty(value = "用户id",index = 0)
    @ExcelIgnore
    private Integer id;

    @ExcelProperty(value = {"用户详细信息","用户名"},index = 1)
    private String name;

    @ExcelProperty(value = {"用户详细信息","用户密码"},index = 2)
    private String pwd;

    @ExcelProperty(value = "用户介绍",index = 3)
    private String userIntroduce;

    @ExcelProperty(value = "性别",index = 4,converter = SexConvertor.class)
    private Integer sex;   //性别的类型要对应数据库的类型

}
