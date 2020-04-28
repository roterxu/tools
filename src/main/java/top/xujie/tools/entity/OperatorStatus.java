package top.xujie.tools.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xujie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperatorStatus {
    private Integer id;
    private String operator;
    private String describ;
    private String code;
    private String propos;
}
