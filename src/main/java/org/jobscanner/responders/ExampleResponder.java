package org.jobscanner.responders;

import java.util.HashMap;
import java.util.Map;

public class ExampleResponder extends AbstractResponder {

    public ExampleResponder() {
        super("en-sent.bin", "en-token.bin", "en-pos-maxent.bin",
                "en-lemmatizer.bin", "categorizer.txt");
    }

    @Override
    protected String getDefaultAnswer() {
        return "I'm sorry, but I did not understand your message. Can you try again with a different word?";
    }

    @Override
    protected Map<String, String> getResponses() {
        Map<String, String> responses = new HashMap<>();
        responses.put("greetings", "Hello, how can I help you?");
        responses.put("price", "The price of our product is 300$");
        responses.put("bye", "Thank you for contacting us! Please come again!");
        return responses;
    }
}
