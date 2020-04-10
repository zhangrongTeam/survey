package com.questionnaire.survey.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.questionnaire.survey.constant.ErrorCode;
import com.questionnaire.survey.entity.SysDictItem;
import com.questionnaire.survey.entity.SysDictType;
import com.questionnaire.survey.mapper.SysDictItemMapper;
import com.questionnaire.survey.mapper.SysDictTypeMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.questionnaire.survey.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.questionnaire.survey.utils.RestResult.fail;
import static com.questionnaire.survey.utils.RestResult.success;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangrong123
 * @since 2020-03-31
 */
@Service
public class SysDictTypeService extends ServiceImpl<SysDictTypeMapper, SysDictType> {

    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;
    @Autowired
    private SysDictItemMapper sysDictItemMapper;

    public RestResult<Void> addSysDictType(SysDictType sysDictType) {
        sysDictType.setId(null);
        if(sysDictTypeMapper.insert(sysDictType)<=0){
            return fail(ErrorCode.CRUD_UPDATE_NO_RECORD);
        }
        return success(null);
    }

    //删除数据字典类型及其字典项目
    @Transactional
    public RestResult<Void> removeSysDictType(String id) {
        SysDictType sysDictType = new SysDictType().selectById(id);
        sysDictType.setDelFlag(true);
        if(!sysDictType.updateById()){
            return fail(ErrorCode.CRUD_UPDATE_NO_RECORD);
        }
        sysDictItemMapper.deleteDicItemtByType(id);
        return success(null);
    }

    public RestResult<Void> editSysDictType(SysDictType sysDictType) {
        if(sysDictTypeMapper.updateById(sysDictType)<0){
            return fail(ErrorCode.CRUD_UPDATE_NO_RECORD);
        }
        return success(null);
    }

    public RestResult<List<SysDictType>> list() {
        List<SysDictType> sysDictTypes = sysDictTypeMapper.selectSysDictTypeList();
        return RestResult.success(sysDictTypes);
    }

    public RestResult<List<SysDictItem>> getDictItemList(String sysDictId) {
        List<SysDictItem> sysDictItems = sysDictItemMapper.selectSysDictItemList(sysDictId);
        return success(sysDictItems);
    }
}
