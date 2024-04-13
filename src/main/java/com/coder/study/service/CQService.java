package com.coder.study.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.mapper.CQMapper;
import com.coder.study.pojo.Commonquestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CQService {
    @Autowired
    private CQMapper cqMapper;

    // 分页查询经常问答，并排序
    public Page<Commonquestion> findCQPage(int page, int size) {
        QueryWrapper<Commonquestion> queryWrapper = new QueryWrapper<>();
        System.out.println("排序");
        queryWrapper.orderByDesc("betop");
        Page selectPage = cqMapper.selectPage(new Page(page, size), queryWrapper);

        return selectPage;
    }

    //分页查询搜索的经常问题，并排序
    public Page<Commonquestion> findSearchCQPage(int page, int size, String des) {
        QueryWrapper<Commonquestion> queryWrapper = new QueryWrapper<>();
        //关键字搜索并排序
        queryWrapper.like("des", des).orderByDesc("betop");
        Page selectPage = cqMapper.selectPage(new Page(page, size), queryWrapper);

        return selectPage;
    }
    //删除
    public void delete(Integer commqid) {
        cqMapper.deleteById(commqid);
    }
    //添加
    public void add(Commonquestion commonquestion) {
        cqMapper.insert(commonquestion);
    }

    // 查询
    public Commonquestion findById(Integer commqid) {
        return cqMapper.selectById(commqid);
    }

    // 修改
    public void update(Commonquestion commonquestion) {
        cqMapper.updateById(commonquestion);
    }

    //管理员置顶
    public void beTop(Commonquestion cq) {

        QueryWrapper<Commonquestion> queryWrapper = new QueryWrapper<>();
        System.out.println("判断");
        queryWrapper.like("betop", "1");
        if (cqMapper.selectList(queryWrapper).toArray().length <= 9) {
            cq.setBetop(1);
            cq.setUpStatus("已置顶"
            );
            cqMapper.updateById(cq);
        } else {
            System.out.println("无法置顶，请取消一个");
        }

    }

    //管理员取消置顶
    public void deleTop(Commonquestion cq) {
        cq.setBetop(0);
        cq.setUpStatus("");
        cqMapper.updateById(cq);
    }
}

