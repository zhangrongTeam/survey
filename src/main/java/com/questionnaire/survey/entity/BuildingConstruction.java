package com.questionnaire.survey.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * <p>
 * 楼宇建筑
 * </p>
 *
 * @author zhangrong123
 * @since 2020-04-02
 */
@TableName("building_construction")
@Data
@Accessors(chain = true)
public class BuildingConstruction extends Model<BuildingConstruction> {

    @TableId
    private int id;
    @TableField("survey_id")
    private String surveyId;
    /**
     * 对应数据字典项value 建筑物类型
     */
    @TableField("building_type")
    private String buildingType;
    /**
     * 对应数据字典项value 楼宇所属区域
     */
    @TableField("building_region")
    private String buildingRegion;
    /**
     * 楼宇名称
     */
    @TableField("building_name")
    private String buildingName;
    /**
     * 自動生成楼宇名称
     */
    @TableField("auto_building_name")
    private String autoBuildingName;
    /**
     * 楼层层数
     */
    @TableField("floor_number")
    private Integer floorNumber;
    /**
     * 洗手池数量
     */
    @TableField("number_of_sinks")
    private Integer numberOfSinks;
    /**
     * 拖把池数量
     */
    @TableField("number_of_mop_pools")
    private Integer numberOfMopPools;
    /**
     * 小便器数量
     */
    @TableField("number_of_urinal")
    private Integer numberOfUrinal;
    /**
     * 蹲便器数量
     */
    @TableField("number_of_squatting_pan")
    private Integer numberOfSquattingPan;
    /**
     * 座便器数量
     */
    @TableField("number_of_pedestal_pan")
    private Integer numberOfPedestalPan;
    /**
     * 淋浴喷头数量
     */
    @TableField("number_of_shower_nozzle")
    private Integer numberOfShowerNozzle;
    /**
     * 直饮水出水口数量
     */
    @TableField("number_of_drinking_water_outlet")
    private Integer numberOfDrinkingWaterOutlet;
    /**
     * 开水器出水口数量
     */
    @TableField("number_of_water_boiler_outlet")
    private Integer numberOfWaterBoilerOutlet;
    /**
     * 进水管径
     */
    @TableField("water_pipe_diameter")
    private Float waterPipeDiameter;
    /**
     * 日用水量
     */
    @TableField("daily_water")
    private Float dailyWater;

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
        return "BuildingConstruction{" +
        ", id=" + id +
        ", surveyId=" + surveyId +
        ", buildingType=" + buildingType +
        ", buildingName=" + buildingName +
        ", autoBuildingName=" + autoBuildingName +
        ", floorNumber=" + floorNumber +
        ", numberOfSinks=" + numberOfSinks +
        ", numberOfMopPools=" + numberOfMopPools +
        ", numberOfUrinal=" + numberOfUrinal +
        ", numberOfSquattingPan=" + numberOfSquattingPan +
        ", numberOfPedestalPan=" + numberOfPedestalPan +
        ", numberOfShowerNozzle=" + numberOfShowerNozzle +
        ", numberOfDrinkingWaterOutlet=" + numberOfDrinkingWaterOutlet +
        ", numberOfWaterBoilerOutlet=" + numberOfWaterBoilerOutlet +
        ", waterPipeDiameter=" + waterPipeDiameter +
        ", dailyWater=" + dailyWater +
        "}";
    }
}
