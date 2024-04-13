package com.coder.study.security;
import com.coder.study.pojo.Admin;
import com.coder.study.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
// 自定义认证授权逻辑
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.认证
        Admin admin = adminService.findByAdminName(username);
        if (admin == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        // 2.授权

      List<GrantedAuthority> grantedAuthorities = new ArrayList();


        // 3.封装为UserDetails对象
        UserDetails userDetails = User.withUsername(admin.getUsername())
                .password(admin.getPassword())
                .authorities(grantedAuthorities)
                .build();
        // 4.返回封装好的UserDetails对象
        return userDetails;
    }
}
