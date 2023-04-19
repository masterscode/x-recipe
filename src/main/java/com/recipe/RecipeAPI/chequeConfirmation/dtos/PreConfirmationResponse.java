package com.recipe.RecipeAPI.chequeConfirmation.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.recipe.RecipeAPI.chequeConfirmation.ChequeConfirmation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreConfirmationResponse {

    @JsonProperty("chequeNo")
    private Integer chequeNo;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("beneficiaryName")
    private String beneficiaryName;

    @JsonProperty("beneficiaryPhoneNo")
    private String beneficiaryPhoneNo;

    @JsonProperty("accountNo")
    private String accountNo;

    @JsonProperty("customerEmail")
    private String customerEmail;

    @JsonProperty("customerPhoneNo")
    private String customerPhoneNo;

    @JsonProperty("makerId")
    private String makerId;

    public PreConfirmationResponse(ChequeConfirmation confirmation) {
        chequeNo = confirmation.getChequeNo();
        amount = String.valueOf(confirmation.getAmount());
        beneficiaryName = confirmation.getBeneficiaryName();
        beneficiaryPhoneNo = confirmation.getBeneficiaryPhoneNo();
        accountNo = confirmation.getAccountNo();
        customerEmail = confirmation.getCustomerEmail();
        customerPhoneNo = "N/A";
        makerId = confirmation.getMakerId();
    }
}
