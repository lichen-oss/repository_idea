package com.ljt.service;

import com.github.pagehelper.PageInfo;
import com.ljt.domain.PromotionAd;
import com.ljt.domain.PromotionAdVo;
import com.ljt.domain.ResponseResult;
import org.springframework.web.bind.annotation.RequestParam;

public interface PromotionAdService {

    public PageInfo findAllAdByPage(PromotionAdVo adVo);

    public void savePromotionAd(PromotionAd promotionAd);

    public void updatePromotionAd(PromotionAd promotionAd);

    public PromotionAd findPromotionAdById(int id);

    void updatePromotionAdStatus(int id, int status);


}
