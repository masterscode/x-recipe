package com.recipe.RecipeAPI.chequeConfirmation.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class ChequeConfirmationRequestDTO {
	private Long chequeNo;
	private BigDecimal amount;
	private String beneficiaryName;
	private String beneficiaryPhoneNo;
	private String senderAccountNo;
	private String senderEmail;
	private String senderPhoneNo;
	private String makerId;
}
