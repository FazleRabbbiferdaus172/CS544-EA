package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PersonMessageListener {

    @JmsListener(destination = "taxTopic")
    public void receiveMessage(final String personAsString) {
        System.out.println(personAsString);
     }

}

