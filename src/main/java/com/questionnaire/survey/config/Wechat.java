package com.questionnaire.survey.config;

public interface Wechat {
    String APPID="wxc5b8fdbff9258be5";
    String APPSECRET="1e6d0a9e3e99d979046bead62f874578";
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
