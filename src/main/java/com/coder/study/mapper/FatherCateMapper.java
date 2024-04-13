package com.coder.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coder.study.pojo.Catefather;
import org.apache.ibatis.annotations.Param;


public interface FatherCateMapper extends BaseMapper<Catefather> {

    // 删除父亲的所有儿子
    void deleteFatherAllSon(Integer fcid);
    // 给父亲添加儿子
    void addFatherSon(@Param("fcid") Integer fcid, @Param("cid") Integer cid);
    Catefather findDesc1(Integer fcid);
}
