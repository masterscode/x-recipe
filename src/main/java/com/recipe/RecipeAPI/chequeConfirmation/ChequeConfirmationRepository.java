package com.recipe.RecipeAPI.chequeConfirmation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ChequeConfirmationRepository extends JpaRepository<ChequeConfirmation, Long> {
	Collection<ChequeConfirmation>findAllByAccountNo(String accountNumber);
	Optional<ChequeConfirmation> findByAccountNoAndChequeNo(String accountNumber,Integer chequeNumber );
}
