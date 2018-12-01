package com.example.login.service;

import com.example.login.bean.Res;
import com.example.login.bean.ResMark;
import com.example.login.bean.ResTmp;
import com.example.login.mapper.ResMapper;
import com.example.login.mapper.ResMarkMapper;
import com.example.login.mapper.ResTmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadService {

    @Autowired(required = false)
    private ResMarkMapper resMarkMapper;
    @Autowired(required = false)
    private ResMapper resMapper;
    @Autowired(required = false)
    private ResTmpMapper resTmpMapper;

    //上传分片
    public  int uploadVideoChunk(ResTmp resTmp){
        return resTmpMapper.insert(resTmp);
    }
    //上传完整文件
    public int uploadVideo(ResMark resMark){

        return resMarkMapper.insert(resMark);

    }
    //添加到res数据表一条记录
    public int addRes(Res res){
        return resMapper.insert(res);
    }

    //查找是否存在文件MD5
    public Res findMD5(String MD5){
        return resMapper.selectByMD5(MD5);
    }
    //删除缓存记录
    public int removeResTmp(String tmpTitle){
        return resTmpMapper.deleteByMD5(tmpTitle);
    }
}
