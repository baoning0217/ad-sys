package com.xishanqu.ad.controller;

import com.alibaba.fastjson.JSON;
import com.xishanqu.ad.exception.AdException;
import com.xishanqu.ad.service.ICreativeService;
import com.xishanqu.ad.vo.CreativeRequest;
import com.xishanqu.ad.vo.CreativeResponse;
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
public class CreativeOPController {

    @Autowired
    private ICreativeService creativeService;

    /**
     * add creative
     * @param request
     * @return
     * @throws AdException
     */
    @PostMapping("/create/creative")
    public CreativeResponse createCreative(@RequestBody CreativeRequest request) throws AdException{
        log.info("ad-sponsor: creativeCreative -> {}", JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }


}
