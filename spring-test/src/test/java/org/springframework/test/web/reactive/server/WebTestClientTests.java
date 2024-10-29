package org.springframework.test.web.reactive.server;

import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WebTestClientTests {

    @Test
    void returnResultParameterizedTypeReferenceVoid() {
        ClientResponse clientResponse = mock(ClientResponse.class);
        when(clientResponse.statusCode()).thenReturn(HttpStatus.OK);
        when(clientResponse.releaseBody()).thenReturn(Mono.empty());

        WebTestClient.ResponseSpec responseSpec = new DefaultWebTestClient.DefaultResponseSpec(
                new ExchangeResult(null, null, null, null, null, null, null, null, null, null),
                clientResponse,
                null,
                result -> {},
                Duration.ofSeconds(5)
        );

        WebTestClient.FluxExchangeResult<Void> result = responseSpec.returnResult(ParameterizedTypeReference.forType(Void.class));
        assertThat(result.getResponseBody()).isEmpty();
    }
}
