
package com.coder.study.pojo;
import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


@Data
public class TeacherInfo implements Serializable {

    @TableId
    private Integer tid;
    private String account;
    private String password;
    private String teaName;
    private String sex;
    private String subject;
    private String field;
    private String phoneNum;
    private String workStatus;
 @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
 @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date workDate;




}
