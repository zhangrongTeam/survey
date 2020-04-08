package com.questionnaire.survey.constant;

public enum UserType {
    SUPER("0","超级管理员"),
    USER("1","调研员")
    ;
    private final String typeCode;

    private final String typeName;

    UserType(String typeCode, String typeName) {
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
