package com.xishanqu.ad.client;

import com.xishanqu.ad.client.vo.AdPlan;
import com.xishanqu.ad.client.vo.AdPlanGetRequest;
import com.xishanqu.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-13
 */
@Component
public class SponsorClientHystrix implements SponsorClient{

    @Override
    public CommonResponse<List<AdPlan>> getAdPlans(AdPlanGetRequest request) {
        return new CommonResponse<>(-1, "eureka-client-ad-sponsor error");
    }

}
