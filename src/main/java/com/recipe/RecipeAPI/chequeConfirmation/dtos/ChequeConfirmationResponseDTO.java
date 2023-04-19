package com.recipe.RecipeAPI.chequeConfirmation.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.recipe.RecipeAPI.chequeConfirmation.ChequeConfirmation;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class
ChequeConfirmationResponseDTO {

	@JsonProperty("accountNo")
	private String accountNo;
	@JsonProperty("chequeNo")
	private Integer chequeNo;
	@JsonProperty("beneficiaryName")
	private String beneficiaryName;
	@JsonProperty("beneficiaryPhoneNo")
	private String beneficiaryPhoneNo;
	@JsonProperty("amount")
	private Integer amount;
	@JsonProperty("makerId")
	private String makerId;
	@JsonProperty("makerDate")
	private String makerDate;
	@JsonProperty("branchName")
	private String branchName;
	@JsonProperty("inputterBranchName")
	private String inputterBranchName;
	@JsonProperty("officerMISCode")
	private String officerMISCode;
	@JsonProperty("officerName")
	private String officerName;
	@JsonProperty("acctName")
	private String acctName;
	@JsonProperty("instrumentType")
	private String instrumentType;
	@JsonProperty("chequeStatusName")
	private String chequeStatusName;
	@JsonProperty("currentDesignation")
	private String currentDesignation;
	@JsonProperty("noofDocuments")
	private String noofDocuments;

	public ChequeConfirmationResponseDTO(ChequeConfirmation confirmation) {

	}

	public static ChequeConfirmationResponseDTO from(ChequeConfirmation chequeConfirmation) {
		return ChequeConfirmationResponseDTO.builder()
			.accountNo(chequeConfirmation.getAccountNo())
			.chequeNo(chequeConfirmation.getChequeNo())
			.chequeStatusName(chequeConfirmation.getChequeStatusName())
			.beneficiaryName(chequeConfirmation.getBeneficiaryName())
			.beneficiaryPhoneNo(chequeConfirmation.getBeneficiaryPhoneNo())
			.branchName(chequeConfirmation.getBranchName())
			.makerId(chequeConfirmation.getMakerId())
			.amount(chequeConfirmation.getAmount())
			.makerDate(chequeConfirmation.getMakerDate())
			.noofDocuments(chequeConfirmation.getNoofDocuments())
			.officerMISCode(chequeConfirmation.getOfficerMISCode())
			.officerName(chequeConfirmation.getOfficerName())
			.instrumentType(chequeConfirmation.getInstrumentType())
			.currentDesignation("N/A")
			.inputterBranchName(chequeConfirmation.getInputterBranchName())
			.acctName(chequeConfirmation.getAcctName())
			.build();
	}
}
