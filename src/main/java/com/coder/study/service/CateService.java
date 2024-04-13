package com.coder.study.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.mapper.CateMapper;
import com.coder.study.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CateService {
    @Autowired
    private CateMapper cateMapper;
    // 分页查询分类信息
    public Page<Category> findCatePage(int page, int size) {
        Page selectPage = cateMapper.selectPage(new Page(page, size), null);
        return selectPage;
    }

public List<Category> findAllSon(){
       return cateMapper.selectList(null);
}
    public void add(Category category){
        cateMapper.insert(category);
    }
    public void delete(Integer cid){
        cateMapper.deleteById(cid);}


}
