package com.questionnaire.survey.entity;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangrong123
 * @since 2020-04-01
 */
@Data
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 登录名
     */
    @TableField("login_name")
    @NotBlank(message = "登录名不能为空")
    private String loginName;
    /**
     * 实际姓名
     */
    @TableField("user_name")
    @NotBlank(message = "用户名不能为空")
    private String userName;
    /**
     * 密码
     */
    @TableField("password")
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * 电话
     */
    @TableField("phone")
    @NotBlank(message = "联系方式不能为空")
    @Size(min = 11, max = 11, message = "号码输入不正确")
    private String phone;
    /**
     * 用户类型[0:super,1:调研人员]
     */
    @TableField("user_type")
    private String userType;
    /**
     * 登陆ip
     */
    @TableField("login_ip")
    private String loginIp;
    /**
     * 登陆时间
     */
    @TableField("login_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime loginDate;
    /**
     * 微信使用字段
     */
    @TableField("open_id")
    private String openId;
    /**
     * 微信使用字段
     */
    @TableField("union_id")
    private String unionId;

    @TableField("department")
    @NotBlank(message = "部门不能为空")
    private String department;

//    @TableField(exist = false)
//    private String departmentName;

    @TableField(exist = false)
    private String code;
    @TableField(exist = false)
    private String token;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        ", id=" + id +
        ", loginName=" + loginName +
        ", userName=" + userName +
        ", password=" + password +
        ", phone=" + phone +
        ", userType=" + userType +
        ", loginIp=" + loginIp +
        ", loginDate=" + loginDate +
        ", openId=" + openId +
        ", unionId=" + unionId +
        "}";
    }
}
