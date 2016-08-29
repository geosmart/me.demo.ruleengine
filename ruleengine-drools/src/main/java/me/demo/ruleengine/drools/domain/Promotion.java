package me.demo.ruleengine.drools.domain;

/**
 * Created by Think on 2016/8/24.
 */
public class Promotion {
    private String promotionName;

    private long point;

    private boolean inPromotion;

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    public boolean isInPromotion() {
        promotionName = "labor's day!";
        point = 99;
        return true;

    }

    public void setInPromotion(boolean inPromotion) {
        this.inPromotion = inPromotion;
    }

}
