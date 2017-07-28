package cn.duozhuan.aboutlambda.bean;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class MeiZiBean {
    private String imageUrl;
    private String describtion;

    public MeiZiBean() {
    }

    public MeiZiBean(String imageUrl, String describtion) {
        this.imageUrl = imageUrl;
        this.describtion = describtion;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }
}
