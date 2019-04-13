package com.xishanqu.ad.service;

import com.xishanqu.ad.exception.AdException;
import com.xishanqu.ad.vo.CreateUserRequest;
import com.xishanqu.ad.vo.CreateUserResponse;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-12
 */
public interface IUserService {

    /**
     * 创建用户
     * @param request
     * @return
     * @throws AdException
     */
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;


}
