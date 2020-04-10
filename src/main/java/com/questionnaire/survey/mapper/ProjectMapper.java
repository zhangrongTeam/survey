package com.questionnaire.survey.mapper;

import com.questionnaire.survey.entity.Project;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhangrong123
 * @since 2020-03-31
 */
@Mapper
@Repository
public interface ProjectMapper extends BaseMapper<Project> {

    List<Project> getProjectList();

    String getStartingProjectId();

    void endOtherProject(@Param("projectId") String projectId);
}
