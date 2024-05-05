package com.wesleypi.cars.service;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebhookService {

    WebClient webClient;

    public WebhookService(WebClient webClientEmpty){
        this.webClient = webClientEmpty;
    }

    public void postWebhook(String url) {
        if (url == null || url.isBlank()){
            return;
        }
        String defaultMessage = "Um novo carro foi cadastrado!";
        webClient.method(HttpMethod.POST)
                .uri(url)
                .body(Mono.just(defaultMessage), String.class)
                .retrieve()
                .toBodilessEntity().block();
    }
}
