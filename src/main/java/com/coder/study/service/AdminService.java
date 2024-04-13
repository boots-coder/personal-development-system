package com.coder.study.service;

/*
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.study.bean.RoleWithStatus;
import com.itbaizhan.study.mapper.AdminMapper;
import com.itbaizhan.study.mapper.RoleMapper;
import com.itbaizhan.study.pojo.Admin;
import com.itbaizhan.study.pojo.Permission;
import com.itbaizhan.study.pojo.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
*/

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.mapper.AdminMapper;
import com.coder.study.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    // 根据id查询管理员
    public Admin findById(Integer aid) {
        return adminMapper.selectById(aid);
    }
    public Admin findByAdminName(String username){
        QueryWrapper<Admin> wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        Admin admin = adminMapper.selectOne(wrapper);
        return admin;
    }
/*    // 新增管理员
    public void add(Admin admin) {
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminMapper.insert(admin);
    }*/


  /*  // 修改管理员
    public void update(Admin admin) {
        // 旧密码
        String oldPassword = adminMapper.selectById(admin.getAid()).getPassword();
        // 新密码
        String newPassword = admin.getPassword();

        // 如果新密码不等于旧密码，对新密码进行加密
        if(!oldPassword.equals(newPassword)){
            admin.setPassword(encoder.encode(admin.getPassword()));
        }

        adminMapper.updateById(admin);
    }
*/
}
