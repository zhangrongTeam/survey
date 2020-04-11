package com.questionnaire.survey.entity;

import java.io.Serializable;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

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
public class Project extends Model<Project> {

    /**
     * 主键id
     */
    @TableId
    private String id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 删除标记
     */
    @TableField("del_flag")
    private boolean delFlag;
    /**
     * 项目状态(已停止未删除：0，在进行：1——未删除时还可出现在列表)
     */
    @TableField("project_status")
    private String projectStatus;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Project{" +
        ", id=" + id +
        ", name=" + name +
        ", delFlag=" + delFlag +
        ", projectStatus=" + projectStatus +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
