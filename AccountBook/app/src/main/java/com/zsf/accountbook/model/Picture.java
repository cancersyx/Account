package com.zsf.accountbook.model;

/**
 * Created by zsf
 * 2017/9/20
 * describe:
 */

public class Picture {
    private int imageId;
    private String desc;

    public Picture(int imageId, String desc) {
        this.imageId = imageId;
        this.desc = desc;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
