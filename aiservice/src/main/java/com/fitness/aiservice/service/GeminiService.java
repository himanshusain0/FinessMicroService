package com.fitness.aiservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;
import java.util.Map;

@Service
@Slf4j
public class GeminiService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public GeminiService(WebClient.Builder builder) {
        this.webClient = builder
                .build();
    }

    public String getAnswer(String prompt) {

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        try {
            return webClient.post()
                    .uri(geminiApiUrl)  // 🔥 only URL
                    .header("Content-Type", "application/json")
                    .header("X-goog-api-key", geminiApiKey)  // 🔥 key in header
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofSeconds(30))
                    .block();

        } catch (WebClientResponseException e) {
            log.error("Gemini API error: Status={}, Body={}",
                    e.getStatusCode(),
                    e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error calling Gemini", e);
            throw new RuntimeException("Gemini API failed", e);
        }
    }
}
