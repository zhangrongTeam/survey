package com.questionnaire.survey.DTO;

import com.questionnaire.survey.entity.BuildingConstruction;
import com.questionnaire.survey.entity.Survey;
import com.questionnaire.survey.entity.WaterMeter;
import com.questionnaire.survey.entity.WaterSupplyNetwork;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddSurveyDTO {
    @NotBlank
    private String systemType;
    private BuildingConstruction buildingConstruction;
    private WaterMeter waterMeter;
    private WaterSupplyNetwork waterSupplyNetwork;
}
