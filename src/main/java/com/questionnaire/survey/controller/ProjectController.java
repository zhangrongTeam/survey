package com.questionnaire.survey.controller;


import com.questionnaire.survey.DTO.ProjectStatusDTO;
import com.questionnaire.survey.entity.Project;
import com.questionnaire.survey.entity.User;
import com.questionnaire.survey.service.ProjectService;
import com.questionnaire.survey.utils.RestResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

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


    @PostMapping("/getProjectStatus")
    @ApiOperation("返回项目状态——是否存在正在进行项目")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<RestResult<Void>> getProjectStatus() {
        return new ResponseEntity<>(projectService.getProjectStatus(),OK);
    }

    @PostMapping("/changeProjectStatus")
    @ApiOperation("更新项目状态")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<Void> changeProjectStatus(@RequestBody ProjectStatusDTO projectStatusDTO){
        return projectService.changeProjectStatus(projectStatusDTO);
    }

    @GetMapping("/deleteProject/{projectId}")
    @ApiOperation("删除项目（删除项目时将逻辑删除所有调研单数据）")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<String> deleteProject(@PathVariable("projectId") String projectId){
        return projectService.deleteProject(projectId);
    }

}

