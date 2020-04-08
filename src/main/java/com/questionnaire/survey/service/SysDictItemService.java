package com.questionnaire.survey.service;

import com.questionnaire.survey.constant.ErrorCode;
import com.questionnaire.survey.entity.SysDictItem;
import com.questionnaire.survey.mapper.SysDictItemMapper;
import com.questionnaire.survey.service.SysDictItemService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.questionnaire.survey.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class SysDictItemService extends ServiceImpl<SysDictItemMapper, SysDictItem> {

    @Autowired
    private SysDictItemMapper sysDictItemMapper;

    public RestResult<Map<String, List<SysDictItem>>> getMapByTypeCodeList(List<String> typeCodeList) {
        if(typeCodeList.size()<=0){
            return RestResult.fail(ErrorCode.LIST_EMPTY);
        }
        Map<String, List<SysDictItem>> map = new HashMap<>(typeCodeList.size());
        for (String typeCode : typeCodeList) {
            List<SysDictItem> list = selectSysDictByType(typeCode);
            map.put(typeCode, list);
        }
        return success(map);
    }

    private List<SysDictItem> selectSysDictByType(String typeCode) {
        return sysDictItemMapper.getDictsByTypeCode(typeCode);
    }

    public RestResult<Void> addSysDictItem(SysDictItem sysDictItem) {
        if(sysDictItemMapper.insert(sysDictItem)<=0){
            return fail(ErrorCode.CRUD_UPDATE_NO_RECORD);
        }
        return success(null);
    }

    public RestResult<Void> removeSysDictItem(String id) {
        SysDictItem sysDictItem = new SysDictItem().selectById(id);
        sysDictItem.setDelFlag(true);
        if(!sysDictItem.updateById()){
            return fail(ErrorCode.CRUD_UPDATE_NO_RECORD);
        }
        return success(null);
    }

    public RestResult<Void> editSysDictItem(SysDictItem sysDictItem) {
        sysDictItemMapper.updateById(sysDictItem);
        return success(null);
    }

    public SysDictItem getSysDictItemById(String departmentId) {
        SysDictItem sysDictItem = sysDictItemMapper.selectById(departmentId);
        return sysDictItem;
    }
}
