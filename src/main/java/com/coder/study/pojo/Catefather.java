package com.coder.study.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Catefather {
    @TableId
    private Integer fcid;
    private String fcName;

    @TableField(exist = false) // 不是数据库的字段
    private List<Category> cates;// 权限集合
}
