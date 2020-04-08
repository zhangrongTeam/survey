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
@TableName("sys_dict_item")
@Data
public class SysDictItem extends Model<SysDictItem> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 数据字典类型 id
     */
    @TableField("dict_type_id")
    private String dictTypeId;
    /**
     * 数据值
     */
    private String value;
    /**
     * 标签名
     */
    private String label;
    /**
     * 排序（升序）
     */
    private Integer sort;
    /**
     * 备注信息
     */
    private String remarks;
    /**
     * 删除标记（1：删除）
     */
    @TableField("del_flag")
    private boolean delFlag;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysDictItem{" +
        ", id=" + id +
        ", dictTypeId=" + dictTypeId +
        ", value=" + value +
        ", label=" + label +
        ", sort=" + sort +
        ", remarks=" + remarks +
        ", delFlag=" + delFlag +
        "}";
    }
}
