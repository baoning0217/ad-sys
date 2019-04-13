package com.xishanqu.ad.service.impl;

import com.xishanqu.ad.constant.CommonStatus;
import com.xishanqu.ad.constant.Constants;
import com.xishanqu.ad.dao.AdPlanRepository;
import com.xishanqu.ad.dao.AdUserRepository;
import com.xishanqu.ad.entity.AdPlan;
import com.xishanqu.ad.entity.AdUser;
import com.xishanqu.ad.exception.AdException;
import com.xishanqu.ad.service.IAdPlanService;
import com.xishanqu.ad.utils.CommonUtils;
import com.xishanqu.ad.vo.AdPlanGetRequest;
import com.xishanqu.ad.vo.AdPlanRequest;
import com.xishanqu.ad.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-13
 */
@Slf4j
@Service
public class AdPlanServiceImpl implements IAdPlanService {

    @Autowired
    private AdPlanRepository adPlanRepository;
    @Autowired
    private AdUserRepository adUserRepository;

    /**
     * 创建推广计划
     * @param request
     * @return
     * @throws AdException
     */
    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        if (!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        //确保关联User是否存在
        Optional<AdUser> adUser = adUserRepository.findById(request.getUserId());
        if (!adUser.isPresent()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        AdPlan oldPlan = adPlanRepository.findByUserIdAndPlanName(request.getUserId(),request.getPlanName());
        if (oldPlan != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PALN_ERROR);
        }
        AdPlan newPlan = adPlanRepository.save(
                new AdPlan(
                    request.getUserId(),
                    request.getPlanName(),
                    CommonUtils.parseStringDate(request.getStartDate()),
                    CommonUtils.parseStringDate(request.getEndDate())
                )
        );
        return new AdPlanResponse(newPlan.getId(),newPlan.getPlanName());
    }

    /**
     * 获取推广计划
     * @param request
     * @return
     * @throws AdException
     */
    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        if (!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        return adPlanRepository.findAllByIdInAndUserId(request.getIds(),request.getUserId());
    }

    /**
     * 更新推广计划
     * @param request
     * @return
     * @throws AdException
     */
    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if (!request.updateValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdPlan plan = adPlanRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (plan == null){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        if (request.getPlanName() != null){
            plan.setPlanName(request.getPlanName());
        }
        if (request.getStartDate() != null){
            plan.setStartDate(CommonUtils.parseStringDate(request.getStartDate()));
        }
        if (request.getEndDate() != null){
            plan.setEndDate(CommonUtils.parseStringDate(request.getEndDate()));
        }
        plan.setUpdateTime(new Date());
        //根据主键判断，存在就更新
        plan = adPlanRepository.save(plan);
        return new AdPlanResponse(plan.getId(),plan.getPlanName());
    }

    /**
     * 删除推广计划
     * @param request
     * @throws AdException
     */
    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if (!request.deleteValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdPlan plan = adPlanRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (plan == null){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());adPlanRepository.save(plan);
    }
}
