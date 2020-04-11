package com.questionnaire.survey.controller;


import com.questionnaire.survey.DTO.AddSurveyDTO;
import com.questionnaire.survey.service.SurveyService;
import com.questionnaire.survey.utils.RestResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangrong123
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/survey/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    //调研单填写提交（微信）
    @PostMapping(path = "/submitSurvey", consumes = APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("保存调研单")
    public ResponseEntity<RestResult<Boolean>> submitSurvey(@Valid @RequestBody AddSurveyDTO addSurveyDTO){
        return new ResponseEntity<>(surveyService.submitSurvey(addSurveyDTO), HttpStatus.OK);
    }
    //分类别 调研单列表查询（管理后台）根据项目id得到全部调研单列表并分类返回
    @GetMapping(path = "/getSurveyListByProject/{projectId}")
    @ApiOperation("根据项目查找调研单列表")
    public Map<String, List> getSurveyListByProject(@PathVariable("projectId") @Valid@NotBlank String projectId){
        return surveyService.getSurveyListByProject(projectId);
    }

    //调研单内容分类别导出（待定，看是导出选中值——前端传过来，还是导出全部）

}

