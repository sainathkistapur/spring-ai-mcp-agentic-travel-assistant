package dev.springai.mcp.agentic.travel.assistant.config;

import dev.springai.mcp.agentic.travel.assistant.advisor.LoggingAdvisor;
import io.modelcontextprotocol.client.McpSyncClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
public class OpenAiConfig {
    @Autowired
    private List<McpSyncClient> mcpSyncClients;

    @Bean
    public ChatClient basicChatClient(ChatClient.Builder chatClientBuilder) {
       log.info("\nConfigured MCP Clients");
        for (McpSyncClient client: mcpSyncClients) {
            log.info("\n" + client.getClientInfo().name());
        }

        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(new InMemoryChatMemoryRepository())
                .build();

        var tools = new SyncMcpToolCallbackProvider(mcpSyncClients);
        return chatClientBuilder
                .defaultToolCallbacks(tools)
                .defaultAdvisors(new LoggingAdvisor(), MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }


}
