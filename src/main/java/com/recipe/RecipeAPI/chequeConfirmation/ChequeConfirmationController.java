package com.recipe.RecipeAPI.chequeConfirmation;


import com.recipe.RecipeAPI.chequeConfirmation.dtos.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChequeConfirmationController {
	private final ChequeConfirmationService chequeConfirmationService;

	@PostMapping("/Auth/Login")
	public ResponseEntity<AuthData> login(@RequestBody SlipFreeLogin loginDto) {
		return ResponseEntity.ok(
			chequeConfirmationService.doLogin(loginDto)
		);
	}

	@GetMapping("/chequeconfirmationrequests/{accountNumber}/customer")
	public ResponseEntity<BaseResponse<Collection<ChequeConfirmationResponseDTO>>> getChequeConfirmations(@PathVariable String accountNumber) {
		return ResponseEntity.ok(
			chequeConfirmationService.getChequeConfirmationForAccount(accountNumber)
		);
	}

	@GetMapping("/chequeconfirmationrequests/{accountNumber}/request/{chequeNumber}/customer")
	public ResponseEntity<BaseResponse<Collection<DetailsDTO>>> getChequeConfirmationDetails(@PathVariable String accountNumber, @PathVariable Integer chequeNumber) {
		return ResponseEntity.ok(
			chequeConfirmationService.getChequeConfirmationDetails(accountNumber, chequeNumber)
		);
	}

	@PostMapping("/chequeconfirmationrequests/create")
	public ResponseEntity<BaseResponse<PreConfirmationResponse>> preChequeConfirmation(@RequestBody PreConfirmationRequest request) {
		return ResponseEntity.ok(
			chequeConfirmationService.doPreConfirmCheque(request)
		);
	}

	@PostMapping("/chequeconfirmationrequests/{accountNumber}/request/{chequeNumber}/customer")
	public ResponseEntity<BaseResponse<ChequeConfirmationResponseDTO>> updateChequeConfirmation(@PathVariable String accountNumber, @PathVariable String chequeNumber, @RequestBody UpdateConfirmationRequestDTO request) {
		return ResponseEntity.ok(
			chequeConfirmationService.updateChequeConfirmation(request, accountNumber, chequeNumber)
		);
	}


}