package com.apisound.service;

import com.apisound.model.Artista;
import com.apisound.model.GeminiRequest;
import com.apisound.model.GeminiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaGemini {

    private static final String apiKey = "GEMINI_API_KEY";

    private static final String GEMINI_ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=";

    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String obterInformacao(Artista artista) {
        try {
            String nome = artista.getNome();
            String tipo = artista.getTipo().toString().toLowerCase();

            String prompt = "Forneça uma breve biografia do(a) artista musical chamado(a) " + nome +
                    ", e liste pelo menos 3 de suas músicas mais famosas. Seja direto e evite repetir o nome do artista várias vezes.";

            GeminiRequest requestBody = new GeminiRequest(prompt);
            String body = objectMapper.writeValueAsString(requestBody);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(GEMINI_ENDPOINT + apiKey))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return "Erro: A API retornou o status " + response.statusCode() + ": " + response.body();
            }

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
    }
}
//        CompletionRequest requisicao = CompletionRequest.builder()
//                .model("text-davinci-003")
//                .prompt("me fale sobre o artista: " + texto)
//                .maxTokens(1000)
//                .temperature(0.7)
//                .build();
//        var resposta = service.createCompletion(requisicao);
//        return resposta.getChoices().get(0).getText();
