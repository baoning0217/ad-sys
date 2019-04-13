package com.xishanqu.ad.service;

import com.xishanqu.ad.exception.AdException;
import com.xishanqu.ad.vo.CreativeRequest;
import com.xishanqu.ad.vo.CreativeResponse;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-13
 */
public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request) throws AdException;

}
