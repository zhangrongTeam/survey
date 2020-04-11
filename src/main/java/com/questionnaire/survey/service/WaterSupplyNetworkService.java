package com.questionnaire.survey.service;

import com.questionnaire.survey.controller.SurveySearchDTO;
import com.questionnaire.survey.entity.WaterSupplyNetwork;
import com.questionnaire.survey.mapper.WaterSupplyNetworkMapper;
import com.questionnaire.survey.service.WaterSupplyNetworkService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 供水管网 服务实现类
 * </p>
 *
 * @author zhangrong123
 * @since 2020-04-02
 */
@Service
public class WaterSupplyNetworkService extends ServiceImpl<WaterSupplyNetworkMapper, WaterSupplyNetwork>{

    @Autowired
    private WaterSupplyNetworkMapper waterSupplyNetworkMapper;
    public List<WaterSupplyNetwork> selectByProjectId(SurveySearchDTO surveySearchDTO) {
        return waterSupplyNetworkMapper.selectByProjectId(surveySearchDTO);
    }
}
