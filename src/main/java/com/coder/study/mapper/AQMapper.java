package com.coder.study.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coder.study.pojo.Answerquestion;

public interface AQMapper extends BaseMapper<Answerquestion> {
//查询问答详情

Answerquestion findDesc(Integer ansid);

}
