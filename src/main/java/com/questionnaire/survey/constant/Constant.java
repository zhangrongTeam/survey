package com.questionnaire.survey.constant;

/**
 * @author zhangrong
 * @since 2018/7/27 8:49
 */
public interface Constant<T> {



    /**
     *
     * @return 错误代码
     */
    String getCode();

    /**
     *
     * @return 错误信息
     */
    String getMsg();


    /** JWT,getAttribute中使用*/
    String USER = "user";

    /**
     * 用户登录redis key标志
     */
    String SYS_USER_REDIS_PREFIX_KEY = "sysUser:/loginUser/";
    String SYS_USER_REDIS_PREFIX_FORMAT_KEY = "sysUser:/loginUser/%s/%s";


    //题目code
    String FLOOR_DRAIN_FLAG = "floor_drain_flag";
    String PIPELINE_FLAG = "pipeline_flag";
    String PH_LOCATION = "ph_location";
    String PH_INDEPENDENT = "ph_independent";
    String PH_SECURITY_CONTROL = "ph_security_control";
    String PH_COMPRESS_PIPE = "ph_compress_pipe";
    String PH_DECORATION = "ph_decoration";
    String PH_DOWN_PRESSURE= "ph_down_pressure";
    String  PIPELINE_SIZE2= "pipeline_size2";
    String  D_MANUFACTURER = "d_manufacturer";
    String  D_PLC = "d_plc";
    String  D_WATER_SUPPLY_MODE = "d_water_supply_mode";
    String  D_START_RUN_DATE = "d_start_run_date";
    String  D_FREQUENCY_CONVERTER_MODEL = "d_frequency_converter_model";
    String  PH_IN_PIPE_MATERIAL= "ph_in_pipe_material";
    String  PH_OUT_PIPE_MATERIAL= "ph_out_pipe_material";
    String  INNER_PINNER_IPELINE_SIZE= "inner__pinner_ipeline_size";

    //腾讯文档使用
    int APPID = 11233;
    String TIMESTAMP = "";
    String NONCE = "";
    String SIGNATURE = "";


}
