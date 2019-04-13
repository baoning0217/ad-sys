package com.xishanqu.ad.service.impl;

import com.xishanqu.ad.dao.CreativeRepository;
import com.xishanqu.ad.entity.Creative;
import com.xishanqu.ad.exception.AdException;
import com.xishanqu.ad.service.ICreativeService;
import com.xishanqu.ad.vo.CreativeRequest;
import com.xishanqu.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-13
 */
@Service
public class CreativeServiceImpl implements ICreativeService {

    @Autowired
    private CreativeRepository creativeRepository;


    @Override
    public CreativeResponse createCreative(CreativeRequest request) throws AdException {
        Creative creative = creativeRepository.save(request.convertToEntity());
        return new CreativeResponse(creative.getId(), creative.getName());
    }


}
