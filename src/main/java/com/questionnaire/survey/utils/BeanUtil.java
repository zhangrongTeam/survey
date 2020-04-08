package com.questionnaire.survey.utils;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

import java.lang.reflect.Type;

/**
 * 使用ModelMapper进行Java Bean转换
 * @author autoCode
 * @version 2017-10-29 10:45
 */
public class BeanUtil {

    /**
     * ModelMapper单例对象，线程安全
     */
    private static final ModelMapper STANDARD_MODEL_MAPPER = new ModelMapper();

    /**
     * ModelMapper单例对象，线程安全
     */
    private static final ModelMapper STRICT_MODEL_MAPPER = new ModelMapper();

    static {
        STRICT_MODEL_MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * 复制相同类型、相同名字的字段到不同的对象(字段匹配策略比较宽松，对于相同后缀的字段也会进行复制)
     * 比如：源对象有字段id，目的对象有id，parentId，则结果会对这两个字段都赋值，
     * 如果需要进行完全匹配，请使用strictCopy方法
     *
     * @param source 源对象
     * @param descClass 目的对象的class
     * @param <S> 源对象类型
     * @param <D> 目的对象类型
     * @return 返回填充过后的目的对象的实体类，如果源对象为null则返回null
     * @see #strictCopy(Object, Class)
     */
    public static <S, D> D copy(S source, Class<D> descClass) {
        return copy(source, descClass, STANDARD_MODEL_MAPPER);
    }

    /**
     * 复制相同类型、相同名字的字段到不同的对象(字段匹配策略比较严格，只会赋值字段名称类型完全相同的字段)
     * 比如：源对象有字段id，目的对象有id，parentId，则结果会对这id字段都赋值，
     * 如果需要进行完全匹配，请使用strictCopy方法
     *
     * @param source 源对象
     * @param descClass 目的对象的class
     * @param <S> 源对象类型
     * @param <D> 目的对象类型
     * @return 返回填充过后的目的对象的实体类，如果源对象为null则返回null
     * @see #strictCopy(Object, Class)
     */
    public static <S, D> D strictCopy(S source, Class<D> descClass) {
        return copy(source, descClass, STRICT_MODEL_MAPPER);
    }

    private static <S, D> D copy(S source, Class<D> descClass, ModelMapper modelMapper) {

        if (source == null) {
            return null;
        }
        return modelMapper.map(source, descClass);
    }

    /**
     * 复制相同类型、相同名字的字段到不同的对象
     * @param source 源对象
     * @param type 目的对象的class可以包含泛型
     * @param <S> 源对象类型
     * @param <D> 目的对象类型
     * @return 返回填充过后的目的对象的实体类
     */
    public static <S, D> D copy(S source, Type type) {
        return STANDARD_MODEL_MAPPER.map(source, type);
    }

    public static <S, D> void addConverter(Converter<S, D> converter) {
        STANDARD_MODEL_MAPPER.addConverter(converter);
    }

    public static <S, D> void addMapping(PropertyMap<S, D> propertyMap) {
        STANDARD_MODEL_MAPPER.addMappings(propertyMap);
    }

}
