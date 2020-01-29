package com.andis.foodchallenge.service;

import com.andis.foodchallenge.external.fatsecret.FatSecretClient;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.Response;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService {

    private FatSecretClient fatSecretClient;

    public FoodService(FatSecretClient client) {
        this.fatSecretClient = client;
    }

    public Optional<Response<CompactFood>> searchFood(String query, int page) {
        return fatSecretClient.search(query, page);
    }

    public Optional<Food> getFood(long id) {
        return fatSecretClient.get(id);
    }
}
