package com.coder.study.pojo;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
@Data
public class Commonquestion  {


    @TableId
    private Integer commqid;

    private String des;

    private String cate;

    private String answ;

    private String upStatus;
    private Integer betop;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date udate;

}
