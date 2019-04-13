package com.xishanqu.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String username;

    public boolean validate(){
        return !StringUtils.isEmpty(username);
    }

}
