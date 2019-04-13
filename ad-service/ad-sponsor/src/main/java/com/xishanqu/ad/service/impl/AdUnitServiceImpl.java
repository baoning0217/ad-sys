package com.xishanqu.ad.service.impl;

import com.xishanqu.ad.constant.Constants;
import com.xishanqu.ad.dao.AdPlanRepository;
import com.xishanqu.ad.dao.AdUnitRepository;
import com.xishanqu.ad.dao.CreativeRepository;
import com.xishanqu.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.xishanqu.ad.dao.unit_condition.AdUnitItRepository;
import com.xishanqu.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.xishanqu.ad.dao.unit_condition.CreativeUnitRepository;
import com.xishanqu.ad.entity.AdPlan;
import com.xishanqu.ad.entity.AdUnit;
import com.xishanqu.ad.entity.unit_condition.AdUnitDistrict;
import com.xishanqu.ad.entity.unit_condition.AdUnitIt;
import com.xishanqu.ad.entity.unit_condition.AdUnitKeyword;
import com.xishanqu.ad.entity.unit_condition.CreativeUnit;
import com.xishanqu.ad.exception.AdException;
import com.xishanqu.ad.service.IAdUnitService;
import com.xishanqu.ad.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-13
 */
@Slf4j
@Service
public class AdUnitServiceImpl implements IAdUnitService {

    @Autowired
    private AdPlanRepository adPlanRepository;
    @Autowired
    private AdUnitRepository adUnitRepository;
    @Autowired
    private AdUnitKeywordRepository adUnitKeywordRepository;
    @Autowired
    private AdUnitDistrictRepository adUnitDistrictRepository;
    @Autowired
    private AdUnitItRepository adUnitItRepository;
    @Autowired
    private CreativeUnitRepository creativeUnitRepository;
    @Autowired
    private CreativeRepository creativeRepository;


    /**
     * 创建推广单元
     * @param request
     * @return
     * @throws AdException
     */
    @Override
    @Transactional
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {
        if (!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        Optional<AdPlan> adPlan = adPlanRepository.findById(request.getPlanId());
        if (!adPlan.isPresent()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        AdUnit oldUnit = adUnitRepository.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());
        if (oldUnit != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROE);
        }
        AdUnit newUnit = adUnitRepository.save(
                new AdUnit(
                        request.getPlanId(),
                        request.getUnitName(),
                        request.getPositionType(),
                        request.getBudget()
                )
        );
        return new AdUnitResponse(newUnit.getId(), newUnit.getUnitName());
    }

    /**
     * 推广单元-关键字限制
     * @param request
     * @return
     * @throws AdException
     */
    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {
        List<Long> unitIds = request.getUnitKeywords()
                                .stream()
                                .map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<Long> ids = Collections.emptyList();
        List<AdUnitKeyword> unitKeywords = new ArrayList<>();
        if (!CollectionUtils.isEmpty(request.getUnitKeywords())){
            request.getUnitKeywords().forEach(i -> unitKeywords.add(new AdUnitKeyword(i.getUnitId(), i.getKeyword())));
            ids = adUnitKeywordRepository.saveAll(unitKeywords)
                    .stream()
                    .map(AdUnitKeyword::getId)
                    .collect(Collectors.toList());
        }
        return new AdUnitKeywordResponse(ids);
    }

    /**
     * 推广单元-兴趣限制
     * @param request
     * @return
     * @throws AdException
     */
    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {
        List<Long> unitIds = request.getUnitIts()
                .stream()
                .map(AdUnitItRequest.UnitIt::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitIt> unitIts = new ArrayList<>();
        request.getUnitIts().forEach(i -> unitIts.add(new AdUnitIt(i.getUnitId(),i.getItTag())));
        List<Long> ids = adUnitItRepository.saveAll(unitIts)
                            .stream()
                            .map(AdUnitIt::getId)
                            .collect(Collectors.toList());
        return new AdUnitItResponse(ids);
    }

    /**
     * 推广单元-区域限制
     * @param request
     * @return
     * @throws AdException
     */
    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        List<Long> unitIds = request.getUnitDistricts()
                                .stream()
                                .map(AdUnitDistrictRequest.UnitDistrict::getUnitId)
                                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitDistrict> unitDistricts = new ArrayList<>();
        request.getUnitDistricts().forEach(i -> unitDistricts.add(new AdUnitDistrict(i.getUnitId(),i.getProvince(),i.getCity())));
        List<Long> ids = adUnitDistrictRepository.saveAll(unitDistricts)
                                .stream()
                                .map(AdUnitDistrict::getId)
                                .collect(Collectors.toList());
        return new AdUnitDistrictResponse(ids);
    }

    /**
     * 推广单元-创意
     * @param request
     * @return
     * @throws AdException
     */
    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {
        List<Long> unitIds = request.getUnitItems()
                .stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getUnitId)
                .collect(Collectors.toList());
        List<Long> creativeIds = request.getUnitItems()
                .stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getCreativeId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds) && !isRelatedCreativeExist(creativeIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<CreativeUnit> creativeUnits = new ArrayList<>();
        request.getUnitItems().forEach(i -> creativeUnits.add(new CreativeUnit(i.getCreativeId(),i.getUnitId())));
        List<Long> ids = creativeUnitRepository.saveAll(creativeUnits)
                .stream()
                .map(CreativeUnit::getId)
                .collect(Collectors.toList());
        return new CreativeUnitResponse(ids);
    }


    private boolean isRelatedCreativeExist(List<Long> creativeIds){
        if (CollectionUtils.isEmpty(creativeIds)){
            return false;
        }
        return creativeRepository.findAllById(creativeIds).size() == new HashSet<>(creativeIds).size();
    }


    //判断是否存在
    private boolean isRelatedUnitExist(List<Long> unitIds){
        if (CollectionUtils.isEmpty(unitIds)){
            return false;
        }
        return adUnitRepository.findAllById(unitIds).size() == new HashSet<>(unitIds).size();
    }


}
