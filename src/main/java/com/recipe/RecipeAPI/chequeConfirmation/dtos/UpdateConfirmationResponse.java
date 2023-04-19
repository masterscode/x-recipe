package com.recipe.RecipeAPI.chequeConfirmation.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateConfirmationResponse {
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("chequeStatus")
    private String chequeStatus;
    @JsonProperty("chequeStatusName")
    private String chequeStatusName;
    @JsonProperty("beneficiaryName")
    private String beneficiaryName;
    @JsonProperty("beneficiaryPhoneNo")
    private String beneficiaryPhoneNo;
    @JsonProperty("chequeNo")
    private Integer chequeNo;
    @JsonProperty("accountNo")
    private String accountNo;
    @JsonProperty("custEmail")
    private String custEmail;
    @JsonProperty("clearedFrom")
    private String clearedFrom;
    @JsonProperty("clearedAt")
    private String clearedAt;
    @JsonProperty("declinedAt")
    private String declinedAt;
    @JsonProperty("acceptedAt")
    private String acceptedAt;
}
