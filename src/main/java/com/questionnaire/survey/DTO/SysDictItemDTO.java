package com.questionnaire.survey.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author zhangrong
 * @since 2018/7/27 16:29
 */
@Data
@ApiModel
public class SysDictItemDTO {

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("数据值")
    @NotNull
    @Size(min = 1,max = 100)
    private String value;

    @ApiModelProperty("标签名")
    @NotNull
    @Size(min = 1,max = 100)
    private String label;

    @ApiModelProperty("数据字典类型id")
    @NotNull
    private String dictTypeId;

    @ApiModelProperty("排序（升序）")
    @NotNull
    @Min(0)
    private int sort;

    @ApiModelProperty("备注信息")
    @Size(max = 255)
    private String remarks;


}
