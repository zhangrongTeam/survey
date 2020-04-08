package com.questionnaire.survey.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author autoCode
 * @version 2017-10-27 14:22
 */
@Data
@ApiModel
public class SysDictTypeDTO {

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("字典类型名称")
    @NotNull
    @Size(min = 1, max = 50, message = "字典名称长度必须在1-32个字符之间")
    private String dictTypeName;

    @ApiModelProperty("字典类型Code")
    @NotNull
    @Size(min = 1, max = 32, message = "字典类型CODE长度必须在1-32个字符之间")
    private String dictTypeCode;

    @ApiModelProperty("备注")
    @Size(min = 1, max = 200, message = "备注长度必须在1-32个字符之间")
    private String dictTypeRemarks;

    @ApiModelProperty("排序")
    private String sort;
}