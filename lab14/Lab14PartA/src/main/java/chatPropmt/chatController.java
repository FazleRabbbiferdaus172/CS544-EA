package chatPropmt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class chatController {
    @Autowired
    private ChatClient chatClient;

    @GetMapping("/chat")
    public QA chat(@RequestParam(value="message") String message) {
        return chatClient
                .prompt()
                .system("Your function is to answer pet healthcare question. The answers should be formated first state the question then answer.")
                .user(message).call().entity(QA.class);
    }
}
