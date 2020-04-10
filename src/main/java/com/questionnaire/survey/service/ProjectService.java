package com.questionnaire.survey.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.questionnaire.survey.DTO.ProjectStatusDTO;
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

    //更新项目状态，之后的每次调研单都将新增给最新项目id
    @Transactional
    public RestResult<Void> changeProjectStatus(ProjectStatusDTO projectStatusDTO) {
        if(isBlank(projectStatusDTO.getProjectId())||isBlank(projectStatusDTO.getChangeToStatus())){
            return RestResult.fail(ErrorCode.EXCEPTION_ILLEGAL_ARGUMENT);
        }
        Project project = new Project().selectById(projectStatusDTO.getProjectId());
        if(projectStatusDTO.getChangeToStatus().equals(ProjectStatus.PROJECT_STARTING.getTypeCode())){
            //如果是开启当前项目
            //停止其他
            projectMapper.endOtherProject(projectStatusDTO.getProjectId());
            //当前项目设为启动
            project.setProjectStatus(ProjectStatus.PROJECT_STARTING.getTypeCode()).setUpdateTime(LocalDateTime.now());
            //项目启动不需要设置调研单状态，因为调研单只有在项目删除时才更新删除flag
        }else {
            project.setProjectStatus(ProjectStatus.PROJECT_END.getTypeCode()).setUpdateTime(LocalDateTime.now());
        }
        project.updateById();
        return RestResult.success(null);
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
        project.updateById();
        surveyService.deleteByProjectId(projectId);
        return RestResult.success(null);
    }

    public RestResult<Void> getProjectStatus() {
        Project project = new Project().setProjectStatus(ProjectStatus.PROJECT_STARTING.getTypeCode()).setDelFlag(false);
        List<Project> projectList = selectList(new EntityWrapper<>(project));
        if(projectList.size()<=0){
            return RestResult.fail(ErrorCode.LIST_EMPTY);
        }
        return RestResult.success(null);
    }
}
