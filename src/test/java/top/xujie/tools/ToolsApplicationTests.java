package top.xujie.tools;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import top.xujie.tools.entity.Employee;
import top.xujie.tools.entity.Guishudi;
import top.xujie.tools.entity.OperatorStatus;
import top.xujie.tools.mapper.OperatorStatusMapper;
import top.xujie.tools.service.impl.BashService;
import top.xujie.tools.service.impl.OperatorStatusService;
import top.xujie.tools.utils.HttpClientsWebUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class ToolsApplicationTests {


    @Resource
    private BashService bashService;

    @Resource
    private OperatorStatusService operatorStatusService;

    @Resource
    OperatorStatusMapper operatorStatusMapper;

    @Test
    public void updatefile() throws InterruptedException, ExecutionException, IOException {
        String path = "C:\\tmp\\scj";
        List fileList = getFileList(path);
        String resultPath = "c:/tmp/result.txt";
        for (Object obj : fileList) {
            bashService.resolve(path, 5000, lines -> {
                bashService.write(resultPath, lines);
            });
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

    @Test
    public void file() throws InterruptedException, ExecutionException, IOException {
        String path = "C:\\tmp/5w.txt";
        String resultPath = "C:\\tmp/result.txt";
        List<String> phoneList = new ArrayList<>();
        bashService.resolve(path, 100000, lines -> {
            for (String line : lines) {
                if (!line.contains("中国移动")) {
                    phoneList.add(line.split("\t")[0]);
                }
            }
            bashService.write(resultPath, phoneList);
        });
    }

    @Test
    public void guishudi() throws Exception {
        String head7 = "1736666";
        String s = "1701734,1701733,1701724,1701722,1701719,1701717,1701714,1701712,1701711,1701700,1701699,1701688";
        String[] split1 = s.split(",");
        for (String head : split1) {
            String url = "https://www.ip138.com/mobile.asp";
            Map<String, String> map = new HashMap<>();
            map.put("mobile", head);
            map.put("action", "mobile");
            String result = HttpClientsWebUtils.doGet(url, map);

            Document document = Jsoup.parse(result);

            Elements elements = document.select("div[class=table]").select("tr");
//        System.out.println(element);

            Guishudi gui = new Guishudi();
            gui.setMobile7(head);
            String elementOperator = elements.get(4).select("td").select("span").get(0).toString();
            String operatorStr = elementOperator.substring(6, elementOperator.length() - 7);

            String operator;
            if (operatorStr.contains("移动")) {
                operator = "中国移动";
            } else if (operatorStr.contains("联通")) {
                operator = "中国联通";
            } else if (operatorStr.contains("电信")) {
                operator = "中国联通";
            } else {
                operator = "";
            }
            gui.setOperater(operator);


            String elementGuishudi = elements.get(3).select("td").select("span").get(0).toString();
            String guishudiStr = elementGuishudi.substring(6, elementGuishudi.length() - 7);

            String[] split = guishudiStr.split("&nbsp;");
            if (split.length != 0) {
                if (split.length == 1) {
                    gui.setMobilePro(split[0]);
                    gui.setMobileCity(split[0].replaceAll("市", ""));
                } else if (split.length == 2) {
                    gui.setMobilePro(split[0]);
                    gui.setMobileCity(split[1].replaceAll("市", ""));
                }
            }

            System.out.println(gui);
        }

    }
}
