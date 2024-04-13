package com.coder.study.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.bean.Result;
import com.coder.study.mapper.StudentMapper;
import com.coder.study.pojo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
private StudentMapper studentMapper;
    @Autowired
    private PasswordEncoder encoder;
    @Value("${project.path}")
    private String projectPath;
    // 分页查询学生信息
    public Page<StudentInfo> findStuPage(int page, int size) {
        Page selectPage = studentMapper.selectPage(new Page(page, size), null);
        return selectPage;
    }
    //添加学生
    public void add(StudentInfo studentInfo) {
        studentMapper.insert(studentInfo);
    }

    // 查询学生
    public StudentInfo findById(Integer sid) {
        return studentMapper.selectById(sid);
    }

    // 修改学生
    public void update(StudentInfo studentInfo) {
        studentMapper.updateById(studentInfo);
    }

    //删除学生
    public void delete(Integer sid) {
        studentMapper.deleteById(sid);
    }



    // 注册
    public Result register(StudentInfo studentInfo) {

        // 验证用户名是否重复
        QueryWrapper<StudentInfo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("stuName", studentInfo.getStuName());
        List<StudentInfo> members = studentMapper.selectList(queryWrapper);
        if (members.size() > 0) {
            return new Result(false, "用户名已存在");
        }
        // 保存用户
        studentMapper.insert(studentInfo);
        return new Result(true, "注册成功！");
    }
    //登录
    public Result login(String name, String password) {
        StudentInfo studentInfo = null;

        // 根据用户名查询
        if (studentInfo == null) {
            QueryWrapper<StudentInfo> queryWrapper = new QueryWrapper();
            queryWrapper.eq("stuName", name);
            studentInfo = studentMapper.selectOne(queryWrapper);
        }

        // 没有查询到用户
        if (studentInfo == null) {
            return new Result(false, "用户名或密码错误");
        }

        // 验证密码
        boolean flag = encoder.matches(password, studentInfo.getPassword());
        if (!flag) {
            return new Result(false, "用户名或密码错误");
        }


        return new Result(true, "登录成功", studentInfo);
    }
}
