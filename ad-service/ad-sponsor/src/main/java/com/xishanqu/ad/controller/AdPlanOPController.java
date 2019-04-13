package com.xishanqu.ad.controller;

import com.alibaba.fastjson.JSON;
import com.xishanqu.ad.entity.AdPlan;
import com.xishanqu.ad.exception.AdException;
import com.xishanqu.ad.service.IAdPlanService;
import com.xishanqu.ad.vo.AdPlanGetRequest;
import com.xishanqu.ad.vo.AdPlanRequest;
import com.xishanqu.ad.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-13
 */
@Slf4j
@RestController
public class AdPlanOPController {

    @Autowired
    private IAdPlanService adPlanService;

    /**
     * 创建adPlan
     * @param request
     * @return
     * @throws AdException
     */
    @PostMapping("/create/adPlan")
    public AdPlanResponse createAdPlan(@RequestBody AdPlanRequest request) throws AdException{
        log.info("ad-sponsor: createAdPlan -> {}", JSON.toJSONString(request));
        return adPlanService.createAdPlan(request);
    }

    /**
     * 获取adPlan
     * @param request
     * @return
     * @throws AdException
     */
    @PostMapping("/get/adPlan")
    public List<AdPlan> getAdPlanByIds(@RequestBody AdPlanGetRequest request) throws AdException{
        log.info("ad-sponsor: getAdPlanByIds -> {}",JSON.toJSONString(request));
        return adPlanService.getAdPlanByIds(request);
    }

    /**
     * 更新adPlan
     * @param request
     * @return
     * @throws AdException
     */
    @PutMapping("/update/adPlan")
    public AdPlanResponse updateAdPlan(@RequestBody AdPlanRequest request) throws AdException{
        log.info("ad-sponsor: updateAdPlan -> {}", JSON.toJSONString(request));
        return adPlanService.updateAdPlan(request);
    }

    /**
     * 删除adPlan
     * @param request
     * @throws AdException
     */
    @DeleteMapping("/delete/adPlan")
    public void deleteAdPlan(@RequestBody AdPlanRequest request) throws AdException{
        log.info("ad-sponsor: deleteAdPlan -> {}",JSON.toJSONString(request));
        adPlanService.deleteAdPlan(request);
    }



}
