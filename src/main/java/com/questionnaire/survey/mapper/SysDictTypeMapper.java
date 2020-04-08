package com.questionnaire.survey.mapper;

import com.questionnaire.survey.entity.SysDictType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.questionnaire.survey.utils.RestResult;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangrong123
 * @since 2020-03-31
 */
@Mapper
@Repository
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

   List<SysDictType> selectSysDictTypeList();


}
