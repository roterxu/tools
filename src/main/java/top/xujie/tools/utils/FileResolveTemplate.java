package top.xujie.tools.utils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author jie.xu
 * @date 2018/11/2
 */
public interface FileResolveTemplate {
    /**
     * 批量处理
     */
    void resolve(List<String> lines) throws ExecutionException, InterruptedException, IOException;
}
