package lab;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class SimpleController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ChatClient chatClient;
    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message")String message) throws IOException {
        List<ProductEntity> profit2024 = productRepository.findAll();
        return chatClient.prompt()
                .system("You are responsible for answering queries about product of our company. here is data of product: "+profit2024)
                .user(message)
                .call()
                .content();
    }
}