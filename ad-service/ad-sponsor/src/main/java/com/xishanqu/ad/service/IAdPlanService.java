package com.xishanqu.ad.service;

import com.xishanqu.ad.entity.AdPlan;
import com.xishanqu.ad.exception.AdException;
import com.xishanqu.ad.vo.AdPlanGetRequest;
import com.xishanqu.ad.vo.AdPlanRequest;
import com.xishanqu.ad.vo.AdPlanResponse;

import java.util.List;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-13
 */
public interface IAdPlanService {

    /**
     * 创建推广计划
     * @param request
     * @return
     * @throws AdException
     */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 获取推广计划
     * @param request
     * @return
     * @throws AdException
     */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    /**
     * 更新推广计划
     * @param request
     * @return
     * @throws AdException
     */
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 删除推广计划
     * @param request
     * @throws AdException
     */
    void deleteAdPlan(AdPlanRequest request) throws AdException;

 }
