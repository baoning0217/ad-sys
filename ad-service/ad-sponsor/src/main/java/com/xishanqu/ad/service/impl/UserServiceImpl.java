package com.xishanqu.ad.service.impl;

import com.xishanqu.ad.constant.Constants;
import com.xishanqu.ad.dao.AdUserRepository;
import com.xishanqu.ad.entity.AdUser;
import com.xishanqu.ad.exception.AdException;
import com.xishanqu.ad.service.IUserService;
import com.xishanqu.ad.utils.CommonUtils;
import com.xishanqu.ad.vo.CreateUserRequest;
import com.xishanqu.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-13
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private AdUserRepository adUserRepository;

    /**
     * 创建用户
     * @param request
     * @return CreateUserResponse
     * @throws AdException
     */
    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        if (!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdUser oldUser = adUserRepository.findByUsername(request.getUsername());
        if (oldUser != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }
        AdUser newUser = adUserRepository.save(new AdUser(
            request.getUsername(), CommonUtils.md5(request.getUsername())
        ));
        return new CreateUserResponse(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getToken(),
                newUser.getCreateTime(),
                newUser.getUpdateTime()
        );
    }


}
