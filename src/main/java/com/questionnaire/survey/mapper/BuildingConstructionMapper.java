package com.questionnaire.survey.mapper;

import com.questionnaire.survey.entity.BuildingConstruction;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 * 楼宇建筑 Mapper 接口
 * </p>
 *
 * @author zhangrong123
 * @since 2020-04-02
 */
@Mapper
public interface BuildingConstructionMapper extends BaseMapper<BuildingConstruction> {

    List<BuildingConstruction> selectByProjectId(@Param("projectId") String projectId);
}
