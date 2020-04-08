package com.questionnaire.survey.mapper;

import com.questionnaire.survey.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhangrong123
 * @since 2020-04-01
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    User queryUser(@Param("loginUser") User loginUser);
}
