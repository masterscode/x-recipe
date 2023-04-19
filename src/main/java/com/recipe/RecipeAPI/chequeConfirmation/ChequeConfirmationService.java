package com.recipe.RecipeAPI.chequeConfirmation;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.recipe.RecipeAPI.chequeConfirmation.dtos.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChequeConfirmationService {
	private final ChequeConfirmationRepository chequeConfirmationRepository;

	public AuthData doLogin(SlipFreeLogin slipFreeLogin) {
		final Long defaultUserId = 250L;
		final String defaultPassword = "P@55W0rd%";

		if (ObjectUtils.notEqual(slipFreeLogin.getClientID(), defaultUserId))
			throw new RuntimeException("Unknown client");

		if (ObjectUtils.notEqual(slipFreeLogin.getPassword(), defaultPassword))
			throw new RuntimeException("UnAuthorized");

		final String token = JWT.create()
			.withSubject(String.valueOf(defaultUserId))
			.withExpiresAt(Instant.ofEpochMilli(ZonedDateTime.now(ZoneId.systemDefault()).toInstant().toEpochMilli() + 9900000))
			.sign(Algorithm.HMAC256("secret"));

		return new AuthData(("Bearer ").concat(token));
	}


	public BaseResponse<Collection<ChequeConfirmationResponseDTO>> getChequeConfirmationForAccount(String accountNumber) {
		return
			new BaseResponse<>("success",
				"Retrieved cheque confirmation successfully",
				chequeConfirmationRepository.findAllByAccountNo(accountNumber).stream()
					.map(ChequeConfirmationResponseDTO::from).collect(Collectors.toList())
			);
	}

	public BaseResponse<Collection<DetailsDTO>> getChequeConfirmationDetails(String accountNumber, int chqNumber) {
		final ChequeConfirmation confirmation = chequeConfirmationRepository.findByAccountNoAndChequeNo(accountNumber, chqNumber).orElseThrow();
		return new BaseResponse<>(
			"success",
			"Retrieved details of cheque confirmation request",
			Collections.singletonList(new DetailsDTO(confirmation))
		);
	}


	public BaseResponse<ChequeConfirmationResponseDTO> updateChequeConfirmation(UpdateConfirmationRequestDTO  req, String acn, String chqNm) {
		final ChequeConfirmation confirmation = chequeConfirmationRepository.findByAccountNoAndChequeNo(acn, Integer.valueOf(chqNm)).orElseThrow();
		confirmation.setChequeStatusName(req.getStatus().toString());
		if(req.getCleared()) confirmation.setClearedAt(LocalDateTime.now().toString());

		chequeConfirmationRepository.save(confirmation);

		return new BaseResponse<>("success", "updated", ChequeConfirmationResponseDTO.from(confirmation));
	}

	public BaseResponse<PreConfirmationResponse> doPreConfirmCheque(PreConfirmationRequest request){
		ChequeConfirmation confirmation = new ChequeConfirmation();

		final ChequeConfirmation saved = chequeConfirmationRepository.save(confirmation);

		return new BaseResponse<>("success", "successful", new PreConfirmationResponse(saved));
	}

}
