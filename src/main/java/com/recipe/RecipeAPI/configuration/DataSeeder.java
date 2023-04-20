package com.recipe.RecipeAPI.configuration;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.RecipeAPI.chequeConfirmation.ChequeConfirmation;
import com.recipe.RecipeAPI.chequeConfirmation.ChequeConfirmationRepository;
import com.recipe.RecipeAPI.dtos.RecipeJSONPojo;
import com.recipe.RecipeAPI.models.Recipe;
import com.recipe.RecipeAPI.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

@Configuration
@Log
@RequiredArgsConstructor
public class DataSeeder {

	private final RecipeRepository recipeRepository;
    private final ObjectMapper objectMapper;

    private final ChequeConfirmationRepository chequeConfirmationRepository;

	@EventListener
	public void seedDB(ContextRefreshedEvent event) {
        CompletableFuture.runAsync(this::seedChequeConfirmation);

		CompletableFuture.supplyAsync(recipeRepository::count)
			.thenApplyAsync(count -> {

				try {
					return Files.readString(Paths.get("src/main/resources/seed-data/recipe-2.json"), Charset.defaultCharset());

				} catch (Exception e) {
					return null;
				}
			}).thenApplyAsync(json -> {

				if (Objects.isNull(json)) return new ArrayList<RecipeJSONPojo>();

				log.log(Level.INFO, "Starting application data seeding ==> " + event.getTimestamp());

				ObjectMapper objectMapper = new ObjectMapper();
				try {
					return objectMapper.readValue(json, new TypeReference<List<RecipeJSONPojo>>() {
					});
				} catch (JsonProcessingException e) {
					log.log(Level.SEVERE, "Json processing exception ==> ", e);
					return new ArrayList<RecipeJSONPojo>();
				}

			})
			.thenAcceptAsync((List<RecipeJSONPojo> pojo) -> {
				List<Recipe> recipes = pojo.stream().map(this::toRecipeAdapter).toList();
				recipeRepository.saveAll(recipes);
			})
			.join();
	}


	Recipe toRecipeAdapter(RecipeJSONPojo p) {
		return Recipe.builder()
			.name(p.getName())
			.description(p.getDescription())
			.imageUrl(p.getImageURL())
			.ingredients(p.getIngredients())
			.timers(p.getTimers())
			.steps(p.getSteps())
			.build();

	}


	private void seedChequeConfirmation() {

        log.info("--> Seeding cheque confirmation started");
        chequeConfirmationRepository.saveAll(deserializeJson());
        log.info("--> Seeding cheque confirmation completed");


	}


	private List<ChequeConfirmation> deserializeJson() {
		final String json = """
			[
			  {
			    "accountNo": "ABC123",
			    "chequeNo": "001",
			    "beneficiaryName": "John Smith",
			    "beneficiaryPhoneNo": "+1 (555) 123-4567",
			    "amount": 1000.00,
			    "makerId": "MS123",
			    "makerDate": "2023-04-20T10:30:00Z",
			    "branchName": "Main Branch",
			    "inputterBranchName": "Main Branch",
			    "officerMISCode": "MIS123",
			    "officerName": "Jane Doe",
			    "acctName": "John Smith",
			    "instrumentType": "Cheque",
			    "chequeStatusName": "Approved",
			    "currentDesignation": "Manager",
			    "noofDocuments": 2
			  },
			  {
			    "accountNo": "DEF456",
			    "chequeNo": "002",
			    "beneficiaryName": "Sarah Johnson",
			    "beneficiaryPhoneNo": "+1 (555) 234-5678",
			    "amount": 500.00,
			    "makerId": "MS124",
			    "makerDate": "2023-04-20T11:00:00Z",
			    "branchName": "Downtown Branch",
			    "inputterBranchName": "Downtown Branch",
			    "officerMISCode": "MIS124",
			    "officerName": "Bob Williams",
			    "acctName": "Sarah Johnson",
			    "instrumentType": "Cheque",
			    "chequeStatusName": "Approved",
			    "currentDesignation": "Supervisor",
			    "noofDocuments": 1
			  },
			  {
			    "accountNo": "GHI789",
			    "chequeNo": "003",
			    "beneficiaryName": "Emily Davis",
			    "beneficiaryPhoneNo": "+1 (555) 345-6789",
			    "amount": 2500.00,
			    "makerId": "MS125",
			    "makerDate": "2023-04-20T12:30:00Z",
			    "branchName": "West Branch",
			    "inputterBranchName": "West Branch",
			    "officerMISCode": "MIS125",
			    "officerName": "Alice Lee",
			    "acctName": "Emily Davis",
			    "instrumentType": "Cheque",
			    "chequeStatusName": "Pending",
			    "currentDesignation": "Manager",
			    "noofDocuments": 3
			  },
			  {
			    "accountNo": "JKL012",
			    "chequeNo": "004",
			    "beneficiaryName": "Michael Brown",
			    "beneficiaryPhoneNo": "+1 (555) 456-7890",
			    "amount": 750.00,
			    "makerId": "MS126",
			    "makerDate": "2023-04-20T13:00:00Z",
			    "branchName": "East Branch",
			    "inputterBranchName": "East Branch",
			    "officerMISCode": "MIS126",
			    "officerName": "David Wilson",
			    "acctName": "Michael Brown",
			    "instrumentType": "Cheque",
			    "chequeStatusName": "Pending",
			    "currentDesignation": "Supervisor",
			    "noofDocuments": 2
			  }
			]       
			""";

        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.log(Level.SEVERE, "json processing exception {}", e.getStackTrace());
            return Collections.emptyList();
        }

    }
}
