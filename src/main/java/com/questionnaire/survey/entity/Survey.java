package com.questionnaire.survey.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;


import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangrong123
 * @since 2020-03-31
 */
@Data
@Accessors(chain = true)
public class Survey extends Model<Survey> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 调研人员id
     */
    @TableField("survey_user_id")
    private String surveyUserId;
    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
    /**
     * 项目id
     */
    @TableField("project_id")
    private String projectId;

    @TableField("system_type")
    private String systemType;

    @TableField("del_flag")
    private boolean delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Survey{" +
        ", id=" + id +
        ", surveyUserId=" + surveyUserId +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", projectId=" + projectId +
        "}";
    }
}
