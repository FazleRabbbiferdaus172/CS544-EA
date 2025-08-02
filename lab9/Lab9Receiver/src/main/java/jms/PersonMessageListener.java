package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PersonMessageListener {

    private int currentValue = 0;

    @JmsListener(destination = "testTopic")
    public void receiveMessage(final String personAsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Person person = objectMapper.readValue(personAsString, Person.class);
            System.out.println("JMS receiver 1: received message:" + person.getFirstName()+" "+person.getLastName());

        } catch (IOException e) {
            System.out.println("JMS receiver 1: Cannot convert : " + personAsString+" to a Person object");
        }
     }

    @JmsListener(destination = "calculatorQueue")
    public void receiveMessageC(final String cms) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CalculatorMessage calcMsg = objectMapper.readValue(cms, CalculatorMessage.class);
            String operator = calcMsg.getOperator();
            int value = calcMsg.getValue();

            switch (operator) {
                case "+":
                    currentValue += value;
                    break;
                case "-":
                    currentValue -= value;
                    break;
                case "*":
                    currentValue *= value;
                    break;
                default:
                    System.out.println("Receiver: Unknown operator '" + operator + "'");
                    break;
            }
            System.out.println("Receiver: The result is " + currentValue);
        }
        catch (Exception x) {}
    }
}

