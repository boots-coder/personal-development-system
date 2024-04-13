package com.coder.study.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CategoryWithStatus {

    private Integer cid;
    private String cName;

    private Boolean fatherHas; //父亲是否拥有该儿子

}
