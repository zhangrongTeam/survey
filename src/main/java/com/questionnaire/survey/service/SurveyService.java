package com.questionnaire.survey.service;

import com.questionnaire.survey.DTO.AddSurveyDTO;
import com.questionnaire.survey.constant.ErrorCode;
import com.questionnaire.survey.controller.SurveySearchDTO;
import com.questionnaire.survey.entity.*;
import com.questionnaire.survey.mapper.ProjectMapper;
import com.questionnaire.survey.mapper.SurveyMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.questionnaire.survey.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangrong123
 * @since 2020-03-31
 */
@Service
public class SurveyService extends ServiceImpl<SurveyMapper, Survey> {

    @Autowired
    private SurveyMapper surveyMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private BuildingConstructionService buildingConstructionService;
    @Autowired
    private WaterMeterService waterMeterService;
    @Autowired
    private WaterSupplyNetworkService waterSupplyNetworkService;

    public static Map<Class, Reflector> reflectors = new ConcurrentHashMap<>();

    //引入yml文件
    @Value("${sys-config.packageUrl}")
    private String packageUrl;

    /**
     * @Title: lineToHump
     * @Description: (下划线转驼峰，并大写第一个字母)
     * @param @param str
     * @param @return    参数
     * @return String    返回类型
     */
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    public static String lineToHump(String str){
        //先控制字符串首字母大写，然后对剩余字符串进行规则判断
        Matcher matcher = linePattern.matcher(str.substring(0, 1).toUpperCase() + str.substring(1));
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
        //group方法是说linePattern这是个规则组，里面可以加好几个规则，（）代表全部匹配，1，2分别代表第几个括号规则组匹配,里面需要是正则表达式
            //再将下划线后第一个字母大写替换给sb
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
            matcher.appendTail(sb);
        return sb.toString();
    }

    //根据项目id删除项目下调研单
    public int deleteByProjectId(String projectId) {
        return surveyMapper.deleteByProjectId(projectId);
    }

//    public Object getSetInsert(Class<?> fromClass,Class<?> toClass) throws Exception {
//        Object toClassObject = toClass.newInstance();
//        //循环传入的类
//        for(Field field : fromClass.getDeclaredFields()){
//            //反射得到方法
//            String methodSetName = "set" + field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
//            String methodGetName = "get" + field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
//            //clz.getDeclaredMethod是获取所有类型的方法，getMethod是获取public方法，同时getMethod能获取到父类的所有方法但是getDeclaredMethod是获取当前类
//            //如果是想得到有参数的方法，必须要传入参数的类型才能获取到此方法
//            Method setMethod = toClass.getMethod(methodSetName,String.class);
//            Method getMethod = toClass.getMethod(methodGetName);
//            //把从来源类中get到的值set到去处类
//            setMethod.invoke(toClassObject,getMethod.invoke(fromClass));
//        }
//        return toClassObject;
//    }

    //将来源对象中的所有属性值set到目标对象中并返回目标对象
//    public Class<?> getSetInsert(Class<?> fromClass,Class<?> toClass) throws Exception {
//        //得到当前类的反射对象
//        Reflector currentReflector = reflectors.get(toClass);
//        if(currentReflector == null){
//            Reflector reflector = new Reflector(toClass);
//            reflectors.put(toClass,reflector);
//            currentReflector = reflector;
//        }
//        //循环传入的类的属性字段
//        for(Field fromClassField : fromClass.getDeclaredFields()){
//                    //根据字段名得到当前类当前属性的反射对象
//                    Invoker setFieldToClass = currentReflector.getSetInvoker(fromClassField.getName());
//                    Invoker getFieldFromClass = currentReflector.getGetInvoker(fromClassField.getName());
//                    //得到当前get方法
//                    String  getMethodsName = currentReflector.getClassMethodByFieldName(fromClassField.getName(),"get");
//
//            Method  getMethod = null;
//            try {
//                getMethod = fromClass.getMethod(getMethodsName);
//            } catch (NoSuchMethodException e) {
//                continue;
//            }
//            if( getMethod.invoke(fromClass.newInstance()) != null){
//                //将从来源类得到的属性set到目标类相应属性中
//                 Object invoke = setFieldToClass.invoke(toClass.newInstance(), (Object[]) getMethod.invoke(fromClass));
//
//            }
//        }
//        return toClass;
//    }

    //调研单提交
    @Transactional
    public RestResult<Boolean> submitSurvey(AddSurveyDTO addSurveyDTO){
//        Class<?> toClz = (Class<?>)Class.forName(packageUrl +"."+ lineToHump(addSurveyDTO.getSystemType()));
        LocalDateTime now = LocalDateTime.now();
        //取最新的项目id set
        String startingProjectId = projectMapper.getStartingProjectId();
        if(isBlank(startingProjectId)){
            return RestResult.fail(ErrorCode.NOT_EXIST_STARTING_PROJECT);
        }
        Survey survey = new Survey();
        survey.setProjectId(startingProjectId);
        survey.setCreateBy(addSurveyDTO.getUserId()).setSurveyUserId(addSurveyDTO.getUserId()).setCreateTime(now).setDelFlag(false);
        survey.insert();
        if(("building_construction").equals(addSurveyDTO.getSystemType())){
            BuildingConstruction buildingConstruction = addSurveyDTO.getBuildingConstruction();
            buildingConstruction.setSurveyId(survey.getId());
            buildingConstruction.insert();
        }else if(("water_supply_network").equals(addSurveyDTO.getSystemType())){
            WaterSupplyNetwork waterSupplyNetwork =  addSurveyDTO.getWaterSupplyNetwork();
            waterSupplyNetwork.setSurveyId(survey.getId());
            waterSupplyNetwork.insert();
        }else if (("water_meter").equals(addSurveyDTO.getSystemType())){
            WaterMeter waterMeter = addSurveyDTO.getWaterMeter();
            waterMeter.setSurveyId(survey.getId());
            waterMeter.insert();
        }else {
            return RestResult.fail(ErrorCode.EXCEPTION_ILLEGAL_ARGUMENT);
        }
        return RestResult.success(true);
    }

    public Map<String, List> getSurveyListByProject(SurveySearchDTO surveySearchDTO) {
        Map<String, List> resultMap = new HashMap<>();
        List<BuildingConstruction> buildingConstructions = buildingConstructionService.selectByProjectId(surveySearchDTO);
        List<WaterMeter> waterMeters = waterMeterService.selectByProjectId(surveySearchDTO);
        List<WaterSupplyNetwork> waterSupplyNetworks = waterSupplyNetworkService.selectByProjectId(surveySearchDTO);
        resultMap.put("building_construction", buildingConstructions);
        resultMap.put("water_supply_network",waterMeters);
        resultMap.put("water_meter",waterSupplyNetworks);
        return resultMap;
    }

//    public static void main(String[] args) throws ClassNotFoundException {
//        Class<?> clz = (Class<?>)Class.forName("com.questionnaire.survey.entity.WaterSupplyNetwork");
//        String line_pattern = lineToHump("line_pattern");
//        System.out.println(clz.getTypeName()+":::::"+clz.getName());
//    }
}
