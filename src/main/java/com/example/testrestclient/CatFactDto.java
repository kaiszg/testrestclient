package com.example.testrestclient;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatFactDto {

    private String fact;
    private Integer length;
}
