package com.questionnaire.survey.config;

public interface Wechat {
    String APPID="wx40c0a4921b72a76f";
    String APPSECRET="acf834ae012941a16fd9c30b360cd305";
    /**
     * 消息推送微信接口
     */
    String SUBSCRIBEMESSAGE="https://api.weixin.qq.com/cgi-bin/message/subscribe/send";

    /**
     * 消息推送模块id
     */
    String CLOCK_NOTICE_TEMPLATEID ="oGvB5h3TzFKxWkiaC0ETtjBzinf9APECh1ZGsuaMMNc";

    String USER_INFO_URL = "https://api.weixin.qq.com/sns/jscode2session";

}
