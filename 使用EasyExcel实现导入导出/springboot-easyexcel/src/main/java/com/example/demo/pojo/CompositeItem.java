package com.example.demo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@ContentRowHeight(18)//内容行高
@HeadRowHeight(25)//标题行高
@ColumnWidth(20)//列宽，可设置成员变量上
@Data
public class CompositeItem implements Serializable {

    //组合项目名称
    @ExcelProperty(value="组合项目名称")
    private String name;
    @ExcelProperty(value="拼音简码")
    private String alphabet;
    //体检项目名称
    @ExcelProperty(value="体检项目名称")
    private String tName;
    @ExcelProperty(value="正常结果")
    private String  normal_result;
    @ExcelProperty(value="结果类型")
    private Integer data_type;
    @ExcelProperty(value="科室")
    private Long hospital_department_id;
    @ExcelProperty(value="检查类型")
    private Integer check_type;
    @ExcelProperty(value="性别限制")
    private Integer sex_limit;
    @ExcelProperty(value="婚姻状况")
    private Integer marital_status;
    @ExcelProperty(value="餐前后(1餐前 2餐后)")
    private Integer meal_method;
    @ExcelProperty(value="是否需要图片")
    private Integer need_picture_flag;
    @ExcelProperty(value="部位")
    private Integer parts;
    @ExcelProperty(value="显示顺序")
    private Integer sort;

}
