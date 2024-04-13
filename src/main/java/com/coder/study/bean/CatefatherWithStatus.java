package com.coder.study.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CatefatherWithStatus {

   // private static final long serialVersionUID = 1L;

    private Integer fcid;
    private String fcName;
    private Boolean sonHas; //儿子是否拥有该父亲

}
