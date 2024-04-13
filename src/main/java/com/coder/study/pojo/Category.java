package com.coder.study.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Category {

    @TableId
    private Integer cid;
    private String cName;



}
