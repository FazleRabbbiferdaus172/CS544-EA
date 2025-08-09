package ToolCalling.company;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final ChatClient chatClient;

    public Controller(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools) {
        this.chatClient = chatClientBuilder
                .defaultSystem("Please prioritise context information for answering questions")
                .defaultToolCallbacks(tools)
                .build();
    }

    @GetMapping("/company")
    public String getProfitOfCompanyForMonth(@RequestParam("message") String message) {
        return chatClient
                .prompt()
                .user(u -> u.text("Give my company profit for the month of {month}.").param("month", message))
                .call()
                .content();
    }
}
