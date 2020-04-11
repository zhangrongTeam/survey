package com.questionnaire.survey.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 供水管网
 * </p>
 *
 * @author zhangrong123
 * @since 2020-04-02
 */
@TableName("water_supply_network")
@Data
@Accessors(chain = true)
public class WaterSupplyNetwork extends Model<WaterSupplyNetwork> {
    @TableId
    private int id;
    @TableField("survey_id")
    private String surveyId;
    /**
     * 材质
     */
    @TableField("line_material")
    private String lineMaterial;
    /**
     * 口径
     */
    @TableField("line_caliber")
    private String lineCaliber;

    @TableField("line_location")
    private String lineLocation;

    @TableField(exist = false)
    private String surveyUserName;
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    @TableField(exist = false)
    private String projectId;
    @TableField(exist = false)
    private String systemType;



    @Override
    public String toString() {
        return "WaterSupplyNetwork{" +
        ", id=" + id +
        ", surveyId=" + surveyId +
        ", lineMaterial=" + lineMaterial +
        ", lineCaliber=" + lineCaliber +
        "}";
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
