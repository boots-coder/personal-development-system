package com.coder.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coder.study.pojo.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper extends BaseMapper<Admin> {

    // 查询学生信息详情
    Admin findStud(Integer aid);
    // 删除学生信息
    Admin addStud(Integer aid);
    // 修改学生信息详情
    Admin updateStud(Integer aid);
    // 查询老师信息详情
    Admin findTeacher(Integer aid);
    // 查询学生信息详情
    Admin addTeacher(Integer aid);
    // 查询学生信息详情
    Admin updateTeacher(Integer aid);
    // 查询学生信息详情
    Admin deleteTeacher(Integer aid);



//    // 删除用户的所有角色
//    void deleteAdminAllRoles(Integer aid);

//    // 给用户添加角色
//    void addAdminRole(@Param("aid") Integer aid, @Param("rid") Integer rid);
//
//    // 根据用户名查询权限
//    List<Permission> findAllPermission(String username);
}
