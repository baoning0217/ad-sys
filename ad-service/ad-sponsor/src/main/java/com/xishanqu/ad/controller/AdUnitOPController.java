package com.xishanqu.ad.controller;

import com.alibaba.fastjson.JSON;
import com.xishanqu.ad.exception.AdException;
import com.xishanqu.ad.service.IAdUnitService;
import com.xishanqu.ad.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-13
 */
@Slf4j
@RestController
public class AdUnitOPController {

    @Autowired
    private IAdUnitService adUnitService;


    /**
     * 创建推广单元
     * @param request
     * @return
     * @throws AdException
     */
    @PostMapping("/create/adUnit")
    public AdUnitResponse createUnit(@RequestBody AdUnitRequest request) throws AdException{
        log.info("ad-sponsor: createUnit -> {}", JSON.toJSONString(request));
        return adUnitService.createUnit(request);
    }

    /**
     * 创建unitKeyword
     * @param request
     * @return
     * @throws AdException
     */
    @PostMapping("/create/unitKeyword")
    public AdUnitKeywordResponse createUnitKeyword(@RequestBody AdUnitKeywordRequest request) throws AdException{
        log.info("ad-sponsor: createUnitKeyword -> {}",JSON.toJSONString(request));
        return adUnitService.createUnitKeyword(request);
    }

    /**
     * 创建unitIt
     * @param request
     * @return
     * @throws AdException
     */
    @PostMapping("/create/unitIt")
    public AdUnitItResponse createUnitIt(@RequestBody AdUnitItRequest request) throws AdException{
        log.info("ad-sponsor: createUnitIt -> {}", JSON.toJSONString(request));
        return adUnitService.createUnitIt(request);
    }

    /**
     * 创建unitDistrict
     * @param request
     * @return
     * @throws AdException
     */
    @PostMapping("/create/ad")
    public AdUnitDistrictResponse createUnitDistrict(@RequestBody AdUnitDistrictRequest request) throws AdException{
        log.info("ad-sponsor: createUnitDistrict -> {}",JSON.toJSONString(request));
        return adUnitService.createUnitDistrict(request);
    }

    /**
     * 创建creativeUnit
     * @param request
     * @return
     * @throws AdException
     */
    @PostMapping("/create/creativeUnit")
    public CreativeUnitResponse createCreativeUnit(@RequestBody CreativeUnitRequest request) throws AdException{
        log.info("ad-sponsor: createCreativeUnit -> {}",JSON.toJSONString(request));
        return adUnitService.createCreativeUnit(request);
    }


}
