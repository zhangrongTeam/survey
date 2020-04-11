package com.questionnaire.survey.controller;

import lombok.Data;

@Data
public class SurveySearchDTO {
    private String projectId;
    private String surveyUserName;
    private String createStartTime;
    private String createEndTime;
}
