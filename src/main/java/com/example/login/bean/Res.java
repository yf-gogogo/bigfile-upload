package com.example.login.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class Res {
    private Integer rId;

    private String rMd5;

    private String rPath;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getrMd5() {
        return rMd5;
    }

    public void setrMd5(String rMd5) {
        this.rMd5 = rMd5 == null ? null : rMd5.trim();
    }

    public String getrPath() {
        return rPath;
    }

    public void setrPath(String rPath) {
        this.rPath = rPath == null ? null : rPath.trim();
    }
}