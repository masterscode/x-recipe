package com.recipe.RecipeAPI.chequeConfirmation;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChequeConfirmation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	private String accountNo;
	private Integer chequeNo;
	private String beneficiaryName;
	private String beneficiaryPhoneNo;
	private Integer amount;
	private String makerId;
	private String makerDate;
	private String branchName;
	private String inputterBranchName;
	private String officerMISCode;
	private String officerName;
	private String acctName;
	private String instrumentType;
	private String chequeStatusName;
	private String noofDocuments;

	private String clearedAt;

	private String customerEmail;

}
