package com.coder.study.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author itbaizhan
 * @since 09-02
 */
@Data
public class Admin implements Serializable {

    @TableId
    private Integer aid;

    private String username;

    private String password;


}
