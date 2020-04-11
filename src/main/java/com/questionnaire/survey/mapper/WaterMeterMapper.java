package com.questionnaire.survey.mapper;

import com.questionnaire.survey.controller.SurveySearchDTO;
import com.questionnaire.survey.entity.WaterMeter;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 * 水表统计 Mapper 接口
 * </p>
 *
 * @author zhangrong123
 * @since 2020-04-02
 */
@Mapper
public interface WaterMeterMapper extends BaseMapper<WaterMeter> {

    List<WaterMeter> selectByProjectId(@Param("surveySearchDTO") SurveySearchDTO surveySearchDTO);
}
