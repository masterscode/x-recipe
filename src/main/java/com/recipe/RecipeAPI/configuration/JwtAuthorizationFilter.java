package com.recipe.RecipeAPI.configuration;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
	private static final String TOKEN_PREFIX = "Bearer ";
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		UsernamePasswordAuthenticationToken auth = getAuthentication(request);
		if (Objects.isNull(auth) && request.getMethod().equals("OPTIONS")) {
			chain.doFilter(request, response);
			return;
		}

		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (Objects.isNull(token) || !token.startsWith(TOKEN_PREFIX)) {
			return null;
		}
		final String clientId = JWT.require(Algorithm.HMAC256("secret"))
			.build()
			.verify(token.replace(TOKEN_PREFIX, ""))
			.getSubject();

		final UserDetails user = userDetailsService.loadUserByUsername(clientId);

		return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
	}
}
