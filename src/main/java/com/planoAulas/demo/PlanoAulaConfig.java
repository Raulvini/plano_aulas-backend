package com.planoAulas.demo;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlanoAulaConfig {

    @Value("${GOOGLE_AI_API_KEY}")
    private String googleApiKey;

    @Bean
    public AssitentIAService planoAulaAssistant(HabilidadePdfTool pdfTool) {

        // 1. Configuração do Modelo do Gemini
        GoogleAiGeminiChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(googleApiKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.7) // Criatividade equilibrada
                .build();

        // 2. Criação do Serviço com Tools integradas
        return AiServices.builder(AssitentIAService.class)
                .chatModel(model)
                .tools(pdfTool) // Registra suas classes de @Tool aqui
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10)) // Opcional: memória
                .build();
    }
}
