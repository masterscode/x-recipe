package com.recipe.RecipeAPI.chequeConfirmation.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.recipe.RecipeAPI.chequeConfirmation.ChequeConfirmation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailsDTO {

    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("chequeStatus")
    private String chequeStatus;
    @JsonProperty("chequeStatusName")
    private String chequeStatusName;
    @JsonProperty("acceptedAt")
    private String acceptedAt;
    @JsonProperty("declinedAt")
    private String declinedAt;
    @JsonProperty("beneficiaryName")
    private String beneficiaryName;
    @JsonProperty("beneficiaryPhoneNo")
    private String beneficiaryPhoneNo;
    @JsonProperty("chequeNo")
    private String chequeNo;
    @JsonProperty("accountNo")
    private String accountNo;
    @JsonProperty("custEmail")
    private String custEmail;
    @JsonProperty("phoneNo")
    private String phoneNo;
    @JsonProperty("clearedFrom")
    private String clearedFrom;
    @JsonProperty("clearedAt")
    private String clearedAt;

    public DetailsDTO(ChequeConfirmation confirmation) {
        amount = confirmation.getAmount();
        chequeStatus = "N/A";
        chequeStatusName = confirmation.getChequeStatusName();
        acceptedAt = "N/A";
        declinedAt = "N/A";
        beneficiaryName = confirmation.getBeneficiaryName();
        beneficiaryPhoneNo = confirmation.getBeneficiaryPhoneNo();
        chequeNo = String.valueOf(confirmation.getChequeNo());
        accountNo = confirmation.getAccountNo();
        custEmail = "N/A";
        phoneNo = "N/A";
        clearedFrom = "N/A";
        clearedAt = "N/A";
    }
}
