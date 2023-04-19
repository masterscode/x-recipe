package com.recipe.RecipeAPI.chequeConfirmation.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreConfirmationRequest{

    private Integer chequeNo;

    private Integer amount;

    private String beneficiaryName;

    private String beneficiaryPhoneNo;

    private String senderAccountNo;

    private String senderEmail;

    private String senderPhoneNo;

    private String makerId;
}
