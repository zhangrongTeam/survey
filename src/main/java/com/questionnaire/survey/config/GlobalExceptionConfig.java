package com.questionnaire.survey.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.questionnaire.survey.utils.RestResult;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

import static com.questionnaire.survey.constant.ErrorCode.*;
import static com.questionnaire.survey.utils.RestResult.fail;


/**
 * @author zhangrong
 * @since 2018/7/27 8:46
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionConfig {

    /**
     * 字符串分隔符
     */
    private final String STR_SEPARATOR = ",";

    private void printException(Exception e) {
        log.error("异常捕获", e);
    }

    /**
     * 捕获校验异常
     * @param e Java Validator产生的异常信息
     * @return 返回校验异常信息对象
     * @see MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResult<String> handleValidateException(MethodArgumentNotValidException e) {
        printException(e);

        List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();

        StringBuilder stringBuilder = new StringBuilder();

        fieldErrorList.forEach(fieldError -> {
            stringBuilder.append(fieldError.getDefaultMessage());
            stringBuilder.append(STR_SEPARATOR);
        });

        // 去除多余的末尾逗号
        if (stringBuilder.lastIndexOf(STR_SEPARATOR) == stringBuilder.length() - 1) {
            stringBuilder.delete(stringBuilder.lastIndexOf(STR_SEPARATOR), stringBuilder.length());
        }

        return fail(EXCEPTION_ILLEGAL_ARGUMENT.getCode(), stringBuilder.toString());
    }

    /**
     * 捕获JWT超时异常
     * @param e JJwt产生的超时异常信息
     * @return 返回JWT超时异常信息
     * @see ExpiredJwtException
     */
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    public RestResult<String> handleJWTException(ExpiredJwtException e) {
        printException(e);

        return fail(JWT_TOKEN_EXPIRED);
    }

    /**
     * 捕获JWT签名异常
     * @param e JWT产生的签名异常
     * @return 返回JWT签名异常信息对象
     * @see SignatureException
     */
    @ExceptionHandler(SignatureException.class)
    @ResponseBody
    public RestResult<String> handleJWTException(SignatureException e) {
        printException(e);

        return fail(JWT_ILLEGAL_TOKEN);
    }

    /**
     * 捕获JWT格式异常
     * @param e JWT产生的格式异常
     * @return 返回检JWT格式异常信息对象
     * @see MalformedJwtException
     */
    @ExceptionHandler(MalformedJwtException.class)
    @ResponseBody
    public RestResult<String> handleJWTException(MalformedJwtException e) {
        printException(e);

        return fail(JWT_ILLEGAL_TOKEN);
    }

    /**
     * 捕获JSON格式异常
     * @param e Jackson JSON产生的JSON格式异常信息
     * @return 返回SON格式异常信息对象
     * @see JsonParseException
     */
    @ExceptionHandler(JsonParseException.class)
    @ResponseBody
    public RestResult<String> handleJsonException(JsonParseException e) {
        printException(e);

        return fail(JSON_ILEEGAL_FORMAT);
    }


    /**
     * 捕获SQL异常
     * @param e Java SQL产生的异常信息
     * @return 返回SQL异常信息对象
     * @see SQLException
     */
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public RestResult<String> handleSQLException(SQLException e) {
        printException(e);

        return fail("SQL => " + e.getErrorCode(), e.getMessage());
    }

}

