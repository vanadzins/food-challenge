package com.andis.foodchallenge.controller;

import com.andis.foodchallenge.external.fatsecret.FatSecretClient;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FoodControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FatSecretClient fatSecretClient;

    @Test
    void getFood_should_return_200_when_food_exists() throws Exception {
        long id = 123;
        when(fatSecretClient.get(id)).thenReturn(Optional.of(new Food()));

        mvc.perform(
                get("/food/" + id))
                .andExpect(status().isOk());
    }

    @Test
    void getFood_should_return_404_when_food_does_not_exist() throws Exception {
        long id = 123;

        mvc.perform(
                get("/food/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchFood_should_return_200_when_results_are_found() throws Exception {
        String query = "pasta";
        when(fatSecretClient.search(query, 0)).thenReturn(Optional.of(new Response<>()));

        mvc.perform(
                get("/food/").param("query", query))
                .andExpect(status().isOk());
    }

    @Test
    void searchFood_should_return_200_when_added_page_and_results_are_found() throws Exception {
        String query = "pasta";
        int page = 1;
        when(fatSecretClient.search(query, page)).thenReturn(Optional.of(new Response<>()));

        mvc.perform(
                get("/food/")
                        .param("query", query)
                        .param("page", String.valueOf(page)))
                .andExpect(status().isOk());
    }

    @Test
    void searchFood_should_return_404_when_results_are_not_found() throws Exception {
        String query = "pasta";

        mvc.perform(
                get("/food/").param("query", query))
                .andExpect(status().isNotFound());
    }
}