package com.coder.study.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.mapper.CateMapper;
import com.coder.study.mapper.FatherCateMapper;
import com.coder.study.pojo.Catefather;
import com.coder.study.bean.CategoryWithStatus;
import com.coder.study.pojo.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FatherCateService {
    @Autowired
    private FatherCateMapper fatherCateMapper;
    @Autowired
    private CateMapper cateMapper;
    // 分页查询分类信息
    public Page<Catefather> findFatherCatePage(int page, int size) {
        Page selectPage = fatherCateMapper.selectPage(new Page(page, size), null);
        return selectPage;
    }
    public List<Catefather> findAllFather(){
        return fatherCateMapper.selectList(null);
    }

    public Catefather findDesc1(Integer fcid) {
        return fatherCateMapper.findDesc1(fcid);
    }
public Catefather findById(Integer fcid){
       return fatherCateMapper.selectById(fcid);
}
    public void add(Catefather fc){
        fatherCateMapper.insert(fc);
    }
    public void delete(Integer fcid){
        fatherCateMapper.deleteById(fcid);}

    // 查询父亲的儿子情况
    public List<CategoryWithStatus> findSon(Integer fcid){
        // 查询所有权限
        List<Category> categories = cateMapper.selectList(null);
        // 查询父亲拥有的儿子id
        List<Integer> cids = cateMapper.findSonIdByFather(fcid);
        // 构建带有状态的儿子集合
        List<CategoryWithStatus> categoryList = new ArrayList();
        for (Category category : categories) {
            // 创建带有状态的儿子
            CategoryWithStatus categoryWithStatus = new CategoryWithStatus();
            BeanUtils.copyProperties(category,categoryWithStatus);
            // 判断角色是否拥有该权限
            if(cids.contains(category.getCid())){
                categoryWithStatus.setFatherHas(true);
            }else{
                categoryWithStatus.setFatherHas(false);
            }
            categoryList.add(categoryWithStatus);
        }
        return categoryList;
    }

    // 给父亲重新分配儿子
    public void updateSons(Integer fcid,Integer[] ids){
        // 删除角色的所有权限
        fatherCateMapper.deleteFatherAllSon(fcid);

  /*      int i = 1/0;
*/
        // 重新给角色添加权限
        for (Integer cid : ids) {
            fatherCateMapper.addFatherSon(fcid,cid);
        }
    }
}
