package com.coder.study.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.bean.Result;
import com.coder.study.mapper.TeacherMapper;
import com.coder.study.pojo.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private PasswordEncoder encoder;
    @Value("${project.path}")
    private String projectPath;

    // 分页查询老师信息
    public Page<TeacherInfo> findTeaPage(int page, int size) {
        Page selectPage = teacherMapper.selectPage(new Page(page, size), null);
        return selectPage;
    }

    //添加教师
    public void add(TeacherInfo teacherInfo) {
        teacherMapper.insert(teacherInfo);
    }

    // 查询教师
    public TeacherInfo findById(Integer tid) {
        return teacherMapper.selectById(tid);
    }

    // 修改教师
    public void update(TeacherInfo teacherInfo) {
        teacherMapper.updateById(teacherInfo);
    }

    //删除老师
    public void delete(Integer tid) {
        teacherMapper.deleteById(tid);
    }




//登录
    public Result login(String name, String password) {
        TeacherInfo teacherInfo = null;

        // 根据用户名查询
        if (teacherInfo == null) {
            QueryWrapper<TeacherInfo> queryWrapper = new QueryWrapper();
            queryWrapper.eq("teaName", name);
            teacherInfo = teacherMapper.selectOne(queryWrapper);
        }

        // 没有查询到用户
        if (teacherInfo == null) {
            return new Result(false, "用户名或密码错误");
        }

        // 验证密码
        boolean flag = encoder.matches(password, teacherInfo.getPassword());
        if (!flag) {
            return new Result(false, "用户名或密码错误");
        }


        return new Result(true, "登录成功", teacherInfo);
    }
}