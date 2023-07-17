package com.ljt.dao;

import com.ljt.domain.PromotionAd;
import com.ljt.domain.PromotionSpace;

import java.util.List;

public interface PromotionAdMapper {
    public List<PromotionAd> findAllAdByPage();

    public void savePromotionAd(PromotionAd PromotionAd);

    /**
     * 修改广告位
     */
    public void updatePromotionAd(PromotionAd PromotionAd);

    public PromotionAd findPromotionAdById(int id);

    void updatePromotionAdStatus(PromotionAd promotionAd);
}