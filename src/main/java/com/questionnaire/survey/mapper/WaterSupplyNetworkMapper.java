package com.questionnaire.survey.mapper;

import com.questionnaire.survey.entity.WaterSupplyNetwork;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 * 供水管网 Mapper 接口
 * </p>
 *
 * @author zhangrong123
 * @since 2020-04-02
 */
@Mapper
public interface WaterSupplyNetworkMapper extends BaseMapper<WaterSupplyNetwork> {

    List<WaterSupplyNetwork> selectByProjectId(@Param("projectId") String projectId);
}
