package com.example.login.bean;

public class ResMark {
    private Integer markId;

    private Integer uId;

    private String markTitle;

    private String rMd5;

    private String rStatus;

    public Integer getMarkId() {
        return markId;
    }

    public void setMarkId(Integer markId) {
        this.markId = markId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getMarkTitle() {
        return markTitle;
    }

    public void setMarkTitle(String markTitle) {
        this.markTitle = markTitle == null ? null : markTitle.trim();
    }

    public String getrMd5() {
        return rMd5;
    }

    public void setrMd5(String rMd5) {
        this.rMd5 = rMd5 == null ? null : rMd5.trim();
    }

    public String getrStatus() {
        return rStatus;
    }

    public void setrStatus(String rStatus) {
        this.rStatus = rStatus == null ? null : rStatus.trim();
    }
}