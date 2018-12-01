package com.example.login;

import com.example.login.bean.Res;
import com.example.login.bean.ResTmp;
import com.example.login.mapper.ResMapper;
import com.example.login.mapper.ResTmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApplicationTests {
    @Autowired(required = false)
    ResMapper resMapper;
    @Autowired(required = false)
    ResTmpMapper resTmpMapper;
    @Test
    public void contextLoads() {

//        Res res = new Res();
//        res.setrId(1);
//        res.setrMd5("1231231");
//        res.setrPath("kkksk");
//        resMapper.insert(res);
//        res = resMapper.selectByMD5("1231231");
//        System.out.println(res.getrId());
        ResTmp resTmp = new ResTmp();
        resTmp.setuId(1);
        resTmp.setTmpMd5("sd");
        resTmp.setTmpPath("/");
        resTmp.setTmpTitle("hahah");
        resTmpMapper.insert(resTmp);
    }
    @Test
    public void testFile() {
        File dir = new File("D:\\game\\cf\\");
        if(dir.exists()){
            File[] files = dir.listFiles();
            List fileList = Arrays.asList(files);
            Collections.sort(fileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (o1.isDirectory() && o2.isFile())
                        return -1;
                    if (o1.isFile() && o2.isDirectory())
                        return 1;
                    return o1.getName().compareTo(o2.getName());
                }
            });
            for (File file:files) {
                System.out.println(file.getName());
                try {
                    //读取小文件的输入流
                    InputStream in = new FileInputStream(file);
                    //写入大文件的输出流
                    File file2 = new File("D:\\game\\cf\\CN103180850A.caj");
                    if(file2.exists()) file2.delete();
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
            }
            dir.delete();
        }
    }
}
