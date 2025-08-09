package ToolCalling.company;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    ChatClient chatClient;
    @Autowired
    ConverterService converterService;
    @Autowired
    ProfitService profitService;

    @GetMapping("/company")
    public String getProfitOfCompanyForMonth(@RequestParam("message") String message) {
        return chatClient
                .prompt()
                .system("Your job is state the profit in euros")
                .tools(profitService)
                .tools(converterService)
                .user(u -> u.text("Give the company profit for the month of {month} in euros.").param("month", message))
                .call()
                .content();
    }
}
