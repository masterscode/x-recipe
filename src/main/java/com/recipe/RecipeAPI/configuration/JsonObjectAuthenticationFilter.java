package com.recipe.RecipeAPI.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.RecipeAPI.chequeConfirmation.dtos.SlipFreeLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;


@Order(2)
//@Configuration
//@RequiredArgsConstructor
public class JsonObjectAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final ObjectMapper objectMapper = new ObjectMapper();


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try {
			BufferedReader reader = request.getReader();
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while (Objects.nonNull((line = reader.readLine()))) {
				stringBuilder.append(line);
			}
			SlipFreeLogin authRequest = objectMapper.readValue(stringBuilder.toString().replace(" ", ""), SlipFreeLogin.class);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.getClientID(), authRequest.getPassword());
			setDetails(request, token);
			return this.getAuthenticationManager().authenticate(token);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
