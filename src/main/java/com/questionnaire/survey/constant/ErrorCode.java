package com.questionnaire.survey.constant;

/**
 * 错误代码枚举
 * @author zhangrong
 * @since 2018/7/27 8:49
 */
public enum ErrorCode {
    /**
     * 重复提交
     */
    REPEAT_SUBMIT_ERROR("repeat_submit_error", "重复提交"),

    /**
     * JSON格式错误
     */
    JSON_ILEEGAL_FORMAT("json_illegal_format", "JSON格式错误，请检查请求参数的格式是否符合标准的JSOn格式"),

    /**
     * token不存在
     */
    JWT_NOT_EXIST("jwt_not_exist", "token不存在"),
    /**
     * 不是合法的jwt token
     */
    JWT_ILLEGAL_TOKEN("jwt_illegal_token", "非法的token"),
    /**
     * Jwt token超时
     */
    JWT_TOKEN_EXPIRED("jwt_token_expired", "token超时"),
    /**
     * 非法参数
     */
    EXCEPTION_ILLEGAL_ARGUMENT("illegal_argument", "非法参数"),

    /**
     * 非法状态
     */
    EXCEPTION_ILLEGAL_STATE("illegal_state", "非法状态"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST("user_not_exist", "用户不存在"),

    OPENID_NOT_EXIST("openId_not_exist", "openId获取失败"),

    /**
     * 用户被禁止登陆
     */
    USER_NOT_ENABLE("user_not_enable", "用户被禁止登陆，请联系管理员"),

    /**
     * 用户没有分配角色
     */
    USER_NOT_HAVE_ROLE("user_not_have_role", "用户没有分配角色，请联系管理员"),

    /**
     * 有下级菜单不允许删除
     */
    ORG_SYSMENU_DELETE("org_admin_user_exist", "有下级菜单不允许删除"),


    /**
     * 用户认证失败
     */
    USER_AUTH_FAILED("user_auth_failed", "用户名或密码错误"),

    /**
     * 更新操作失败
     */
    CRUD_UPDATE_NO_RECORD("update_no_record", "更新操作失败"),

    NOT_EXIST_STARTING_PROJECT("not_exist_starting_project", "当前没有进行中的项目"),

    END_STARTING_PROJECT("end_starting_project", "请先结束进行中的项目，才可开启新项目"),

    STARTING_PROJECT_CANNOT_DELETE("starting_project_cannot_delete","进行中的项目不允许删除，请先结束项目"),

    /**
     * 确认密码不一致
     */
    USER_CONFIRM_PASSWORD_FAIL("user_confirm_password_fail", "两次输入密码不一致"),

    /**
     * 密码为空
     */
    USER_OLD_PASSWORD_IS_EMPTY("user_password_is_empty", "旧密码为空"),
    /**
     * 密码为空
     */
    USER_PASSWORD_IS_EMPTY("user_password_is_empty", "新密码为空"),
    /**
     * 用户名已存在
     */
    USER_NAME_DUPLICATE("user_name_duplicate", "用户名该项目中已存在，请更换"),
    /**
     * 登录名已存在
     */
    LOGIN_NAME_DUPLICATE("user_duplicate_login_name", "登录名已存在，请更换"),
    /**
     * 登录名为空
     */
    LOGIN_NAME_EMPTY("login_name_empty","登录名为空"),

    /**
     * 项目为空
     */
    PROJECT_ID_EMPTY("project_id_empty","项目id为空"),

    /**
     * 组织不存在
     */
    ORG_NOT_EXIST("org_not_exist", "组织不存在"),

    /**
     * 没有站点树权限
     */
    SITE_TREE_NOT_EXIST("site_tree_not_exist", "没有站点权限"),

    /**
     * 用户权限不足
     */
    USER_LACK_OF_RIGHT("user_lack_of_right", "用户权限不足"),

    /**
     * 删除操作失败
     */
    CRUD_DELETE_NO_RECORD("delete_no_record", "删除操作失败"),


    SYS_NOT_EXIST("sys_not_exist", "请求数据不存在"),

    START_TIME_BIG_END_TIME("starttime_big_endtime","开始时间大于结束时间"),

    SYS_NOT_ROLE("sys_not_role", "没有操作权限"),

    OLD_PWD_ERROR("old_pwd_error","修改失败，原密码输入错误"),

    DATA_ALREADY_EXISTS("data_already_exists","数据已存在"),

    SEND_MESSAGE_FAIL("send_message_fail", "发送验证码失败"),

    SEND_MESSAGE_CODE_EMPTY("send_message_code_empty", "验证码不存在"),

    SEND_MESSAGE_CODE_ERROR("SEND_MESSAGE_CODE_ERROR", "验证码错误"),

    TYPE_CODE_ERROR("type_code_error", "类型参数非法"),

    DELETED("deleted", "用户已冻结"),

    PHONE_EMPTY("phone_empty", "手机号为空"),

    MSG_EMPTY("msg_empty", "消息内容为空"),

    PHONE_IS_NOT("PHONE_IS_NOT", "该手机号未注册"),

    GET_USER_FAILED("get_user_failed", "获取当前用户失败"),

    PIC_NAME_EMPTY("pic_name_empty","文件名称为空"),


    FILE_SIZE_LIMIT("file_size_limit","文件大小超出20Mb"),

    UPLOAD_FILE_ERROR("upload_file_error","上传文件异常"),

    TWO_PWD_NOT_EQUAL("two_pwd_not_equal","两次密码不一致"),

    TWO_PASSWORD_EQUAL("two_password_equal", "新密码与旧密码一致"),

    MOBILE_NOT_UPDATE("mobile_not_update", "登录手机号不能修改"),
    LONGI_IS_EMPTY("longi_is_empty", "经度为空"),

    LATI_IS_EMPTY("lati_is_empty", "纬度为空"),

    LIST_EMPTY("list_empty", "集合为空"),

    SITE_EMPTY("site_empty", "站点为空"),

    ALARM_EMPTY("alarm_empty", "告警为空"),

    REPORT_EMPTY("report_empty", "昨日异常报告为空"),

    METER_EMPTY("meter_empty", "水表为空"),

    HOURS_ERROR("hours_error", "小时数格式错误"),
    ACTION_FAIL("action_fail", "操作失败，请检查后重试"),

    NAME_EMPTY("name_empty", "名字不能为空"),

    DATE_EMPTY("date_empty", "日期不能为空"),

    DATE_FORMAT_ERROR("date_format_error", "日期格式不正确"),

    DAYS_IS_ZERO("days_id_zero", "选择天数必须大于0"),

    OUT_OF_RANGE("out_of_range", "超出范围"),

    PAGE_EMPTY("page_empty","正确输入页码"),

    DATA_VERSION_OLD("data_version_old","数据版本低于服务器版本，请更新数据"),

    PHONE_EXIT("phone_exit","手机号已存在"),

    DEPT_CAN_NOT_MOVE_CHILD("dept_can_not_move_child","顶级部门的子集不能删除"),

    DEPT_CAN_NOT_BE_DELETE("dept_can_not_be_delete","部门不能被删除"),

    DEPT_NOT_EXIST("dept_not_exist","部门不存在"),

    NO_PROJECT_RIGHT("no_project_right","没有项目权限"),

    NO_COMMUNITY_UNDONE("no_community_undone","项目下没有未调研的小区"),

    COMMUNITY_IS_DONE("community_is_done","当前小区已经在调研中，不能重复选择"),

    PROJECT_NO_TEMPLATE("project_no_template","当前项目没有调研模板"),

    SURVEY_UNDONE("survey_Undone","用户有未完成调研单"),

    COMMUNITY_DATA_REPEAT("community_data_repeat","小区数据重复"),

    ;

    private final String code;

    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
