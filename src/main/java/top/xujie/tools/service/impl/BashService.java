package top.xujie.tools.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import top.xujie.tools.utils.FileResolveTemplate;
import top.xujie.tools.utils.LineFormatTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author jie.xu
 * @date 2018/11/2
 * @describe 文件处理类
 */
@Service
@Slf4j
public class BashService {

    /**
     * 写文件测试类
     *
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public void writeTest() throws InterruptedException, ExecutionException, IOException {
        String sourcePath = "文件路径";
        this.resolve(sourcePath, 5000, lines -> {
            for (String line : lines) {
                System.out.println(line);
            }
        });
    }


    /**
     * 写文件测试类
     *
     * @throws IOException
     */
    public void readTest() throws IOException {
        List<Integer> phones = Arrays.asList(
                130, 131, 132, 133, 134, 135,
                136, 137, 138, 139, 141, 145,
                146, 147, 149, 150, 151, 152,
                153, 155, 156, 157, 158, 159,
                166, 170, 171, 172, 173, 174,
                175, 176, 177, 178, 180, 181,
                182, 183, 184, 185, 186, 187,
                188, 189, 198, 199, 191, 162);
//        String path = "/mnt/d6/phone-sha256/phone-sha256.txt";

        String path = "C:\\tmp/phone-sha256.txt";
        for (Integer phone : phones) {
            String head = String.valueOf(phone);
            long start = Long.parseLong(head + "00000000");
            long end = Long.parseLong(head + "99999999");
            List<String> phoneStr = new ArrayList<>();
            for (long i = start; i <= end; i++) {
                phoneStr.add(String.valueOf(i));
                if (i % 5000 == 0 || i == end) {
                    this.write(path, phoneStr, DigestUtils::sha256Hex);
                    phoneStr.clear();
                }
            }
        }
    }

    /**
     * 读文件
     *
     * @param filePath            文件路径
     * @param batchSize           每次读取行数
     * @param fileResolveTemplate
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void resolve(String filePath, int batchSize, FileResolveTemplate fileResolveTemplate) throws IOException, ExecutionException, InterruptedException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)))) {
            int count = 0;
            String line;
            List<String> tmpList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                count++;
                tmpList.add(line);
                if (count % batchSize == 0) {
                    fileResolveTemplate.resolve(tmpList);
                    tmpList.clear();
                }
            }
            if (tmpList.size() > 0) {
                fileResolveTemplate.resolve(tmpList);
            }
        }

    }


    /**
     * 写文件
     *
     * @param filePath           文件路径
     * @param lines              每次写文件行数
     * @param lineFormatTemplate
     * @throws IOException
     */
    public void write(String filePath, List<String> lines, LineFormatTemplate lineFormatTemplate) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filePath), true))) {
            int count = 0;
            for (String line : lines) {
                String result = lineFormatTemplate.format(line);
                if (result == null) {
                    continue;
                }
                count++;
                bufferedWriter.write(result);
                if (count % 5000 == 0) {
                    bufferedWriter.flush();
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        }
    }


    public void write(String filePath, List<String> lines) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filePath), true))) {
            int count = 0;
            for (String line : lines) {
                if (line == null) {
                    continue;
                }
                count++;
                bufferedWriter.write(line);
                if (count % 5000 == 0) {
                    bufferedWriter.flush();
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        }
    }


}
