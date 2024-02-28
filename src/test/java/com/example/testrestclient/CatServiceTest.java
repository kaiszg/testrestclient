package com.example.testrestclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@Import(RestClientConfig.class)
@RestClientTest(CatService.class)
@ExtendWith(SpringExtension.class)
class CatServiceTest {

    @Autowired
    private CatService sut;
    @Autowired
    private MockRestServiceServer server;
    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    public void testFact() {
        // given
        final var mockFact = CatFactDto.builder().fact("testFact").length(12).build();
        final var factMockResponse = objectMapper.writeValueAsString(mockFact);
        server.expect(requestTo("https://catfact.ninja/fact"))
                .andRespond(withSuccess(factMockResponse, MediaType.APPLICATION_JSON));

        // when
        final var resultingFact = sut.getRandomCatFact();

        // then
        assertThat(resultingFact).isEqualTo("testFact");
    }

}