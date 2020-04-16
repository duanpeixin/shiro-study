package com.dpx.shiro.entity;

import java.io.Serializable;
import java.util.List;

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
@ApiModel(value="RoleInfo对象", description="")
@Data
public class RoleInfo implements Serializable {

private static final long serialVersionUID=1L;

    public RoleInfo(Integer id, String roleName, List<AuthorityInfo> authorityInfoList) {
        this.id = id;
        this.roleName = roleName;
        this.authorityInfoList = authorityInfoList;
    }

    public RoleInfo() {
    }

    private Integer id;

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty(value = "角色所拥有的权限")
    private List<AuthorityInfo> authorityInfoList;
}
