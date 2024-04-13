package com.coder.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coder.study.pojo.Category;

import java.util.List;


public interface CateMapper extends BaseMapper<Category> {
    // 查询父亲拥有的所有儿子id
    List<Integer> findSonIdByFather(Integer fcid);
}