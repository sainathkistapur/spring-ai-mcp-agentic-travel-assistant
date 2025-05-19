package dev.springai.mcp.agentic.travel.assistant.controller;

import io.modelcontextprotocol.client.McpSyncClient;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/springai/openai/mcp/client/custom/server")
@RequiredArgsConstructor
public class McpClientController {

    private final ChatClient basicChatClient;

    @Autowired
    private List<McpSyncClient> mcpSyncClients;

    @GetMapping("/basic-chat")
    public String mcpBasicChat(@RequestBody String prompt,
                               @RequestHeader(value = "conversationId") String conversationId) {
        return basicChatClient.prompt()
                .user(prompt)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, conversationId))
                 .call()
                .content();
    }
}
