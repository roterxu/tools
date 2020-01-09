package top.xujie.tools.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xujie
 */
@Data
@NoArgsConstructor
public class Result<T> {
    private String massage;
    private Object data;
    private String code;
}
