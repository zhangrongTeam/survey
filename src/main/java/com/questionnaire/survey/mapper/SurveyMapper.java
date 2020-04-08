package com.questionnaire.survey.mapper;

import com.questionnaire.survey.entity.Survey;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

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
public interface SurveyMapper extends BaseMapper<Survey> {

    int deleteByProjectId(@Param("projectId") String projectId);
}
