package com.coder.study.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
/**
 * StudentInfo 学生信息
 *
 * @author bootsCoder
 * @date created on 2023/11/10
 */
@Data
public class StudentInfo {
    @TableId
    private Integer sid;

    private String account;

    private String password;

    private String stuName;

    private String sex;

    private String school;



}
