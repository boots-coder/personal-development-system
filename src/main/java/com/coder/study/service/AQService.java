package com.coder.study.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.mapper.AQMapper;
import com.coder.study.mapper.CQMapper;
import com.coder.study.pojo.Answerquestion;
import com.coder.study.pojo.Commonquestion;
import com.coder.study.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AQService {
    @Autowired
    private AQMapper aqMapper;
    @Autowired
    private CQMapper cqMapper;
    // 分页查询人工问答信息
    public Page<Answerquestion> findAQPage(int page, int size) {
        Page selectPage = aqMapper.selectPage(new Page(page, size), null);
        return selectPage;
    }

    // 分页查询我的问答并排序
    public Page<Answerquestion> findMyAQPage(int page, int size,String stuName) {
        QueryWrapper<Answerquestion> queryWrapper = new QueryWrapper<>();
        System.out.println("排序");
        queryWrapper.like("stuName",stuName).orderByDesc("sol_sta");
        Page selectPage = aqMapper.selectPage(new Page(page, size), queryWrapper);

        return selectPage;
    }

    // 添加到常见问题库
    public void addTo(Answerquestion answerquestion) {
        System.out.println("添加到常见问题");
        Commonquestion canswerquestion=new Commonquestion();
        canswerquestion.setAnsw(answerquestion.getAnswer());
        canswerquestion.setCate(answerquestion.getCate());
        canswerquestion.setDes(answerquestion.getDes());
        canswerquestion.setUdate(answerquestion.getAnsdate());
        cqMapper.insert(canswerquestion);
    }

    // 查询人工问答详情
    public Answerquestion findDesc(Integer ansid){
        return aqMapper.findDesc(ansid);
    }
    //根据id查找问题
    public Answerquestion findById(Integer ansid){
        return aqMapper.selectById(ansid);
    }
    // 提出一个问题
    public void add(Answerquestion answerquestion) {
        aqMapper.insert(answerquestion);
    }
    // 修改问题
    public void update(Answerquestion answerquestion) {
        aqMapper.updateById(answerquestion);
    }
}
