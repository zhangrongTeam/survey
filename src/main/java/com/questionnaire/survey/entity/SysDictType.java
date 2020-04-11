package com.questionnaire.survey.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangrong123
 * @since 2020-03-31
 */
@Data
@TableName("sys_dict_type")
public class SysDictType extends Model<SysDictType> {

    /**
     * id
     */
    private String id;
    /**
     * 数据字典名称
     */
    @TableField("dict_type_name")
    private String dictTypeName;
    /**
     * 数据字典code
     */
    @TableField("dict_type_code")
    private String dictTypeCode;
    /**
     * 备注
     */
    @TableField("dict_type_remarks")
    private String dictTypeRemarks;
    /**
     * 排序（升序）
     */
    private Integer sort;
    /**
     * 删除标记(1是删除)
     */
    @TableField("del_flag")
    private boolean delFlag;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysDictType{" +
        ", id=" + id +
        ", dictTypeName=" + dictTypeName +
        ", dictTypeCode=" + dictTypeCode +
        ", dictTypeRemarks=" + dictTypeRemarks +
        ", sort=" + sort +
        ", delFlag=" + delFlag +
        "}";
    }
}
