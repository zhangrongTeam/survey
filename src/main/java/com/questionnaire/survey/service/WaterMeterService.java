package com.questionnaire.survey.service;

import com.questionnaire.survey.entity.WaterMeter;
import com.questionnaire.survey.mapper.WaterMeterMapper;
import com.questionnaire.survey.service.WaterMeterService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 水表统计 服务实现类
 * </p>
 *
 * @author zhangrong123
 * @since 2020-04-02
 */
@Service
public class WaterMeterService extends ServiceImpl<WaterMeterMapper, WaterMeter>{

    @Autowired
    private WaterMeterMapper waterMeterMapper;
    public List<WaterMeter> selectByProjectId(String projectId) {
        return waterMeterMapper.selectByProjectId(projectId);
    }
}
