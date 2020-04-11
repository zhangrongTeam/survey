package com.questionnaire.survey.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Auther: zhangrong
 * @Date: 2019/3/13 14:57
 * @Description:
 */
@Data
@ApiModel(description = "用户登录请求参数")
public class LoginDTO implements Serializable {

    @ApiModelProperty("账号")
    @NotNull(message = "账号不能为空")
    @Size(min = 3, max = 20, message = "账号必须在3到20位字符之间")
    private String loginName;

    @ApiModelProperty("用户登录密码")
    @NotNull(message = "密码不能为空")
    @Size(min = 3, max = 20, message = "密码必须在3到20位字符之间")
    private String password;

}

