package top.xujie.tools.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import top.xujie.tools.service.impl.BashService;

import java.io.File;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xujie
 */
@Slf4j
public class Dev {

    public static void main(String[] args) throws Exception {

        String path = "C:\\tmp\\scj";
        List fileList = getFileList(path);

        for (Object obj : fileList) {

        }

    }

    public static List getFileList(String path) {
        List list = new ArrayList();
        try {
            File file = new File(path);
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                list.add(path + "\\" + filelist[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
