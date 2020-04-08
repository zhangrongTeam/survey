package com.questionnaire.survey.controller;


import com.questionnaire.survey.DTO.SysDictItemDTO;
import com.questionnaire.survey.entity.SysDictItem;
import com.questionnaire.survey.service.SysDictItemService;
import com.questionnaire.survey.utils.RestResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.questionnaire.survey.utils.BeanUtil.copy;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangrong123
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/survey/sysDictItem")
public class SysDictItemController {


    @Autowired
    private SysDictItemService sysDictItemService;
    @PostMapping("/getMapByTypeCodeList")
    @ApiOperation("根据typeCodeList获取字典Map")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<Map<String, List<SysDictItem>>> getMapByTypeCodeList(@RequestBody List<String> typeCodeList){
        return sysDictItemService.getMapByTypeCodeList(typeCodeList);
    }

    @PostMapping("/addSysDictItem")
    @ApiOperation("添加字典")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<Void> addSysDictItem(@Valid @RequestBody SysDictItemDTO sysDictItemDTO) {
        return sysDictItemService.addSysDictItem(copy(sysDictItemDTO, SysDictItem.class));
    }

    @DeleteMapping("removeSysDictItem/{id}")
    @ApiOperation("删除字典")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<Void> removeSysDictItem(@PathVariable String id) {
        return sysDictItemService.removeSysDictItem(id);
    }

    @PutMapping("/editSysDictItem/{id}")
    @ApiOperation("修改字典")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<Void> editSysDictItem(@Valid @RequestBody SysDictItemDTO sysDictItemDTO) {
        return sysDictItemService.editSysDictItem(copy(sysDictItemDTO, SysDictItem.class));
    }
}

