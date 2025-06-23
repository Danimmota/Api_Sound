package com.apisound.service;

import com.apisound.model.Artista;
import com.apisound.model.GeminiRequest;
import com.apisound.model.GeminiResponse;
import com.apisound.model.Musica;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;

public class ConsultaGemini {

    private static final String apiKey = "?key=AIzaSyDBm5sZ9ELfMwi35c3BvJ05AbscN-3YNW8";

    private static final String GEMINI_ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent";

    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String obterInformacao(Artista artista) {
        try {
            String nome = artista.getNome();
            String tipo = artista.getTipo().toString().toLowerCase();
            String musicas = artista
                    .getMusicas().stream()
                    .map(Musica::getTitulo)
                    .collect(Collectors.joining(", "));

            String prompt = "Me fale sobre o artista " + nome +
                    " e suas músicas mais famosas: " +
                   (musicas.isBlank() ? "." : ", e tem músicas conhecidas como: " + musicas + ".");

            GeminiRequest requestBody = new GeminiRequest(prompt);
            String body = objectMapper.writeValueAsString(requestBody);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(GEMINI_ENDPOINT + apiKey))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            GeminiResponse resposta = objectMapper.readValue(response.body(), GeminiResponse.class);

            if (resposta.getCandidates() == null || resposta.getCandidates().isEmpty()) {
                return "A API Gemini não retornou nenhuma resposta relevante.";
            }

            var texto = resposta.getCandidates().get(0)
                    .getContent()
                    .getParts()
                    .get(0)
                    .getText();
            return texto != null ? texto.trim() : "Resposta da API não contém texto.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao chamar a API do Gemini: " + e.getMessage();
        }
//        CompletionRequest requisicao = CompletionRequest.builder()
//                .model("text-davinci-003")
//                .prompt("me fale sobre o artista: " + texto)
//                .maxTokens(1000)
//                .temperature(0.7)
//                .build();
//        var resposta = service.createCompletion(requisicao);
//        return resposta.getChoices().get(0).getText();
    }

}
