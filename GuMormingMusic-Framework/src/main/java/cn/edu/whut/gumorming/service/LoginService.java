package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.model.ResponseResult;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.service
 * @createTime : 2024/2/18 16:01
 * @Email : gumorming@163.com
 * @Description :
 */

public interface LoginService {
    ResponseResult<String> login(User user);

    ResponseResult<?> logout();

    ResponseResult<?> report();
}