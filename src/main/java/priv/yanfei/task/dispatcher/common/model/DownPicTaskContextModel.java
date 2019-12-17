package priv.yanfei.task.dispatcher.common.model;

import cn.hutool.json.JSONObject;

public class DownPicTaskContextModel {


    private String picUrl;

    private String picTitle;

    private String picPackTitle;

    private String picType;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicTitle() {
        return picTitle;
    }

    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
    }

    public String getPicPackTitle() {
        return picPackTitle;
    }

    public void setPicPackTitle(String picPackTitle) {
        this.picPackTitle = picPackTitle;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }
}
