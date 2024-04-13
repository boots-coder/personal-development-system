package com.coder.study.mapper;




import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coder.study.pojo.Commonquestion;


public interface CQMapper extends BaseMapper<Commonquestion> {


    Commonquestion findDesc(Integer commqid);

}



