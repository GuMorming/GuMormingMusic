package cn.edu.whut.gumorming.model;

import cn.edu.whut.gumorming.entity.User;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model
 * @createTime : 2024/2/18 15:16
 * @Email : gumorming@163.com
 * @Description : 实现UserDetails接口,在登录认证成功时封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;
    private String token;
    /**
     * 操作系统
     */

    private String os;

    /**
     * 浏览器
     */

    private String browser;
    /**
     * 权限信息
     */
    private List<String> permissions;
    /**
     * 封装好的权限信息
     */
    // 序列化忽略,1.安全问题2.防止到Redis中出错
    @JSONField(serialize = false)
    List<SimpleGrantedAuthority> authorities;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
        authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission))
                .toList();
    }

    /**
     * 把permissions中String类型的权限信息封装成
     * GrantedAuthority的实现类SimpleGrantedAuthority
     *
     * @return 封装好的权限信息authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }

        authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission))
                .toList();

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}