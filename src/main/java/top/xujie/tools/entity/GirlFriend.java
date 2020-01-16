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
public class GirlFriend {

    private String name;
    private Integer age;
    private String hairColor;
    private String height;
    private String weight;
    private String hobby;
    private String birthday;
    private String address;
    private String email;
    private String gift;
    private String phone;


}
