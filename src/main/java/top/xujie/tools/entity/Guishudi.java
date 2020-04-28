package top.xujie.tools.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xujie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guishudi implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private String mobile7;

    /**
     *
     */
    private String mobilePro;

    /**
     *
     */
    private String mobileCity;

    /**
     *
     */
    private String operater;

    /**
     *
     */
    private String areaCode;

    /**
     *
     */
    private String postCode;
    /**
     *
     */
    private Date gmtModified;


}
