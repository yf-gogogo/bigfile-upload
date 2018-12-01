package com.example.login.bean;

public class ResTmp {
    private Integer tmpId;

    private Integer uId;

    private String tmpTitle;

    private String tmpMd5;

    private String tmpPath;

    public Integer getTmpId() {
        return tmpId;
    }

    public void setTmpId(Integer tmpId) {
        this.tmpId = tmpId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getTmpTitle() {
        return tmpTitle;
    }

    public void setTmpTitle(String tmpTitle) {
        this.tmpTitle = tmpTitle == null ? null : tmpTitle.trim();
    }

    public String getTmpMd5() {
        return tmpMd5;
    }

    public void setTmpMd5(String tmpMd5) {
        this.tmpMd5 = tmpMd5 == null ? null : tmpMd5.trim();
    }

    public String getTmpPath() {
        return tmpPath;
    }

    public void setTmpPath(String tmpPath) {
        this.tmpPath = tmpPath == null ? null : tmpPath.trim();
    }
}