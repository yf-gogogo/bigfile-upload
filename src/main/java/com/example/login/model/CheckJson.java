package com.example.login.model;

import java.util.ArrayList;
import java.util.List;

public class CheckJson {
    private List<String> block_info;
    private boolean exist;

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public List<String> getBlock_info() {
        return block_info;
    }

    public void setBlock_info(List<String> block_info) {
        this.block_info = block_info;
    }
}
