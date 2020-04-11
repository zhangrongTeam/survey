package com.questionnaire.survey.DTO;

import lombok.Data;

@Data
public class SearchDTO {
    private String createStartTime;
    private String createEndTime;
    private String projectName;
    private String projectStatus;
}
