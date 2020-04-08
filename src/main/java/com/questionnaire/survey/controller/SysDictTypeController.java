package com.questionnaire.survey.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.questionnaire.survey.DTO.SysDictTypeDTO;
import com.questionnaire.survey.entity.SysDictItem;
import com.questionnaire.survey.entity.SysDictType;
import com.questionnaire.survey.service.SysDictTypeService;
import com.questionnaire.survey.utils.RestResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

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
@RequestMapping("/survey/sysDictType")
public class SysDictTypeController {

    @Autowired
    private SysDictTypeService sysDictTypeService;

    @PostMapping("/addSysDictType")
    @ApiOperation("添加字典类型")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<Void> addSysDictType(@Valid @RequestBody SysDictTypeDTO sysDictType) {
        return sysDictTypeService.addSysDictType(copy(sysDictType, SysDictType.class));
    }
    @DeleteMapping("/removeSysDictType/{id}")
    @ApiOperation("删除字典类型")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<Void> removeSysDictType(@PathVariable @Valid @NotBlank String id) {
        return sysDictTypeService.removeSysDictType(id);
    }

    //@Valid注解是只让类中的校验生效
    @PutMapping("/editSysDictType/{id}")
    @ApiOperation("修改字典类型")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<Void> editSysDictType(@Valid @RequestBody SysDictTypeDTO sysDictType) {
        return sysDictTypeService.editSysDictType(copy(sysDictType, SysDictType.class));
    }

    @PostMapping("/list")
    @ApiOperation("查询字典类型列表")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<List<SysDictType>> list() {
        return sysDictTypeService.list();
    }

    @GetMapping("/getDictListByItems/{sysDictId}")
    @ApiOperation("根据字典类型id查询字典项列表")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<List<SysDictItem>> getDictListByItems(@PathVariable("sysDictId") @NotBlank String sysDictId) {
        return sysDictTypeService.getDictItemList(sysDictId);
    }
}

