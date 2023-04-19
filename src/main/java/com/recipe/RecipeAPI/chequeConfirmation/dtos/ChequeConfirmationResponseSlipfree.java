package com.recipe.RecipeAPI.chequeConfirmation.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class ChequeConfirmationResponseSlipfree {

	private BigDecimal amount;
	private long chequeNo;

	private String chequeStatus;
	private String chequeStatusName;
	private String acceptedAt;
	private String declinedAt;
	private String beneficiaryName;
	private String beneficiaryPhoneNo;
	private String accountNo;
	private String custEmail;
	private String phoneNo;
	private String clearedFrom;
	private LocalDateTime clearedAt;
}
