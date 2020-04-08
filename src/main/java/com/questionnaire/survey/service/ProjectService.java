package com.questionnaire.survey.service;

import com.questionnaire.survey.constant.ErrorCode;
import com.questionnaire.survey.constant.ProjectStatus;
import com.questionnaire.survey.entity.Project;
import com.questionnaire.survey.mapper.ProjectMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.questionnaire.survey.utils.JwtUtil;
import com.questionnaire.survey.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangrong123
 * @since 2020-03-31
 */
@Service
public class ProjectService extends ServiceImpl<ProjectMapper, Project> {


    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private SurveyService surveyService;

    //查询未删除项目列表（仅限管理员）
    public RestResult<List<Project>> getProjectList() {
        if(!JwtUtil.isSuperUser()){
            return RestResult.fail(ErrorCode.USER_NOT_ENABLE);
        }
        List<Project> projectList = projectMapper.getProjectList();
        return RestResult.success(projectList);
    }

    //新增项目
    public RestResult<String> addProject(Project project) {
        if(isNotBlank(projectMapper.getStartingProjectId())){
            return RestResult.fail(ErrorCode.END_STARTING_PROJECT);
        }
        if(isBlank(project.getName())){
            return RestResult.fail(ErrorCode.EXCEPTION_ILLEGAL_ARGUMENT);
        }
        project.setCreateTime(LocalDateTime.now()).setDelFlag(false).setProjectStatus(ProjectStatus.PROJECT_STARTING.getTypeCode());
       //实体更新可以拿到插入后得id
        if(project.insert()){
            return RestResult.success(project.getId());
        }
        return RestResult.fail(ErrorCode.CRUD_UPDATE_NO_RECORD);
    }

    //终止项目，之后的每次调研单都将新增给最新项目id
    public RestResult<Void> endProject(String projectId) {
        if(isBlank(projectId)){
            return RestResult.fail(ErrorCode.EXCEPTION_ILLEGAL_ARGUMENT);
        }
        Project project = new Project().selectById(projectId);
        project.setProjectStatus(ProjectStatus.PROJECT_END.getTypeCode()).setUpdateTime(LocalDateTime.now());
        if(project.updateById()){
            return RestResult.success(null);
        }
        return RestResult.fail(ErrorCode.CRUD_UPDATE_NO_RECORD);
    }

    //删除项目
    @Transactional
    public RestResult<String> deleteProject(String projectId) {
        if(isBlank(projectId)){
            return RestResult.fail(ErrorCode.EXCEPTION_ILLEGAL_ARGUMENT);
        }
        Project project = new Project().selectById(projectId);
        //如果是进行中，则不允许删除，标志变为完成才允许删除
        if(ProjectStatus.PROJECT_STARTING.getTypeCode().equals(project.getProjectStatus())){
            return RestResult.fail(ErrorCode.STARTING_PROJECT_CANNOT_DELETE);
        }
        project.setDelFlag(true).setUpdateTime(LocalDateTime.now());
        if(project.updateById()){
            //如果项目更新成功且调研单删除标志更新成功
            if(surveyService.deleteByProjectId(projectId)<=0){
                return RestResult.fail(ErrorCode.CRUD_UPDATE_NO_RECORD);
            }
            return RestResult.success(null);
        }
        return RestResult.fail(ErrorCode.CRUD_UPDATE_NO_RECORD);
    }
}
