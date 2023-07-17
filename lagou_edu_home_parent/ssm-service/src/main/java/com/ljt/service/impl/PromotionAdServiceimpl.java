package com.ljt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljt.dao.PromotionAdMapper;
import com.ljt.dao.PromotionSpaceMapper;
import com.ljt.domain.PromotionAd;
import com.ljt.domain.PromotionAdVo;
import com.ljt.domain.PromotionSpace;
import com.ljt.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionAdServiceimpl implements PromotionAdService {
    @Autowired
    private PromotionAdMapper promotionAdMapper;
    @Override
    public PageInfo findAllAdByPage(PromotionAdVo adVo) {
        PageHelper.startPage(adVo.getCurrentPage(),adVo.getPageSize());
        List<PromotionAd> allAdByPage = promotionAdMapper.findAllAdByPage();
        PageInfo<PromotionAd> promotionAdPageInfo = new PageInfo<>(allAdByPage);
        return promotionAdPageInfo;

    }
    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        promotionAd.setCreateTime(new Date());
        promotionAd.setUpdateTime(new Date());
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        promotionAd.setUpdateTime(new Date());
        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    @Override
    public PromotionAd findPromotionAdById(int id) {
        PromotionAd promotionAd = promotionAdMapper.findPromotionAdById(id);
        return promotionAd;
    }

    @Override
    public void updatePromotionAdStatus(int id, int status) {
        PromotionAd promotionAd = new PromotionAd();promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

        promotionAdMapper.updatePromotionAdStatus(promotionAd);

    }
}
