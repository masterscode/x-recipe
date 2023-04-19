package com.recipe.RecipeAPI.chequeConfirmation.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Data
public class ChequeConfirmationDto {

	private String accountNo;
	private String beneficiaryName;
	private String beneficiaryPhoneNo;
	private String makerId;
	private String branchName;
	private String inputterBranchName;
	private String officerMISCode;
	private String officerName;
	private String acctName;
	private String instrumentType;
	private String chequeStatusName;
	private String chequeStatus;
	private String currentDesignation;
	private Long noofDocuments;
	private Long chequeNo;
	private BigDecimal amount;
	private LocalDateTime makerDate;
}
