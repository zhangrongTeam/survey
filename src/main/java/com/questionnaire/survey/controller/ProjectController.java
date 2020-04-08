package com.questionnaire.survey.controller;


import com.questionnaire.survey.entity.Project;
import com.questionnaire.survey.entity.User;
import com.questionnaire.survey.service.ProjectService;
import com.questionnaire.survey.utils.RestResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangrong123
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/survey/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/getCurrentProject")
    @ApiOperation("查询未删除项目列表")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<List<Project>> getProjectList(){
        return projectService.getProjectList();
    }

    @PostMapping("/addProject")
    @ApiOperation("新增项目")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<String> addProject(@RequestBody Project project){
        return projectService.addProject(project);
    }

    @PostMapping("/endProject/{projectId}")
    @ApiOperation("更新项目状态")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<Void> endProject(@PathVariable("projectId") String projectId){
        return projectService.endProject(projectId);
    }

    @PostMapping("/deleteProject/{projectId}")
    @ApiOperation("删除项目（删除项目时将删除所有调研单数据）")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<String> deleteProject(@PathVariable("projectId") String projectId){
        return projectService.deleteProject(projectId);
    }

}

