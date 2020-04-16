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
@ApiModel(value="UserInfo对象", description="")
@Data
public class UserInfo implements Serializable {

private static final long serialVersionUID=1L;

    public UserInfo(Integer id, String userName, String password, List<RoleInfo> roleInfoList) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roleInfoList = roleInfoList;
    }

    public UserInfo() {
    }

    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "角色集合")
    private List<RoleInfo> roleInfoList;

}
