package cn.edu.whut.gumorming.config;

import cn.edu.whut.gumorming.filter.JwtAuthenticationTokenFilter;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.config
 * @createTime : 2024/2/18 15:34
 * @Email : gumorming@163.com
 * @Description :
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity() // 启用注解权限配置
public class AdminSpringSecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    public static final String[] ADMIN_WHITELIST = {
            "/report"
    };

    private static final String[] KNIFE4J_WHITELIST = {
            "/webjars/"
            , "/webjars/**"
            , "/doc.html/"
            , "/doc.html#/"
            , "/doc.html/**"
            , "/v3/api-docs/swagger-config"
            , "/v3/api-docs/default"
    };

    /**
     * @param http
     * @return
     * @throws Exception
     * @see <a href="https://docs.spring.io/spring-security/reference/migration-7/configuration.html#_use_the_lambda_dsl">...</a>
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                //允许跨域
                .cors(withDefaults())
                // 关闭csrf
                .csrf(csrf -> csrf.disable())
                //不通过Session获取SecurityContext
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // 登录接口 允许匿名访问
                        .requestMatchers("/login").anonymous()
                        .requestMatchers(ADMIN_WHITELIST).permitAll()
                        .requestMatchers(KNIFE4J_WHITELIST).permitAll()
                        // 其他接口皆须认证
                        .anyRequest().authenticated())
                .logout(logout -> logout.disable())
                // 自定义的过滤器 在 登录校验过滤器 之前生效
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(handler -> handler
                        // 认证失败处理
                        .authenticationEntryPoint(authenticationEntryPoint)
                        // 权限不足处理
                        .accessDeniedHandler(accessDeniedHandler))
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // 自定义 UserDetailsService
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        // 配置password加密 BCrypt
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }

    /**
     * 创建BCryptEncoder并注入容器
     *
     * @return BCryptPasswordEncoder
     */

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 修改{@link SecurityContextHolder}策略
     */
    @PostConstruct
    public void setStrategyName() {
        // 程序启动后修改认证信息上下文存储策略，支持子线程中获取认证信息
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

}