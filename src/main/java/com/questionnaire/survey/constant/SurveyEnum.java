package com.questionnaire.survey.constant;

public enum SurveyEnum {
    /**
     * 项目状态
     */
    PROJECT_STARTING("1", "进行中"),
    PROJECT_END("2", "已完成"),
    ;
    private final String typeCode;

    private final String typeName;

    SurveyEnum (String typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public String getTypeName() {
        return typeName;
    }
}
