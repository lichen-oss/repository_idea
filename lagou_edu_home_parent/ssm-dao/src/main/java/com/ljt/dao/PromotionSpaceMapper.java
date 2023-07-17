package com.ljt.dao;

import com.ljt.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {
    public List<PromotionSpace> findAllPromotionSpace();

    /*
添加广告位
*/
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 修改广告位
     */

    public void updatePromotionSpace(PromotionSpace promotionSpace);

    public PromotionSpace findPromotionSpaceById(int id);
}
