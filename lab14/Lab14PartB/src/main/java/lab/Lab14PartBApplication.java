package lab;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab14PartBApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(Lab14PartBApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ProductEntity productEntity1 = new ProductEntity("a", 1.1, 1);
		ProductEntity productEntity2 = new ProductEntity("b", 2.2, 2);
		ProductEntity productEntity3 = new ProductEntity("c", 3.3, 3);
		ProductEntity productEntity4 = new ProductEntity("d", 4.4, 4);

		productRepository.save(productEntity1);
		productRepository.save(productEntity2);
		productRepository.save(productEntity3);
		productRepository.save(productEntity4);
	}

	@Bean
	public ChatClient chatClient(ChatModel chatModel) {
		ChatClient.Builder builder = ChatClient.builder(chatModel);
		return builder.build();
	}
}
