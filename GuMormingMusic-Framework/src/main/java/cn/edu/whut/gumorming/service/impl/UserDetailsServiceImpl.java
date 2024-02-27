package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.mapper.MenuMapper;
import cn.edu.whut.gumorming.mapper.UserMapper;
import cn.edu.whut.gumorming.model.LoginUser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.service.impl
 * @createTime : 2024/2/18 14:35
 * @Email : gumorming@163.com
 * @Description :
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username);
        Optional<User> userOptional = userMapper.getOne(lambdaQueryWrapper);
        if (userOptional.isEmpty()) {
            throw new BadCredentialsException(HttpCodeEnum.LOGIN_ERROR.getMsg());
        }

        // 数据库中查询对应权限信息
        List<String> permissions = menuMapper.selectPermsByUserId(userOptional.get().getId());
        // 数据封装为UserDetails
        return new LoginUser(userOptional.get(), permissions);
    }
}