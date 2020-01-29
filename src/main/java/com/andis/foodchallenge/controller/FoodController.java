package com.andis.foodchallenge.controller;

import com.andis.foodchallenge.service.FoodService;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService service;

    public FoodController(FoodService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFood(@PathVariable long id) {
        return ResponseEntity.ok(
                service.getFood(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Food with id: " + id + " not found!"
                        ))
        );
    }

    @GetMapping
    public ResponseEntity<Response<CompactFood>> searchFood(@RequestParam String query, @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(
                service.searchFood(query, page)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "No results for search query: " + query + " and page: " + page
                        ))
        );
    }
}
