package org.sour.agent;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface LangChain4jAssistant {

    @SystemMessage("""
            You are a helpful assistant.

            Today is {{current_date}}.
            """)
    TokenStream chat(@MemoryId String chatId, @UserMessage String userMessage);
}
