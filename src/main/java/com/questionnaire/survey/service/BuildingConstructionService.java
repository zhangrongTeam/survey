package com.questionnaire.survey.service;

import com.questionnaire.survey.entity.BuildingConstruction;
import com.questionnaire.survey.mapper.BuildingConstructionMapper;
import com.questionnaire.survey.service.BuildingConstructionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 楼宇建筑 服务实现类
 * </p>
 *
 * @author zhangrong123
 * @since 2020-04-02
 */
@Service
public class BuildingConstructionService extends ServiceImpl<BuildingConstructionMapper, BuildingConstruction>{

    @Autowired
    private BuildingConstructionMapper buildingConstructionMapper;

    public List<BuildingConstruction> selectByProjectId(String projectId) {
        return buildingConstructionMapper.selectByProjectId(projectId);
    }
}
