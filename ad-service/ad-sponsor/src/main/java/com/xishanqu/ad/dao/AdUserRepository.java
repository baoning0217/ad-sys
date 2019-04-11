package com.xishanqu.ad.dao;

import com.xishanqu.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-11
 */
public interface AdUserRepository extends JpaRepository<AdUser,Long> {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    AdUser findByUsername(String username);
}
