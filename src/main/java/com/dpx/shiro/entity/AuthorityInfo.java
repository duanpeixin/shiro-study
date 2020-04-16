package com.dpx.shiro.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author dpx
 * @since 2020-04-16
 */
@ApiModel(value="AuthorityInfo对象", description="")
@Data
public class AuthorityInfo implements Serializable {

private static final long serialVersionUID=1L;

    public AuthorityInfo(Integer id, String authorityName) {
        this.id = id;
        this.authorityName = authorityName;
    }

    public AuthorityInfo() {
    }

    private Integer id;

    @ApiModelProperty(value = "权限名")
    private String authorityName;
}
