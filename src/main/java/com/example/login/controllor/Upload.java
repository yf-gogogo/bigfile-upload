package com.example.login.controllor;

import com.example.login.bean.Res;
import com.example.login.bean.ResMark;
import com.example.login.bean.ResTmp;
import com.example.login.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@CrossOrigin
public class Upload {

    @Autowired
    UploadService uploadService;

    private final String UPLOAD_PATH = "D:/game/cf/";

    @RequestMapping(value = "/check",method=RequestMethod.POST)
   public Map<String, Object> check(@RequestParam("fileMD5") String md5){
        System.out.println(md5);
        Map<String, Object> map = new HashMap<String, Object>();

        Res res = uploadService.findMD5(md5);
        if(res != null) {
            map.put("msg","已存在");
            map.put("exist",true);
        }else {
            map.put("msg","未存在");
            map.put("exist",false);
        }
        return map;
   }
    //上传，当为单个文件时，fileMD5=chunkMD5
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
   public Map<String, String> upload(@RequestParam("file") MultipartFile file,
                                     @RequestParam(value = "chunks",required = false) int chunks,
                                     @RequestParam(value = "chunk") int chunk,
                                     @RequestParam(value = "name") String name,
                                     @RequestParam(value = "chunkMD5") String md5){
        if(chunks == 1){
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_PATH + file.getOriginalFilename());
                Files.write(path,bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //插入数据库标注表res_mark数据
            ResMark resMark = new ResMark();
            resMark.setuId(1);
            resMark.setMarkTitle(name);
            resMark.setrMd5(md5);
            resMark.setrStatus("uploaded");
            System.out.println(uploadService.uploadVideo(resMark));
            //插入res表数据
            Res res = new Res();
            res.setrMd5(md5);
            res.setrPath(UPLOAD_PATH + file.getOriginalFilename());
            System.out.println(uploadService.addRes(res));
        }else {
            File dir = new File(UPLOAD_PATH + name +".tmp");
            if (!dir.exists()) {//如果文件夹不存在
                dir.mkdir();//创建文件夹
            }
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_PATH + name +".tmp/" + chunk);
                Files.write(path,bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //插入数据库分片表记录，用于断点续传时读取
            ResTmp resTmp = new ResTmp();
            resTmp.setuId(1);
            resTmp.setTmpMd5(md5);
            resTmp.setTmpPath(UPLOAD_PATH + name +".tmp/" + chunk);
            resTmp.setTmpTitle( Integer.toString(chunk));
            uploadService.uploadVideoChunk(resTmp);
        }

        Map<String,String> map = new HashMap<String, String>();

        if(chunks>1){
            map.put("msg","分片上传完成");
        }else{
            map.put("msg","文件上传完成");
        }

        return map;
   }
   //合并文件
    @RequestMapping(value = "/merge",method = RequestMethod.POST)
    public Map<String, String> merge(@RequestParam("fileMD5") String md5, @RequestParam("fileName") String fileName){
        System.out.println(md5+" "+fileName);
        File dir = new File(UPLOAD_PATH+"/"+fileName+".tmp");
        if(dir.exists()){
            File[] files = dir.listFiles();
            List<File> fileList = Arrays.asList(files);
            //按时间排序
            Collections.sort(fileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (o1.isDirectory() && o2.isFile())
                        return -1;
                    if (o1.isFile() && o2.isDirectory())
                        return 1;
                    return Integer.parseInt(o1.getName())-Integer.parseInt(o2.getName());
//                    return o1.getName().compareTo(o2.getName());
                }
            });

            for (File file:fileList) {
                System.out.println(file.getAbsolutePath());
                try {
                    //读取小文件的输入流
                    InputStream in = new FileInputStream(file);
                    //写入大文件的输出流
                    File file2 = new File(UPLOAD_PATH+"/"+fileName);
                    OutputStream out = new FileOutputStream(file2,true);
                    int len = -1;
                    byte[] bytes = new byte[1*1024*1024];
                    while((len = in.read(bytes))!=-1) {
                        out.write(bytes, 0, len);
                    }
                    out.close();
                    in.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                file.delete();
                uploadService.removeResTmp(file.getName());
            }
            dir.delete();
        }
        //插入数据库标注表res_mark数据
        ResMark resMark = new ResMark();
        resMark.setuId(1);
        resMark.setMarkTitle(fileName);
        resMark.setrMd5(md5);
        resMark.setrStatus("uploaded");
        uploadService.uploadVideo(resMark);
        //插入res表数据
        Res res = new Res();
        res.setrMd5(md5);
        res.setrPath(UPLOAD_PATH + fileName);
        uploadService.addRes(res);

        Map<String,String> map = new HashMap<String, String>();

        map.put("msg","合并分片完成");

        return map;
    }
}
