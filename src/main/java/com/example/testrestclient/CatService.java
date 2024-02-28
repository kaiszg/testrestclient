package com.example.testrestclient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CatService {

    private final RestClient restClient;

    public String getRandomCatFact() {
        final var response = restClient.get()
                .uri("https://catfact.ninja/fact")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(CatFactDto.class);

        Objects.requireNonNull(response);
        return response.getFact();
    }

}
