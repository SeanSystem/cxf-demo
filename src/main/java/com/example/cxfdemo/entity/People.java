package com.example.cxfdemo.entity;

import com.example.cxfdemo.annotation.NotNull;
import com.example.cxfdemo.annotation.Pattern;
import lombok.Data;

/**
 * @author Sean
 * @date 2019/11/19
 */
@Data
public class People {

    @NotNull(value = "姓名不能为空")
    private String name;

    @NotNull(value = "年龄不能为空")
    private Integer age;

    @NotNull(value = "手机号码不能为空")
    @Pattern(regexp = "^\\d{11}$", message = "手机号码格式错误")
    private String phone;
}
