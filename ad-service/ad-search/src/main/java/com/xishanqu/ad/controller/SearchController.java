package com.xishanqu.ad.controller;

import com.alibaba.fastjson.JSON;
import com.xishanqu.ad.annotation.IgnoreResponseAdvice;
import com.xishanqu.ad.client.SponsorClient;
import com.xishanqu.ad.client.vo.AdPlan;
import com.xishanqu.ad.client.vo.AdPlanGetRequest;
import com.xishanqu.ad.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-13
 */
@Slf4j
@RestController
public class SearchController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SponsorClient sponsorClient;

    /**
     * 微服务使用Ribbon调用
     * @param request
     * @return
     */
    @SuppressWarnings("all")
    @IgnoreResponseAdvice
    @PostMapping("/getAdPlanByRibbon")
    public CommonResponse<List<AdPlan>> getAdPlanByRibbon(@RequestBody AdPlanGetRequest request){
        log.info("ad-search: getAdPlanByRibbon -> {}", JSON.toJSONString(request));
        return restTemplate.postForEntity("http://eureka-client-ad-sponsor/ad-sponsor/get/adPlan",request,CommonResponse.class).getBody();
    }


    /**
     * 微服务使用Feign调用
     * @param request
     * @return
     */
    @IgnoreResponseAdvice
    @PostMapping("/getAdPlans")
    public CommonResponse<List<AdPlan>> getAdPlans(@RequestBody AdPlanGetRequest request){
        log.info("ad-search: getAdPlans -> {}",JSON.toJSONString(request));
        return sponsorClient.getAdPlans(request);
    }


}
