package com.coder.study.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author itbaizhan
 * @since 09-02
 */
@Data
public class Answerquestion implements Serializable {


    @TableId
    private Integer ansid;
    private Integer stuid;
    private String stuName;

    private String des;
    private String answer;

    private String cate;

    private String sovelStatus;

    private Integer marks;
    private Integer sol_sta;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date qdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ansdate;
    @TableField(exist = false) // 不是数据库的字段
    private List<Catefather> fcates;


}
