package com.andis.foodchallenge.external.fatsecret;

import com.andis.foodchallenge.configuration.settings.FatSecretSettings;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.services.Response;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FatSecretClient {

    private final FatsecretService service;

    public FatSecretClient(FatSecretSettings settings) {
        this.service = new FatsecretService(settings.getKey(), settings.getSecret());
    }

    public Optional<Food> get(long id) {
        return Optional.ofNullable(
                service.getFood(id)
        );
    }

    public Optional<Response<CompactFood>> search(String query, int page) {
        return Optional.ofNullable(
                service.searchFoods(query, page)
        );
    }
}
