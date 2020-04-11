package com.questionnaire.survey.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 水表统计
 * </p>
 *
 * @author zhangrong123
 * @since 2020-04-02
 */
@TableName("water_meter")
@Data
public class WaterMeter extends Model<WaterMeter> {

    @TableId
    private Long id;
    @TableField("survey_id")
    private String surveyId;
    /**
     * 水表类别，对应数据字典项目id
     */
    @TableField("water_meter_category")
    private String waterMeterCategory;
    /**
     * 水表口径，对应数据字典项目id
     */
    @TableField("water_meter_caliber")
    private String waterMeterCaliber;
    /**
     * 水表材质
     */
    @TableField("water_meter_material")
    private String waterMeterMaterial;
    /**
     * 是否能上传
     */
    @TableField("can_i_upload_data")
    private Integer canIUploadData;
    /**
     * 水表位置
     */
    @TableField("water_meter_location")
    private String waterMeterLocation;

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
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WaterMeter{" +
        ", id=" + id +
        ", surveyId=" + surveyId +
        ", waterMeterCategory=" + waterMeterCategory +
        ", waterMeterCaliber=" + waterMeterCaliber +
        ", waterMeterMaterial=" + waterMeterMaterial +
        ", canIUploadData=" + canIUploadData +
        ", waterMeterLocation=" + waterMeterLocation +
        "}";
    }
}
