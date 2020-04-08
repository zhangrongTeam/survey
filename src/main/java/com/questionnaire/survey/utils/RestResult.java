package com.questionnaire.survey.utils;

import com.questionnaire.survey.constant.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "RestResult", description = "Rest api请求结果统一封装对象")
public final class RestResult<T> {

    /**
     * 操作成功状态
     */
    public static final String OPT_SUCCESS_STATUS = "complete";

    /**
     * 操作失败状态
     */
    public static final String OPT_ERROR_STATUS = "error";

    /**
     * api执行结果状态：成功:complete 失败: error
     */
    @ApiModelProperty("api执行结果状态：成功:complete 失败: error")
    private String status;

    /**
     * 错误代码
     */
    @ApiModelProperty("错误代码")
    private String errorCode;

    /**
     * 错误信息
     */
    @ApiModelProperty("错误信息")
    private String errorMessage;

    /**
     * 返回结果数据
     */
    @ApiModelProperty("返回结果数据")
    private T resultData;

    public static <T> RestResult<T> success(T t) {
        return new RestResult<>("complete", null, null, t);
    }

    public static <T> RestResult<T> fail(String errorCode, String errorMessage) {
        return new RestResult<>("error", errorCode, errorMessage, null);
    }

    public static <T> RestResult<T> fail(ErrorCode errorCode) {
        return fail(errorCode.getCode(), errorCode.getMessage());
    }

    public static <T> RestResult<T> failList(ErrorCode errorCode, T t) {
        return new RestResult<>(errorCode.getCode(), errorCode.getMessage(), null, t);
    }

}
