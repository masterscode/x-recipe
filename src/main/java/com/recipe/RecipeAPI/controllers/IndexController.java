package com.recipe.RecipeAPI.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IndexController {
    @GetMapping
    public ResponseEntity<Map<String, Object>> indexPath(HttpServletRequest req){
        return ResponseEntity.ok(
                Map.of(
                        "Note: ", "Welcome to this Fake Recipe API. Data provided from this service are not mine.",
                        "remote-host", req.getRemoteHost(),
                        "client-app", req.getHeader("User-Agent"),
                        "ip-address", Objects.requireNonNullElse(req.getHeader("x-forwarded-for"), req.getRemoteAddr())
                )
        );
    }
}
