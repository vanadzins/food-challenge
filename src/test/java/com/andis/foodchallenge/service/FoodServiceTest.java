package com.andis.foodchallenge.service;

import com.andis.foodchallenge.external.fatsecret.FatSecretClient;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {

    @InjectMocks
    FoodService service;

    @Mock
    FatSecretClient fatSecretClient;

    @Test
    void getFood_should_return_non_empty_optional_when_food_exists() {
        when(fatSecretClient.get(anyLong())).thenReturn(Optional.of(new Food()));

        Optional<Food> food = service.getFood(anyLong());
        Assert.isTrue(food.isPresent(), "Optional should not be empty");
    }

    @Test
    void getFood_should_return_empty_optional_when_food_does_not_exist() {
        when(fatSecretClient.get(anyLong())).thenReturn(Optional.empty());

        Optional<Food> food = service.getFood(anyLong());
        Assert.isTrue(food.isEmpty(), "Optional should be empty");
    }

    @Test
    void searchFood_should_return_non_empty_optional_when_results_found() {
        when(fatSecretClient.search(anyString(), anyInt())).thenReturn(Optional.of(new Response<>()));

        Optional<Response<CompactFood>> response = service.searchFood(anyString(), anyInt());
        Assert.isTrue(response.isPresent(), "Optional should not be empty");
    }

    @Test
    void searchFood_should_return_empty_optional_when_no_results_found() {
        when(fatSecretClient.search(anyString(), anyInt())).thenReturn(Optional.empty());

        Optional<Response<CompactFood>> response = service.searchFood(anyString(), anyInt());
        Assert.isTrue(response.isEmpty(), "Optional should be empty");
    }
}